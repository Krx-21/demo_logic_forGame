package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_TitleScreen {
    private Stage stage;

    public UI_TitleScreen(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startGame());
        layout.getChildren().add(startButton);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Title Screen");
        stage.show();
    }

    private void startGame() {
        // Logic to start the game
    }
}