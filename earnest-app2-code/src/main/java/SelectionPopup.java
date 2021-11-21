import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;


public class SelectionPopup {


    public static void display()
    {
        Stage selectionPopupWindow=new Stage();

        selectionPopupWindow.initModality(Modality.APPLICATION_MODAL);
        selectionPopupWindow.setTitle("Invalid Input Warning");


        Label label1 = new Label("Invalid Selection");
        label1.setFont(Font.font ("System", 36));

        Label label2 = new Label("To use this function a selection from the table is required.");
        label2.setFont(Font.font ("System", 18));

        Label label3 = new Label("Make a selection then try again.");
        label3.setFont(Font.font ("System", 18));

        VBox layout= new VBox(10);


        layout.getChildren().addAll(label1, label2, label3);

        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 600, 400);

        selectionPopupWindow.setScene(scene1);

        selectionPopupWindow.showAndWait();

    }

}
