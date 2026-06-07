import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import service.ProductoService;
import service.UsuarioService;
import service.PedidoService;
import javafx.scene.control.TextField;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        root.setSpacing(15);
        root.setStyle(
                "-fx-padding: 20;" +
                        "-fx-alignment: center;"
        );


        Label titulo = new Label("CompraYa");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button btnProductos = new Button("📦 Ver Productos");
        Button btnUsuarios = new Button("👤 Ver Usuarios");
        Button btnPedidos = new Button("🛒 Ver Pedidos");
        Button btnRegistrarUsuario = new Button("➕ Registrar Usuario");
        Button btnAgregarProducto = new Button("➕ Agregar Producto");

        btnAgregarProducto.setPrefWidth(200);
        btnRegistrarUsuario.setPrefWidth(200);
        btnProductos.setPrefWidth(200);
        btnUsuarios.setPrefWidth(200);
        btnPedidos.setPrefWidth(200);

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

        btnRegistrarUsuario.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootRegistro = new VBox(10);
            rootRegistro.setStyle("-fx-padding: 15;");

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");

            TextField txtEmail = new TextField();
            txtEmail.setPromptText("Email");

            PasswordField txtPassword = new PasswordField();
            txtPassword.setPromptText("Password");

            Button btnGuardar = new Button("Guardar Usuario");

            btnGuardar.setOnAction(ev -> {

                UsuarioService service = new UsuarioService();

                service.registrarUsuario(
                        txtNombre.getText(),
                        txtEmail.getText(),
                        txtPassword.getText()
                );

                ventana.close();
            });

            rootRegistro.getChildren().addAll(
                    txtNombre,
                    txtEmail,
                    txtPassword,
                    btnGuardar
            );

            Scene scene = new Scene(rootRegistro, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Registrar Usuario");
            ventana.show();
        });

        btnAgregarProducto.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootProducto = new VBox(10);
            rootProducto.setStyle("-fx-padding: 15;");

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");

            TextField txtPrecio = new TextField();
            txtPrecio.setPromptText("Precio");

            TextField txtStock = new TextField();
            txtStock.setPromptText("Stock");

            Button btnGuardar = new Button("Guardar Producto");

            btnGuardar.setOnAction(ev -> {

                ProductoService service = new ProductoService();

                service.registrarProducto(
                        txtNombre.getText(),
                        Double.parseDouble(txtPrecio.getText()),
                        Integer.parseInt(txtStock.getText())
                );

                ventana.close();
            });

            rootProducto.getChildren().addAll(
                    txtNombre,
                    txtPrecio,
                    txtStock,
                    btnGuardar
            );

            Scene scene = new Scene(rootProducto, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Agregar Producto");
            ventana.show();
        });

        root.getChildren().addAll(
                titulo,
                btnRegistrarUsuario,
                btnAgregarProducto,
                btnProductos,
                btnUsuarios,
                btnPedidos
        );

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("CompraYa");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}