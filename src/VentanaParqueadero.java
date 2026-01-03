import javax.swing.*;
import java.awt.*;

public class VentanaParqueadero extends JFrame {

    private Parqueadero parqueadero;

    public VentanaParqueadero() {
        parqueadero = new Parqueadero();

        setTitle("Sistema de Parqueadero");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearComponentes();
        setVisible(true);
    }

    private void crearComponentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("PARQUEADERO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnEntrada = new JButton("Registrar Entrada");
        JButton btnSalida = new JButton("Registrar Salida");
        JButton btnSalir = new JButton("Salir");

        // Acciones
        btnEntrada.addActionListener(e -> registrarEntrada());
        btnSalida.addActionListener(e -> registrarSalida());
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(titulo);
        panel.add(btnEntrada);
        panel.add(btnSalida);
        panel.add(btnSalir);

        add(panel);
    }


    private void registrarEntrada() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa:");
        if (placa == null || placa.isBlank()) return;

        TipoVehiculo tipo = (TipoVehiculo) JOptionPane.showInputDialog(
                this,
                "Seleccione el tipo:",
                "Tipo de Vehículo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                TipoVehiculo.values(),
                TipoVehiculo.CARRO
        );

        if (tipo != null) {
            String mensaje = parqueadero.registrarEntrada(placa, tipo);
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }



    private void registrarSalida() {

        String placa = JOptionPane.showInputDialog(
                this,
                "Ingrese la placa:",
                "Salida de Vehículo",
                JOptionPane.QUESTION_MESSAGE
        );

        if (placa == null || placa.isBlank()) {
            JOptionPane.showMessageDialog(this, "Placa inválida");
            return;
        }

        String recibo = parqueadero.registrarSalida(placa.toUpperCase());

        if (recibo != null) {
            JOptionPane.showMessageDialog(
                    this,
                    recibo,
                    "Recibo de Pago",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
