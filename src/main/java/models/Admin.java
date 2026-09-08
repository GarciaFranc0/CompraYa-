package models;

public class Admin extends Usuario {

    public Admin() {
        super();
    }

    public Admin(int id, String nombre, String email, String password) {
        super(id, nombre, email, password, "ADMIN");
    }
}