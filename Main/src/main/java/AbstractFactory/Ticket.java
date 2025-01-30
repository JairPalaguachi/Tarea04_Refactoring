package AbstractFactory;

public abstract class Ticket {
    private TicketDetails details;

    public Ticket(TicketDetails details) {
        this.details = details;
    }
    

    public Ticket() {
    }

    public void agregarCaracteristica(){
        System.out.println("Agregando características");
    }
    public void mostrarDetalles() {
        System.out.println("Ticket #" + details.getIdTicket() + " - Fecha: " + details.getPresentationDate() +
                ", Sección: " + details.getSection() + ", Asiento: " + details.getSeat() +
                ", Precio: $" + details.getPrice());
    }

    public double calcularCostoTotal(double costoExtras) {
        return details.getPrice() + costoExtras;
    }

    public boolean estaDisponible() {
        return "disponible".equalsIgnoreCase(details.getStatus());
    }

    public void marcarComoVendido() {
        if (estaDisponible()) {
            System.out.println("El ticket #" + details.getIdTicket() + " ha sido marcado como vendido.");
        } else {
            System.out.println("El ticket no está disponible para la venta.");
        }
    }

    

}
