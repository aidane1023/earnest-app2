/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

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
        //Take in items from textFields
        //Confirm items meet restraints (else run errorMessage)
        //Add item to List class
        //Display item in observable table view
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
