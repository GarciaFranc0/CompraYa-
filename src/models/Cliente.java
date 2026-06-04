package models;

public class Cliente extends Usuario {

    public Cliente() {
        super();
    }

    public Cliente(int id, String nombre, String email, String password) {
        super(id, nombre, email, password, "CLIENTE");
    }
}
