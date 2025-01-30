/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package AbstractFactory;

import Observer.ClienteNotificacion;
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
public class UserTest {
    
    @Test
    public void test16() {
        try {
            AbstractFactoryTicket factory = new AbstractFactoryTicket() {
                @Override
                public Platea createPlatea() {
                    return null;
                }
                @Override
                public Balcon createBalcon() {
                    return null;
                }
                @Override
                public Vip createVip() {
                    return null;
                }
            };
            User user = new User(1, "Ana", "Lopez", "", "0956505598", new UserPurchase(new ClienteNotificacion()), factory, new Map());
            fail("Se esperaba una IllegalArgumentException por email vacío");
        } catch (IllegalArgumentException e) {
            assertEquals("El email no puede estar vacío", e.getMessage());
        }
    }
    
    @Test
    public void test17() {
        try {
            AbstractFactoryTicket factory = new AbstractFactoryTicket() {
                @Override
                public Platea createPlatea() {
                    return null;
                }
                @Override
                public Balcon createBalcon() {
                    return null;
                }
                @Override
                public Vip createVip() {
                    return null;
                }
            };
            User user = new User(1, "Juan", "Perez", "juan@example.com", "0964054324", new UserPurchase(new ClienteNotificacion()), factory, new Map());
            assertNotNull(user);
        } catch (Exception e) {
            fail("No se esperaba ninguna excepción, pero ocurrió: " + e.getMessage());
        }
    }
    
    @Test
    public void test18() {
        AbstractFactoryTicket factory = new AbstractFactoryTicket() {
            @Override
            public Platea createPlatea() {
                return null;
            }
            @Override
            public Balcon createBalcon() {
                return null;
            }
            @Override
            public Vip createVip() {
                return null;
            }
        };
        String incorrectPhone = "0965dfrr2";
        try {
            User user = new User(2, "Luis", "Gonzales", "luis@example.com", incorrectPhone, new UserPurchase(new ClienteNotificacion()), factory, new Map());
            fail("Se esperaba una excepción por teléfono incorrecto.");
        } catch (IllegalArgumentException e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("Ingrese celular en formato correcto", e.getMessage());
        }
    }
}
