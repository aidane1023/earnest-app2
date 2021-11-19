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
    TextField serialNumberField;
    @FXML
    TextField nameField;
    @FXML
    TextField valueField;
    @FXML
    TableView<Item> List;
    @FXML
    ObservableList<Item> inventoryList;

    Boolean editorGate;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set all necessary visual adjustments
    }

    //Controller will contain all call parameters
    //Allows for easier testing while only needing one class to control GUI

    public void newItem (ActionEvent actionEvent) {
        Item item = new Item(null, null, 0.0);
        //Call add function from Item
        item.add();
        //Update fields
        refresh();
    }
    public void deleteItem(ActionEvent actionEvent) {
        Item item = new Item(null, null, 0.0);
        //Call delete function from Item class
        item.delete();
        //Update fields
        refresh();
    }

    public void editItem(ActionEvent actionEvent) {
        Item item = new Item(null, null, 0.0);
        //Call edit function from Item class
        item.edit();
        //Update fields
        refresh();
    }

    public void clearList(ActionEvent actionEvent) {
        List list = new List(null);
        //Call clear function from List class
        list.clear();
    }

    public void searchList(ActionEvent actionEvent) {
        List list = new List(null);
        //Call search function from List class
        list.search();
    }

    public void sortAZ(ActionEvent actionEvent) {
        //Pass parameter a-z
        sortList("AZ");
    }

    public void sort321(ActionEvent actionEvent) {
        //Pass parameter 321
        sortList("321");
    }

    public void sortSerial(ActionEvent actionEvent) {
        //Pass parameter serial
        sortList("Serial");
    }

    public void manual(ActionEvent actionEvent) {
        //Open Manual
    }

    public void saveList(ActionEvent actionEvent) {
        FileManagement file = new FileManagement();
        //Call search function from fileManagement class
        file.save();
    }

    public void loadList(ActionEvent actionEvent) {
        FileManagement file = new FileManagement();
        //Call search function from fileManagement class
        file.save();
    }

    private void refresh() {
        serialNumberField.setText(null);
        nameField.setText(null);
        valueField.setText(null);
    }

    public void sortList(String parameter) {
        List list = new List(null);
        //Take menuItem selection as parameter
        //Use parameter in call for sort from List class
        list.sort(parameter);
    }
}
