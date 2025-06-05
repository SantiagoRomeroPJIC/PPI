package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Cita;

public class CitaDAO {
    private Connection con;

    public CitaDAO(Connection conexion) {
        this.con = conexion;
    }

    public boolean registrarCita(Cita cita) {
        String sql = "INSERT INTO Citas (fecha, hora, servicio, nombreMascota, nombreCliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, cita.getFecha());
            pst.setString(2, cita.getHora());
            pst.setString(3, cita.getServicio());
            pst.setString(4, cita.getNombreMascota());
            pst.setString(5, cita.getNombreCliente());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar cita: " + e.getMessage());
            return false;
        }
    }
    public boolean citaExiste(Date fecha, String hora) {
        String sql = "SELECT COUNT(*) FROM Citas WHERE fecha = ? AND hora = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, new java.sql.Date(fecha.getTime()));
            pst.setString(2, hora);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar cita existente: " + e.getMessage());
        }
        return false;
    }

    
    public List<String> obtenerHorasOcupadas(Date fecha) {
        List<String> horasOcupadas = new ArrayList<>();
        String sql = "SELECT hora FROM Citas WHERE fecha = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, new java.sql.Date(fecha.getTime()));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	
                horasOcupadas.add(rs.getString("hora"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener horas ocupadas: " + e.getMessage());
        }
        return horasOcupadas;
    }

    
}
