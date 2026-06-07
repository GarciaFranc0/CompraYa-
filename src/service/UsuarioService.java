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

            String sql =
                    "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, "CLIENTE");

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
            String password
    ) {

        try {
            Connection con = Conexion.conectar();

            String sql = """
            UPDATE usuarios
            SET nombre = ?, email = ?, password = ?
            WHERE id = ?
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, id);

            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);

            int filas = ps.executeUpdate();

            System.out.println("Filas modificadas: " + filas);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorEmail(String email) {

        try {
            Connection con = Conexion.conectar();

            String sql = "SELECT * FROM usuarios WHERE email = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setRol(rs.getString("rol"));

                con.close();

                return usuario;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean validarLogin(
            String email,
            String password
    ) {
        try {
            Connection con = Conexion.conectar();

            String sql =
                    "SELECT * FROM usuarios WHERE email = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            boolean existe = rs.next();

            con.close();

            return existe;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}