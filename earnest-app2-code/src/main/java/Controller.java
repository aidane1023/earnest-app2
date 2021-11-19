/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.net.URL;
import java.util.*;

public class Controller {
    @FXML
    Button add;
    @FXML
    Button delete;
    @FXML
    Button edit;
    @FXML
    Button clear;
    @FXML
    Button search;
    @FXML
    MenuButton sort;
    @FXML
    MenuButton manual;
    @FXML
    MenuButton load;
    @FXML
    MenuButton save;
    @FXML
    TextField serialNumber;
    @FXML
    TextField name;
    @FXML
    TextField value;
    @FXML
    TableView<Item> List;
    @FXML
    ObservableList<Item> inventoryList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set all necessary visual adjustments
    }

    //Controller will contain all call parameters
    //Allows for easier testing while only needing one class to control GUI

    public void newItem () {
        //Collect data from textFileds
        //Call add function from Item
        //Check against format parameters
        //If fails check call error message
        //Else update table and List
    }
    public void deleteItem() {
        //Call delete function from Item class
        //Update table
    }

    public void editItem() {
        //Call edit function from Item class
        //Update table
    }

    public void clearList() {
        //Call clear function from List class
        //Update table
    }

    public void searchList() {
        //Call search function from List class
        //Update table
    }

    public void sortList() {
        //Take menuItem selection as parameter
        //Use parameter in call for sort from List class
        //Update table
    }

    public void manual() {
        //Open Manual
    }

    public void saveList() {
        //Call save function from List class
        //Update table
    }

    public void loadList() {
        //Call load function from List class
        //Update table
    }
}
