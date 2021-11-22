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

    public String toStringTab() {
        //Format list view
        return (this.getSerialNumber() + "\t" + this.getName() + "\t" + this.getValue());
    }

    @Override
    public String toString(){
        return (this.getSerialNumber() + " " + this.getName() + " " + this.getValue());
    }
}
