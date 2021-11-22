/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.Objects;

public class ErrorMessage {

    @FXML
    public Boolean invalidInputCheck(String serialNumber, String name, Double value, ObservableList<Item> inventoryList) {
        //Ensure parameters are met
        return serialFormat(serialNumber, inventoryList) && name.length() >= 2 && name.length() <= 256 && value >= 0;
    }

    public boolean serialFormat(String serialNumber, ObservableList<Item> inventoryList) {
        if (serialNumber.length() == 13 && serialNumber.matches("[a-zA-Z]-[a-zA-Z_0-9]+-[a-zA-Z_0-9]+-[a-zA-Z_0-9]+")) {
            for (Item item : inventoryList) {
                if (Objects.equals(item.getSerialNumber(), serialNumber)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
}
