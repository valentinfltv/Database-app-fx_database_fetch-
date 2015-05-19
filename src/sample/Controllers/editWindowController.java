package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;

import sample.Functionality.logic;

/**
 * Created by IamFabulous on 5/4/2015.
 */

public class editWindowController extends logic {

    @FXML
    public Button Add, ClearAll, Cancel;
    public TextField info, phone;
    public Label alertLabel;

    @FXML
    public void closeEditWindow(ActionEvent ev) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clearAll(ActionEvent ev) {
        info.setText("");
        phone.setText("");
    }

    @FXML
    private void add(ActionEvent ev) {

        if (info.getText().equals("") && phone.getText().equals("")) {
            alertLabel.setText("Insert info and phone");
//
        } else {
            alertLabel.setText("");

            createAddPreparedStatement("INSERT INTO new_schema.table (info,phone) VALUES (?,?)", info.getText(), phone.getText());

            alertLabel.setText("Done");
            info.setText("");
            phone.setText("");

        }

    }
}

