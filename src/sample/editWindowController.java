package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by IamFabulous on 5/4/2015.
 */
public class editWindowController {

    @FXML
    public Button Cancel;

    @FXML
    public void closeEditWindow(ActionEvent ev){
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }
}
