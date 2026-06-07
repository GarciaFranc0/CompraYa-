import service.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService();

        int opcion;

        do {

            System.out.println("\n=== COMPRA YA ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Ver usuarios");
            System.out.println("3. Ver productos");
            System.out.println("4. Crear pedido");
            System.out.println("5. Ver pedidos");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    usuarioService.registrarUsuario(nombre, email, password);
                    break;

                case 2:
                    usuarioService.listarUsuarios();
                    break;

                case 3:
                    productoService.listarProductos();
                    break;

                case 4:

                    System.out.print("ID Usuario: ");
                    int idUsuario = sc.nextInt();

                    System.out.print("ID Producto: ");
                    int idProducto = sc.nextInt();

                    System.out.print("Cantidad: ");
                    int cantidad = sc.nextInt();

                    pedidoService.crearPedido(idUsuario, idProducto, cantidad);
                    break;

                case 5:
                    pedidoService.listarPedidos();
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}