package AbstractFactory;

import java.util.Scanner;

public class PagoHandler {

    private ServicioDePago servicio;
    public void procesar_Pago(UserPurchase up, Scanner sc){
        System.out.println("Procesando pago...");
        servicio.realizar_pago(up, sc);
    }
    public void mostrar_confirmacion(UserPurchase up, boolean b, Scanner scanner){
        System.out.println("¿Está usted seguro de continuar con el pago? (si/no)");
        String respuesta = scanner.nextLine();
        while (true){
            if (respuesta.equalsIgnoreCase("si")){
                up.mostrar_resultadoPago(true);
                // Notificación de la compra
                System.out.println("\n¡Compra exitosa! Le hemos enviado una notificación a su email.");
                break;
            }else if (respuesta.equalsIgnoreCase("no")){
                up.mostrar_resultadoPago(false);
                // Notificación de la compra
                System.out.println("\n¡Compra cancelada! Le hemos enviado una notificación a su email.");
                break;
            }    
            else {
                System.out.println("Entrada inválida. Debe ser si/no.");
                scanner.next();  // Limpiar buffer
            }
        }

    }

    // Método para obtener un número válido 
    public  int obtenerNumeroValido(Scanner scanner, String mensaje, int min, int max) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero >= min && numero <= max) {
                    break;
                } else {
                    System.out.println("El número de tarjeta debe estar entre " + min + " y " + max + ".");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                scanner.next();  // Limpiar buffer
            }
        }
        return numero;
    }
}
