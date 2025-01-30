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


    public void solicitar_datos_pago(Scanner scanner, Ticket ticket, double precioBase, int asiento, UserPurchase userPurchase){
        System.out.println("Solicitando Datos...");
        String metodoPago;
        while (true) {
            System.out.print("\nIngrese el método de pago (Paypal/Tarjeta): ");
            metodoPago = scanner.nextLine();
            if (metodoPago.equalsIgnoreCase("Paypal")) {
                
                IAbstractFactoryPago paypal = new Paypal();
                paypal.procesar_Pago(userPurchase);
                PagoHandler pagoHandler= paypal.crearServicioPago();
                int tarjeta= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                userPurchase.dar_datos_pago(userName, lastName, metodoPago, tarjeta);

                // Confirmación de la compra
                scanner.nextLine();
                System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
                String respuesta = scanner.nextLine();  // Ahora se espera la respuesta del usuario
                boolean agregarExtras = respuesta.equalsIgnoreCase("si");

                double costoExtras = 0;
                String opcionesAdicionales = "";

                if (agregarExtras) {
                    // Mostrar opciones adicionales
                    userPurchase.ver_opciones_adicionales(ticket, scanner);
                
                    System.out.print("Ingrese sus opciones (por ejemplo, '1, 2' para Alimento y Bebida): ");
                    String opciones = scanner.nextLine();
                
                    String[] seleccionados = opciones.split(",");
                
                    // Primero, envolvemos el ticket original en un decorador base
                    Decorator decorador = new Decorator(ticket);  
                
                    // Procesar las opciones seleccionadas
                    for (String opcion : seleccionados) {
                        opcion = opcion.trim();  // Eliminar espacios extras
                        switch (opcion) {
                            case "1":
                                costoExtras += 5;
                                opcionesAdicionales += "Alimento, ";
                                // Usamos el ticket para crear un nuevo decorador
                                decorador = new AlimentoDecorator(ticket);  
                                decorador.agregarCaracteristica();
                                
                                break;
                            case "2":
                                costoExtras += 3;
                                opcionesAdicionales += "Bebida, ";
                                decorador = new BebidaDecorator(ticket); 
                                decorador.agregarCaracteristica();
                                
                                break;
                            case "3":
                                costoExtras += 10;
                                opcionesAdicionales += "Estacionamiento, ";
                                decorador = new EstacionamientoDecorator(ticket);  
                                decorador.agregarCaracteristica();
                                break;
                            default:
                                System.out.println("Opción no válida: " + opcion);
                                break;
                        }
                    }
                
                    // Eliminar la coma al final de las opciones adicionales
                    if (opcionesAdicionales.endsWith(", ")) {
                        opcionesAdicionales = opcionesAdicionales.substring(0, opcionesAdicionales.length() - 2);
                    }
                
                    
                    
                }

                // Confirmación final de la compra
                userPurchase.confirmar_compra(agregarExtras, ticket, this);

                // Calcular el costo total
                double costoTotal = precioBase + costoExtras;

                

                // Mostrar el resultado del pago
                System.out.println("\nResumen de su compra:");
                System.out.println("Tipo de ticket: " + ticket.getClass().getSimpleName());
                System.out.println("Asiento seleccionado: " + asiento);
                if (!opcionesAdicionales.isEmpty()) {
                    System.out.println("Opciones adicionales: " + opcionesAdicionales);
                }
                System.out.println("Costo base: $" + precioBase);
                System.out.println("Costo de opciones adicionales: $" + costoExtras);
                System.out.println("Costo total: $" + costoTotal);

                pagoHandler.mostrar_confirmacion(userPurchase,true,scanner);
  
                scanner.close();
                break;
            
                        
                
            } else if (metodoPago.equalsIgnoreCase("Tarjeta")){
                IAbstractFactoryPago tarjeta = new Tarjeta();
                tarjeta.procesar_Pago(userPurchase);
                PagoHandler pagoHandler= tarjeta.crearServicioPago();
                int numero= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                userPurchase.dar_datos_pago(userName, lastName, metodoPago, numero);

                // Confirmación de la compra
                scanner.nextLine();
                System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
                String respuesta = scanner.nextLine();  // Ahora se espera la respuesta del usuario
                boolean agregarExtras = respuesta.equalsIgnoreCase("si");

                double costoExtras = 0;
                String opcionesAdicionales = "";

                

                if (agregarExtras) {
                    // Mostrar opciones adicionales
                    userPurchase.ver_opciones_adicionales(ticket, scanner);
                
                    System.out.print("Ingrese sus opciones (por ejemplo, '1, 2' para Alimento y Bebida): ");
                    String opciones = scanner.nextLine();
                
                    String[] seleccionados = opciones.split(",");
                
                    
                    Decorator decorador = new Decorator(ticket);  
                
                    // Procesar las opciones seleccionadas
                    for (String opcion : seleccionados) {
                        opcion = opcion.trim();  // Eliminar espacios extras
                        switch (opcion) {
                            case "1":
                                costoExtras += 5;
                                opcionesAdicionales += "Alimento, ";
                                // Usamos el ticket para crear un nuevo decorador
                                decorador = new AlimentoDecorator(ticket);  
                                decorador.agregarCaracteristica();
                                break;
                            case "2":
                                costoExtras += 3;
                                opcionesAdicionales += "Bebida, ";
                                decorador = new BebidaDecorator(ticket);  
                                decorador.agregarCaracteristica();
                                break;
                            case "3":
                                costoExtras += 10;
                                opcionesAdicionales += "Estacionamiento, ";
                                decorador = new EstacionamientoDecorator(ticket);  
                                decorador.agregarCaracteristica();
                                break;
                            default:
                                System.out.println("Opción no válida: " + opcion);
                                break;
                        }
                    }
                
                    // Eliminar la coma al final de las opciones adicionales
                    if (opcionesAdicionales.endsWith(", ")) {
                        opcionesAdicionales = opcionesAdicionales.substring(0, opcionesAdicionales.length() - 2);
                    }
                
                }
                

                // Confirmación final de la compra
                userPurchase.confirmar_compra(agregarExtras, ticket, this);

                // Calcular el costo total
                double costoTotal = precioBase + costoExtras;

                //userPurchase.mostrar_resultadoPago(true); 

                // Mostrar el resultado del pago
                System.out.println("\nResumen de su compra:");
                System.out.println("Tipo de ticket: " + ticket.getClass().getSimpleName());
                System.out.println("Asiento seleccionado: " + asiento);
                if (!opcionesAdicionales.isEmpty()) {
                    System.out.println("Opciones adicionales: " + opcionesAdicionales);
                }
                System.out.println("Costo base: $" + precioBase);
                System.out.println("Costo de opciones adicionales: $" + costoExtras);
                System.out.println("Costo total: $" + costoTotal);

                pagoHandler.mostrar_confirmacion(userPurchase,true,scanner);

                
                scanner.close();
                break;

            }else {
                System.out.println("Método de pago inválido. Por favor, ingrese 'Paypal' o 'Tarjeta'.");
            }
        }
        
    }
    public void mostrarCostoFinal(){
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
