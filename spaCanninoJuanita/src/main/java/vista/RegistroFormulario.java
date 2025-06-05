package vista;

import javax.swing.*;
import java.awt.*;
import conexion.CConexion;
import controlador.UsuarioController;
import java.sql.Connection;

public class RegistroFormulario extends JFrame {

    private JTextField nombreText, usuarioText, razaText, nombrePerroText;
    private JPasswordField passwordText;

    public RegistroFormulario() {
        setTitle("Registro de Cliente - SPA Juanita");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Formulario de Registro"));
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        nombreText = crearCampo(panel, gbc, "Nombre completo:", 0);
        usuarioText = crearCampo(panel, gbc, "Usuario:", 1);
        passwordText = new JPasswordField();
        addCampo(panel, gbc, "Contraseña:", passwordText, 2);
        razaText = crearCampo(panel, gbc, "Raza del perro:", 3);
        nombrePerroText = crearCampo(panel, gbc, "Nombre del perro:", 4);

        // Botón
        JButton registrarBtn = new JButton("Registrar");
        registrarBtn.setBackground(new Color(144, 202, 249));
        registrarBtn.setFocusPainted(false);
        registrarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(registrarBtn, gbc);

        // Acción del botón
        registrarBtn.addActionListener(e -> registrarUsuario());
    }

    private JTextField crearCampo(JPanel panel, GridBagConstraints gbc, String label, int y) {
        JLabel jlabel = new JLabel(label);
        jlabel.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(jlabel, gbc);

        JTextField campo = new JTextField();
        gbc.gridx = 1;
        panel.add(campo, gbc);

        return campo;
    }

    private void addCampo(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int y) {
        JLabel jlabel = new JLabel(label);
        jlabel.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(jlabel, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void registrarUsuario() {
        String nombre = nombreText.getText();
        String usuario = usuarioText.getText();
        String pass = new String(passwordText.getPassword());
        String raza = razaText.getText();
        String nombrePerro = nombrePerroText.getText();

        CConexion con = new CConexion();
        Connection conexion = con.establecerConexion();

        UsuarioController controlador = new UsuarioController(conexion);
        controlador.registrar(nombre, usuario, pass, raza, nombrePerro);
    }
}

