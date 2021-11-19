/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

public class List {
    Item[] inventory;

    public Item[] getInventory() {
        return inventory;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public List(Item[] inventory) {
        this.setInventory(inventory);
    }

    public void clear() {
        //remove all items from List
        //Update observable table
    }

    public void sort() {
        //Take in selected sort order from GUI
        //Sort List to meet selection
        //Update observable table
    }

    public void search() {
        //Use text fields as parameters
        //Use contains to find all items in List that contain instances of parameters
        //Update observable table
    }
}
