package service;

import database.Conexion;
import java.sql.Connection;
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
}