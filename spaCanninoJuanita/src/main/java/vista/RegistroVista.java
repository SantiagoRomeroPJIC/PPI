package vista;
import conexion.CConexion;
import controlador.UsuarioController;
import java.sql.Connection;

public class RegistroVista {
	
	public static void main(String[] args) {
        CConexion conexion = new CConexion();
        Connection con = conexion.establecerConexion();

        UsuarioController controller = new UsuarioController(con);
        controller.registrar("David", "Arosty", "1234");
	}
}