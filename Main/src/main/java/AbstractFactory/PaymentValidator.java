package AbstractFactory;

import java.util.Scanner;

public class PaymentValidator {
    public int obtenerNumeroValido(Scanner scanner, String mensaje, int min, int max) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("El número debe estar entre " + min + " y " + max + ".");
                }
            } else {
                System.out.println("Entrada inválida. Debe ser un número.");
                scanner.next();  // Limpiar buffer
            }
        }
    }
}
