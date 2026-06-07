import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ProductoService;
import service.UsuarioService;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        Button btnProductos = new Button("Ver Productos");
        Button btnUsuarios = new Button("Ver Usuarios");
        Button btnPedidos = new Button("Ver Pedidos");

        btnProductos.setOnAction(e -> {

            ProductoService service = new ProductoService();

            Stage ventana = new Stage();

            VBox rootProductos = new VBox(10);

            Label productos = new Label(
                    service.obtenerProductos()
            );

            rootProductos.getChildren().add(productos);

            Scene sceneProductos = new Scene(
                    rootProductos,
                    400,
                    300
            );

            ventana.setScene(sceneProductos);
            ventana.setTitle("Productos");
            ventana.show();
        });

        btnUsuarios.setOnAction(e -> {
            UsuarioService service = new UsuarioService();
            service.listarUsuarios();
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