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
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
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
    TextField serialNumberField = new TextField();
    @FXML
    TextField nameField = new TextField();
    @FXML
    TextField valueField = new TextField();
    @FXML
    TableView<Item> table;
    @FXML
    TableColumn<Item, String> serialColumn = new TableColumn<>();
    @FXML
    TableColumn<Item, String> nameColumn = new TableColumn<>();
    @FXML
    TableColumn<Item, Double> valueColumn = new TableColumn<>();

    ObservableList<Item> inventoryList = FXCollections.observableArrayList();

    Boolean editorGate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set all necessary visual adjustments
        serialColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
    }

    //Controller will contain all call parameters
    //Allows for easier testing while only needing one class to control GUI

    public void newItem (ActionEvent actionEvent) {
        ErrorMessage errorCheck = new ErrorMessage();
        //Take in items from textFields
        //Add item to TableView
        if (inventoryList.size() < 1024) {
            serialNumberField.setText(serialNumberField.getText());
            nameField.setText(nameField.getText());
            valueField.setText(valueField.getText());

            //Make Temp variables for easier conversions
            String tempSerialNumber = serialNumberField.getText();
            String tempName = nameField.getText();
            double tempDouble = Double.parseDouble(valueField.getText());

            //Confirm items meet restraints (else run errorMessage)
            if (errorCheck.invalidInputCheck()) {
                String tempValue = String.format("$%.2f", tempDouble);
                //Display item in observable table view
                inventoryList.add(new Item(tempSerialNumber, tempName, tempValue));
                table.setItems(inventoryList);
            }
        }
        //Update fields
        refresh();
    }

    public void deleteItem(ActionEvent actionEvent) {
        ErrorMessage errorCheck = new ErrorMessage();
        //Ensure an event is selected
        try {
            //Remove event from list
            inventoryList.remove(table.getSelectionModel().getSelectedItem());
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
        } catch(Exception e) {
            System.out.println("No selection");
            errorCheck.invalidSelection();
        }
        //Update fields
        refresh();
    }

    public void editItem(ActionEvent actionEvent) {
        Item item = new Item(null, null, null);
        ErrorMessage errorCheck = new ErrorMessage();
        //Requires a selection
        //Fill event fields
        try {
            serialNumberField.setText(table.getItems().get(table.getSelectionModel().getSelectedIndex()).getSerialNumber());
            serialNumberField.setText(inventoryList.get(table.getSelectionModel().getSelectedIndex()).getSerialNumber());
            nameField.setText(inventoryList.get(table.getSelectionModel().getSelectedIndex()).getName());
            valueField.setText((inventoryList.get(table.getSelectionModel().getSelectedIndex()).getValue()));
            editorGate = true;
        } catch (Exception e) {
            System.out.println("No Selection");
            errorCheck.invalidSelection();
        }
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
