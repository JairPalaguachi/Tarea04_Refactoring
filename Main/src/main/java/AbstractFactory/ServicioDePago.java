package AbstractFactory;

import java.util.Scanner;

public class ServicioDePago {
    
    private PagoHandler pago;
    public void realizar_pago(UserPurchase up, Scanner sc){
        System.out.println("Realizando el pago...");
        pago.mostrar_confirmacion(up,true,  sc);
    }
}
