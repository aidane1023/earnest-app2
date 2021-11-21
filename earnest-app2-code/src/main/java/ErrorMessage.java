/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;


public class ErrorMessage {

    @FXML
    public Boolean invalidInputCheck(String serialNumber, String name, Double value) {
        //Ensure parameters are met
        return serialFormat(serialNumber) && name.length() >= 2 && name.length() <= 256 && value >= 0;
    }

    public void invalidSelection() {
        //Present a popup error when no selection is made
        SelectionPopup.display();
    }

    private boolean serialFormat(String serialNumber) {
            if (serialNumber.length() == 13) {
                return serialNumber.matches("[a-zA-Z]-[a-zA-Z_0-9]+-[a-zA-Z_0-9]+-[a-zA-Z_0-9]+");
            }
            else {
                return false;
            }
    }
}
