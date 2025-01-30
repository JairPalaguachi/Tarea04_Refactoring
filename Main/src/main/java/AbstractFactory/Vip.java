package AbstractFactory;
public class Vip extends Ticket{
    public void verDisponibilidad() {
        System.out.println("Verificando disponibilidad del ticket vip...");
    }

    public void agregarCaracteristica() {
        System.out.println("Características adicionales al ticket vip añadidas.");
    }
}
