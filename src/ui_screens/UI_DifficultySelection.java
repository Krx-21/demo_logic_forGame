package ui_screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.MainMenu;
import game.Difficulty;
import game.Game;

/*
 * UI_DifficultySelection: หน้าจอ UI สำหรับเลือกโหมดความยาก
 * สร้างปุ่มและตัวเลือกความยาก เพื่อส่งต่อไปยังคลาส Game
 */

public class UI_DifficultySelection extends VBox {

    private final Scene scene;

    public UI_DifficultySelection(Stage stage) {
        // สร้างปุ่มสำหรับเลือกความยาก
        Button easyButton = createStyledButton("Easy");
        Button mediumButton = createStyledButton("Medium");
        Button hardButton = createStyledButton("Hard");
        Button nightmareButton = createStyledButton("Nightmare");

        // ปุ่มย้อนกลับไป Main Menu
        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> backToMainMenu(stage));

        // กำหนดการทำงานของปุ่มต่างๆ
        easyButton.setOnAction(e -> startGame(stage, Difficulty.EASY));
        mediumButton.setOnAction(e -> startGame(stage, Difficulty.MEDIUM));
        hardButton.setOnAction(e -> startGame(stage, Difficulty.HARD));
        nightmareButton.setOnAction(e -> startGame(stage, Difficulty.NIGHTMARE));

        // ตั้งค่ารูปแบบของ VBox
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(50, 0, 50, 0)); // เพิ่มระยะห่างบน-ล่าง

        // เพิ่มปุ่มต่างๆ ลงใน VBox
        this.getChildren().addAll(easyButton, mediumButton, hardButton, nightmareButton, backButton);

        // ตั้งค่าฉากและแสดง
        scene = new Scene(this, 1280, 720);
        stage.setScene(scene);
    }

    public Scene getCustomScene() {
        return scene;
    }

    private void startGame(Stage stage, Difficulty difficulty) {
        Game game = new Game(stage, difficulty);
        stage.setScene(game.getGameScene());
    }

    private void backToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu(stage);
        stage.setScene(mainMenu.getCustomScene());
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 24px; -fx-pref-width: 250px; -fx-pref-height: 60px; -fx-text-fill: #FFFFFF; -fx-background-color: #001f3f;");
        return button;
    }
}
