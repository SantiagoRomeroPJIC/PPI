package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal - SPA Juanita");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton registrarUsuarioBtn = new JButton("Registrar Usuario");
        JButton registrarCitaBtn = new JButton("Registrar Cita");
        JButton verCitasBtn = new JButton("Ver Citas");
        JButton salirBtn = new JButton("Salir");

        // Acción del botón "Registrar Usuario"
        registrarUsuarioBtn.addActionListener(e -> {
            RegistroFormulario formulario = new RegistroFormulario();
            formulario.setVisible(true);
        });

        // Acción del botón "Registrar Cita"
        registrarCitaBtn.addActionListener(e -> {
            FormularioCita formularioCita = new FormularioCita();
            formularioCita.setVisible(true);
        });

        // Acción del botón "Ver Citas" (a implementar luego)
        verCitasBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad aún no implementada.");
        });

        // Acción del botón "Salir"
        salirBtn.addActionListener(e -> {
            dispose();
        });

        add(registrarUsuarioBtn);
        add(registrarCitaBtn);
        add(verCitasBtn);
        add(salirBtn);
    }
}

