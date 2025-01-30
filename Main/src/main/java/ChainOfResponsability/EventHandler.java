package ChainOfResponsability;

import java.util.Scanner;

import AbstractFactory.*;
import Observer.ClienteNotificacion;
import Observer.INotificarCanal;

public class EventHandler {
    private User u;
    private ClienteNotificacion notificacion;

    // Constructor con Null Object por defecto
    public EventHandler() {
        this.u = new NullUser(); // Evita null en User
        this.notificacion = new NullClienteNotificacion(); // Evita null en ClienteNotificacion
    }

    public void actualizar_evento(Scanner sc, UserPurchase up) {
        System.out.println("Actualizando evento...");
        u.verificar_cliente();
        INotificarCanal canal = notificacion.crearServicioNotificacion();
        canal.enviar_notificacion("Enviar notificación...", true, this, sc, up);
        u.enviar_detalles_notificacion();
    }

    public void notificarExito() {
        System.out.println("Notificando éxito...");
        u.mostrar_detalle();
    }

    public void notificarFracaso(INotificarCanal ic, Scanner sc, UserPurchase up) {
        System.out.println("Notificando fracaso...");
        PurchaseProcessor.solicitar_datos_pago(sc, null, 0.0, 0,up);
        ic.enviar_notificacion("Enviando notificación", true, this, sc, up);
    }

    // Métodos para inyectar dependencias (si es necesario)
    public void setUser(User user) {
        this.u = (user != null) ? user : new NullUser();
    }

    public void setNotificacion(ClienteNotificacion notificacion) {
        this.notificacion = (notificacion != null) ? notificacion : new NullClienteNotificacion();
    }
}

// Implementación de Null Object para User
class NullUser extends User {

    public NullUser(){
        super();
    }

    @Override
    public void verificar_cliente() {
        System.out.println("[NullUser] Cliente no disponible.");
    }

    @Override
    public void enviar_detalles_notificacion() {
        System.out.println("[NullUser] No hay detalles de notificación.");
    }

    @Override
    public void mostrar_detalle() {
        System.out.println("[NullUser] No hay detalles para mostrar.");
    }
}

// Implementación de Null Object para ClienteNotificacion
class NullClienteNotificacion extends ClienteNotificacion {
    public NullClienteNotificacion() {
        super(); // Asegura que se llama al constructor de ClienteNotificacion
    }

    @Override
    public INotificarCanal crearServicioNotificacion() {
        System.out.println("[NullClienteNotificacion] No hay servicio de notificación disponible.");
        return new NullNotificarCanal(); // También aplicamos Null Object a INotificarCanal
    }
}

// Null Object para INotificarCanal (Opcional)
class NullNotificarCanal implements INotificarCanal {

    public void enviar_notificacion(String mensaje, Boolean estado, EventHandler handler, Scanner sc, UserPurchase up) {
        System.out.println("[NullNotificarCanal] No se puede enviar la notificación.");
    }

    public void notificarExito(String mensaje){
        System.out.println("[NullNotificarCanal] No se puede notificar Exito.");
    }
    public void notificarFracaso(String mensaje){
        System.out.println("[NullNotificarCanal] No se puede notificar Fracaso.");
    }
}

