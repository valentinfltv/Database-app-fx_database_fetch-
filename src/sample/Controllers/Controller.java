package sample.Controllers;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.*;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.*;
        import sample.User;
        import javafx.event.ActionEvent;

        import sample.Functionality.logic;

public class Controller extends logic{

    private ObservableList<User> emptyList = FXCollections.observableArrayList();
    private ObservableList<User> usersData = FXCollections.observableArrayList();
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TextField infoTextField, phoneTextField;
    @FXML
    private Label selectedIndexLabel;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> infoColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    // инициализация формы данными
    @FXML
    private void initialize() {
        emptyList = null;
        usersData = initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        infoColumn.setCellValueFactory(new PropertyValueFactory<User, String>("info"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));

        tableUsers.setItems(usersData);
    }

//    //searching in database
//    @FXML
//    public void searching() {
//        tableUsers.setItems(emptyList);
//
//        if (infoTextField.equals("") && phoneTextField.equals("")) {
//            //label.setText("insert something")
//        } else {
//
//            if (!infoTextField.equals("") && !phoneTextField.equals("")) {
//                for (User u : usersData) {
//                    if (u.getInfo().equals(infoTextField.getText()) && u.getPhone().equals(phoneTextField.getText())) {
//                        emptyList.add(u);
//                        tableUsers.setItems(emptyList);
//                    }
//                }
//
//            } else{
//                if (!infoTextField.equals("")) {
//                    for (User u : usersData) {
//                        if (u.getInfo().equals(infoTextField.getText())) {
//                            System.out.println("true");
//                            emptyList.add(u);
//                            tableUsers.setItems(emptyList);
//                        }
//
//                }
//                    if (!phoneTextField.equals("")) {
//                        for (User u : usersData) {
//                            if (u.getPhone().equals(phoneTextField.getText())) {
//                                emptyList.add(u);
//                                tableUsers.setItems(emptyList);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    //создание модального окна при нажатии кнопки
    @FXML
    public void editWindow(ActionEvent actionEvent) {
        try {
            Stage secondStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/Fxml/sample2.fxml"));
            secondStage.setTitle("Some shitty Window");
            secondStage.setMinWidth(370d);
            secondStage.setMinHeight(170d);
            secondStage.setScene(new Scene(root));
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            secondStage.show();

            //если окно закрыто - обновление выведенной таблицы
            secondStage.setOnHiding(windowEvent -> {
                usersData.removeAll(usersData);
                tableUsers.setItems(usersData);
                initialize();
            });
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = tableUsers.getSelectionModel().getSelectedIndex();
        int index = (tableUsers.getItems().get(selectedIndex)).getId();
        tableUsers.getItems().remove(selectedIndex);

        delete(index);

        usersData.removeAll(usersData);
        tableUsers.setItems(usersData);
        initialize();
    }

    @FXML
    private void deleteAll() {

        deleteAllUsers();

        usersData.removeAll(usersData);
        tableUsers.setItems(usersData);
        initialize();
    }




}
