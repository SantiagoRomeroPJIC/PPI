package modelo;

public class Perro {
	
	private int id_perro;
	private String nombrePerro;
	private String tamaño;
	private String raza;
	private int idCliente;
	
	
	
	public Perro(int id_perro, String nombrePerro, String tamaño, String raza, int idCliente) {
		super();
		this.id_perro = id_perro;
		this.nombrePerro = nombrePerro;
		this.tamaño = tamaño;
		this.raza = raza;
		this.idCliente = idCliente;
	}



	public Perro(String nombrePerro, String tamaño, String raza, int idCliente) {
		super();
		this.nombrePerro = nombrePerro;
		this.tamaño = tamaño;
		this.raza = raza;
		this.idCliente = idCliente;
	}



	public int getId_perro() {
		return id_perro;
	}



	public void setId_perro(int id_perro) {
		this.id_perro = id_perro;
	}



	public String getNombrePerro() {
		return nombrePerro;
	}



	public void setNombrePerro(String nombrePerro) {
		this.nombrePerro = nombrePerro;
	}



	public String getTamaño() {
		return tamaño;
	}



	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}
	
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}


	public int getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
	@Override
	public String toString() {
	    return nombrePerro;
	}

	
	
}
