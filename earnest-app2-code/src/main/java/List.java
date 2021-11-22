/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import javafx.collections.ObservableList;

public class List {

    public void clear(ObservableList<Item> inventoryList) {
        //Clear all events from list
        inventoryList.remove(0, inventoryList.size());
    }

}
