package vista;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import dao.CitaDAO;
import dao.PerroDAO;
import modelo.Cita;
import modelo.Perro;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class FormularioCita extends JFrame {

    private final String[] HORAS_TOTALES = {
        "08:00", "09:00", "10:00", "11:00", "12:00",
        "13:00", "14:00", "15:00", "16:00"
    };

    private Connection conexion;
    private int idCliente;

    public FormularioCita(Connection conexion, int idCliente) {
        this.conexion = conexion;
        this.idCliente = idCliente;

        setTitle("Agendar Cita - SPA Juanita");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel fechaLabel = new JLabel("Fecha:");
        fechaLabel.setBounds(30, 30, 100, 25);
        add(fechaLabel);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 30, 180, 25);
        add(dateChooser);

        JLabel horaLabel = new JLabel("Hora:");
        horaLabel.setBounds(30, 70, 100, 25);
        add(horaLabel);

        JComboBox<String> horaCombo = new JComboBox<>(HORAS_TOTALES);
        horaCombo.setBounds(150, 70, 180, 25);
        add(horaCombo);

        JLabel servicioLabel = new JLabel("Servicio:");
        servicioLabel.setBounds(30, 110, 100, 25);
        add(servicioLabel);

        JTextField servicioText = new JTextField();
        servicioText.setBounds(150, 110, 180, 25);
        add(servicioText);

        JLabel mascotaLabel = new JLabel("Mascota:");
        mascotaLabel.setBounds(30, 150, 100, 25);
        add(mascotaLabel);

        JComboBox<Perro> mascotaCombo = new JComboBox<>();
        mascotaCombo.setBounds(150, 150, 180, 25);
        add(mascotaCombo);

        JLabel clienteLabel = new JLabel("Cliente (ID):");
        clienteLabel.setBounds(30, 190, 100, 25);
        add(clienteLabel);

        JLabel clienteIdLabel = new JLabel(String.valueOf(idCliente));
        clienteIdLabel.setBounds(150, 190, 180, 25);
        add(clienteIdLabel);

        JButton agendarBtn = new JButton("Agendar Cita");
        agendarBtn.setBounds(120, 250, 150, 30);
        add(agendarBtn);

        CitaDAO citaDAO = new CitaDAO(conexion);
        PerroDAO perroDAO = new PerroDAO(conexion);

        // Cargar perros del cliente en el combo
        List<Perro> perros = perroDAO.obtenerPerrosPorCliente(idCliente);
        for (Perro perro : perros) {
            mascotaCombo.addItem(perro); // Perro debe tener método toString() que devuelva el nombre
        }

        // Escuchar cambios de fecha
        dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                java.util.Date fechaSeleccionada = dateChooser.getDate();
                if (fechaSeleccionada != null) {
                    Date fechaSql = new Date(fechaSeleccionada.getTime());
                    List<String> horasOcupadas = citaDAO.obtenerHorasOcupadas(fechaSql);

                    horaCombo.removeAllItems();
                    for (String hora : HORAS_TOTALES) {
                        if (!horasOcupadas.contains(hora)) {
                            horaCombo.addItem(hora);
                        }
                    }
                }
            }
        });

        agendarBtn.addActionListener(e -> {
            java.util.Date fechaUtil = dateChooser.getDate();
            if (fechaUtil == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
                return;
            }

            Date fechaSql = new Date(fechaUtil.getTime());
            String hora = (String) horaCombo.getSelectedItem();
            String servicio = servicioText.getText();
            Perro perroSeleccionado = (Perro) mascotaCombo.getSelectedItem();

            if (hora == null || servicio.isEmpty() || perroSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            if (citaDAO.citaExiste(fechaSql, hora)) {
                JOptionPane.showMessageDialog(this, "Ya existe una cita para esa fecha y hora.");
            } else {
                Cita cita = new Cita(fechaSql, hora, servicio, perroSeleccionado.getId_perro(), idCliente);
                boolean exito = citaDAO.registrarCita(cita);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cita registrada con éxito.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al registrar la cita.");
                }
            }
        });
    }
}
	

