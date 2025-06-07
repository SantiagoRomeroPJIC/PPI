package dao;

import modelo.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private Connection conexion;

    public CitaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrarCita(Cita cita) {
        String sql = "INSERT INTO citas (fecha, hora, servicio, id_perro, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setDate(1, cita.getFecha());
            pst.setString(2, cita.getHora());
            pst.setString(3, cita.getServicio());
            pst.setInt(4, cita.getIdPerro());
            pst.setInt(5, cita.getIdCliente());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar cita: " + e.getMessage());
            return false;
        }
    }

    public List<String> obtenerHorasOcupadas(Date fecha) {
        List<String> horas = new ArrayList<>();
        String sql = "SELECT hora FROM citas WHERE fecha = ?";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setDate(1, fecha);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                horas.add(rs.getString("hora"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener horas ocupadas: " + e.getMessage());
        }
        return horas;
    }

    public boolean citaExiste(Date fecha, String hora) {
        String sql = "SELECT COUNT(*) FROM citas WHERE fecha = ? AND hora = ?";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setDate(1, fecha);
            pst.setString(2, hora);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar existencia de cita: " + e.getMessage());
        }
        return false;
    }

    public List<Cita> obtenerCitasPorCliente(int idCliente) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE id_cliente = ?";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita(
                    rs.getInt("id"),
                    rs.getDate("fecha"),
                    rs.getString("hora"),
                    rs.getString("servicio"),
                    rs.getInt("id_perro"),
                    rs.getInt("id_cliente")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas por cliente: " + e.getMessage());
        }
        return citas;
    }
}

