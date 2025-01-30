package com.mycompany.main;

import AbstractFactory.Map;
import AbstractFactory.MicroTeatroFactory;
import AbstractFactory.PurchaseProcessor;
import AbstractFactory.SelectFunctionsMaps;
import AbstractFactory.StandUpFactory;
import AbstractFactory.TeatroFactory;
import AbstractFactory.Ticket;
import AbstractFactory.User;
import AbstractFactory.UserPurchase;
import Observer.ClienteNotificacion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear el usuario
        System.out.println("Bienvenido al sistema de compra de tickets.");

        // Validación de nombre
        String nombre = obtenerInputValido(scanner, "Por favor, ingrese su nombre: ", "^[a-zA-Z]+$", "El nombre solo puede contener letras.");

        // Validación de apellido
        String apellido = obtenerInputValido(scanner, "Por favor, ingrese su apellido: ", "^[a-zA-Z]+$", "El apellido solo puede contener letras.");
        
        // Validación de email
        String email = obtenerInputValido(scanner, "Por favor, ingrese su email: ", "^[\\w-]+(?:\\.[\\w-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", "Email inválido. Asegúrese de que el formato sea correcto.");
        
        // Validación de teléfono
        String telefono = obtenerInputValido(scanner, "Por favor, ingrese su número de teléfono: ", "^[0-9]{10}$", "El número de teléfono debe tener 10 dígitos.");


        // Validacion tipo de evento
        System.out.println("\nElija el tipo de evento al que desea asistir:");
        System.out.println("1. Teatro");
        System.out.println("2. StandUp ");
        System.out.println("3. MicroTeatro");


        int opcionEvento = obtenerOpcionValida(scanner, "Ingrese el número correspondiente: ", 1, 3);

        // Crear usuario con los datos ingresados
        User usuario = null;
        switch (opcionEvento) {
            case 1:
                usuario = new User(1, nombre, apellido, email, telefono, null, new TeatroFactory(), new Map());
                break;
            case 2:
                usuario = new User(1, nombre, apellido, email, telefono, null, new StandUpFactory(), new Map());
                break;
            case 3:
                usuario = new User(1, nombre, apellido, email, telefono, null, new MicroTeatroFactory(), new Map());
                break;
        }
        

        // Crear la compra de tickets
        UserPurchase userPurchase = new UserPurchase(new ClienteNotificacion(usuario.getId(), "Compra realizada", "Correo", new java.util.Date()));

        // Asignar la compra al usuario
        //usuario = new User(1, nombre, apellido, email, telefono, compra, new MicroTeatroFactory(), new Map());

        // Mostrar opciones de tipo de ticket
        System.out.println("\nElija el tipo de ticket que desea comprar:");
        System.out.println("1. Platea - $20");
        System.out.println("2. Balcón - $30");
        System.out.println("3. VIP - $50");

        int opcionTicket = obtenerOpcionValida(scanner, "Ingrese el número correspondiente: ", 1, 3);

        Ticket ticket = null;
        double precioBase = 0;

        switch (opcionTicket) {
            case 1:
                ticket = usuario.getFactory().createPlatea();
                precioBase = 20;
                break;
            case 2:
                ticket = usuario.getFactory().createBalcon();
                precioBase = 30;
                break;
            case 3:
                ticket = usuario.getFactory().createVip();
                precioBase = 50;
                break;
        }

        // Verificar disponibilidad del ticket
        ticket.verDisponibilidad();

        // Selección de asiento
        int asiento = obtenerOpcionValida(scanner, "\nElija el número de asiento (1-10): ", 1, 10);
        SelectFunctionsMaps selectMap = new SelectFunctionsMaps();
        selectMap.selectSeat(asiento, nombre);

        // Limpiar el buffer de entrada después de nextInt()
        scanner.nextLine();  // Esto consume el salto de línea dejado por nextInt()

        // Datos de pago
        PurchaseProcessor.solicitar_datos_pago(scanner,ticket,precioBase,asiento, userPurchase);

    }
        

    // Método para obtener una entrada válida de texto
    private static String obtenerInputValido(Scanner scanner, String mensaje, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(mensaje);
            input = scanner.nextLine();
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }

    // Método para obtener una opción válida (número dentro de un rango)
    private static int obtenerOpcionValida(Scanner scanner, String mensaje, int min, int max) {
        int opcion;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= min && opcion <= max) {
                    break;
                } else {
                    System.out.println("Por favor ingrese un número entre " + min + " y " + max + ".");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                scanner.next();  // Limpiar buffer
            }
        }
        return opcion;
    }

    

}
