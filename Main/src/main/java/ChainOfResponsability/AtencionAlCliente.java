package ChainOfResponsability;
public abstract class AtencionAlCliente implements Manejadora, IAvailabilityFunction {
    private Manejadora next;

    @Override
    public void setNext(Manejadora next) {
        this.next = next;
    }

    @Override
    public void handle(String request) {
        if (checkDisponibility(1)) { // Ejemplo de uso de disponibilidad
            System.out.println("Atención al cliente maneja la solicitud.");
        } else if (next != null) {
            next.handle(request);
        }
    }

    @Override
    public boolean checkDisponibility(int idFunction) {
        // Lógica para comprobar disponibilidad
        return true;
    }
}
