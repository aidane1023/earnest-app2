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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.ResourceBundle;

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
    MenuItem menuSortSerial;
    @FXML
    MenuItem menuSortName;
    @FXML
    MenuItem menuSortValue;
    @FXML
    MenuItem menuSortOriginal;
    @FXML
    MenuItem menuManual;
    @FXML
    MenuItem menuLoad;
    @FXML
    MenuItem menuSave;
    @FXML
    TextField serialNumberField;
    @FXML
    TextField nameField;
    @FXML
    TextField valueField;
    @FXML
    TableView<Item> table;
    @FXML
    TableColumn<Item, String> serialColumn = new TableColumn<>();
    @FXML
    TableColumn<Item, String> nameColumn = new TableColumn<>();
    @FXML
    TableColumn<Item, Double> valueColumn = new TableColumn<>();

    ObservableList<Item> inventoryList = FXCollections.observableArrayList();

    Boolean editorGate = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set all necessary visual adjustments
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    //Controller will contain all call parameters
    //Allows for easier testing while only needing one class to control GUI

    public void newItem (ActionEvent actionEvent) throws IOException {
        ErrorMessage errorCheck = new ErrorMessage();
        String tempSerialNumber = "";
        String tempName = "";
        double tempDouble = 0;
        //Take in items from textFields
        //Add item to TableView
        if (inventoryList.size() < 1024) {
            try {
                //Make Temp variables for easier conversions
                tempSerialNumber = serialNumberField.getText();
                tempName = nameField.getText();
                tempDouble = Double.parseDouble(valueField.getText());
            } catch (Exception e) {
                System.out.println("Empty field");
                refresh();
                InvalidPopup.display();
            }

            //Confirm items meet restraints (else run errorMessage)
            if (errorCheck.invalidInputCheck(tempSerialNumber, tempName, tempDouble, inventoryList)) {
                if (!editorGate) {
                    //Display item in observable table view
                    try {
                        inventoryList.add(new Item(tempSerialNumber, tempName, String.format("$%.2f",Double.parseDouble(valueField.getText()))));
                        table.setItems(inventoryList);
                    } catch (Exception e) {
                        System.out.println("invalid input");
                    }
                } else {
                    inventoryList.set(table.getSelectionModel().getSelectedIndex(), new Item(serialNumberField.getText(), nameField.getText(), valueField.getText()));
                    table.setItems(inventoryList);
                    editorGate = false;
                }
            } else {
                InvalidPopup.display();
            }
        }
        //Update fields
        refresh();
    }

    public void deleteItem(ActionEvent actionEvent) {
        //Ensure an event is selected
        try {
            //Remove event from list
            inventoryList.remove(table.getSelectionModel().getSelectedItem());
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
        } catch(Exception e) {
            System.out.println("No selection");
        }
        //Update fields
        refresh();
    }

    public void editItem(ActionEvent actionEvent) {
        //Requires a selection
        //Fill event fields
        try {
            serialNumberField.setText(inventoryList.get(table.getSelectionModel().getSelectedIndex()).getSerialNumber());
            nameField.setText(inventoryList.get(table.getSelectionModel().getSelectedIndex()).getName());
            String tempValueString = inventoryList.get(table.getSelectionModel().getSelectedIndex()).getValue().substring(1);
            valueField.setText(tempValueString);
            editorGate = true;
        } catch (Exception e) {
            System.out.println("No Selection");
        }
    }

    public void clearList(ActionEvent actionEvent) {
        List list = new List();
        //Call clear function from List class
        list.clear(inventoryList);
        //Update display
        table.setItems(inventoryList);
        refresh();
    }

    public void searchList(ActionEvent actionEvent) {
        ObservableList<Item> contains = FXCollections.observableArrayList();
        //Account for blank fields
        //Take in textFields as parameters
        //Search for all items that contain data
        for (Item item : inventoryList) {
            //Serial number contains check
            if (serialNumberField.getText() != null && item.getSerialNumber().contains(serialNumberField.getText())) {
                contains.add(new Item(item.getSerialNumber(), item.getName(), item.getValue()));
            }
            //Name contains check
            else if (nameField.getText() != null && item.getName().contains(nameField.getText())) {
                contains.add(new Item(item.getSerialNumber(), item.getName(), item.getValue()));
            }
            //Value contains check
            else if (valueField.getText() != null && item.getValue().contains(valueField.getText())) {
                contains.add(new Item(item.getSerialNumber(), item.getName(), item.getValue()));
            }
        }
        //Update table
        table.setItems(contains);
        //Update textFields
        refresh();
    }

    public void sortAZ(ActionEvent actionEvent) {
        ObservableList<Item> nameSort = FXCollections.observableArrayList();
        nameSort.addAll(inventoryList);
        nameSort.sort(new Comparator<Item>() {
            @Override
            public int compare(Item u1, Item u2) {
                return u1.getName().compareTo(u2.getName());
            }
        });
        table.setItems(nameSort);

    }

    public void sort321(ActionEvent actionEvent) {
        ObservableList<Item> valueSort = FXCollections.observableArrayList();
        valueSort.addAll(inventoryList);
        valueSort.sort(new Comparator<Item>() {
            @Override
            public int compare(Item u1, Item u2) {
                return u1.getValue().compareTo(u2.getValue());
            }
        });
        table.setItems(valueSort);
    }

    public void sortSerial(ActionEvent actionEvent) {
        ObservableList<Item> serialSort = FXCollections.observableArrayList();
        serialSort.addAll(inventoryList);
        serialSort.sort(new Comparator<Item>() {
            @Override
            public int compare(Item u1, Item u2) {
                return u1.getSerialNumber().compareTo(u2.getSerialNumber());
            }
        });
        table.setItems(serialSort);
    }

    public void sortOriginal(ActionEvent actionEvent) {
        table.setItems(inventoryList);
    }

    public void manual(ActionEvent actionEvent) {
        //Open Manual
        URL resource = getClass().getResource("User's Manual.pdf");
        if (Desktop.isDesktopSupported()) {
            try {
                assert resource != null;
                Desktop.getDesktop().open(Paths.get(resource.toURI()).toFile());
            } catch (IOException | URISyntaxException ex) {
                // no application registered for PDFs
            }
        }

    }

    public void saveList(ActionEvent actionEvent) {
        FileManagement writeFile = new FileManagement();
        //Call write function in file class
        writeFile.save(inventoryList);
    }

    public void loadList(ActionEvent actionEvent) {
        FileManagement file = new FileManagement();
        //Call search function from fileManagement class
        file.load(inventoryList);
    }

    private void refresh() {
        serialNumberField.setText(null);
        nameField.setText(null);
        valueField.setText(null);
    }
}
