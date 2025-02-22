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
import player.Player;  // แก้ไข import ให้ถูกต้อง
import enemies.*;
import skills.*;
import java.util.List;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import player.Player;
import game.DifficultyManager;
import javafx.stage.Modality; // เพิ่มบรรทัดนี้
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Game {
    // สมมุติว่าเรามี instance ของ Game ที่ใช้ในระบบ turn
    private static Game currentGame;

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
    private Player player;  // เปลี่ยนจาก Character เป็น Player
    private Character currentEnemy;
    private DifficultyManager difficultyManager;  // เพิ่มตัวแปร
    private HBox mainButtonBox;
    private HBox skillButtonBox;
    
    public Game(Stage stage, Difficulty difficulty) {
        this.gameStage = stage;
        this.gameDifficulty = difficulty;
        gameStage.setResizable(false); // ไม่ให้ปรับขนาดหน้าต่างได้
        currentGame = this;
        this.themeList = new ArrayList<>();
        this.monsterList = new ArrayList<>();
        this.bossList = new ArrayList<>();
        this.randomGenerator = new Random();
        this.roundsCompleted = 0;
        this.playerHP = 100; // Initial player HP
        this.monsterHP = 0; // Initial monster HP
        
        // ใน constructor ของ Game.java
        player = new Player("Adventurer");  // สร้าง Player แทน Character
        this.difficultyManager = new DifficultyManager(difficulty);
        
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
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2c3e50;");

        // แสดงสถานะ
        playerHPLabel = new Label("Player HP: " + player.getHp());
        monsterHPLabel = new Label("Monster HP: ---");
        enemyNameLabel = new Label("Enemy: ---");

        // สไตล์ Label
        String labelStyle = "-fx-font-size: 24px; -fx-text-fill: white;";
        playerHPLabel.setStyle(labelStyle);
        monsterHPLabel.setStyle(labelStyle);
        enemyNameLabel.setStyle(labelStyle + "-fx-font-weight: bold;");

        // ปุ่มควบคุม
        mainButtonBox = new HBox(20);
        mainButtonBox.setAlignment(Pos.CENTER);
        Button skillButton = createStyledButton("Use Skill", e -> showSkillButtons());
        Button itemButton = createStyledButton("Use Item", e -> useItem());
        mainButtonBox.getChildren().addAll(skillButton, itemButton);

        // Skill buttons (initially hidden)
        skillButtonBox = createSkillButtons();
        skillButtonBox.setVisible(false);
        skillButtonBox.setManaged(false);

        // Add all components
        root.getChildren().addAll(
            playerHPLabel, 
            monsterHPLabel, 
            enemyNameLabel, 
            mainButtonBox,
            skillButtonBox
        );

        fight();

        return new Scene(root, 1280, 720); // ปรับขนาดเป็น 1280x720
    }

    private Button createStyledButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setStyle(
            "-fx-font-size: 18px; " +
            "-fx-background-color: #34495e; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10px; " +
            "-fx-border-color: #455a64; " +
            "-fx-border-width: 2px;"
        );
        
        button.setOnMouseEntered(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #455a64;"));
        
        button.setOnMouseExited(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #34495e;"));
        
        button.setOnAction(handler);
        return button;
    }
    
    private HBox createSkillButtons() {
        HBox skillButtonBox = new HBox(10);
        skillButtonBox.setAlignment(Pos.CENTER);
        
        if (player instanceof Player) {
            Player playerChar = (Player) player;
            List<Skill> playerSkills = playerChar.getSkills();
            
            for (Skill skill : playerSkills) {
                Button skillButton = createStyledButton(skill.getName(), e -> {
                    useSkill(skill.getName());
                    showMainButtons();
                });
                
                // Add tooltip for skill info
                Tooltip tooltip = new Tooltip(
                    skill.getDescription() + "\n" +
                    "Mana Cost: " + skill.getManaCost() + "\n" +
                    "Cooldown: " + skill.getCooldown() + " turns"
                );
                skillButton.setTooltip(tooltip);
                
                skillButtonBox.getChildren().add(skillButton);
            }
        }
        
        // Back button
        Button backButton = createStyledButton("Back", e -> showMainButtons());
        backButton.setStyle(backButton.getStyle() + "-fx-background-color: #c0392b;");
        skillButtonBox.getChildren().add(backButton);
        
        return skillButtonBox;
    }

    private void showSkillButtons() {
        mainButtonBox.setVisible(false);
        mainButtonBox.setManaged(false);
        skillButtonBox.setVisible(true);
        skillButtonBox.setManaged(true);
    }

    private void showMainButtons() {
        skillButtonBox.setVisible(false);
        skillButtonBox.setManaged(false);
        mainButtonBox.setVisible(true);
        mainButtonBox.setManaged(true);
    }
    
    private void initializeThemes() {
        Collections.addAll(themeList, Theme.values());
        Collections.shuffle(themeList);
        currentTheme = themeList.remove(0);
    }
    
    private void initializeMonstersAndBosses() {
        monsterList.clear();
        bossList.clear();
        
        System.out.println("Initializing monsters and bosses for theme: " + currentTheme);
        
        switch (currentTheme) {
            case FROST:
                System.out.println("=== Frost Theme ===");
                // Boss
                bossList.add(new FrostfangQueen());  // Boss with ice-based abilities
                // Monsters
                monsterList.add(new SnowGoblin());   // Basic ice monster
                monsterList.add(new FrostWisp());    // Magical ice creature
                monsterList.add(new IceDrake());     // Dragon-type ice monster
                monsterList.add(new PolarYeti());    // Strong ice beast
                break;

            case LAVA:
                System.out.println("=== Lava Theme ===");
                // Boss
                bossList.add(new VolcanoBoss());     // Boss with fire-based abilities
                // Monsters
                monsterList.add(new FlameImp());     // Quick fire attacker
                monsterList.add(new LavaSlime());    // Basic lava monster
                monsterList.add(new MagmaWolf());    // Aggressive fire monster
                monsterList.add(new IgneousGolem()); // Tanky fire monster
                break;

            case STEAMPUNK:
                System.out.println("=== Steampunk Theme ===");
                // Boss
                bossList.add(new ClockworkRequiem()); // Mechanical boss
                // Monsters
                monsterList.add(new RustyAutomaton()); // Basic mechanical monster
                monsterList.add(new SteamSpider());    // Quick mechanical monster
                monsterList.add(new MechanicalHound()); // Aggressive mechanical monster
                monsterList.add(new BatteryMantis());   // Electric-based monster
                break;
        }

        // Print current theme monsters and boss
        System.out.println("\nMonsters in " + currentTheme + " theme:");
        for (Monster monster : monsterList) {
            System.out.println("- " + monster.getName());
        }
        System.out.println("\nBoss in " + currentTheme + " theme:");
        for (BaseBoss boss : bossList) {
            System.out.println("- " + boss.getName());
        }
    }
    
    private void fight() {
        // เลือกมอนสเตอร์แบบสุ่ม
        Random random = new Random();
        currentEnemy = monsterList.get(random.nextInt(monsterList.size()));
        monsterHP = currentEnemy.getHp();
        
        // แสดงชื่อและ HP ของมอนสเตอร์
        enemyNameLabel.setText("Enemy: " + currentEnemy.getName());
        monsterHPLabel.setText("Monster HP: " + monsterHP);
        playerHPLabel.setText("Player HP: " + player.getHp());
        
        // เช็ค Speed เพื่อกำหนดใครเริ่มก่อน
        if (player.getSpd() > currentEnemy.getSpd()) {
            System.out.println("Player is faster! Player goes first.");
            // ผู้เล่นเริ่มก่อน (ไม่ต้องทำอะไรเพราะปุ่มควบคุมพร้อมใช้งานอยู่แล้ว)
        } else if (player.getSpd() < currentEnemy.getSpd()) {
            System.out.println("Enemy is faster! Enemy goes first.");
            enemyAttack();  // ศัตรูโจมตีก่อน
        } else {
            // ถ้า Speed เท่ากัน สุ่มว่าใครจะได้เริ่มก่อน
            if (random.nextBoolean()) {
                System.out.println("Equal speed! Random decided player goes first.");
                // ผู้เล่นเริ่มก่อน
            } else {
                System.out.println("Equal speed! Random decided enemy goes first.");
                enemyAttack();  // ศัตรูโจมตีก่อน
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
        System.out.println("\nEnemy's turn!");
        
        List<Skill> enemySkills = currentEnemy.getSkills();
        if (enemySkills != null && !enemySkills.isEmpty()) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(enemySkills.size());
            Skill chosenSkill = enemySkills.get(index);
            
            System.out.println(currentEnemy.getName() + " uses " + chosenSkill.getName() + "!");
            chosenSkill.use(currentEnemy, player);
            
            playerHPLabel.setText("Player HP: " + player.getHp());
        } else {
            if (doesAttackHit(currentEnemy, player)) {
                int damage = Math.max(currentEnemy.getAtk() - player.getDef(), 1);
                player.takeDamage(damage);
                System.out.println(currentEnemy.getName() + " attacks Player causing " + damage + " damage.");
                playerHPLabel.setText("Player HP: " + player.getHp());
            } else {
                System.out.println(currentEnemy.getName() + "'s attack missed the Player!");
            }
        }

        if (player.getHp() <= 0) {
            Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
            gameOverAlert.setTitle("Game Over");
            gameOverAlert.setHeaderText("You have been defeated!");
            gameOverAlert.setContentText("Better luck next time!");
            
            gameOverAlert.showAndWait().ifPresent(response -> {
                // กลับไปยังหน้าเมนูหลัก
                MainMenu mainMenu = new MainMenu(gameStage);
                gameStage.setScene(mainMenu.getCustomScene());
            });
        } else {
            System.out.println("Enemy turn complete. Now player's turn.");
        }
    }
    
    private void showSkillDialog() {
        Stage skillStage = new Stage();
        skillStage.initModality(Modality.APPLICATION_MODAL);
        skillStage.setTitle("Choose Skill");

        // ตั้งค่าขนาดหน้าต่างสกิล
        skillStage.setWidth(800);
        skillStage.setHeight(600);
        skillStage.setResizable(false);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #2c3e50;");

        // สร้างปุ่มสกิลทั้ง 6 สกิล
        if (player instanceof Player) {
            Player playerChar = (Player) player;
            List<Skill> playerSkills = playerChar.getSkills();
            
            for (Skill skill : playerSkills) {
                Button skillButton = new Button(skill.getName());
                skillButton.setPrefWidth(300);
                skillButton.setPrefHeight(50);
                
                // สร้าง tooltip แสดงรายละเอียดสกิล
                Tooltip tooltip = new Tooltip(
                    skill.getDescription() + "\n" +
                    "Mana Cost: " + skill.getManaCost() + "\n" +
                    "Cooldown: " + skill.getCooldown() + " turns"
                );
                skillButton.setTooltip(tooltip);
                
                // สไตล์ปุ่มสกิล
                skillButton.setStyle(
                    "-fx-font-size: 16px; " +
                    "-fx-background-color: #34495e; " +
                    "-fx-text-fill: white; " +
                    "-fx-padding: 10px; " +
                    "-fx-border-color: #455a64; " +
                    "-fx-border-width: 2px;"
                );
                
                // เมื่อเมาส์ชี้
                skillButton.setOnMouseEntered(e -> 
                    skillButton.setStyle(skillButton.getStyle() + "-fx-background-color: #455a64;"));
                
                // เมื่อเมาส์ออก
                skillButton.setOnMouseExited(e -> 
                    skillButton.setStyle(skillButton.getStyle() + "-fx-background-color: #34495e;"));
                
                skillButton.setOnAction(e -> {
                    useSkill(skill.getName());
                    skillStage.close();
                });
                
                layout.getChildren().add(skillButton);
            }
        }

        // ปุ่มย้อนกลับ
        Button backButton = new Button("Back");
        backButton.setPrefWidth(300);
        backButton.setPrefHeight(50);
        backButton.setStyle(
            "-fx-font-size: 16px; " +
            "-fx-background-color: #c0392b; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10px; " +
            "-fx-border-color: #922b21; " +
            "-fx-border-width: 2px;"
        );
        
        backButton.setOnMouseEntered(e -> 
            backButton.setStyle(backButton.getStyle() + "-fx-background-color: #922b21;"));
        
        backButton.setOnMouseExited(e -> 
            backButton.setStyle(backButton.getStyle() + "-fx-background-color: #c0392b;"));
        
        backButton.setOnAction(e -> skillStage.close());
        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout);
        skillStage.setScene(scene);
        skillStage.showAndWait();
    }
    
    private void useSkill(String skillName) {
        if (player instanceof Player) {
            Player playerChar = (Player) player;
            List<Skill> playerSkills = playerChar.getSkills();
            
            // หาสกิลที่เลือกจากรายการสกิลของผู้เล่น
            Skill selectedSkill = playerSkills.stream()
                .filter(skill -> skill.getName().equals(skillName))
                .findFirst()
                .orElse(null);
                
            if (selectedSkill != null) {
                System.out.println("Using skill: " + selectedSkill.getName());
                
                // บันทึกค่า HP ของศัตรูก่อนใช้สกิล
                int enemyHpBefore = currentEnemy.getHp();
                
                // ใช้สกิล
                selectedSkill.use(player, currentEnemy);
                
                // อัพเดต HP ของศัตรู
                int enemyHpAfter = currentEnemy.getHp();
                int damageDone = enemyHpBefore - enemyHpAfter;
                
                System.out.println("Player deals " + damageDone + " damage to " + currentEnemy.getName());
                
                monsterHP = enemyHpAfter;
                monsterHPLabel.setText("Monster HP: " + monsterHP);

                if (monsterHP <= 0) {
                    System.out.println("Enemy defeated!");
                    if (currentEnemy instanceof Monster) {
                        showVictoryAlert((Monster) currentEnemy);
                    }
                    fight();  // เริ่มรอบใหม่
                } else {
                    System.out.println("Player turn complete. Now enemy's turn.");
                    enemyAttack();
                }
            }
        }
    }
    
    private void useItem() {
        System.out.println("Using item...");
        playerHP += 10;
        playerHPLabel.setText("Player HP: " + playerHP);
    }
    
    private void printAllSkillInfo() {
        System.out.println("Available Skills:");
        for (String skillName : SkillRepository.getAllSkillNames()) {
            Skill skill = SkillRepository.getSkill(skillName);
            System.out.println("- " + skill.getName() + ": " + skill.getDescription() 
                + " [Mana Cost: " + skill.getManaCost() + ", Cooldown: " + skill.getCooldown() + "]");
        }
    }
    
    // Method คำนวณ hit chance โดยใช้สูตร: attackerAcc / (attackerAcc + defenderSpd)
    private boolean doesAttackHit(Character attacker, Character defender) {
        if (defender instanceof Player) {
            // ลดค่า multiplier จาก 0.4 เป็น 0.25 เพื่อให้มอนสเตอร์มีโอกาสโจมตีถูกมากขึ้น
            return Math.random() * 100 > defender.getSpd() * 0.25;
        }
        return Math.random() * 100 > defender.getSpd() * 0.2;
    }
    
    // ตัวอย่างวิธีใช้งานใน attack method
    private void performAttack(Character attacker, Character defender) {
        if (doesAttackHit(attacker, defender)) {
            int damage = attacker.getAtk() - defender.getDef();
            damage = Math.max(damage, 1);
            defender.takeDamage(damage);
            System.out.println(attacker.getName() + " attacks and deals " + damage + " to " + defender.getName());
        } else {
            System.out.println(attacker.getName() + "'s attack missed " + defender.getName());
        }
    }
    
    // เมธอดสำหรับเริ่มเทิร์นของผู้ใช้งาน
    public void startUserTurn() {
        System.out.println("User's turn starts again!");
        // โค้ดเปิดใช้งานควบคุมผู้เล่นและรีเซ็ตสถานะที่เกี่ยวข้องกับเทิร์นนี้
    }
    
    // เมธอด static ที่เรียกใช้เมธอด startUserTurn() จาก instance ปัจจุบัน
    public static void grantUserExtraTurn() {
        System.out.println("An extra turn has been granted to the user!");
        if (currentGame != null) {
            currentGame.startUserTurn();
        }
    }

    private void showVictoryAlert(Monster defeatedEnemy) {
        Alert victoryAlert = new Alert(Alert.AlertType.INFORMATION);
        victoryAlert.setTitle("Victory!");
        victoryAlert.setHeaderText("Enemy Defeated!");
        
        // Calculate XP gained based on enemy stats and difficulty
        int xpGained = calculateXPGained(defeatedEnemy);
        player.addXP(xpGained);
        
        StringBuilder message = new StringBuilder();
        message.append("You have defeated ").append(defeatedEnemy.getName()).append("!\n");
        message.append("XP Gained: ").append(xpGained).append("\n");
        message.append("Current Level: ").append(player.getLevel()).append("\n");
        message.append("XP: ").append(player.getCurrentXP()).append("/").append(player.getXPToNextLevel());
        
        if (bossList.stream().anyMatch(boss -> boss.getName().equals(defeatedEnemy.getName()))) {
            message.append("\nCongratulations on defeating the boss!");
            message.append("\nYou've cleared this theme!");
        }
        
        victoryAlert.setContentText(message.toString());
        victoryAlert.showAndWait();
    }

    private int calculateXPGained(Monster defeatedEnemy) {
        // Base XP calculation based on enemy stats
        int baseXP = (defeatedEnemy.getHp() / 10) + 
                     (defeatedEnemy.getAtk() * 2) + 
                     (defeatedEnemy.getDef() * 2) + 
                     (defeatedEnemy.getSpd());
                     
        // Bonus XP for boss
        if (bossList.stream().anyMatch(boss -> boss.getName().equals(defeatedEnemy.getName()))) {
            baseXP *= 2;
        }
        
        // Apply difficulty multiplier
        return (int)(baseXP * difficultyManager.getExpMultiplier());
    }
    
    public List<Skill> getSkills() {
        // แทนที่จะส่งคืน this.skills ให้ส่งคืนสกิลจาก player แทน
        return player.getSkills();
    }
}