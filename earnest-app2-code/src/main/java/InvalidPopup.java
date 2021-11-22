import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;


public class InvalidPopup {


    public static void display()
    {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Invalid Input Warning");


        Label label1 = new Label("Invalid Input");
        label1.setFont(Font.font ("System", 36));

        Label label2 = new Label("One or more entered text fields contained an invalid input.");
        label2.setFont(Font.font ("System", 18));

        Label label3 = new Label("Update text fields and try again.");
        label3.setFont(Font.font ("System", 18));

        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, label2, label3);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 600, 400);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}

