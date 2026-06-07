import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import service.ProductoService;
import service.UsuarioService;
import service.PedidoService;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        Button btnProductos = new Button("Ver Productos");
        Button btnUsuarios = new Button("Ver Usuarios");
        Button btnPedidos = new Button("Ver Pedidos");

        // PRODUCTOS
        btnProductos.setOnAction(e -> {

            ProductoService service = new ProductoService();

            Stage ventana = new Stage();

            VBox rootProductos = new VBox(10);

            Label lblProductos =
                    new Label(service.obtenerProductos());

            lblProductos.setWrapText(true);

            rootProductos.getChildren().add(lblProductos);

            Scene sceneProductos =
                    new Scene(rootProductos, 500, 300);

            ventana.setScene(sceneProductos);
            ventana.setTitle("Productos");
            ventana.show();
        });

        // USUARIOS
        btnUsuarios.setOnAction(e -> {

            System.out.println("ENTRO AL BOTON");

            UsuarioService service = new UsuarioService();

            String texto = service.obtenerUsuarios();

            System.out.println("DEVUELVE:");
            System.out.println(texto);

            Stage ventana = new Stage();

            VBox rootUsuarios = new VBox(10);

            Label lblUsuarios = new Label(texto);

            rootUsuarios.getChildren().add(lblUsuarios);

            Scene sceneUsuarios = new Scene(rootUsuarios, 500, 300);

            ventana.setScene(sceneUsuarios);
            ventana.setTitle("Usuarios");
            ventana.show();
        });

        // PEDIDOS
        btnPedidos.setOnAction(e -> {

            PedidoService service = new PedidoService();

            Stage ventana = new Stage();

            VBox rootPedidos = new VBox(10);

            Label lblPedidos =
                    new Label(service.obtenerPedidos());

            lblPedidos.setWrapText(true);

            rootPedidos.getChildren().add(lblPedidos);

            Scene scenePedidos =
                    new Scene(rootPedidos, 500, 300);

            ventana.setScene(scenePedidos);
            ventana.setTitle("Pedidos");
            ventana.show();
        });

        root.getChildren().addAll(
                btnProductos,
                btnUsuarios,
                btnPedidos
        );

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("CompraYa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}