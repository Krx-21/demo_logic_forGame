package game;

import characters.Character;
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
import ui.MainMenu;
import javafx.application.Platform; // เพิ่มบรรทัดนี้

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    private Label enemyNameLabel; // Label for displaying enemy name
    private Character player;
    private Character currentEnemy;
    
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
        
        // ใน constructor ของ Game.java
        player = new Character("Adventurer", 100, 30, 20, 60);
        
        initializeThemes();
        initializeMonstersAndBosses();
        
        playerHPLabel = new Label("Player HP: " + playerHP);
        monsterHPLabel = new Label("Monster HP: " + monsterHP);
        
        // สร้าง Label แสดงชื่อ Enemy ด้วยฟ้อนต์ขนาดใหญ่และสีเด่น
        enemyNameLabel = new Label("Enemy: ");
        enemyNameLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #FF0000;");
        
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
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(50, 0, 50, 0));
        vbox.getChildren().addAll(playerHPLabel, monsterHPLabel, enemyNameLabel, fightButton, actionButtons);
        
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
        
        // เพิ่มศัตรูและบอสตามธีมปัจจุบัน
        switch (currentTheme) {
            case FROST:
                monsterList.add(new IceDrake());
                monsterList.add(new BatteryMantis());
                bossList.add(new FrostfangQueen());
                break;
            case LAVA:
                // เพิ่ม lava monsters และ bosses
                break;
            case STEAMPUNK:
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
            Monster monster = monsterList.remove(0);
            monsterHP = monster.getHp(); // Set initial monster HP
            monsterHPLabel.setText("Monster HP: " + monsterHP);
            System.out.println("Fighting " + monster.getName());
            currentEnemy = monster;
        } else if (!bossList.isEmpty()) {
            BaseBoss boss = bossList.remove(0);
            monsterHP = boss.getHp(); // Set initial boss HP
            monsterHPLabel.setText("Monster HP: " + monsterHP);
            System.out.println("Fighting " + boss.getName());
            currentEnemy = boss;
        }
        
        // แสดงชื่อ enemy ด้วย Label ที่มีฟ้อนต์ใหญ่
        enemyNameLabel.setText("Fighting: " + currentEnemy.getName());
        
        // ตรวจสอบลำดับการโจมตีโดยพิจารณาจาก spd และอย่าเรียก playerAttack() อัตโนมัติเมื่อผู้เล่นโจมตีก่อน
        if (player.getSpd() > currentEnemy.getSpd()) {
            System.out.println("Player attacks first. Please select a skill to use.");
            // รอการเลือกสกิลจากผู้เล่น โดยที่ event handler ของปุ่ม Use Skill จะเป็นตัวเรียก playerAttack()
        } else if (player.getSpd() < currentEnemy.getSpd()) {
            System.out.println("Enemy attacks first.");
            enemyAttack();
            if (player.getHp() > 0) {
                System.out.println("Now, it's player's turn. Please select a skill to use.");
            }
        } else {
            // กรณี spd เท่ากัน ให้สุ่มเลือกฝ่ายที่โจมตีก่อน
            if (randomGenerator.nextBoolean()) {
                System.out.println("Player attacks first (tie-break). Please select a skill to use.");
            } else {
                System.out.println("Enemy attacks first (tie-break).");
                enemyAttack();
                if (player.getHp() > 0) {
                    System.out.println("Now, it's player's turn. Please select a skill to use.");
                }
            }
        }
    }
    
    private void playerAttack() {
        Skill skill = SkillRepository.getSkill("Ice Blast");
        System.out.println("Player attacks " + currentEnemy.getName() + " with " + skill.getName());
        // ใช้สกิลโจมตี: สมมุติว่า skill.use() จะลด HP ของฝ่ายตรงข้าม
        skill.use(player, currentEnemy);
        // อัพเดต HP ของศัตรู
        monsterHP = currentEnemy.getHp();
        monsterHPLabel.setText("Monster HP: " + monsterHP);
    }
    
    private void enemyAttack() {
        System.out.println("Enemy's turn: " + currentEnemy.getName() + " is attacking!");
        int damage;
        if (currentEnemy instanceof IceDrake) {
            damage = ((IceDrake) currentEnemy).performAttack();
        } else {
            damage = currentEnemy.getAtk();
        }
        System.out.println(currentEnemy.getName() + " attacks Player causing " + damage + " damage.");
        player.takeDamage(damage); // หัก HP ของ player
        playerHP = player.getHp();
        playerHPLabel.setText("Player HP: " + playerHP);
        System.out.println("After enemy attack, Player HP: " + playerHP);
        
        if (playerHP <= 0) {
            System.out.println("Game Over! Returning to Main Menu...");
            MainMenu mainMenu = new MainMenu(gameStage);
            gameStage.setScene(mainMenu.getScene());
        }
    }
    
    private void showSkillDialog() {
        List<String> skills = new ArrayList<>(SkillRepository.getAllSkillNames());
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>(skills.get(0), skills);
        dialog.setTitle("Choose Skill");
        dialog.setHeaderText("Select a skill to use:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(this::useSkill);
    }
    
    private void useSkill(String skillName) {
        Skill skill = SkillRepository.getSkill(skillName);
        if (skill != null) {
            System.out.println("Using skill: " + skill.getName());
            // บันทึก HP ของศัตรูก่อนโจมตี
            int enemyHPBefore = currentEnemy.getHp();
            // ใช้สกิลโจมตี
            skill.use(player, currentEnemy);
            // บันทึก HP หลังโจมตี
            int enemyHPAfter = currentEnemy.getHp();
            // คำนวณดาเมจที่เกิดขึ้น
            int damageDone = enemyHPBefore - enemyHPAfter;
            System.out.println("Player deals " + damageDone + " damage to " + currentEnemy.getName());
            // อัปเดต Label แสดง HP ของศัตรู
            monsterHP = enemyHPAfter;
            monsterHPLabel.setText("Monster HP: " + monsterHP);

            // หากศัตรูยังมี HP อยู่ ให้ดำเนินการให้ฝ่ายศัตรูโจมตี
            if (monsterHP > 0) {
                System.out.println("Player turn complete. Now enemy's turn.");
                enemyAttack();
            } else {
                System.out.println("Enemy defeated!");
                fight(); // เริ่มรอบต่อไป
            }
        }
    }
    
    private void useItem() {
        System.out.println("Using item...");
        playerHP += 10;
        playerHPLabel.setText("Player HP: " + playerHP);
    }
    
}