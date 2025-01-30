/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package AbstractFactory;

import java.time.LocalDateTime;
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
public class TicketDetailsTest {
    @Test
    public void test19() {
        int idTicket = 1;
        String status = "Disponible";
        LocalDateTime presentationDate = LocalDateTime.now();
        int idFunction = 101;
        int price = 50;
        String section = "A";
        int seat = 10;
        TicketDetails ticketDetails = new TicketDetails.TicketBuilder()
                .setIdTicket(idTicket)
                .setStatus(status)
                .setPresentationDate(presentationDate)
                .setIdFunction(idFunction)
                .setPrice(price)
                .setSection(section)
                .setSeat(seat)
                .build();
        assertEquals(idTicket, ticketDetails.getIdTicket());
        assertEquals(status, ticketDetails.getStatus());
        assertEquals(presentationDate, ticketDetails.getPresentationDate());
        assertEquals(idFunction, ticketDetails.getIdFunction());
        assertEquals(price, ticketDetails.getPrice());
        assertEquals(section, ticketDetails.getSection());
        assertEquals(seat, ticketDetails.getSeat());
    }
    
    @Test
    public void test20() {
        int idTicket = 2;
        String status = "Disponible";
        LocalDateTime presentationDate = LocalDateTime.now();
        int idFunction = 102;
        int price = -10;
        String section = "B";
        int seat = 20;

        try {
            TicketDetails ticketDetails = new TicketDetails.TicketBuilder()
                    .setIdTicket(idTicket)
                    .setStatus(status)
                    .setPresentationDate(presentationDate)
                    .setIdFunction(idFunction)
                    .setPrice(price)
                    .setSection(section)
                    .setSeat(seat)
                    .build();
            fail("Se esperaba una IllegalArgumentException por precio negativo");

        } catch (IllegalArgumentException e) {
            assertEquals("El mensaje de la excepción debe ser el correcto", 
                         "El precio no puede ser negativo", e.getMessage());
        }
    }
    
    @Test
    public void test21() {
        int idTicket = 3;
        String status = "Disponible";
        LocalDateTime presentationDate = LocalDateTime.now();
        int idFunction = 103;
        int price = 30;
        String section = "";
        int seat = 30;

        try {
            TicketDetails ticketDetails = new TicketDetails.TicketBuilder()
                    .setIdTicket(idTicket)
                    .setStatus(status)
                    .setPresentationDate(presentationDate)
                    .setIdFunction(idFunction)
                    .setPrice(price)
                    .setSection(section)
                    .setSeat(seat)
                    .build();
            fail("Se esperaba una IllegalArgumentException por sección vacía");

        } catch (IllegalArgumentException e) {
            assertNotEquals("El mensaje de la excepción debe ser el correcto", 
                         "La sección no puede ser vacía o nula", e.getMessage());
        }
    }
}
