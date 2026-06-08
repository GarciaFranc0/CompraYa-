package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:sqlite:compraya.db";

    public static Connection conectar() {
        try {
            System.out.println("URL: " + URL);
            System.out.println("Directorio actual: " + System.getProperty("user.dir"));

            File archivo = new File("compraya.db");
            System.out.println(archivo.getAbsolutePath());
            System.out.println(archivo.exists());

            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}