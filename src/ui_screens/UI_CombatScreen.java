package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_CombatScreen {
    private Stage stage;

    public UI_CombatScreen(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label combatLabel = new Label("Combat Screen");
        layout.getChildren().add(combatLabel);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Combat Screen");
        stage.show();
    }
}