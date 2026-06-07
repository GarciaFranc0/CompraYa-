import service.UsuarioService;

public class Main {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        usuarioService.registrarUsuario(
                "Franco",
                "franco@mail.com",
                "1234"
        );
    }
}