import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    private List<Vehiculo> listaVehiculos = new ArrayList<>();

    public String registrarEntrada(String placa, TipoVehiculo tipo) {

        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return "ERROR\nEl vehículo con placa " + placa + " ya está registrado.";
            }
        }

        Vehiculo nuevoVehiculo = new Vehiculo(placa, tipo);
        listaVehiculos.add(nuevoVehiculo);

        return """
            ENTRADA REGISTRADA
            
            Placa: %s
            Tipo: %s
            Hora: %s
            """.formatted(
                nuevoVehiculo.getPlaca(),
                nuevoVehiculo.getTipo(),
                nuevoVehiculo.getHoraEntradaFormateada()
        );
    }


    public String registrarSalida(String placa) {
        Vehiculo v = buscarVehiculo(placa);
        if (v == null) {
            return "ERROR: El vehículo con placa " + placa + " no se encuentra.";
        }

        LocalDateTime salida = LocalDateTime.now();
        Duration d = Duration.between(v.getHoraEntrada(), salida);
        long minutos = Math.max(1, d.toMinutes());

        double total = minutos * (v.getTipo().getTarifaPorHora() / 60.0);
        listaVehiculos.remove(v);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        return """
                ===============================
                       RECIBO DE PAGO
                ===============================
                Placa: %s
                Tipo: %s
                Hora entrada: %s
                Hora salida: %s
                Tiempo: %d minutos
                Total a pagar: $%.2f
                ===============================
                """.formatted(
                v.getPlaca(),
                v.getTipo(),
                v.getHoraEntradaFormateada(),
                salida.format(f),
                minutos,
                total
        );
    }

    private Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public List<Vehiculo> getVehiculosActivos() {
        return listaVehiculos;
    }
}
