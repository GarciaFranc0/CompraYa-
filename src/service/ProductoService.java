package service;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductoService {

    public void listarProductos() {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM productos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - $" +
                                rs.getDouble("precio") +
                                " - Stock: " +
                                rs.getInt("stock")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerProductos() {

        StringBuilder productos = new StringBuilder();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM productos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                productos.append(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("nombre")
                                + " - $"
                                + rs.getDouble("precio")
                                + "\n"
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(productos.toString());
        return productos.toString();
    }

    public void agregarProducto(String nombre, double precio, int stock) {

        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO productos(nombre, precio, stock) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            ps.executeUpdate();

            System.out.println("Producto agregado");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarProducto(String nombre, double precio, int stock) {

        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            ps.executeUpdate();

            System.out.println("Producto registrado");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}