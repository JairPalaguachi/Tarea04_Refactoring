package AbstractFactory;
import java.net.Socket;
import java.util.Scanner;

import Decorator.AlimentoDecorator;
import Decorator.BebidaDecorator;
import Decorator.Decorator;
import Decorator.EstacionamientoDecorator;

public class User {
    private int idUser;
    private String userName;
    private String lastName;
    private String userEmail;
    private String userPhone;
    private UserPurchase userPurchase;
    private AbstractFactoryTicket factory;
    private Map map;


    public User(int idUser, String userName, String lastName, String userEmail, String userPhone, UserPurchase userPurchase, AbstractFactoryTicket factory, Map map ) {
        this.idUser = idUser;
        this.userName = userName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPurchase = userPurchase;
        this.factory = factory;
        this.map= map;
    }

    public void printUserInfo() {
        System.out.println("Usuario: " + userName + " " + lastName + ", Email: " + userEmail);
    }
    
    public UserPurchase createUserPurchase(){
        return this.userPurchase;
    }

    public void mostrar_tickets_reservados(){
        System.out.println("Mostrando tickets reservados...");
    }


    
    public static void mostrarCostoFinal(){
        System.out.println("Mostrando costo final...");
    }
    
    
    // Getters y Setters

    public String getUserName() {
        return userName;
    }
    public int getId(){
        return idUser;
    }




    

    public void verificar_cliente(){
        System.out.println("Verificando cliente....");
    }
    public void mostrar_detalle(){
        System.out.println("Mostrando detalle...");
    }
    public void enviar_detalles_notificacion(){
        System.out.println("Enviando detalles...");
    }

    public AbstractFactoryTicket getFactory() {
        return factory;
    }

    public void setFactory(AbstractFactoryTicket factory) {
        this.factory = factory;
    }

    
    
}
