package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_Town {
    private Stage stage;

    public UI_Town(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label townLabel = new Label("Town Screen");
        layout.getChildren().add(townLabel);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Town Screen");
        stage.show();
    }
}