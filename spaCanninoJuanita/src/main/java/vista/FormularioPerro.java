package vista;

import dao.PerroDAO;
import modelo.Perro;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class FormularioPerro extends JFrame {

    public FormularioPerro(Connection conexion, int idCliente) {
        setTitle("Registrar Perro");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre del perro:");
        nombreLabel.setBounds(30, 30, 120, 25);
        add(nombreLabel);

        JTextField nombreText = new JTextField();
        nombreText.setBounds(160, 30, 150, 25);
        add(nombreText);

        JLabel tamañoLabel = new JLabel("Tamaño:");
        tamañoLabel.setBounds(30, 70, 120, 25);
        add(tamañoLabel);

        String[] tamaños = {"Pequeño", "Mediano", "Grande"};
        JComboBox<String> tamañoCombo = new JComboBox<>(tamaños);
        tamañoCombo.setBounds(160, 70, 150, 25);
        add(tamañoCombo);

        JLabel razaLabel = new JLabel("Raza del perro:");
        nombreLabel.setBounds(30, 30, 120, 25);
        add(razaLabel);

        JTextField razaText = new JTextField();
        nombreText.setBounds(160, 30, 150, 25);
        add(razaText);

        JButton registrarBtn = new JButton("Registrar");
        registrarBtn.setBounds(100, 130, 120, 30);
        add(registrarBtn);

        registrarBtn.addActionListener(e -> {
            String nombrePerro = nombreText.getText();
            String tamaño = (String) tamañoCombo.getSelectedItem();
            String raza = razaText.getText();
            
            if (nombrePerro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del perro es obligatorio.");
                return;
            }

            Perro perro = new Perro(nombrePerro, tamaño, raza, idCliente);
            PerroDAO perroDAO = new PerroDAO(conexion);

            if (perroDAO.registrarPerro(perro)) {
                JOptionPane.showMessageDialog(this, "Perro registrado correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el perro.");
            }
        });
    }
}
