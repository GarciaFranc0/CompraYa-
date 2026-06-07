package service;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioService {

    public void registrarUsuario(String nombre, String email, String password) {

        try {
            Connection con = Conexion.conectar();

            String sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();

            System.out.println("Usuario registrado");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("nombre") + " - " +
                                rs.getString("email")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerUsuarios() {

        StringBuilder usuarios = new StringBuilder();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                usuarios.append(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("nombre")
                                + " - "
                                + rs.getString("email")
                                + "\n"
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios.toString();
    }
}
