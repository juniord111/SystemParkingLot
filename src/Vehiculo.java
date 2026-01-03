import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehiculo {

    private String placa;
    private TipoVehiculo tipo;
    private LocalDateTime horaEntrada;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

    public Vehiculo(String placa, TipoVehiculo tipo) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaEntrada = LocalDateTime.now();
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraEntradaFormateada() {
        return horaEntrada.format(FORMATO);
    }
}
