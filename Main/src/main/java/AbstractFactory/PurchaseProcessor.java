package AbstractFactory;

import java.util.Scanner;

import Decorator.AlimentoDecorator;
import Decorator.BebidaDecorator;
import Decorator.Decorator;
import Decorator.EstacionamientoDecorator;

public class PurchaseProcessor   {

    public static void dar_datos_pago(String cliente_name, String cliente_lastname, String cliente_method, int cliente_card){
        System.out.println("Dando datos de pago...");

    }

    public static void actualizar_costo(){
        System.out.println("Actualizando el costo...");
    }

    

    public static void solicitar_datos_pago(Scanner scanner, Ticket ticket, double precioBase, int asiento, UserPurchase up){
        System.out.println("Solicitando Datos...");
        String metodoPago;
        while (true) {
            System.out.print("\nIngrese el método de pago (Paypal/Tarjeta): ");
            metodoPago = scanner.nextLine();
            if (metodoPago.equalsIgnoreCase("Paypal")) {
                
                IAbstractFactoryPago paypal = new Pago();
                paypal.procesar_Pago(up);
                PagoHandler pagoHandler= paypal.crearServicioPago();
                int tarjeta= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                dar_datos_pago(up.getClienteName(),up.getClienteLastName(), metodoPago, tarjeta);

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
                up.confirmar_compra(agregarExtras, ticket);

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

                pagoHandler.mostrar_confirmacion(true,scanner,up);
  
                scanner.close();
                break;
            
                        
                
            } else if (metodoPago.equalsIgnoreCase("Tarjeta")){
                IAbstractFactoryPago tarjeta = new Pago();
                tarjeta.procesar_Pago(up);
                PagoHandler pagoHandler= tarjeta.crearServicioPago();
                int numero= pagoHandler.obtenerNumeroValido(scanner, "Ingrese el número de tarjeta (solo números): ", 100000000, 999999999);
                dar_datos_pago(up.getClienteName(), up.getClienteLastName(), metodoPago, numero);

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
                up.confirmar_compra(agregarExtras, ticket);

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

                pagoHandler.mostrar_confirmacion(true,scanner,up);

                
                scanner.close();
                break;

            }else {
                System.out.println("Método de pago inválido. Por favor, ingrese 'Paypal' o 'Tarjeta'.");
            }
        }
        
    }

    public static void ver_opciones_adicionales(Ticket t, Scanner sc){
        System.out.println("Mostrando opciones adicionales...");
        System.out.println("\n¿Qué opción adicional adicional desea agregar? Ingrese los números correspondientes, separados por comas.");
        System.out.println("1. Alimento - $5");
        System.out.println("2. Bebida - $3");
        System.out.println("3. Estacionamiento - $10");

        
    }
}
