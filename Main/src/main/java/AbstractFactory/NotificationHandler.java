package AbstractFactory;

import Observer.ClienteNotificacion;
import Observer.INotificarCanal;

public class NotificationHandler {
    

    public  static void mostrar_resultadoPago(Boolean pagoExitoso, UserPurchase up){
        System.out.println("Mostrando resultado del pago... ");
        ClienteNotificacion notificacion_service= up.getClienteNotificacion();
        if (pagoExitoso){
            INotificarCanal canal= notificacion_service.crearServicioNotificacion();
            System.out.println("¡Pago exitoso!");
            canal.notificarExito("Enviando notificacion...");
        } else{
            INotificarCanal canal= notificacion_service.crearServicioNotificacion();
            System.out.println("¡Pago fallido!");
            canal.notificarFracaso("Enviando notificacion...");
        }
        
    }
}
