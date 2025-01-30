package AbstractFactory;
import Observer.*;

import java.util.Scanner;

import Decorator.AlimentoDecorator;
import Decorator.BebidaDecorator;
import Decorator.Decorator;
import Decorator.EstacionamientoDecorator;
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
        solicitar_datos_pago(sc,null, 0.0, 0);
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
    public void confirmar_compra(Boolean AgregarAdicionales, Ticket t){
        System.out.println("Confirmando compra...");
        if(AgregarAdicionales==true){
            //Decorator d= ver_opciones_adicionales(t, sc);
            //d.agregarCaracteristica();
            actualizar_costo();
            User.mostrarCostoFinal();
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

    public void solicitar_datos_pago(Scanner scanner, Ticket ticket, double precioBase, int asiento){
        System.out.println("Solicitando Datos...");
        String metodoPago;
        while (true) {
            System.out.print("\nIngrese el método de pago (Paypal/Tarjeta): ");
            metodoPago = scanner.nextLine();
            if (metodoPago.equalsIgnoreCase("Paypal")) {
                
                IAbstractFactoryPago paypal = new Paypal();
                paypal.procesar_Pago(this);
                PagoHandler pagoHandler= paypal.crearServicioPago();
                int tarjeta= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                this.dar_datos_pago(this.cliente_name, this.cliente_lastname, metodoPago, tarjeta);

                // Confirmación de la compra
                scanner.nextLine();
                System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
                String respuesta = scanner.nextLine();  // Ahora se espera la respuesta del usuario
                boolean agregarExtras = respuesta.equalsIgnoreCase("si");

                double costoExtras = 0;
                String opcionesAdicionales = "";

                if (agregarExtras) {
                    // Mostrar opciones adicionales
                    ver_opciones_adicionales(ticket, scanner);
                
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
                confirmar_compra(agregarExtras, ticket);

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

                pagoHandler.mostrar_confirmacion(this,true,scanner);
  
                scanner.close();
                break;
            
                        
                
            } else if (metodoPago.equalsIgnoreCase("Tarjeta")){
                IAbstractFactoryPago tarjeta = new Tarjeta();
                tarjeta.procesar_Pago(this);
                PagoHandler pagoHandler= tarjeta.crearServicioPago();
                int numero= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                dar_datos_pago(this.cliente_name, this.cliente_lastname, metodoPago, numero);

                // Confirmación de la compra
                scanner.nextLine();
                System.out.print("\n¿Desea agregar opciones adicionales (Alimento/Bebida/Estacionamiento)? (sí/no): ");
                String respuesta = scanner.nextLine();  // Ahora se espera la respuesta del usuario
                boolean agregarExtras = respuesta.equalsIgnoreCase("si");

                double costoExtras = 0;
                String opcionesAdicionales = "";

                

                if (agregarExtras) {
                    // Mostrar opciones adicionales
                    this.ver_opciones_adicionales(ticket, scanner);
                
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
                confirmar_compra(agregarExtras, ticket);

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

                pagoHandler.mostrar_confirmacion(this,true,scanner);

                
                scanner.close();
                break;

            }else {
                System.out.println("Método de pago inválido. Por favor, ingrese 'Paypal' o 'Tarjeta'.");
            }
        }
        
    }


}