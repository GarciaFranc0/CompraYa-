package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:sqlite:compraya.db";

    public static Connection conectar() {
        try {
            System.out.println("URL: " + URL);
            System.out.println("Directorio actual: " + System.getProperty("user.dir"));

            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}