package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui_screens.UI_DifficultySelection;
import ui_screens.UI_Story;

/*
 * MainMenu: จัดการเมนูหลักของเกม
 * สร้างปุ่มและการนำทางไปสู่ฉากอื่นๆ
 */

public class MainMenu extends VBox {

    private Scene scene;

    public MainMenu(Stage stage) {
        initialize(stage);
        scene = new Scene(new StackPane(this), 1280, 720);
    }

    private void initialize(Stage stage) {
        // ตั้งค่ารูปแบบของ VBox
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(50, 0, 50, 0)); // เพิ่มระยะห่างบน-ล่าง

        // สร้างปุ่ม
        Button startButton = createStyledButton("Start Game");
        startButton.setOnAction(e -> startGame(stage));

        Button storyButton = createStyledButton("Story");
        storyButton.setOnAction(e -> showStory(stage));

        Button exitButton = createStyledButton("Exit");
        exitButton.setOnAction(e -> exitGame(stage));

        this.getChildren().addAll(startButton, storyButton, exitButton);
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 24px; -fx-pref-width: 250px; -fx-pref-height: 60px; -fx-text-fill: #FFFFFF; -fx-background-color: #001f3f;");
        return button;
    }

    private void startGame(Stage stage) {
        UI_DifficultySelection difficultySelection = new UI_DifficultySelection(stage);
        stage.setScene(difficultySelection.getCustomScene());
    }

    private void showStory(Stage stage) {
        UI_Story story = new UI_Story(stage);
        stage.setScene(story.getCustomScene());
    }

    private void exitGame(Stage stage) {
        stage.close();
    }

    public Scene getCustomScene() {
        return scene;
    }
}
