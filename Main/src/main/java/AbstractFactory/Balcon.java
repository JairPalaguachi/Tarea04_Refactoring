package AbstractFactory;
public class Balcon extends Ticket{

    public void verDisponibilidad() {
        System.out.println("Verificando disponibilidad del ticket balcon...");
    }

    public void agregarCaracteristica() {
        System.out.println("Características adicionales al ticket balcon añadidas.");
    }
}
