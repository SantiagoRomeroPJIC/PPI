package vista;

import controlador.UsuarioController;
import conexion.CConexion;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class LoginVista extends JFrame {

    private JTextField usuarioText;
    private JPasswordField passwordText;
    private UsuarioController usuarioController;

    public LoginVista() {
        setTitle("Iniciar Sesión - SPA Juanita");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        CConexion con = new CConexion();
        Connection conexion = con.establecerConexion();
        usuarioController = new UsuarioController(conexion);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(40, 30, 80, 25);
        add(usuarioLabel);

        usuarioText = new JTextField();
        usuarioText.setBounds(130, 30, 150, 25);
        add(usuarioText);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(40, 70, 80, 25);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(130, 70, 150, 25);
        add(passwordText);

        JButton loginBtn = new JButton("Iniciar Sesión");
        loginBtn.setBounds(50, 120, 120, 30);
        add(loginBtn);

        JButton registrarBtn = new JButton("Registrarse");
        registrarBtn.setBounds(180, 120, 120, 30);
        add(registrarBtn);

        loginBtn.addActionListener(e -> {
            String usuario = usuarioText.getText();
            String contrasena = new String(passwordText.getPassword());

            if (usuarioController.validarCredenciales(usuario, contrasena)) {
                int idCliente = usuarioController.obtenerIdCliente(usuario); // Obtener el ID
                if (idCliente != -1) {
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
                    new MenuPrincipal(conexion, idCliente).setVisible(true); // Pásalo al menú principal
                    dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });


        registrarBtn.addActionListener(e -> {
            new RegistroFormulario().setVisible(true);
        });
    }
}

