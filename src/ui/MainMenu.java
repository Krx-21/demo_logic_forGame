package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui_screens.UI_DifficultySelection;
import ui_screens.UI_Story;
import java.io.FileInputStream;

/*
 * MainMenu: จัดการเมนูหลักของเกม
 * สร้างปุ่มและการนำทางไปสู่ฉากอื่นๆ
 */

public class MainMenu extends VBox {

    private Scene scene;

    public MainMenu(Stage stage) {
        try {
            // ใช้ path ที่ถูกต้อง
            Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/images/MainMenu.png"));
            if (backgroundImage.isError()) {
                throw new IllegalArgumentException("Could not load background image");
            }
            
            ImageView background = new ImageView(backgroundImage);
            background.setFitWidth(1280);
            background.setFitHeight(720);
            
            initialize(stage);
            
            StackPane root = new StackPane();
            root.getChildren().addAll(background, this);
            
            scene = new Scene(root, 1280, 720);
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            this.setStyle("-fx-background-color: #2c3e50;");
            initialize(stage);
            scene = new Scene(this, 1280, 720);
        }
    }

    private void initialize(Stage stage) {
        // ตั้งค่ารูปแบบของ VBox
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(50, 0, 50, 0)); // เพิ่มระยะห่างบน-ล่าง
        
        // ทำให้พื้นหลังของ VBox โปร่งใส
        this.setStyle("-fx-background-color: transparent;");

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
        // ปรับสไตล์ปุ่มให้ดูดีขึ้น
        button.setStyle(
            "-fx-font-size: 24px; " +
            "-fx-pref-width: 250px; " +
            "-fx-pref-height: 60px; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: rgba(0, 31, 63, 0.8); " + // ทำให้พื้นหลังปุ่มโปร่งใสเล็กน้อย
            "-fx-background-radius: 30; " +  // ทำให้ปุ่มมีขอบมน
            "-fx-border-color: white; " +
            "-fx-border-radius: 30; " +
            "-fx-border-width: 2px;"
        );
        
        // เพิ่ม hover effect
        button.setOnMouseEntered(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: rgba(0, 41, 83, 0.9);")
        );
        button.setOnMouseExited(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: rgba(0, 31, 63, 0.8);")
        );
        
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
