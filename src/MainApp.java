import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Producto;
import service.ProductoService;
import service.UsuarioService;
import service.PedidoService;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import models.Usuario;
import models.Pedido;
import models.Producto;

import java.util.List;


public class MainApp extends Application {

    private String emailLogueado;

    @Override
    public void start(Stage stage) {

        VBox rootLogin = new VBox(10);

        rootLogin.setStyle("-fx-padding: 20;" + "-fx-alignment: center;");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");

        Button btnLogin = new Button("Iniciar Sesión");
        Button btnRegistrarse = new Button("Registrarse");

        btnLogin.setOnAction(e -> {

            UsuarioService service = new UsuarioService();

            String email = txtEmail.getText();

            boolean login = service.validarLogin(email, txtPassword.getText());

            if(login){
                emailLogueado = email;
                Usuario usuario =
                        service.buscarPorEmail(email);

                if(usuario.getRol().equals("ADMIN")){
                    abrirMenuPrincipal(stage);
                }else{
                    abrirMenuCliente(stage);
                }

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email o contraseña incorrectos");
                alert.showAndWait();
            }
        });
        btnRegistrarse.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootRegistro = new VBox(10);
            rootRegistro.setStyle("-fx-padding: 15;");

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nombre");

            TextField txtEmailRegistro = new TextField();
            txtEmailRegistro.setPromptText("Email");

            PasswordField txtPasswordRegistro = new PasswordField();
            txtPasswordRegistro.setPromptText("Password");

            Button btnGuardar = new Button("Registrarse");

            btnGuardar.setOnAction(ev -> {

                UsuarioService service = new UsuarioService();

                service.registrarUsuario(txtNombre.getText(), txtEmailRegistro.getText(), txtPasswordRegistro.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Usuario registrado correctamente");
                alert.showAndWait();

                ventana.close();
            });


            rootRegistro.getChildren().addAll(txtNombre, txtEmailRegistro, txtPasswordRegistro, btnGuardar);

            Scene scene = new Scene(rootRegistro, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Registrarse");
            ventana.show();
        });

        rootLogin.getChildren().addAll(txtEmail, txtPassword, btnLogin, btnRegistrarse);

        Scene scene = new Scene(rootLogin, 300, 200);

        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();


    }

    private void abrirMenuPrincipal(Stage stage) {

        VBox root = new VBox(10);

        root.setSpacing(15);
        root.setStyle("-fx-padding: 20;" + "-fx-alignment: center;");

        Label titulo = new Label("CompraYa");

        Button btnModuloUsuarios = new Button("👤 Usuarios");
        Button btnModuloProductos = new Button("📦 Productos");
        Button btnModuloPedidos = new Button("🛒 Pedidos");

        Button btnProductos = new Button("📦 Ver Productos");
        Button btnUsuarios = new Button("👤 Ver Usuarios");
        Button btnPedidos = new Button("🛒 Ver Pedidos");

        Button btnRegistrarUsuario = new Button("➕ Registrar Usuario");
        Button btnAgregarProducto = new Button("➕ Agregar Producto");
        Button btnCrearPedido = new Button("➕ Crear Pedido");
        Button btnEliminarProducto = new Button("🗑 Eliminar Producto");
        Button btnEliminarUsuario = new Button("🗑 Eliminar Usuario");
        Button btnEditarProducto = new Button("✏ Editar Producto");
        Button btnEditarUsuario = new Button("✏ Editar Usuario");


        btnEditarUsuario.setPrefWidth(200);
        btnEditarProducto.setPrefWidth(200);
        btnEliminarUsuario.setPrefWidth(200);
        btnEliminarProducto.setPrefWidth(200);
        btnCrearPedido.setPrefWidth(200);
        btnAgregarProducto.setPrefWidth(200);
        btnRegistrarUsuario.setPrefWidth(200);
        btnProductos.setPrefWidth(200);
        btnUsuarios.setPrefWidth(200);
        btnPedidos.setPrefWidth(200);

        btnModuloUsuarios.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootUsuarios = new VBox(10);
            rootUsuarios.setStyle("-fx-padding: 20;" + "-fx-alignment: center;");

            rootUsuarios.getChildren().addAll(btnUsuarios, btnRegistrarUsuario, btnEditarUsuario, btnEliminarUsuario);

            Scene scene = new Scene(rootUsuarios, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Gestión de Usuarios");
            ventana.show();
        });

