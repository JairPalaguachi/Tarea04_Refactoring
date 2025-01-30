package ChainOfResponsability;
import java.util.Scanner;

import AbstractFactory.*;
import Observer.ClienteNotificacion;
import Observer.INotificarCanal;

public class EventHandler {
        private User u;
        private ClienteNotificacion notificacion;
        
        public void actualizar_evento(Scanner sc, UserPurchase up ){
            System.out.println("Actualizando evento...");
            u.verificar_cliente();
            INotificarCanal canal= notificacion.crearServicioNotificacion();
            canal.enviar_notificacion("Enviar notificacion...", true, this,sc,  up);
            u.enviar_detalles_notificacion();
        }
    
        public void notificarExito(){
            System.out.println("Notificando exito...");
            u.mostrar_detalle();
        }
    
        public void notificarFracaso(INotificarCanal ic, Scanner sc, UserPurchase up){
            System.out.println("Notificando fracado...");
            u.solicitar_datos_pago(sc,null,0.0,0, up);
        ic.enviar_notificacion("Enviando notificacion", true, this, sc, up);

    }
}
