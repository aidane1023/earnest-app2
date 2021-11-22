/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 aidan earnest
 */

import com.google.gson.JsonArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileManagement {
    @FXML
    FileChooser fileChooser = new FileChooser();

    public void save(ObservableList<Item> inventoryList) {
        Window stage = new Stage();
        fileChooser.setInitialDirectory(new File("src\\main\\resources"));
        fileChooser.setTitle("Save Inventory");
        fileChooser.setInitialFileName("myInventory");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            if (file.getAbsolutePath().contains(".txt")) {
                writeTXT(file, inventoryList);
            }
            else if (file.getAbsolutePath().contains(".html")) {
                writeHTML(file, inventoryList);
            }
            else if (file.getAbsolutePath().contains("json")){
                writeJSON(file, inventoryList);
            }

        } catch (Exception ex) {
            System.out.println("error opening save dialog");
        }
    }

    public void load(ObservableList<Item> inventoryList) {
        Window stage = new Stage();
        fileChooser.setInitialDirectory(new File("src\\main\\resources"));
        fileChooser.setTitle("Load Inventory");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TSV", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));

        try {
            File file = fileChooser.showOpenDialog(stage);

            if (file.getName().contains(".txt")) {
                loadTSV(file, inventoryList);
            }
            else if (file.getName().contains(".html")) {
                loadHTML(file, inventoryList);
            }
            else if (file.getName().contains("json")){
                loadJSON(file, inventoryList);
            }

        } catch (Exception ex) {
            System.out.println("error opening load dialog");
        }

    }

    public void loadTSV(File file, ObservableList<Item> inventoryList) throws IOException {
        /*int counter = 0;
        //Using a file selector, load file
        BufferedReader TSVFile =
                new BufferedReader(new FileReader(file));

        String dataRow = TSVFile.readLine(); // Read first line.
        System.out.println(dataRow);

        while (dataRow != null){
            String[] dataArray = dataRow.split("   ");
            System.out.println(dataArray);
            inventoryList.set(counter,new Item(dataArray[0], dataArray[1], dataArray[2]));
            System.out.println(); // Print the data line.
            dataRow = TSVFile.readLine(); // Read next line of data.
            counter++;
        }
        // Close the file once all data has been read.
        TSVFile.close();

        // End the printout with a blank line.
        System.out.println();

        //Fill components into list
        //Update observable table*/
    }
    public void loadHTML(File file, ObservableList<Item> inventoryList) {
        //Using a file selector, load file
        //Fill components into list
        //Update observable table
    }
    public void loadJSON(File file, ObservableList<Item> inventoryList) {
        //Using a file selector, load file
        //Fill components into list
        //Update observable table
    }

    public void writeTXT(File file, ObservableList<Item> inventoryList) {
        List<String> strings = new ArrayList<>();
        //Convert to string
        for (Item item : inventoryList) {
            strings.add(item.toStringTab());
        }

        //Write to file
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            for (String str : strings) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }
    public void writeHTML(File file, ObservableList<Item> inventoryList) {
        List<String> strings = new ArrayList<>();
        //Convert to string
        for (Item item : inventoryList) {
            strings.add(item.toString());
        }

        //Write to file
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            writer.write("<!DOCTYPE html>\n<HTML>\n<title>" + file.getName() + "</title>\n");
            writer.write("<body>\n<p>");
            for (String str : strings) {
                writer.write(str + System.lineSeparator());
            }
            writer.write("</p>\n</body>\n<HTML>");
            writer.close();

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }

    }
    public void writeJSON(File file, ObservableList<Item> inventoryList) {
        //Write to file
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            writer.write("{\n\t\"Inventory\" : [\n\t\t");
            for (int i = 0; i < inventoryList.size(); i++) {
                writer.write("{\"serialNumber\": \"" + inventoryList.get(i).getSerialNumber() + "\", \"name\": \"" + inventoryList.get(i).getName() + "\", \"value\": \"" + inventoryList.get(i).getValue() + "\" }");
                if (i != inventoryList.size() - 1) {
                    writer.write(",\n\t\t");
                } else {
                    writer.write("\n\t]\n}");
                }
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }
}
