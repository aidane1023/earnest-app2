/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class Item {
    private String serialNumber; // format as A-XXX-XXX-XXX
    private String name;
    private Double value; // format as $XX.XX

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Item(String serialNumber, String name, Double value) {
        this.setSerialNumber(serialNumber);
        this.setName(name);
        this.setValue(value);
    }

    public void add() {
        Controller controller = new Controller();
        ErrorMessage errorCheck = new ErrorMessage();
        //Take in items from textFields
        //Add event to list view
        if (controller.inventoryList.size() <= 1024) {
            controller.serialNumberField.setText(controller.serialNumberField.getText());
            controller.nameField.setText(controller.nameField.getText());
            controller.valueField.setText(controller.valueField.getText());

            //Make Temp variables for easier conversions
            String tempSerialNumber = controller.serialNumberField.getText();
            String tempName = controller.nameField.getText();
            //Convert value from text to double
            Double tempValue = Double.parseDouble(controller.valueField.getText());

            //Confirm items meet restraints (else run errorMessage)
            if (errorCheck.invalidInputCheck()) {
                //Display item in observable table view
                controller.inventoryList.add(new Item(tempSerialNumber, tempName, tempValue));
                controller.List.setItems(controller.inventoryList);
            }
        }
    }

    public void delete() {
        //Requires a selection
        //Delete selected Item from List class
        //Delete selected item from observable table
    }

    public void edit() {
        //Requires a selection
        //Fills fields with components from selection
    }
}
