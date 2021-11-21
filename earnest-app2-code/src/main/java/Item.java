/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

public class Item {
    private String serialNumber; // format as A-XXX-XXX-XXX
    private String name;
    private String value; // format as $XX.XX

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Item(String serialNumber, String name, String value) {
        this.setSerialNumber(serialNumber);
        this.setName(name);
        this.setValue(value);
    }

    public void add(String serialField, String nameField, String valueField) {
        Controller controller = new Controller();
        ErrorMessage errorCheck = new ErrorMessage();
        //Take in items from textFields
        //Add item to TableView if under the size limit
        if (controller.inventoryList.size() < 1024) {
            //Confirm items meet restraints (else run errorMessage)
            if (errorCheck.invalidInputCheck()) {
                //Display item in observable table view
                controller.inventoryList.add(new Item(serialField, nameField, valueField));
                controller.table.getItems().add(new Item(serialField, nameField, valueField));
            }
        }
    }

    public void delete() {
        Controller controller = new Controller();
        ErrorMessage errorCheck = new ErrorMessage();
        //Ensure an event is selected
        try {
            //Remove event from list
            controller.inventoryList.remove(controller.table.getSelectionModel().getSelectedItem());
        } catch(Exception e) {
            System.out.println("No selection");
            errorCheck.invalidSelection();
        }
    }

    public void edit() {
        Controller controller = new Controller();
        ErrorMessage errorCheck = new ErrorMessage();
        //Requires a selection
        //Fill event fields
        try {

            controller.serialNumberField.setText(controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getSerialNumber());
            controller.nameField.setText(controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getName());
            controller.valueField.setText((controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getValue()));
            controller.editorGate = true;
        } catch (Exception e) {
            System.out.println("No Selection");
            errorCheck.invalidSelection();
        }
    }
}
