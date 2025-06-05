package vista;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import conexion.CConexion;
import dao.CitaDAO;
import modelo.Cita;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class FormularioCita extends JFrame {

    private final String[] HORAS_TOTALES = {
        "08:00", "09:00", "10:00", "11:00", "12:00",
        "13:00", "14:00", "15:00", "16:00"
    };

    public FormularioCita() {
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

        JLabel mascotaLabel = new JLabel("Nombre Mascota:");
        mascotaLabel.setBounds(30, 150, 120, 25);
        add(mascotaLabel);

        JTextField mascotaText = new JTextField();
        mascotaText.setBounds(150, 150, 180, 25);
        add(mascotaText);

        JLabel clienteLabel = new JLabel("Nombre Cliente:");
        clienteLabel.setBounds(30, 190, 120, 25);
        add(clienteLabel);

        JTextField clienteText = new JTextField();
        clienteText.setBounds(150, 190, 180, 25);
        add(clienteText);

        JButton agendarBtn = new JButton("Agendar Cita");
        agendarBtn.setBounds(120, 250, 150, 30);
        add(agendarBtn);

        CConexion con = new CConexion();
        Connection conexion = con.establecerConexion();
        CitaDAO citaDAO = new CitaDAO(conexion);

        // Escuchar cambios de fecha
        dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                java.util.Date fechaSeleccionada = dateChooser.getDate();
                if (fechaSeleccionada != null) {
                    Date fechaSql = new Date(fechaSeleccionada.getTime());
                    List<String> horasOcupadas = citaDAO.obtenerHorasOcupadas(fechaSql);

                    // Limpiar comboBox y agregar solo horas disponibles
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
            String nombreMascota = mascotaText.getText();
            String nombreCliente = clienteText.getText();

            if (hora == null || servicio.isEmpty() || nombreMascota.isEmpty() || nombreCliente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            if (citaDAO.citaExiste(fechaSql, hora)) {
                JOptionPane.showMessageDialog(this, "Ya existe una cita para esa fecha y hora.");
            } else {
                Cita cita = new Cita(fechaSql, hora, servicio, nombreMascota, nombreCliente);
                boolean exito = citaDAO.registrarCita(cita);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cita registrada con éxito.");
                    dispose(); // cierra el formulario después del registro
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al registrar la cita.");
                }
            }
        });
    }
}


