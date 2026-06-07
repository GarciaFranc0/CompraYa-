package service;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
