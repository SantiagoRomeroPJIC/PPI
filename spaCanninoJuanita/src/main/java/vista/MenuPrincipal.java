package vista;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import conexion.CConexion;

public class MenuPrincipal extends JFrame {

    private int idCliente;
    private Connection conexion;
    
    public MenuPrincipal(Connection conexion, int idCliente, String NombreUsuario) {
    	this.conexion = conexion;
        this.idCliente = idCliente;
    }


    public MenuPrincipal(int idCliente) {
        this.idCliente = idCliente;
     
 

        setTitle("Menú Principal - SPA Juanita");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Establecer conexión
        CConexion con = new CConexion();
        conexion = con.establecerConexion();

        JButton registrarPerroBtn = new JButton("Registrar Perro");
        JButton registrarCitaBtn = new JButton("Registrar Cita");
        JButton verCitasBtn = new JButton("Ver Citas");
        JButton salirBtn = new JButton("Salir");

        // Acción: Registrar Perro
        registrarPerroBtn.addActionListener(e -> {
            FormularioPerro formularioPerro = new FormularioPerro(conexion, idCliente);
            formularioPerro.setVisible(true);
        });

        // Acción: Registrar Cita (de momento no está ligada a perro aún)
        registrarCitaBtn.addActionListener(e -> {
            FormularioCita formularioCita = new FormularioCita(conexion, idCliente);
            formularioCita.setVisible(true);
        });

        // Acción: Ver Citas
        verCitasBtn.addActionListener(e -> {
            VerCitasVista vista = new VerCitasVista(conexion, idCliente);
            vista.setVisible(true);
        });

        // Acción: Salir
        salirBtn.addActionListener(e -> {
            dispose();
        });

        // Añadir botones al menú
        add(registrarPerroBtn);
        add(registrarCitaBtn);
        add(verCitasBtn);
        add(salirBtn);
    }
}

