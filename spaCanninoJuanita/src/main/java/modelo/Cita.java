package modelo;

import java.sql.Date;

public class Cita {
	private int id;
    private Date fecha;
    private String hora;
    private String servicio;
    private int idPerro;
    private int idCliente;

    public Cita(Date fecha, String hora, String servicio, int idPerro, int idCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.servicio = servicio;
        this.idPerro = idPerro;
        this.idCliente = idCliente;
    }

    public Cita(int id, Date fecha, String hora, String servicio, int idPerro, int idCliente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.servicio = servicio;
        this.idPerro = idPerro;
        this.idCliente = idCliente;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public int getIdPerro() {
		return idPerro;
	}

	public void setIdPerro(int idPerro) {
		this.idPerro = idPerro;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

 
    
}
