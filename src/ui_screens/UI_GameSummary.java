package ui_screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ui.MainMenu;
import player.Player;
import game.GameStats;

public class UI_GameSummary extends VBox {
    private Scene customScene; // เปลี่ยนชื่อตัวแปรจาก scene เป็น customScene
    private Stage stage;

    public UI_GameSummary(Stage stage, Player player, GameStats stats) {
        this.stage = stage;
        initialize(player, stats);
    }

    private void initialize(Player player, GameStats stats) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(50));
        this.setStyle("-fx-background-color: #2c3e50;");

        // หัวข้อ
        Label titleLabel = createStyledLabel("Game Complete!", 48);
        titleLabel.setStyle(titleLabel.getStyle() + "-fx-text-fill: gold;");

        // สถานะตัวละคร
        Label playerStats = createStyledLabel(String.format("""
            Final Character Status:
            Level: %d
            HP: %d/%d
            ATK: %d
            DEF: %d
            SPD: %d
            """, 
            player.getLevel(), 
            player.getHp(), player.getMaxHp(),
            player.getAtk(), player.getDef(), player.getSpd()
        ), 24);

        // สถิติการเล่น
        Label gameStats = createStyledLabel(String.format("""
            Battle Statistics:
            Monsters Defeated: %d
            Bosses Defeated: %d
            Total Experience Gained: %d
            Total Damage Dealt: %d
            Total Damage Taken: %d
            Skills Used: %d
            """,
            stats.getMonstersDefeated(),
            stats.getBossesDefeated(),
            stats.getTotalXP(),
            stats.getTotalDamageDealt(),
            stats.getTotalDamageTaken(),
            stats.getSkillsUsed()
        ), 24);

        // ปุ่มกลับเมนูหลัก
        Button mainMenuButton = new Button("Return to Main Menu");
        mainMenuButton.setStyle("""
            -fx-font-size: 20px;
            -fx-background-color: #e74c3c;
            -fx-text-fill: white;
            -fx-padding: 15 30;
            -fx-background-radius: 5;
            """);
        mainMenuButton.setOnAction(e -> backToMainMenu());

        this.getChildren().addAll(
            titleLabel,
            playerStats,
            gameStats,
            mainMenuButton
        );

        customScene = new Scene(this, 1280, 720); // เปลี่ยนการกำหนดค่าให้ customScene
    }

    private Label createStyledLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setFont(Font.font("System", FontWeight.BOLD, fontSize));
        label.setStyle("-fx-text-fill: white;");
        return label;
    }

    private void backToMainMenu() {
        MainMenu mainMenu = new MainMenu(stage);
        stage.setScene(mainMenu.getCustomScene());
    }

    // เปลี่ยนชื่อเมธอดเพื่อไม่ให้ override getScene()
    public Scene getCustomScene() {
        return customScene;
    }
}