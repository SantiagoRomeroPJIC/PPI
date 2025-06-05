package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class CConexion {
Connection conectar;
	
	String usuario ="root";
	String password ="Noviembre20";
	String bd ="loginPPI";
	String ip ="localhost";
	String puerto ="3306";
	
	String cadena ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;

	public Connection establecerConexion() {
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conectar = DriverManager.getConnection(cadena,usuario,password);
			JOptionPane.showMessageDialog(null, "Está Vivooooo!");
			
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Problemas en la conexion" + e.toString());
		}
		return conectar;
	}
}
