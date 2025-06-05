package modelo;

import java.sql.Date;

public class Cita {
    private Date fecha;
    private String hora;
    private String servicio;
    private String nombreMascota;
    private String nombreCliente;

    public Cita(Date fecha, String hora, String servicio, String nombreMascota, String nombreCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.servicio = servicio;
        this.nombreMascota = nombreMascota;
        this.nombreCliente = nombreCliente;
    }

    // Getters
    public Date getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getServicio() { return servicio; }
    public String getNombreMascota() { return nombreMascota; }
    public String getNombreCliente() { return nombreCliente; }

    // Setters (opcional)
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
}
