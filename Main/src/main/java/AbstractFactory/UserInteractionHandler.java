package AbstractFactory;

import java.util.Scanner;

public class UserInteractionHandler {

    public boolean confirmarCompra(Scanner scanner) {
        System.out.println("\n¿Está usted seguro de continuar con el pago? (sí/no)");
        String respuesta = scanner.nextLine();
        while (true) {
            if (respuesta.equalsIgnoreCase("sí")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Entrada inválida. Debe ser sí o no.");
                respuesta = scanner.nextLine();
            }
        }
    }
}
