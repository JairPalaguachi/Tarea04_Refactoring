package AbstractFactory;
import Observer.*;

import java.util.Scanner;

import Decorator.Decorator;
public class UserPurchase {
    private ClienteNotificacion notificacion_service;
    private String cliente_name;
    private String cliente_lastname;
    private String cliente_method;
    private int cliente_card;
    private IAbstractFactoryPago pago;
    

    public UserPurchase(ClienteNotificacion notificacion_service){
        this.notificacion_service=notificacion_service;

    }
    public void buyTicket(User user) {
        System.out.println("El usuario " + user.getUserName() + " ha comprado un ticket.");
    }
    public void acceder_al_carrito(User u, Scanner sc){
        u.mostrar_tickets_reservados();
        u.solicitar_datos_pago(sc,null, 0.0, 0, this);
    }
    public void dar_datos_pago(String cliente_name, String cliente_lastname, String cliente_method, int cliente_card){
        System.out.println("Dando datos de pago...");

    }
    public void ver_opciones_adicionales(Ticket t, Scanner sc){
        System.out.println("Mostrando opciones adicionales...");
        System.out.println("\n¿Qué opción adicional adicional desea agregar? Ingrese los números correspondientes, separados por comas.");
        System.out.println("1. Alimento - $5");
        System.out.println("2. Bebida - $3");
        System.out.println("3. Estacionamiento - $10");

        
    }
    public void actualizar_costo(){
        System.out.println("Actualizando el costo...");
    }
    public void confirmar_compra(Boolean AgregarAdicionales, Ticket t, User u){
        System.out.println("Confirmando compra...");
        if(AgregarAdicionales==true){
            //Decorator d= ver_opciones_adicionales(t, sc);
            //d.agregarCaracteristica();
            actualizar_costo();
            u.mostrarCostoFinal();
        }
    }
    public void mostrar_resultadoPago(Boolean pagoExitoso){
        System.out.println("Mostrando resultado del pago... ");
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