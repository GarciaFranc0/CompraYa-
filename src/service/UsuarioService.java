package service;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.Usuario;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> obtenerUsuariosLista() {

        List<String> usuarios = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                usuarios.add(
                        rs.getInt("id")
                                + " - "
                                + rs.getString("nombre")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public void eliminarUsuario(int id) {

        try {
            Connection con = Conexion.conectar();

            String sql = "DELETE FROM usuarios WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerUsuariosTabla() {

        List<Usuario> usuarios = new ArrayList<>();

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));

                usuarios.add(usuario);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public void editarUsuario(
            int id,
            String nombre,
            String email,
            String password,
            String rol
    ) {

        try {
            Connection con = Conexion.conectar();

            String sql = """
                UPDATE usuarios
                SET nombre = ?, email = ?, password = ?, rol = ?
                WHERE id = ?
                """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, rol);
            ps.setInt(5, id);

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}