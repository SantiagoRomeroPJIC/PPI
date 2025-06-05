package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
	
	private String nombreClienteLogueado;

    public MenuPrincipal(String nombreClienteLogueado) {
    	
    	this.nombreClienteLogueado = nombreClienteLogueado;
    	
        setTitle("Menú Principal - SPA Juanita");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        
        JButton registrarCitaBtn = new JButton("Registrar Cita");
        JButton verCitasBtn = new JButton("Ver Citas");
        JButton salirBtn = new JButton("Salir");

       

        // Acción del botón "Registrar Cita"
        registrarCitaBtn.addActionListener(e -> {
            FormularioCita formularioCita = new FormularioCita(nombreClienteLogueado);
            formularioCita.setVisible(true);
        });

        // Acción del botón "Ver Citas" 
        verCitasBtn.addActionListener(e -> new VerCitasVista(nombreClienteLogueado).setVisible(true));

        // Acción del botón "Salir"
        salirBtn.addActionListener(e -> {
            dispose();
        });

        
        add(registrarCitaBtn);
        add(verCitasBtn);
        add(salirBtn);
    }
}

