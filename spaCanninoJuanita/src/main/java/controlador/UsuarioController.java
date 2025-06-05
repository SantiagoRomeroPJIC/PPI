package controlador;

import dao.UsuarioDAOS;
import modelo.Usuario;
import java.sql.Connection;


public class UsuarioController {
	
	private UsuarioDAOS usuarioDAO;
	
	public UsuarioController(Connection conexion) {
		this.usuarioDAO = new UsuarioDAOS(conexion);
	}
	
	public void registrar(String nombreCompleto, String usuario, String contrasena, String raza, String nombrePerro) {
		Usuario nuevo = new Usuario(nombreCompleto, usuario, contrasena, raza, nombrePerro);
		usuarioDAO.registrarUsuario(nuevo);
	}
	
	public Usuario iniciarSesion(String usuario, String contrasena) {
	    return usuarioDAO.iniciarSesion(usuario, contrasena);
	}

	public boolean validarCredenciales(String usuario, String contrasena) {
	    return usuarioDAO.validarCredenciales(usuario, contrasena);
	}

	
}
