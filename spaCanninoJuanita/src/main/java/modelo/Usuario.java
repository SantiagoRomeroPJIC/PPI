package modelo;

public class Usuario {

	private String nombreCompleto;
	private String usuario;
	private String contrasena;
	private String raza;
	private String nombrePerro;
	
	
	public Usuario(String nombreCompleto, String usuario, String contrasena, String raza, String nombrePerro) {
		this.nombreCompleto = nombreCompleto;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.raza = raza;
		this.nombrePerro = nombrePerro;
	}
	
	public String getNombrecompleto() {return nombreCompleto;}
	public String getUsuario() {return usuario;}
	public String getContrasena() {return contrasena;}
	public String getRaza() {return raza;}
	public String getNombreperro() {return nombrePerro;}
	
	public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setNombrePerro(String nombrePerro) { this.nombrePerro = nombrePerro; }
	
}
