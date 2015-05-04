package sample;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.*;
        import javafx.scene.control.Button;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.*;
        import sample.User;

        import javafx.scene.control.TableColumn;

        import javafx.event.ActionEvent;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.Statement;

public class Controller {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/new_schema";
    static final String USER = "root";
    static final String PASS = "root";

    private ObservableList<User> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<User> tableUsers;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> infoColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;


    // инициализируем форму данными
    @FXML
    private void initialize() {
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<User, String>("info"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));

        tableUsers.setItems(usersData);
    }

    @FXML
    public void editWindow(ActionEvent actionEvent) {
        try {
            Stage secondStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
            secondStage.setTitle("Some shitty Window");
            secondStage.setMinWidth(370d);
            secondStage.setMinHeight(170d);
            secondStage.setScene(new Scene(root));
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            secondStage.show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }




    // подготавливаем данные для таблицы
    private void initData() {

        Connection conn = null;
        Statement stmt = null;
        String sql = "SELECT id,info,phone FROM new_schema.table";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User s = new User(rs.getInt("id"),rs.getString("info"),rs.getString("phone"));
                usersData.add(s);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

       // System.out.println("Done!");
    }

}
