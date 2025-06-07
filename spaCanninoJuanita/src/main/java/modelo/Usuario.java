package modelo;

public class Usuario {

	private String nombreCompleto;
	private String usuario;
	private String contrasena;
	
	
	
	public Usuario(String nombreCompleto, String usuario, String contrasena) {
		this.nombreCompleto = nombreCompleto;
		this.usuario = usuario;
		this.contrasena = contrasena;

	}
	
	public String getNombrecompleto() {return nombreCompleto;}
	public String getUsuario() {return usuario;}
	public String getContrasena() {return contrasena;}

	
	public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
 
	
}
