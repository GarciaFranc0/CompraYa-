package service;

import database.Conexion;
import models.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    public void crearPedido(int idUsuario, int idProducto, int cantidad) {

        try {
            Connection con = Conexion.conectar();

            String sql = """
                INSERT INTO pedidos
                (id_usuario, id_producto, cantidad, estado)
                VALUES (?, ?, ?, ?)
                """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idUsuario);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setString(4, "Pendiente");

            ps.executeUpdate();

            System.out.println("Pedido creado");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarPedidos() {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM pedidos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        "Pedido " + rs.getInt("id") +
                                " | Usuario: " + rs.getInt("id_usuario") +
                                " | Producto: " + rs.getInt("id_producto") +
                                " | Cantidad: " + rs.getInt("cantidad") +
                                " | Estado: " + rs.getString("estado")
                );
            }

            rs.close();
            st.close(); // o ps.close()
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerPedidos() {

        StringBuilder pedidos = new StringBuilder();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM pedidos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                pedidos.append(
                        "Pedido "
                                + rs.getInt("id")
                                + " | Usuario: "
                                + rs.getInt("id_usuario")
                                + " | Producto: "
                                + rs.getInt("id_producto")
                                + " | Cantidad: "
                                + rs.getInt("cantidad")
                                + " | Estado: "
                                + rs.getString("estado")
                                + "\n"
                );
            }

            rs.close();
            st.close(); // o ps.close()
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos.toString();
    }

    public List<Pedido> obtenerPedidosPorUsuario(int idUsuario) {

        List<Pedido> pedidos = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = """
        SELECT p.id,
               pr.nombre AS producto,
               p.cantidad,
               p.estado
        FROM pedidos p
        JOIN productos pr ON p.id_producto = pr.id
        WHERE p.id_usuario = ?
        """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setNombreProducto(rs.getString("producto"));
                pedido.setCantidad(rs.getInt("cantidad"));
                pedido.setEstado(rs.getString("estado"));

                pedidos.add(pedido);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public List<Pedido> obtenerPedidosTabla() {

        List<Pedido> pedidos = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = """
            SELECT p.id,
                u.nombre AS usuario,
                pr.nombre AS producto,
                p.cantidad,
                p.estado
            FROM pedidos p
            JOIN usuarios u ON p.id_usuario = u.id
            JOIN productos pr ON p.id_producto = pr.id
            """;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setNombreUsuario(rs.getString("usuario"));
                pedido.setNombreProducto(rs.getString("producto"));
                pedido.setCantidad(rs.getInt("cantidad"));
                pedido.setEstado(rs.getString("estado"));

                pedidos.add(pedido);
            }
            System.out.println("FILAS TABLA: " + pedidos.size());
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public void actualizarEstadoPedido(int idPedido, String estado){

        try{
            Connection con = Conexion.conectar();

            String sql =
                    "UPDATE pedidos SET estado = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, estado);
            ps.setInt(2, idPedido);

            ps.executeUpdate();

            ps.close();
            con.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int contarPedidos(){

        try{
            Connection con = Conexion.conectar();

            String sql = "SELECT COUNT(*) FROM pedidos";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            int total = 0;

            if(rs.next()){
                total = rs.getInt(1);
            }

            System.out.println("TOTAL PEDIDOS: " + total);

            rs.close();
            st.close();
            con.close();

            return total;

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

}
