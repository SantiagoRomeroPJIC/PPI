package vista;

import dao.CitaDAO;
import modelo.Cita;
import conexion.CConexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class VerCitasVista extends JFrame {

    public VerCitasVista(Connection conexion, int idCliente ) {
        setTitle("Mis Citas Agendadas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Conexión y DAO
        CConexion con = new CConexion();
        Connection conectar = con.establecerConexion();
        CitaDAO citaDAO = new CitaDAO(conexion);

        // Obtener las citas del cliente
        List<Cita> citas = citaDAO.obtenerCitasPorCliente(idCliente);

        // Modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Servicio");
        model.addColumn("Mascota");

        // Llenar tabla
        if (citas != null && !citas.isEmpty()) {
            for (Cita c : citas) {
                model.addRow(new Object[]{
                    c.getFecha().toString(),
                    c.getHora(),
                    c.getServicio(),
                    c.getIdPerro()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "No tienes citas registradas.");
        }

        JTable tabla = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }
}

