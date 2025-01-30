package AbstractFactory;
public interface IAbstractFactoryPago {
    public PagoHandler crearServicioPago();
    public void procesar_Pago(UserPurchase u);
}