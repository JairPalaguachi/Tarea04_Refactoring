package com.mycompany.main;

import AbstractFactory.*;
import Decorator.AlimentoDecorator;
import Decorator.BebidaDecorator;
import Decorator.Decorator;
import Decorator.EstacionamientoDecorator;
import Observer.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear el usuario
        System.out.println("Bienvenido al sistema de compra de tickets.");

        // Validar nombre, apellido, email y teléfono
        String nombre = obtenerInputValido(scanner, "Por favor, ingrese su nombre: ", "^[a-zA-Z]+$", "El nombre solo puede contener letras.");
        String apellido = obtenerInputValido(scanner, "Por favor, ingrese su apellido: ", "^[a-zA-Z]+$", "El apellido solo puede contener letras.");
        String email = obtenerInputValido(scanner, "Por favor, ingrese su email: ", "^[\\w-]+(?:\\.[\\w-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", "Email inválido.");
        String telefono = obtenerInputValido(scanner, "Por favor, ingrese su número de teléfono: ", "^[0-9]{10}$", "El número de teléfono debe tener 10 dígitos.");

        // Seleccionar el tipo de evento
        System.out.println("\nElija el tipo de evento al que desea asistir:");
        System.out.println("1. Teatro");
        System.out.println("2. StandUp");
        System.out.println("3. MicroTeatro");
        int opcionEvento = obtenerOpcionValida(scanner, "Ingrese el número correspondiente: ", 1, 3);

        AbstractFactoryTicket factory = switch (opcionEvento) {
            case 1 -> new TeatroFactory();
            case 2 -> new StandUpFactory();
            case 3 -> new MicroTeatroFactory();
            default -> throw new IllegalStateException("Opción inválida.");
        };

        User usuario = new User(1, nombre, apellido, email, telefono, null, factory, new Map());
        UserPurchase userPurchase = new UserPurchase(new ClienteNotificacion(1, "Compra realizada", "Correo", new java.util.Date()));

        // Mostrar opciones de ticket
        System.out.println("\nElija el tipo de ticket que desea comprar:");
        System.out.println("1. Platea - $20");
        System.out.println("2. Balcón - $30");
        System.out.println("3. VIP - $50");

        int opcionTicket = obtenerOpcionValida(scanner, "Ingrese el número correspondiente: ", 1, 3);
        Ticket ticket = switch (opcionTicket) {
            case 1 -> factory.createPlatea();
            case 2 -> factory.createBalcon();
            case 3 -> factory.createVip();
            default -> throw new IllegalStateException("Opción inválida.");
        };

        double precioBase = switch (opcionTicket) {
            case 1 -> 20;
            case 2 -> 30;
            case 3 -> 50;
            default -> 0;
        };

        // Inicializar correctamente PaymentValidator
        PaymentValidator paymentValidator = new PaymentValidator();
        PurchaseProcessor.paymentV = paymentValidator;

        // Selección de asiento
        int asiento = obtenerOpcionValida(scanner, "\nElija el número de asiento (1-10): ", 1, 10);
        SelectFunctionsMaps selectMap = new SelectFunctionsMaps();
        selectMap.selectSeat(asiento, nombre);

        // Limpiar buffer
        scanner.nextLine();

        // Preguntar si desea opciones adicionales
        boolean agregarExtras = solicitarOpcionesAdicionales(scanner, ticket);

        // Procesar el pago
        PurchaseProcessor.solicitar_datos_pago(scanner, ticket, precioBase, asiento, userPurchase);

        // Mostrar resumen una única vez
        mostrarResumenCompra(ticket, precioBase, asiento, agregarExtras);
        scanner.close();
    }

    // Método para obtener una entrada válida de texto
    private static String obtenerInputValido(Scanner scanner, String mensaje, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    // Método para obtener una opción válida
    private static int obtenerOpcionValida(Scanner scanner, String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion >= min && opcion <= max) {
                    scanner.nextLine();  // Limpiar el buffer después de nextInt()
                    return opcion;
                }
            }
            System.out.println("Entrada inválida. Intente nuevamente.");
            scanner.nextLine();
        }
    }

    private static boolean solicitarOpcionesAdicionales(Scanner scanner, Ticket ticket) {
        System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
        String respuesta = scanner.nextLine();
        boolean agregarExtras = respuesta.equalsIgnoreCase("si");

        if (agregarExtras) {
            System.out.println("\nOpciones adicionales disponibles:");
            System.out.println("1. Alimento - $5");
            System.out.println("2. Bebida - $3");
            System.out.println("3. Estacionamiento - $10");

            agregarExtrasTicket(scanner, ticket);
        }
        return agregarExtras;
    }

    private static void agregarExtrasTicket(Scanner scanner, Ticket ticket) {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionValida(scanner, "Seleccione una opción adicional: ", 1, 4);

            switch (opcion) {
                case 1:
                    Decorator decoradorAlimento = new AlimentoDecorator(ticket);
                    decoradorAlimento.agregarCaracteristica();
                    break;
                case 2:
                    Decorator decoradorBebida = new BebidaDecorator(ticket);
                    decoradorBebida.agregarCaracteristica();
                    break;
                case 3:
                    Decorator decoradorEstacionamiento = new EstacionamientoDecorator(ticket);
                    decoradorEstacionamiento.agregarCaracteristica();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    
    }
    

    private static void mostrarResumenCompra(Ticket ticket, double precioBase, int asiento, boolean agregarExtras) {
        double costoExtras = agregarExtras ? 18 : 0;  // Precio total de extras de ejemplo
        System.out.println("\nResumen de su compra:");
        System.out.println("Tipo de ticket: " + ticket.getClass().getSimpleName());
        System.out.println("Asiento seleccionado: " + asiento);
        System.out.println("Costo base: $" + precioBase);
        System.out.println("Costo de opciones adicionales: $" + costoExtras);
        System.out.println("Costo total: $" + (precioBase + costoExtras));
    }
}









