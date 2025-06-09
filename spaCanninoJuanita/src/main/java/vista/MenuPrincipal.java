package vista;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import conexion.CConexion;

public class MenuPrincipal extends JFrame {

    private int idCliente;
    private Connection conexion;
    
    public MenuPrincipal(Connection conexion, int idCliente) {
        this.conexion = conexion;
        this.idCliente = idCliente;

        setTitle("Menú Principal - SPA Juanita");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton registrarPerroBtn = new JButton("Registrar Perro");
        JButton registrarCitaBtn = new JButton("Registrar Cita");
        JButton verCitasBtn = new JButton("Ver Citas");
        JButton salirBtn = new JButton("Salir");

        registrarPerroBtn.addActionListener(e -> {
            FormularioPerro formularioPerro = new FormularioPerro(conexion, idCliente);
            formularioPerro.setVisible(true);
        });

        registrarCitaBtn.addActionListener(e -> {
            FormularioCita formularioCita = new FormularioCita(conexion, idCliente);
            formularioCita.setVisible(true);
        });

        verCitasBtn.addActionListener(e -> {
            VerCitasVista vista = new VerCitasVista(conexion, idCliente);
            vista.setVisible(true);
        });

        salirBtn.addActionListener(e -> {
            dispose();
        });

        add(registrarPerroBtn);
        add(registrarCitaBtn);
        add(verCitasBtn);
        add(salirBtn);
    }

}

