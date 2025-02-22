package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_Inventory {
    private Stage stage;

    public UI_Inventory(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label inventoryLabel = new Label("Inventory Screen");
        layout.getChildren().add(inventoryLabel);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Inventory Screen");
        stage.show();
    }
}