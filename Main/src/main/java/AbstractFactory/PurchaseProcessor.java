package AbstractFactory;

import java.util.Scanner;

import Decorator.AlimentoDecorator;
import Decorator.BebidaDecorator;
import Decorator.Decorator;
import Decorator.EstacionamientoDecorator;

public class PurchaseProcessor   {

    public static void dar_datos_pago(String cliente_name, String cliente_lastname, String cliente_method, int cliente_card){
        System.out.println("Dando datos de pago...");

    }

    public static void actualizar_costo(){
        System.out.println("Actualizando el costo...");
    }

    public static void solicitar_datos_pago(Scanner scanner, Ticket ticket, double precioBase, int asiento, UserPurchase up) {
        System.out.println("Solicitando Datos...");
        String metodoPago = obtenerMetodosPago(scanner);
    
        if (metodoPago.equalsIgnoreCase("Paypal")) {
            procesarPagoConMetodo(scanner, ticket, precioBase, asiento, up, metodoPago);
        } else if (metodoPago.equalsIgnoreCase("Tarjeta")) {
            procesarPagoConMetodo(scanner, ticket, precioBase, asiento, up, metodoPago);
        } else {
            System.out.println("Método de pago inválido. Por favor, ingrese 'Paypal' o 'Tarjeta'.");
        }
    }

    private static String obtenerMetodosPago(Scanner scanner){
        String metodoPago;
        while (true) {
            System.out.print("\nIngrese el método de pago (Paypal/Tarjeta): ");
            metodoPago = scanner.nextLine();
            if (metodoPago.equalsIgnoreCase("Paypal") || metodoPago.equalsIgnoreCase("Tarjeta")) {
                break;
            } else {
                System.out.println("Método de pago inválido. Intente nuevamente.");
            }
        }
        return metodoPago;
    }

    private static void procesarPagoConMetodo(Scanner scanner, Ticket ticket, double precioBase, int asiento, UserPurchase up, String metodoPago) {
        IAbstractFactoryPago pago = new Pago();
        pago.procesar_Pago(up);
        PagoHandler pagoHandler = pago.crearServicioPago();
    
        int numeroTarjeta = pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
        dar_datos_pago(up.getClienteName(), up.getClienteLastName(), metodoPago, numeroTarjeta);
    
        boolean agregarExtras = solicitarOpcionesAdicionales(scanner, ticket);
    
        // Confirmación final de la compra
        up.confirmar_compra(agregarExtras, ticket);
    
        // Mostrar resumen de compra
        mostrarResumenCompra(ticket, precioBase, asiento, agregarExtras);
        pagoHandler.mostrar_confirmacion(true, scanner, up);
    }

    private static void mostrarResumenCompra(Ticket ticket, double precioBase, int asiento, boolean agregarExtras) {
        double costoExtras = 0;
        System.out.println("\nResumen de su compra:");
        System.out.println("Tipo de ticket: " + ticket.getClass().getSimpleName());
        System.out.println("Asiento seleccionado: " + asiento);
        System.out.println("Costo base: $" + precioBase);
        System.out.println("Costo de opciones adicionales: $" + costoExtras);
        System.out.println("Costo total: $" + (precioBase + costoExtras));
    }

    private static void agregarExtrasTicket(Scanner scanner, Ticket ticket) {
        System.out.print("Ingrese sus opciones (por ejemplo, '1, 2' para Alimento y Bebida): ");
        String opciones = scanner.nextLine();
    
        String[] seleccionados = opciones.split(",");
        Decorator decorador = new Decorator(ticket);
    
        for (String opcion : seleccionados) {
            opcion = opcion.trim();
            switch (opcion) {
                case "1":
                    decorador = new AlimentoDecorator(ticket);
                    decorador.agregarCaracteristica();
                    break;
                case "2":
                    decorador = new BebidaDecorator(ticket);
                    decorador.agregarCaracteristica();
                    break;
                case "3":
                    decorador = new EstacionamientoDecorator(ticket);
                    decorador.agregarCaracteristica();
                    break;
                default:
                    System.out.println("Opción no válida: " + opcion);
                    break;
            }
        }
    }

    private static boolean solicitarOpcionesAdicionales(Scanner scanner, Ticket ticket) {
        System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
        String respuesta = scanner.nextLine();
        boolean agregarExtras = respuesta.equalsIgnoreCase("sí");
    
        if (agregarExtras) {
            ver_opciones_adicionales(ticket, scanner);
            agregarExtrasTicket(scanner, ticket);
        }
        return agregarExtras;
    }

    public static void ver_opciones_adicionales(Ticket t, Scanner sc){
        System.out.println("Mostrando opciones adicionales...");
        System.out.println("\n¿Qué opción adicional adicional desea agregar? Ingrese los números correspondientes, separados por comas.");
        System.out.println("1. Alimento - $5");
        System.out.println("2. Bebida - $3");
        System.out.println("3. Estacionamiento - $10");

        
    }
}
