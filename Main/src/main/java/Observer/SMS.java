package Observer;

import java.util.Scanner;

import AbstractFactory.UserPurchase;
import ChainOfResponsability.*;

public class SMS implements INotificarCanal {
    @Override
    public void notificarExito(String mensaje) {
        System.out.println("Notificación exitosa por sms: " + mensaje);
    }

    @Override
    public void notificarFracaso(String mensaje) {
        System.out.println("Notificación fallida por sms: " + mensaje);
    }
    @Override
    public void enviar_notificacion(String mensaje, Boolean exito, EventHandler e, Scanner sc, UserPurchase up) {
        System.out.println("Enviando notificacion: " + mensaje);
    }
}
