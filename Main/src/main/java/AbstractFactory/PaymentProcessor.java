package AbstractFactory;

import java.util.Scanner;

public class PaymentProcessor {
    private ServicioDePago servicioDePago;

    public PaymentProcessor(ServicioDePago servicioDePago) {
        this.servicioDePago = servicioDePago;
    }

    public void procesarPago(UserPurchase up,Scanner sc) {
        System.out.println("Procesando el pago...");
        servicioDePago.realizar_pago(up,sc);
    }
}
