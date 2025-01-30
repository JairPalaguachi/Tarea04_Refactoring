package AbstractFactory;

import java.util.Scanner;

public class PagoHandler {
    private PaymentProcessor processor;
    private PaymentValidator validator;
    private UserInteractionHandler interactionHandler;

    public PagoHandler() {
        this.processor = new PaymentProcessor(new ServicioDePago());
        this.validator = new PaymentValidator();
        this.interactionHandler = new UserInteractionHandler();
    }

    public void procesarPagoConValidacion(UserPurchase up, Scanner scanner) {
        processor.procesarPago(up,scanner);

        int tarjetaValida = validator.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);

        boolean confirmar = interactionHandler.confirmarCompra(scanner);
        if (confirmar) {
            System.out.println("Pago confirmado para el número de tarjeta: " + tarjetaValida);
        } else {
            System.out.println("El pago ha sido cancelado.");
        }
    }
}
