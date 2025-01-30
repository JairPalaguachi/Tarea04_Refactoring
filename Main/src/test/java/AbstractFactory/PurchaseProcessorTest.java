/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package AbstractFactory;

import Observer.ClienteNotificacion;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henry
 */
public class PurchaseProcessorTest {
    
    @Test
    public void test13() {
        PurchaseProcessor.paymentV = new PaymentValidator();
        String input = "Paypal\n123456789\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        TicketDetails ticketDetails = new TicketDetails.TicketBuilder().setIdTicket(1).setStatus("disponible")
                .setPresentationDate(LocalDateTime.now())
                .setIdFunction(101)
                .setPrice(50)
                .setSection("A")
                .setSeat(12)
                .build();

        Ticket ticket = new Ticket(ticketDetails) {};
        UserPurchase userPurchase = new UserPurchase(new ClienteNotificacion());
        PurchaseProcessor.solicitar_datos_pago(scanner, ticket, 50.0, 12, userPurchase);
        assertTrue(true);
    }
    @Test
    public void test14() {
        // Inicializar PaymentValidator en PurchaseProcessor para evitar NullPointerException
        PurchaseProcessor.paymentV = new PaymentValidator();

        // Simular entrada del usuario (número de tarjeta inválido como texto)
        String input = "Tarjeta\nabcd1234\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Verificar que el método lanza la excepción esperada
        assertThrows(NoSuchElementException.class, () -> {
            PurchaseProcessor.solicitar_datos_pago(scanner, null, 50.0, 8, null);
        });
    }
    @Test
    public void test15() {
        PurchaseProcessor.paymentV = new PaymentValidator();
        String input = "Tarjeta\0912345678\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        assertThrows(NoSuchElementException.class, () -> {
        PurchaseProcessor.solicitar_datos_pago(scanner, null, 50.0, 8, null);
        });
    }
    
    
    
}
