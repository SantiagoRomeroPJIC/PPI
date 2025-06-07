package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Perro;

public class PerroDAO {
    private Connection con;

    public PerroDAO(Connection conexion) {
        this.con = conexion;
    }

    // Registrar un perro
    public boolean registrarPerro(Perro perro) {
        String sql = "INSERT INTO Perros (nombrePerro, tamaño, raza, id_cliente) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, perro.getNombrePerro());
            pst.setString(2, perro.getTamaño());
            pst.setInt(3, perro.getIdCliente());
            pst.setString(4, perro.getRaza());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al registrar el perro: " + e.getMessage());
            return false;
        }
    }

    public List<Perro> obtenerPerrosPorCliente(int idCliente) {
        List<Perro> perros = new ArrayList<>();
        String sql = "SELECT * FROM perros WHERE id_cliente = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCliente);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Perro perro = new Perro(
                    rs.getInt("id_perro"),
                    rs.getString("nombre"),
                    rs.getString("tamano"),
                    rs.getString("raza"),
                    rs.getInt("id_cliente")
                );
                perros.add(perro);
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo perros del cliente: " + e.getMessage());
        }
        return perros;
    }


    // (Opcional) Obtener perro por ID
    public Perro obtenerPerroPorId(int idPerro) {
        String sql = "SELECT * FROM Perros WHERE id_perro = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idPerro);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Perro(
                    rs.getInt("id_perro"),
                    rs.getString("nombrePerro"),
                    rs.getString("tamaño"),
                    rs.getString("raza"),
                    rs.getInt("id_cliente")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener perro por ID: " + e.getMessage());
        }
        return null;
    }
}
