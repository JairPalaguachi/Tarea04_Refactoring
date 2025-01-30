package AbstractFactory;

public class Pago implements IAbstractFactoryPago {
     
        @Override
        public void procesar_Pago(UserPurchase u){
            System.out.println("Procesando pago (tarjeta/Paypal)...");
        }
    
        public PagoHandler crearServicioPago(){
            return new PagoHandler();
        }
    
}
