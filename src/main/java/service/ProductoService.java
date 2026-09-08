package service;

import database.Conexion;
import models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

            rs.close();
            st.close();
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

            rs.close();
            st.close(); // o ps.close()
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(productos.toString());
        return productos.toString();
    }

    public void agregarProducto(String nombre, double precio, int stock) {
        System.out.println("ENTRO A agregarProducto");
        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO productos(nombre, precio, stock) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            ps.executeUpdate();

            System.out.println("Producto agregado");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarProducto(String nombre, double precio, int stock) {
        System.out.println("ENTRO A registrarProducto");
        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto registrado");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int id) {

        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM productos WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            con.close();

            System.out.println("Producto eliminado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerProductosLista() {

        List<String> productos = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM productos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                productos.add(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("nombre")
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    public void editarProducto(
            int id,
            String nombre,
            double precio,
            int stock
    ) {

        try {
            Connection con = Conexion.conectar();

            String sql = """
                UPDATE productos
                SET nombre = ?, precio = ?, stock = ?
                WHERE id = ?
                """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock);
            ps.setInt(4, id);

            ps.executeUpdate();

            con.close();

            System.out.println("Producto actualizado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizarStock(int idProducto, int nuevoStock){

        try{
            Connection con = Conexion.conectar();

            String sql =
                    "UPDATE productos SET stock = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, nuevoStock);
            ps.setInt(2, idProducto);

            ps.executeUpdate();

            ps.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int contarProductos(){

        try{
            Connection con = Conexion.conectar();

            String sql = "SELECT COUNT(*) FROM productos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int total = 0;

            if(rs.next()){
                total = rs.getInt(1);
            }

            rs.close();
            st.close();
            con.close();

            return total;

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public Producto buscarPorId(int id) {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM productos WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Producto producto = new Producto();

                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                rs.close();
                ps.close();
                con.close();

                return producto;
            }

            rs.close();
            ps.close();
            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Producto> obtenerProductosTabla() {

        List<Producto> productos = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM productos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Producto producto = new Producto();

                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));

                productos.add(producto);
            }
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

}