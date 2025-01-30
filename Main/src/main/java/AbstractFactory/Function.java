package AbstractFactory;
import java.time.LocalDate;

public class Function {
    private int idFunction;
    private LocalDate date;
    private String time;
    private String status;

    public Function(int idFunction, LocalDate date, String time, String status) {
        this.idFunction = idFunction;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public void mostrarDetalles() {
        System.out.println("Función #" + idFunction + " - Fecha: " + date + ", Hora: " + time + ", Estado: " + status);
    }

    // Getters y Setters
}
