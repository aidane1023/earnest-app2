/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

public class Item {
    private String serialNumber; // format as A-XXX-XXX-XXX
    private String name;
    private Double price; // format as $XX.XX

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item(String serialNumber, String name, Double price) {
        this.setSerialNumber(serialNumber);
        this.setName(name);
        this.setPrice(price);
    }

    public void add() {
        Controller controller = new Controller();
        ErrorMessage errorCheck = new ErrorMessage();
        //Take in items from textFields
        //Add event to list view
        if (controller.inventoryList.size() < 1024) {
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
                controller.table.setItems(controller.inventoryList);
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
            //Convert double back to string
            double tempDouble = controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getPrice();
            String tempString = Double.toString(tempDouble);

            controller.serialNumberField.setText(controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getSerialNumber());
            controller.nameField.setText(controller.inventoryList.get(controller.table.getSelectionModel().getSelectedIndex()).getName());
            controller.valueField.setText(tempString);
            controller.editorGate = true;
        } catch (Exception e) {
            System.out.println("No Selection");
            errorCheck.invalidSelection();
        }
    }
}
