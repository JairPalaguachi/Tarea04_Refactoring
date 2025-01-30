package AbstractFactory;

import java.time.LocalDateTime;

public class TicketDetails {
    private int idTicket;
    private String status;
    private LocalDateTime presentationDate;
    private int idFunction;
    private int price;
    private String section;
    private int seat;

    // Constructor privado: solo puede ser accedido a través del Builder
    private TicketDetails(TicketBuilder builder) {
        this.idTicket = builder.idTicket;
        this.status = builder.status;
        this.presentationDate = builder.presentationDate;
        this.idFunction = builder.idFunction;
        this.price = builder.price;
        this.section = builder.section;
        this.seat = builder.seat;
    }

    // Getters
    public int getIdTicket() {
        return idTicket;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPresentationDate() {
        return presentationDate;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public int getPrice() {
        return price;
    }

    public String getSection() {
        return section;
    }

    public int getSeat() {
        return seat;
    }

    // Clase interna Builder
    public static class TicketBuilder {
        private int idTicket;
        private String status;
        private LocalDateTime presentationDate;
        private int idFunction;
        private int price;
        private String section;
        private int seat;

        // Métodos para asignar valores
        public TicketBuilder setIdTicket(int idTicket) {
            this.idTicket = idTicket;
            return this;
        }

        public TicketBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TicketBuilder setPresentationDate(LocalDateTime presentationDate) {
            this.presentationDate = presentationDate;
            return this;
        }

        public TicketBuilder setIdFunction(int idFunction) {
            this.idFunction = idFunction;
            return this;
        }

        public TicketBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public TicketBuilder setSection(String section) {
            this.section = section;
            return this;
        }

        public TicketBuilder setSeat(int seat) {
            this.seat = seat;
            return this;
        }

        // Método para construir el objeto `TicketDetails`
        public TicketDetails build() {
            return new TicketDetails(this);
        }
    }
}
