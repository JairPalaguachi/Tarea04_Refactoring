package AbstractFactory;
import Observer.*;
import java.util.Scanner;

public class UserPurchase {
    private ClienteNotificacion notificacion_service;
    private String cliente_name;
    private String cliente_lastname;
    
    public UserPurchase(ClienteNotificacion notificacion_service){
        this.notificacion_service=notificacion_service;
    }
    public ClienteNotificacion getClienteNotificacion(){
        return this.notificacion_service;
    }
    public String getClienteName(){
        return this.cliente_name;
    }
    public String getClienteLastName(){
        return this.cliente_lastname;
    }

    public void buyTicket(User user) {
        System.out.println("El usuario " + user.getUserName() + " ha comprado un ticket.");
    }
    public void acceder_al_carrito(User u, Scanner sc){
        u.mostrar_tickets_reservados();
        PurchaseProcessor.solicitar_datos_pago(sc,null, 0.0, 0,this);
    }
    
    public void confirmar_compra(Boolean AgregarAdicionales, Ticket t){
        System.out.println("Confirmando compra...");
        if(AgregarAdicionales==true){
            PurchaseProcessor.actualizar_costo();
            User.mostrarCostoFinal();
        }
    }
    

    


}