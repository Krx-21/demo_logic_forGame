package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_BattleResult {
    private Stage stage;

    public UI_BattleResult(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Label resultLabel = new Label("Battle Result");
        layout.getChildren().add(resultLabel);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Battle Result");
        stage.show();
    }
}