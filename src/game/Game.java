package game;

import enemies.ClockworkRequiem;
import enemies.FrostfangQueen;
import enemies.IceDrake;
import enemies.BatteryMantis;
import enemies.BaseBoss;
import enemies.Monster;
import fields.TimeDistortionEffect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skills.Skill;
import skills.SkillRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/*
 * Game: จัดการลูปเกมโดยรวม รวมถึงการสุ่มมอนสเตอร์/บอส
 * จัดการสถานะของผู้เล่นและมอนสเตอร์ การแสดง UI และการเรียกใช้งานสกิล
 */

public class Game {
    private final Scene gameScene;
    private final Stage gameStage;
    private final Difficulty gameDifficulty;
    private final List<Theme> themeList;
    private final List<Monster> monsterList;
    private final List<BaseBoss> bossList;
    private final Random randomGenerator;
    private Theme currentTheme;
    private int roundsCompleted;
    private int playerHP;
    private int monsterHP;
    private Label playerHPLabel;
    private Label monsterHPLabel;

    public Game(Stage stage, Difficulty difficulty) {
        this.gameStage = stage;
        this.gameDifficulty = difficulty;
        this.themeList = new ArrayList<>();
        this.monsterList = new ArrayList<>();
        this.bossList = new ArrayList<>();
        this.randomGenerator = new Random();
        this.roundsCompleted = 0;
        this.playerHP = 100; // Initial player HP
        this.monsterHP = 0; // Initial monster HP

        initializeThemes();
        initializeMonstersAndBosses();

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(50, 0, 50, 0));

        playerHPLabel = new Label("Player HP: " + playerHP);
        monsterHPLabel = new Label("Monster HP: " + monsterHP);

        Button fightButton = new Button("Fight!");
        fightButton.setStyle("-fx-font-size: 24px; -fx-pref-width: 250px; -fx-pref-height: 60px; -fx-text-fill: #FFFFFF; -fx-background-color: #001f3f;");
        fightButton.setOnAction(e -> fight());

        HBox actionButtons = new HBox();
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setSpacing(20);

        Button skillButton = new Button("Use Skill");
        skillButton.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px; -fx-pref-height: 40px;");
        skillButton.setOnAction(e -> showSkillDialog());

        Button itemButton = new Button("Use Item");
        itemButton.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px; -fx-pref-height: 40px;");
        itemButton.setOnAction(e -> useItem());

        actionButtons.getChildren().addAll(skillButton, itemButton);

        vbox.getChildren().addAll(playerHPLabel, monsterHPLabel, fightButton, actionButtons);

        gameScene = new Scene(vbox, 1280, 720);
    }

    public Scene getGameScene() {
        return gameScene;
    }

    private void initializeThemes() {
        Collections.addAll(themeList, Theme.values());
        Collections.shuffle(themeList);
        currentTheme = themeList.remove(0);
    }

    private void initializeMonstersAndBosses() {
        monsterList.clear();
        bossList.clear();

        // Add monsters and bosses for the current theme
        switch (currentTheme) {
            case FOREST:
                monsterList.add(new IceDrake());
                monsterList.add(new BatteryMantis());
                bossList.add(new FrostfangQueen());
                break;
            case DESERT:
                // Add desert monsters and bosses
                break;
            case OCEAN:
                monsterList.add(new BatteryMantis());
                bossList.add(new ClockworkRequiem());
                break;
        }
    }

    private void fight() {
        if (monsterList.isEmpty() && bossList.isEmpty()) {
            roundsCompleted++;
            if (roundsCompleted >= 12) {
                System.out.println("Game completed! You have defeated all themes.");
                return;
            }

            if (themeList.isEmpty()) {
                System.out.println("All themes completed! Reshuffling themes...");
                initializeThemes();
            }
            currentTheme = themeList.remove(0);
            initializeMonstersAndBosses();
        }

        if (!monsterList.isEmpty()) {
            // Fight a monster
            Monster monster = monsterList.remove(0);
            monsterHP = monster.getHp(); // Set initial monster HP
            monsterHPLabel.setText("Monster HP: " + monsterHP);
            System.out.println("Fighting " + monster.getName());
        } else if (!bossList.isEmpty()) {
            // Fight the boss
            BaseBoss boss = bossList.remove(0);
            monsterHP = boss.getHp(); // Set initial boss HP
            monsterHPLabel.setText("Monster HP: " + monsterHP);
            System.out.println("Fighting " + boss.getName());
        }
    }

    private void showSkillDialog() {
        List<String> skills = new ArrayList<>(SkillRepository.getAllSkillNames());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(skills.get(0), skills);
        dialog.setTitle("Choose Skill");
        dialog.setHeaderText("Select a skill to use:");
        dialog.setContentText("Skills:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(skill -> useSkill(skill));
    }

    private void useSkill(String skillName) {
        Skill skill = SkillRepository.getSkill(skillName);
        if (skill != null) {
            System.out.println("Using skill: " + skill.getName());
            // Implement skill usage logic based on selected skill
            switch (skill.getName()) {
                case "Fireball":
                    monsterHP -= 20; // Example skill damage
                    break;
                case "Ice Shard":
                    monsterHP -= 15; // Example skill damage
                    break;
                case "Thunder Strike":
                    monsterHP -= 25; // Example skill damage
                    break;
            }
            monsterHPLabel.setText("Monster HP: " + monsterHP);
            if (monsterHP <= 0) {
                System.out.println("Monster defeated!");
                fight(); // Proceed to the next fight
            }
        }
    }

    private void useItem() {
        // Implement item usage logic
        System.out.println("Using item...");
        playerHP += 10; // Example item healing
        playerHPLabel.setText("Player HP: " + playerHP);
    }
}