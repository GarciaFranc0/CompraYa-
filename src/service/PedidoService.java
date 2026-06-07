package service;

import database.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedidos.toString();
    }
}
