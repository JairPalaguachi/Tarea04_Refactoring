package AbstractFactory;
import java.time.LocalDateTime;

public abstract class Ticket {
    private int idTicket;
    private String status;
    private LocalDateTime presentationDate;
    private int idFunction;
    private int price;
    private String section;
    private int seat;

    public Ticket(int idTicket, String status, LocalDateTime presentationDate, int idFunction, int price, String section, int seat) {
        this.idTicket = idTicket;
        this.status = status;
        this.presentationDate = presentationDate;
        this.idFunction = idFunction;
        this.price = price;
        this.section = section;
        this.seat = seat;
    }
    public Ticket(){
        
    }

     // Nuevo método para verificar disponibilidad basado en el estado
     public boolean estaDisponible() {
        return "disponible".equalsIgnoreCase(status);
    }

        // Nuevo método para mostrar detalles del ticket
    public void mostrarDetalles() {
        System.out.println("Ticket #" + idTicket + " - Fecha: " + presentationDate + ", Sección: " + section + ", Asiento: " + seat + ", Precio: $" + price);
    }
    // Nuevo método para calcular el costo total con adicionales
    public double calcularCostoTotal(double costoExtras) {
        return price + costoExtras;
    }

        // Nuevo método para marcar como vendido
    public void marcarComoVendido() {
            if (estaDisponible()) {
                this.status = "vendido";
                System.out.println("El ticket #" + idTicket + " ha sido marcado como vendido.");
            } else {
                System.out.println("El ticket no está disponible para la venta.");
            }
    }
    public void agregarCaracteristica() {
        System.out.println("Características adicionales al ticket añadidas.");
    }

    // Getters y Setters
    public int getPrice(){
        return this.price;
    }
    public int getIdTicket() {
        return idTicket;
    }
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getPresentationDate() {
        return presentationDate;
    }
    public void setPresentationDate(LocalDateTime presentationDate) {
        this.presentationDate = presentationDate;
    }
    public int getIdFunction() {
        return idFunction;
    }
    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    
}
