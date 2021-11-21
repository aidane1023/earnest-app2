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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
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

    @FXML
    FileChooser fileChooser = new FileChooser();

    Boolean editorGate = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Set all necessary visual adjustments
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        fileChooser.setInitialDirectory(new File("src\\main\\resources"));
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
            if (errorCheck.invalidInputCheck(tempSerialNumber, tempName, tempDouble)) {
                if (!editorGate) {
                    //Display item in observable table view
                    inventoryList.add(new Item(tempSerialNumber, tempName, String.format("$%.2f",tempDouble)));
                    table.setItems(inventoryList);
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
        ErrorMessage errorCheck = new ErrorMessage();
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
            errorCheck.invalidSelection();
        }
    }

    public void clearList(ActionEvent actionEvent) {
        List list = new List(null);
        //Call clear function from List class
        emptyList();
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

    @FXML
    public void saveList(ActionEvent actionEvent) {
        Window stage = new Stage();
        fileChooser.setTitle("Save Inventory");
        fileChooser.setInitialFileName("myInventory");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));

        try {
            File file = fileChooser.showSaveDialog(stage);
        } catch (Exception ex) {
            System.out.println("error opening save dialog");
        }
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

    public void emptyList() {
        //Clear all events from list
        inventoryList.remove(0, inventoryList.size());
        //Update display
        table.setItems(inventoryList);
    }

}
