package sample;

import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;




public class AlertBox {

    public static void display(String title , String message){

        Stage window = new Stage();
        // blocks other windows from function
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);

        Label label = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(closeButton,label);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