        btnModuloProductos.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootProductos = new VBox(10);
            rootProductos.setStyle("-fx-padding: 20;" + "-fx-alignment: center;");

            rootProductos.getChildren().addAll(btnProductos, btnAgregarProducto, btnEditarProducto, btnEliminarProducto);

            Scene scene = new Scene(rootProductos, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Gestión de Productos");
            ventana.show();
        });

        btnModuloPedidos.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootPedidos = new VBox(10);
            rootPedidos.setStyle("-fx-padding: 20;" + "-fx-alignment: center;");

            rootPedidos.getChildren().addAll(btnPedidos, btnCrearPedido);

            Scene scene = new Scene(rootPedidos, 300, 200);

            ventana.setScene(scene);
            ventana.setTitle("Gestión de Pedidos");
            ventana.show();
        });

        // PRODUCTOS
        btnProductos.setOnAction(e -> {

            ProductoService service = new ProductoService();

            Stage ventana = new Stage();

            VBox rootProductos = new VBox(10);

            TableView<Producto> tabla = new TableView<>();

            TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("id")
            );

            TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
            colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

            tabla.getColumns().addAll(colId, colNombre, colPrecio, colStock);

            tabla.setItems(FXCollections.observableArrayList(service.obtenerProductosTabla()));

            rootProductos.getChildren().add(tabla);

            Scene scene = new Scene(rootProductos, 700, 400);

            ventana.setScene(scene);
            ventana.setTitle("Productos");
            ventana.show();
        });

        // USUARIOS
        btnUsuarios.setOnAction(e -> {

            UsuarioService service = new UsuarioService();

            Stage ventana = new Stage();

            VBox rootUsuarios = new VBox(10);

            TableView<Usuario> tabla = new TableView<>();

            TableColumn<Usuario, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            TableColumn<Usuario, String> colEmail = new TableColumn<>("Email");
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            tabla.getColumns().addAll(colId, colNombre, colEmail);

            tabla.setItems(FXCollections.observableArrayList(service.obtenerUsuariosTabla()));

            rootUsuarios.getChildren().add(tabla);

            Scene sceneUsuarios = new Scene(rootUsuarios, 600, 400);

            ventana.setScene(sceneUsuarios);
            ventana.setTitle("Usuarios");
            ventana.show();
        });

        // PEDIDOS
        btnPedidos.setOnAction(e -> {

            PedidoService service = new PedidoService();

            Stage ventana = new Stage();

            VBox rootPedidos = new VBox(10);

            TableView<Pedido> tabla = new TableView<>();

            TableColumn<Pedido, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<Pedido, String> colUsuario = new TableColumn<>("Usuario");

            colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));

            TableColumn<Pedido, String> colProducto = new TableColumn<>("Producto");

            colProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

            TableColumn<Pedido, Integer> colCantidad = new TableColumn<>("Cantidad");
            colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

            TableColumn<Pedido, String> colEstado = new TableColumn<>("Estado");
            colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

            tabla.getColumns().addAll(colId, colUsuario, colProducto, colCantidad, colEstado);

            tabla.setItems(FXCollections.observableArrayList(service.obtenerPedidosTabla()));

            rootPedidos.getChildren().add(tabla);

            Scene scene = new Scene(rootPedidos, 800, 400);

            ventana.setScene(scene);
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

                service.registrarUsuario(txtNombre.getText(), txtEmail.getText(), txtPassword.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Usuario registrado correctamente");
                alert.showAndWait();
                ventana.close();
            });

            rootRegistro.getChildren().addAll(txtNombre, txtEmail, txtPassword, btnGuardar);

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

                service.registrarProducto(txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Producto registrado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootProducto.getChildren().addAll(txtNombre, txtPrecio, txtStock, btnGuardar);

            Scene scene = new Scene(rootProducto, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Agregar Producto");
            ventana.show();
        });

        btnCrearPedido.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootPedido = new VBox(10);
            rootPedido.setStyle("-fx-padding: 15;");

            UsuarioService usuarioService = new UsuarioService();
            ProductoService productoService = new ProductoService();

            ComboBox<String> cmbUsuarios = new ComboBox<>();
            cmbUsuarios.setPromptText("Seleccionar Usuario");

            cmbUsuarios.getItems().addAll(usuarioService.obtenerUsuariosLista());

            ComboBox<String> cmbProductos = new ComboBox<>();
            cmbProductos.setPromptText("Seleccionar Producto");

            cmbProductos.getItems().addAll(productoService.obtenerProductosLista());

            TextField txtCantidad = new TextField();
            txtCantidad.setPromptText("Cantidad");

            Button btnGuardar = new Button("Crear Pedido");

            btnGuardar.setOnAction(ev -> {

                PedidoService service = new PedidoService();

                String usuarioSeleccionado = cmbUsuarios.getValue();
                String productoSeleccionado = cmbProductos.getValue();

                int idUsuario = Integer.parseInt(usuarioSeleccionado.split(" - ")[0]);

                int idProducto = Integer.parseInt(productoSeleccionado.split(" - ")[0]);

                service.crearPedido(idUsuario, idProducto, Integer.parseInt(txtCantidad.getText()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Pedido creado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootPedido.getChildren().addAll(cmbUsuarios, cmbProductos, txtCantidad, btnGuardar);

            Scene scene = new Scene(rootPedido, 300, 250);

            ventana.setScene(scene);
            ventana.setTitle("Crear Pedido");
            ventana.show();
        });

        btnEliminarUsuario.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootEliminar = new VBox(10);
            rootEliminar.setStyle("-fx-padding: 15;");

            UsuarioService service = new UsuarioService();

            ComboBox<String> cmbUsuarios = new ComboBox<>();
            cmbUsuarios.setPromptText("Seleccionar Usuario");

            cmbUsuarios.getItems().addAll(service.obtenerUsuariosLista());

            Button btnEliminar = new Button("Eliminar Usuario");

            btnEliminar.setOnAction(ev -> {

                String seleccionado = cmbUsuarios.getValue();

                int id = Integer.parseInt(seleccionado.split(" - ")[0]);

                service.eliminarUsuario(id);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Usuario eliminado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootEliminar.getChildren().addAll(cmbUsuarios, btnEliminar);

            Scene scene = new Scene(rootEliminar, 300, 150);

            ventana.setScene(scene);
            ventana.setTitle("Eliminar Usuario");
            ventana.show();
        });

        btnEditarProducto.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootEditar = new VBox(10);
            rootEditar.setStyle("-fx-padding: 15;");

            ProductoService service = new ProductoService();

            ComboBox<String> cmbProductos = new ComboBox<>();
            cmbProductos.setPromptText("Seleccionar Producto");

            cmbProductos.getItems().addAll(service.obtenerProductosLista());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nuevo Nombre");

            TextField txtPrecio = new TextField();
            txtPrecio.setPromptText("Nuevo Precio");

            TextField txtStock = new TextField();
            txtStock.setPromptText("Nuevo Stock");

            Button btnGuardar = new Button("Guardar Cambios");

            btnGuardar.setOnAction(ev -> {

                String seleccionado = cmbProductos.getValue();

                int id = Integer.parseInt(seleccionado.split(" - ")[0]);

                service.editarProducto(id, txtNombre.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Producto actualizado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootEditar.getChildren().addAll(cmbProductos, txtNombre, txtPrecio, txtStock, btnGuardar);

            Scene scene = new Scene(rootEditar, 350, 250);

            ventana.setScene(scene);
            ventana.setTitle("Editar Producto");
            ventana.show();
        });

        btnEditarUsuario.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootEditar = new VBox(10);
            rootEditar.setStyle("-fx-padding: 15;");

            UsuarioService service = new UsuarioService();

            ComboBox<String> cmbUsuarios = new ComboBox<>();
            cmbUsuarios.setPromptText("Seleccionar Usuario");

            cmbUsuarios.getItems().addAll(service.obtenerUsuariosLista());

            TextField txtNombre = new TextField();
            txtNombre.setPromptText("Nuevo Nombre");

            TextField txtEmail = new TextField();
            txtEmail.setPromptText("Nuevo Email");

            TextField txtPassword = new TextField();
            txtPassword.setPromptText("Nuevo Password");

            Button btnGuardar = new Button("Guardar Cambios");

            btnGuardar.setOnAction(ev -> {

                String seleccionado = cmbUsuarios.getValue();

                int id = Integer.parseInt(seleccionado.split(" - ")[0]);

                service.editarUsuario(id, txtNombre.getText(), txtEmail.getText(), txtPassword.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Usuario actualizado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootEditar.getChildren().addAll(cmbUsuarios, txtNombre, txtEmail, txtPassword, btnGuardar);

            Scene scene = new Scene(rootEditar, 350, 300);

            ventana.setScene(scene);
            ventana.setTitle("Editar Usuario");
            ventana.show();
        });

        btnEliminarProducto.setOnAction(e -> {

            Stage ventana = new Stage();

            VBox rootEliminar = new VBox(10);
            rootEliminar.setStyle("-fx-padding: 15;");

            ProductoService service = new ProductoService();

            ComboBox<String> cmbProductos = new ComboBox<>();
            cmbProductos.setPromptText("Seleccionar Producto");

            cmbProductos.getItems().addAll(
                    service.obtenerProductosLista()
            );

            Button btnEliminar = new Button("Eliminar Producto");

            btnEliminar.setOnAction(ev -> {

                String seleccionado = cmbProductos.getValue();

                int id = Integer.parseInt(seleccionado.split(" - ")[0]);

                service.eliminarProducto(id);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Producto eliminado correctamente");
                alert.showAndWait();

                ventana.close();
            });

            rootEliminar.getChildren().addAll(cmbProductos, btnEliminar);

            Scene scene = new Scene(rootEliminar, 300, 150);

            ventana.setScene(scene);
            ventana.setTitle("Eliminar Producto");
            ventana.show();

        });

        root.getChildren().addAll(titulo, btnModuloUsuarios, btnModuloProductos, btnModuloPedidos);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("CompraYa");
        stage.setScene(scene);
    }
    private void abrirMenuCliente(Stage stage){

        VBox root = new VBox(15);

        root.setStyle(
                "-fx-padding: 20;" +
                        "-fx-alignment: center;"
        );

        Label titulo = new Label("🛒 CompraYa");

        titulo.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        Button btnProductos = new Button("📦 Ver Productos");
        Button btnCrearPedido = new Button("🛒 Crear Pedido");
        Button btnMisPedidos = new Button("📋 Mis Pedidos");

        btnProductos.setPrefWidth(200);
        btnCrearPedido.setPrefWidth(200);
        btnMisPedidos.setPrefWidth(200);

        btnProductos.setOnAction(e -> mostrarProductos());
        btnCrearPedido.setOnAction(e -> mostrarCrearPedido());
        btnMisPedidos.setOnAction(e -> mostrarMisPedidos());

        root.getChildren().addAll(
                titulo,
                btnProductos,
                btnCrearPedido,
                btnMisPedidos
        );

        Scene scene = new Scene(root, 500, 350);

        stage.setTitle("CompraYa - Cliente");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarProductos() {

        ProductoService service = new ProductoService();

        Stage ventana = new Stage();

        VBox rootProductos = new VBox(10);

        TableView<Producto> tabla = new TableView<>();

        TableColumn<Producto, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tabla.getColumns().addAll(colId, colNombre, colPrecio, colStock);

        tabla.setItems(FXCollections.observableArrayList(service.obtenerProductosTabla()));

        rootProductos.getChildren().add(tabla);

        Scene scene = new Scene(rootProductos, 700, 400);

        ventana.setScene(scene);
        ventana.setTitle("Productos");
        ventana.show();
    }

    private void mostrarCrearPedido() {

        Stage ventana = new Stage();

        VBox rootPedido = new VBox(10);
        rootPedido.setStyle("-fx-padding: 15;");

        UsuarioService usuarioService = new UsuarioService();
        ProductoService productoService = new ProductoService();

        ComboBox<String> cmbProductos = new ComboBox<>();
        cmbProductos.setPromptText("Seleccionar Producto");

        cmbProductos.getItems().addAll(productoService.obtenerProductosLista());

        TextField txtCantidad = new TextField();
        txtCantidad.setPromptText("Cantidad");


        Button btnGuardar = new Button("Crear Pedido");

        btnGuardar.setOnAction(ev -> {

            PedidoService service = new PedidoService();
            Usuario usuario = usuarioService.buscarPorEmail(emailLogueado);

            int idUsuario = usuario.getId();

            String productoSeleccionado = cmbProductos.getValue();

            int idProducto = Integer.parseInt(productoSeleccionado.split(" - ")[0]);

            Producto producto = productoService.buscarPorId(idProducto);
            int cantidad = Integer.parseInt(txtCantidad.getText());

            if (cantidad > producto.getStock()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No hay stock suficiente");

                alert.showAndWait();

                return;
            }
            if (cantidad <= 0) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("La cantidad debe ser mayor a 0");
                alert.showAndWait();

                return;
            }

            service.crearPedido(idUsuario, idProducto, cantidad);

            productoService.actualizarStock(idProducto, producto.getStock() - cantidad);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Pedido creado correctamente");
            alert.showAndWait();

            ventana.close();
        });

        rootPedido.getChildren().addAll(cmbProductos, txtCantidad, btnGuardar);

        Scene scene = new Scene(rootPedido, 300, 250);

        ventana.setScene(scene);
        ventana.setTitle("Crear Pedido");
        ventana.show();
    }

    private void mostrarMisPedidos() {

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario =
                usuarioService.buscarPorEmail(emailLogueado);

        PedidoService pedidoService = new PedidoService();

        List<Pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(usuario.getId());

        Stage ventana = new Stage();

        VBox root = new VBox(10);

        TableView<Pedido> tabla = new TableView<>();

        TableColumn<Pedido, Integer> colId =
                new TableColumn<>("ID");
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Pedido, String> colProducto =
                new TableColumn<>("Producto");
        colProducto.setCellValueFactory(
                new PropertyValueFactory<>("nombreProducto")
        );

        TableColumn<Pedido, Integer> colCantidad =
                new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        TableColumn<Pedido, String> colEstado =
                new TableColumn<>("Estado");
        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        tabla.getColumns().addAll(
                colId,
                colProducto,
                colCantidad,
                colEstado
        );

        tabla.setItems(
                FXCollections.observableArrayList(
                        pedidos
                )
        );

        root.getChildren().add(tabla);

        Scene scene = new Scene(root, 700, 400);

        ventana.setScene(scene);
        ventana.setTitle("Mis Pedidos");
        ventana.show();
    }


    public static void main(String[] args) {
        launch();
    }
}