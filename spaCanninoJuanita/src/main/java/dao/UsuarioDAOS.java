	package dao;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
	import modelo.Usuario;
	
	public class UsuarioDAOS {
		Connection con; 
		
		public UsuarioDAOS (Connection conexion){
			this.con = conexion;
		}
	
		public void registrarUsuario(Usuario usuario) {
			try {
				String sql = "INSERT INTO Usuarios (nombreCompleto, ingresoUsuario, ingresoPassword, ingresoRaza, ingresoNombrePerro) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, usuario.getNombrecompleto());
	            pst.setString(2, usuario.getUsuario());
	            pst.setString(3, usuario.getContrasena());
	            pst.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "error al registrar usuario" + e.getLocalizedMessage());    
			}	
		}
		
		public Usuario iniciarSesion(String usuario, String contrasena) {
		    try {
		        String sql = "SELECT * FROM Usuarios WHERE ingresoUsuario = ? AND ingresoPassword = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		        pst.setString(1, usuario);
		        pst.setString(2, contrasena);
		        var rs = pst.executeQuery();

		        if (rs.next()) {
		            return new Usuario(
		                rs.getString("nombreCompleto"),
		                rs.getString("ingresoUsuario"),
		                rs.getString("ingresoPassword")
		                
		            );
		        }

		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
		    }
		    return null;
		}
		
		
		
		public boolean validarCredenciales(String usuario, String contrasena) {
		    try {
		        String sql = "SELECT * FROM Usuarios WHERE ingresoUsuario = ? AND ingresoPassword = ?";
		        PreparedStatement pst = con.prepareStatement(sql);
		        pst.setString(1, usuario);
		        pst.setString(2, contrasena);
		        ResultSet rs = pst.executeQuery();

		        return rs.next(); // Si hay una fila, las credenciales son válidas
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error al validar credenciales: " + e.getMessage());
		        return false;
		    }
		}
		
		public int obtenerIdPorUsuario(String nombreUsuario) {
		    int id = -1;
		    String sql = "SELECT id FROM usuarios WHERE ingresoUsuario = ?";

		    try (PreparedStatement pst = con.prepareStatement(sql)) {
		        pst.setString(1, nombreUsuario);
		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            id = rs.getInt("id");
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al obtener ID del usuario: " + e.getMessage());
		    }

		    return id;
		}


		
	}
