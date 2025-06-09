package controlador;

import dao.UsuarioDAOS;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioController {

    private UsuarioDAOS usuarioDAO;
    private Connection conexion;

    public UsuarioController(Connection conexion) {
        this.conexion = conexion; 
        this.usuarioDAO = new UsuarioDAOS(conexion);
    }

    public void registrar(String nombreCompleto, String usuario, String contrasena ) {
        Usuario nuevo = new Usuario(nombreCompleto, usuario, contrasena );
        usuarioDAO.registrarUsuario(nuevo);
    }

    public Usuario iniciarSesion(String usuario, String contrasena) {
        return usuarioDAO.iniciarSesion(usuario, contrasena);
    }

    public boolean validarCredenciales(String usuario, String contrasena) {
        return usuarioDAO.validarCredenciales(usuario, contrasena);
    }

    public int obtenerIdCliente(String nombreUsuario) {
        return usuarioDAO.obtenerIdPorUsuario(nombreUsuario);
    }

}
