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
import javafx.application.Platform; 
import player.Player;  
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
import javafx.stage.Modality; 
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
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import ui_screens.UI_GameSummary;
import game.GameStats;
import javafx.util.Duration;
import skills.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Dialog;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import javafx.scene.layout.Region;
import javafx.scene.control.DialogPane;  // เพิ่ม import นี้ที่ด้านบนของไฟล์

// หรือถ้าต้องการรวมกับ imports อื่นๆ ที่เกี่ยวกับ controls:
import javafx.scene.control.*;  // import ทั้งหมดใน package control
import java.util.Map;
import java.util.HashMap;
import effects.BaseDotEffect;
// ...existing imports...

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
    private VBox skillButtonBox;
    private Set<Monster> defeatedMonsters;  // เปลี่ยนจาก List เป็น Set
    private boolean isFightingBoss;
    private GameStats gameStats;
    private Dialog<String> skillDialog; // เปลี่ยนจาก Stage เป็น Dialog
    private List<Skill> playerSkills; // เพิ่มตัวแปร playerSkills
    private Label playerMPLabel; // เพิ่มตัวแปรสำหรับ Label MP
    private Map<String, Integer> skillCooldowns = new HashMap<>(); // เพิ่มตัวแปรใหม่
    private Label playerStatusLabel; // สำหรับแสดงสถานะของผู้เล่น
    private Label enemyStatusLabel;  // สำหรับแสดงสถานะของศัตรู

    public Game(Stage stage, Difficulty difficulty) {
        this.gameStage = stage;
        this.gameDifficulty = difficulty;
        this.gameStats = new GameStats();  // สร้าง instance ของ GameStats
        gameStage.setResizable(false); // ไม่ให้ปรับขนาดหน้าต่างได้
        currentGame = this;
        this.themeList = new ArrayList<>();
        this.monsterList = new ArrayList<>();
        this.bossList = new ArrayList<>();
        this.randomGenerator = new Random();
        this.roundsCompleted = 0;
        this.playerHP = 100; // Initial player HP
        this.monsterHP = 0; // Initial monster HP
        this.defeatedMonsters = new HashSet<>();  // ใช้ HashSet แทน ArrayList
        this.isFightingBoss = false;
        
        // ใน constructor ของ Game.java
        player = new Player("Adventurer");  // สร้าง Player แทน Character
        this.difficultyManager = new DifficultyManager(difficulty);
        this.playerSkills = ((Player)player).getSkills(); // เก็บ skills ของ player
        
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

        // Status labels
        playerHPLabel = new Label("Player HP: " + player.getHp());
        playerMPLabel = new Label("Player MP: " + ((Player)player).getMp() + "/" + ((Player)player).getMaxMp());
        monsterHPLabel = new Label("Monster HP: ---");
        enemyNameLabel = new Label("Enemy: ---");

        // สไตล์ Label
        String labelStyle = "-fx-font-size: 24px; -fx-text-fill: white;";
        playerHPLabel.setStyle(labelStyle);
        playerMPLabel.setStyle(labelStyle);
        monsterHPLabel.setStyle(labelStyle);
        enemyNameLabel.setStyle(labelStyle + "-fx-font-weight: bold;");

        // สร้าง Labels สำหรับแสดงสถานะ
        playerStatusLabel = new Label("Status Effects: None");
        enemyStatusLabel = new Label("Status Effects: None");
        
        // สไตล์ของ status labels
        String statusStyle = """
            -fx-font-size: 16px;
            -fx-text-fill: #e74c3c;
            -fx-font-style: italic;
            """;
        playerStatusLabel.setStyle(statusStyle);
        enemyStatusLabel.setStyle(statusStyle);

        // ปุ่มควบคุม
        mainButtonBox = new HBox(20);
        mainButtonBox.setAlignment(Pos.CENTER);
        Button skillButton = createStyledButton("Use Skill", e -> showSkillDialog());
        Button itemButton = createStyledButton("Use Item", e -> useItem());
        mainButtonBox.getChildren().addAll(skillButton, itemButton);

        // สร้าง skillButtonBox
        skillButtonBox = new VBox(10);
        skillButtonBox.setAlignment(Pos.CENTER);
        skillButtonBox.setPadding(new Insets(20));
        skillButtonBox.setVisible(false);
        skillButtonBox.setManaged(false);

        root.getChildren().addAll(
            playerHPLabel,
            playerMPLabel,
            playerStatusLabel,  // เพิ่ม status labels
            monsterHPLabel, 
            enemyNameLabel,
            enemyStatusLabel,   // เพิ่ม status labels
            mainButtonBox,
            skillButtonBox
        );

        fight();

        return new Scene(root, 1280, 720);
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
    
    private VBox createSkillButtons() {
        VBox skillButtonsBox = new VBox(10);
        skillButtonsBox.setPadding(new Insets(20));
        skillButtonsBox.setAlignment(Pos.CENTER);

        // แสดง MP
        Label mpLabel = new Label();
        mpLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3498db; -fx-font-weight: bold;");
        updateMPLabel(mpLabel);

        for (Skill skill : playerSkills) {
            Button skillButton = new Button(skill.getName());
            skillButton.setPrefWidth(300);
            skillButton.setPrefHeight(50);

            // สร้าง tooltip
            String tooltipText = String.format("%s\nMP: %d | Cooldown: %d\n%s",
                skill.getName(),
                skill.getManaCost(),
                skill.getCooldown(),
                skill.getDescription()
            );

            Tooltip tooltip = new Tooltip(tooltipText);
            tooltip.setStyle("""
                -fx-font-size: 14px;
                -fx-background-color: #2c3e50;
                -fx-text-fill: white;
                -fx-padding: 5px;
                """);
            
            tooltip.setShowDelay(Duration.millis(50));
            tooltip.setHideDelay(Duration.millis(100));
            tooltip.setShowDuration(Duration.INDEFINITE);
            
            skillButton.setTooltip(tooltip);

            // ปรับสีปุ่มตาม MP
            if (skill.getManaCost() > ((Player)player).getMp()) {
                skillButton.setStyle("-fx-background-color: #95a5a6;");
                skillButton.setDisable(true);
            } else {
                skillButton.setStyle("-fx-background-color: #3498db;");
            }

            // แก้ไขการจัดการ event
            skillButton.setOnAction(e -> {
                useSkill(skill.getName());
                showMainButtons(); // กลับไปแสดงปุ่มหลัก
            });

            skillButtonsBox.getChildren().add(skillButton);
        }

        skillButtonsBox.getChildren().add(0, mpLabel);
        return skillButtonsBox;
    }

    private void updateMPLabel(Label mpLabel) {
        if (player instanceof Player) {
            Player p = (Player) player;
            mpLabel.setText(String.format("MP: %d/%d", p.getMp(), p.getMaxMp()));
            
            // อัพเดทสีตามค่า MP
            if (p.getMp() < p.getMaxMp() * 0.3) {
                mpLabel.setTextFill(Color.RED);
            } else {
                mpLabel.setTextFill(Color.BLUE);
            }
        }
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
        // ถ้ากำลังสู้กับบอสอยู่ ให้สู้ต่อ
        if (isFightingBoss && !bossList.isEmpty()) {
            currentEnemy = bossList.get(0);
            System.out.println("Fighting boss: " + currentEnemy.getName());
        } 
        // ถ้าเคลียร์มอนสเตอร์ครบแล้ว และยังไม่ได้สู้กับบอส
        else if (defeatedMonsters.size() >= monsterList.size() && !bossList.isEmpty() && !isFightingBoss) {
            currentEnemy = bossList.get(0);
            isFightingBoss = true;
            System.out.println("Starting boss fight: " + currentEnemy.getName());
        }
        // ถ้าเคลียร์บอสแล้ว หรือไม่มีบอส
        else if ((defeatedMonsters.size() >= monsterList.size() && bossList.isEmpty()) || 
                 (isFightingBoss && bossList.isEmpty())) {
            // เปลี่ยนธีม
            if (!themeList.isEmpty()) {
                currentTheme = themeList.remove(0);
                defeatedMonsters.clear();
                isFightingBoss = false;
                System.out.println("\nChanging to new theme: " + currentTheme);
                initializeMonstersAndBosses();
                fight();
                return;
            } else {
                showGameCompleteAlert();
                return;
            }
        }
        // สุ่มมอนสเตอร์ที่ยังไม่เคยเจอ
        else {
            List<Monster> availableMonsters = monsterList.stream()
                    .filter(m -> !defeatedMonsters.contains(m))
                    .collect(Collectors.toList());
            
            if (!availableMonsters.isEmpty()) {
                currentEnemy = availableMonsters.get(randomGenerator.nextInt(availableMonsters.size()));
                System.out.println("Fighting monster: " + currentEnemy.getName());
            } else {
                System.out.println("No available monsters left!");
                return;
            }
        }

        // รีเซ็ต HP ของศัตรู
        resetEnemyStats();
        updateUI();
        determineFirstAttacker();
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
            int hpBefore = player.getHp();
            chosenSkill.use(currentEnemy, player);
            int hpAfter = player.getHp();
            int damageTaken = hpBefore - hpAfter;
            gameStats.addDamageTaken(damageTaken);  // เพิ่มค่าความเสียหายที่ได้รับ
            
            playerHPLabel.setText("Player HP: " + player.getHp());
        } else {
            if (doesAttackHit(currentEnemy, player)) {
                int damage = Math.max(currentEnemy.getAtk() - player.getDef(), 1);
                player.takeDamage(damage);
                gameStats.addDamageTaken(damage);  // เพิ่มค่าความเสียหายที่ได้รับ
                System.out.println(currentEnemy.getName() + " attacks Player causing " + damage + " damage.");
                playerHPLabel.setText("Player HP: " + player.getHp());
            }
        }

        if (player.getHp() <= 0) {
            showDefeatAlert();
        } else {
            // ควรเพิ่มการตรวจสอบและอัพเดตเอฟเฟคที่นี่
            if (player instanceof Player) {
                Player p = (Player) player;
                // ติ๊กเอฟเฟคของผู้เล่น
                for (BaseDotEffect effect : p.getActiveEffects()) {
                    effect.tickEffect(p);
                }
                // ลบเอฟเฟคที่หมดอายุ
                p.getActiveEffects().removeIf(effect -> effect.getDuration() <= 0);
                
                // ฟื้นฟู MP และลด cooldown
                p.restoreMp(10);
                reduceCooldowns();
            }
            
            // ติ๊กเอฟเฟคของศัตรู
            for (BaseDotEffect effect : currentEnemy.getActiveEffects()) {
                effect.tickEffect(currentEnemy);
            }
            // ลบเอฟเฟคที่หมดอายุ
            currentEnemy.getActiveEffects().removeIf(effect -> effect.getDuration() <= 0);
            
            updateUI();
            System.out.println("Enemy turn complete. Now player's turn.");
        }
    }
    
    private void showSkillDialog() {
        mainButtonBox.setVisible(false);
        mainButtonBox.setManaged(false);

        // เคลียร์ปุ่มเก่าและสร้างใหม่
        skillButtonBox.getChildren().clear();

        // แสดง MP
        Label mpLabel = new Label();
        mpLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #3498db; -fx-font-weight: bold;");
        updateMPLabel(mpLabel);
        skillButtonBox.getChildren().add(mpLabel);

        // สร้างปุ่มสกิล
        for (Skill skill : playerSkills) {
            Button skillButton = createSkillButton(skill);
            skillButtonBox.getChildren().add(skillButton);
        }

        // เพิ่ม spacer
        Region spacer = new Region();
        spacer.setPrefHeight(20);
        
        // สร้างปุ่ม Back
        Button backButton = new Button("Back");
        backButton.setPrefWidth(300);
        backButton.setPrefHeight(50);
        backButton.setStyle("""
            -fx-background-color: #e74c3c;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-background-radius: 5;
            """);
        
        backButton.setOnMouseEntered(e -> backButton.setStyle("""
            -fx-background-color: #c0392b;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-background-radius: 5;
            """));
        
        backButton.setOnMouseExited(e -> backButton.setStyle("""
            -fx-background-color: #e74c3c;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-background-radius: 5;
            """));
        
        backButton.setOnAction(e -> showMainButtons());

        skillButtonBox.getChildren().addAll(spacer, backButton);
        skillButtonBox.setVisible(true);
        skillButtonBox.setManaged(true);
    }
    
    private void useSkill(String skillName) {
        if (player instanceof Player) {
            Player playerChar = (Player) player;
            List<Skill> playerSkills = playerChar.getSkills();
            
            Skill selectedSkill = playerSkills.stream()
                .filter(skill -> skill.getName().equals(skillName))
                .findFirst()
                .orElse(null);
                
            // เช็ค cooldown ก่อนใช้สกิล
            if (selectedSkill != null && playerChar.getMp() >= selectedSkill.getManaCost()) {
                if (skillCooldowns.getOrDefault(skillName, 0) > 0) {
                    System.out.println("Skill is on cooldown: " + skillCooldowns.get(skillName) + " turns remaining!");
                    return;
                }

                System.out.println("Using skill: " + selectedSkill.getName());
                
                // หัก MP และตั้งค่า cooldown
                playerChar.useMp(selectedSkill.getManaCost());
                skillCooldowns.put(skillName, selectedSkill.getCooldown());
                
                int enemyHpBefore = currentEnemy.getHp();
                selectedSkill.use(player, currentEnemy);
                int enemyHpAfter = currentEnemy.getHp();
                
                // อัพเดตทั้ง HP และ MP
                monsterHP = enemyHpAfter;
                monsterHPLabel.setText("Monster HP: " + monsterHP);
                playerMPLabel.setText("Player MP: " + playerChar.getMp() + "/" + playerChar.getMaxMp());

                int damage = enemyHpBefore - enemyHpAfter;
                gameStats.addDamageDealt(damage);
                gameStats.incrementSkillsUsed();

                // ฟื้นฟู MP หลังจบเทิร์น
                playerChar.restoreMp(10);  // เปลี่ยนจาก 20 เป็น 10
                
                // อัพเดต UI
                updateUI();

                if (monsterHP <= 0) {
                    System.out.println("Enemy defeated!");
                    if (currentEnemy instanceof Monster) {
                        showVictoryAlert((Monster) currentEnemy);
                    }
                    fight();
                } else {
                    // ให้เอฟเฟคทำงานก่อนจบเทิร์น
                    for (BaseDotEffect effect : currentEnemy.getActiveEffects()) {
                        effect.tickEffect(currentEnemy);
                    }
                    currentEnemy.getActiveEffects().removeIf(effect -> effect.getDuration() <= 0);
                    
                    enemyAttack();
                }
            } else {
                System.out.println("Not enough MP to use skill!");
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
        // คำนวณ XP ก่อนที่จะเปลี่ยนแปลงสถานะใดๆ
        int baseXP = calculateBaseXP(defeatedEnemy);
        int bonusXP = isFightingBoss ? baseXP * 2 : 0;
        int totalXP = baseXP + bonusXP;

        Alert victoryAlert = new Alert(Alert.AlertType.INFORMATION);
        victoryAlert.setTitle("Victory!");

        if (isFightingBoss) {
            bossList.remove(0);
            victoryAlert.setHeaderText("Boss Defeated: " + defeatedEnemy.getName());
            victoryAlert.setContentText(String.format(
                "Congratulations!\nBase XP: %d\nBoss Bonus: %d\nTotal XP gained: %d",
                baseXP, bonusXP, totalXP
            ));
            gameStats.incrementBossesDefeated();
        } else {
            defeatedMonsters.add(defeatedEnemy);
            victoryAlert.setHeaderText("Defeated: " + defeatedEnemy.getName());
            victoryAlert.setContentText(String.format("Experience gained: %d", totalXP));
            gameStats.incrementMonstersDefeated();
        }

        victoryAlert.showAndWait();

        // ให้ XP กับผู้เล่นและเพิ่มในสถิติ
        if (player instanceof Player) {
            ((Player) player).addXP(totalXP);
            gameStats.addXP(totalXP);  // เพิ่ม XP ลงในสถิติ
            System.out.println("Player gained " + totalXP + " XP");
        }

        fight();
    }

    private int calculateBaseXP(Monster enemy) {
        // ปรับสูตรคำนวณ XP
        int baseXP = (enemy.getMaxHp() / 10) + 
                     (enemy.getAtk() * 2) + 
                     (enemy.getDef() * 2) + 
                     (enemy.getSpd());
        
        // ปรับตามความยาก
        switch (gameDifficulty) {
            case EASY:
                baseXP = (int)(baseXP * 0.8);
                break;
            case HARD:
                baseXP = (int)(baseXP * 1.2);
                break;
            default: // NORMAL
                break;
        }
        
        return baseXP;
    }

    private void showDefeatAlert() {
        Alert defeatAlert = new Alert(Alert.AlertType.INFORMATION);
        defeatAlert.setTitle("Defeat!");
        defeatAlert.setHeaderText("You have been defeated!");
        defeatAlert.setContentText("Better luck next time!");
        defeatAlert.showAndWait().ifPresent(response -> {
            // แสดงหน้าสรุปเกมที่มีปุ่ม Back to Main Menu อยู่แล้ว
            UI_GameSummary summary = new UI_GameSummary(gameStage, (Player)player, gameStats);
            gameStage.setScene(summary.getCustomScene());
        });
    }
    
    public List<Skill> getSkills() {
        // แทนที่จะส่งคืน this.skills ให้ส่งคืนสกิลจาก player แทน
        return player.getSkills();
    }

    private Button createDevButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(40);
        button.setStyle(
            "-fx-font-size: 16px; " +
            "-fx-background-color: #c0392b; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 10px; " +
            "-fx-border-color: #922b21; " +
            "-fx-border-width: 2px; " +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 5;"
        );
        
        button.setOnMouseEntered(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #922b21;"));
        
        button.setOnMouseExited(e -> 
            button.setStyle(button.getStyle() + "-fx-background-color: #c0392b;"));
        
        button.setOnAction(handler);
        return button;
    }

    private void devKill() {
        if (currentEnemy != null) {
            System.out.println("Developer used instant kill!");
            
            // คำนวณ XP
            int baseXP = calculateBaseXP(currentEnemy);
            int bonusXP = isFightingBoss ? baseXP * 2 : 0;
            int totalXP = baseXP + bonusXP;
            
            // ให้ศัตรูตาย
            currentEnemy.setHp(0);
            monsterHP = 0;
            monsterHPLabel.setText("Monster HP: " + monsterHP);

            System.out.println("Enemy defeated by dev command!");
            
            // แสดง Alert และให้ XP
            Alert victoryAlert = new Alert(Alert.AlertType.INFORMATION);
            victoryAlert.setTitle("Victory!");
            
            if (isFightingBoss) {
                bossList.remove(0);
                isFightingBoss = false;
                victoryAlert.setHeaderText("Boss Defeated: " + currentEnemy.getName());
                victoryAlert.setContentText(String.format(
                    "Congratulations!\nBase XP: %d\nBoss Bonus: %d\nTotal XP gained: %d",
                    baseXP, bonusXP, totalXP
                ));
                gameStats.incrementBossesDefeated();
            } else {
                if (currentEnemy instanceof Monster) {
                    defeatedMonsters.add((Monster)currentEnemy);
                }
                victoryAlert.setHeaderText("Defeated: " + currentEnemy.getName());
                victoryAlert.setContentText(String.format("Experience gained: %d", totalXP));
                gameStats.incrementMonstersDefeated();
            }

            victoryAlert.showAndWait();

            // ให้ XP กับผู้เล่น
            if (player instanceof Player) {
                ((Player) player).addXP(totalXP);
                System.out.println("Player gained " + totalXP + " XP");
            }

            gameStats.addXP(totalXP);
            
            fight();
        }
    }

    private int calculateBaseXP(Character enemy) {  // เปลี่ยนจาก Monster เป็น Character
        // ปรับสูตรคำนวณ XP
        int baseXP = (enemy.getMaxHp() / 10) + 
                     (enemy.getAtk() * 2) + 
                     (enemy.getDef() * 2) + 
                     (enemy.getSpd());
        
        // ปรับตามความยาก
        switch (gameDifficulty) {
            case EASY:
                baseXP = (int)(baseXP * 0.8);
                break;
            case HARD:
                baseXP = (int)(baseXP * 1.2);
                break;
            default: // NORMAL
                break;
        }
        
        return baseXP;
    }

    private void showGameCompleteAlert() {
        UI_GameSummary summary = new UI_GameSummary(gameStage, (Player)player, gameStats);
        gameStage.setScene(summary.getCustomScene()); // เปลี่ยนเป็น getCustomScene()
    }

    private void determineFirstAttacker() {
        // ตรวจสอบความเร็วเพื่อกำหนดผู้เริ่มต้น
        if (player.getSpd() > currentEnemy.getSpd()) {
            System.out.println("Player is faster! Player goes first.");
        } else if (player.getSpd() < currentEnemy.getSpd()) {
            System.out.println("Enemy is faster! Enemy goes first.");
            enemyAttack();
        } else {
            // ถ้าความเร็วเท่ากัน สุ่มผู้เริ่มต้น
            if (randomGenerator.nextBoolean()) {
                System.out.println("Equal speed! Random decided player goes first.");
            } else {
                System.out.println("Equal speed! Random decided enemy goes first.");
                enemyAttack();
            }
        }
    }

    private void resetEnemyStats() {
        // รีเซ็ต HP ของศัตรู
        if (isFightingBoss && currentEnemy instanceof BaseBoss) {
            currentEnemy = new BaseBoss(
                currentEnemy.getName(),
                currentEnemy.getMaxHp(),
                currentEnemy.getAtk(),
                currentEnemy.getDef(),
                currentEnemy.getSpd(),
                currentEnemy.getSkills(),
                ((BaseBoss)currentEnemy).getFieldEffect()
            );
        } else {
            currentEnemy = new Monster(
                currentEnemy.getName(),
                currentEnemy.getMaxHp(),
                currentEnemy.getAtk(),
                currentEnemy.getDef(),
                currentEnemy.getSpd(),
                currentEnemy.getSkills()
            );
        }
        monsterHP = currentEnemy.getHp();
    }

    private void updateUI() {
        // อัพเดต UI แสดงสถานะการต่อสู้
        enemyNameLabel.setText("Enemy: " + currentEnemy.getName());
        monsterHPLabel.setText(String.format("Monster HP: %d/%d", monsterHP, currentEnemy.getMaxHp()));
        playerHPLabel.setText(String.format("Player HP: %d/%d", player.getHp(), player.getMaxHp()));
        
        if (player instanceof Player) {
            Player p = (Player) player;
            playerMPLabel.setText(String.format("Player MP: %d/%d", p.getMp(), p.getMaxMp()));
            
            // อัพเดตสถานะของผู้เล่น
            List<String> playerEffects = p.getActiveEffects().stream()
                .map(effect -> String.format("%s (%d turns)", 
                    effect.getName(), 
                    effect.getDuration()))
                .collect(Collectors.toList());
            
            if (playerEffects.isEmpty()) {
                playerStatusLabel.setText("Status Effects: None");
                playerStatusLabel.setStyle("-fx-text-fill: #95a5a6;"); // สีเทาถ้าไม่มีสถานะ
            } else {
                playerStatusLabel.setText("Status Effects: " + String.join(", ", playerEffects));
                playerStatusLabel.setStyle("-fx-text-fill: #e74c3c;"); // สีแดงถ้ามีสถานะ
            }
        }
        
        // อัพเดตสถานะของศัตรู
        List<String> enemyEffects = currentEnemy.getActiveEffects().stream()
            .map(effect -> String.format("%s (%d turns)", 
                effect.getName(), 
                effect.getDuration()))
            .collect(Collectors.toList());
        
        if (enemyEffects.isEmpty()) {
            enemyStatusLabel.setText("Status Effects: None");
            enemyStatusLabel.setStyle("-fx-text-fill: #95a5a6;"); // สีเทาถ้าไม่มีสถานะ
        } else {
            enemyStatusLabel.setText("Status Effects: " + String.join(", ", enemyEffects));
            enemyStatusLabel.setStyle("-fx-text-fill: #e74c3c;"); // สีแดงถ้ามีสถานะ
        }

        // แสดงข้อความว่ากำลังสู้กับใคร
        if (isFightingBoss) {
            System.out.println("Fighting boss: " + currentEnemy.getName());
        } else {
            System.out.println("Fighting monster: " + currentEnemy.getName());
        }
    }

    private Button createSkillButton(Skill skill) {
        Button skillButton = new Button(skill.getName());
        skillButton.setPrefWidth(300);
        skillButton.setPrefHeight(50);

        // สร้าง tooltip
        String tooltipText = String.format("%s\nMP: %d | Cooldown: %d\n%s",
            skill.getName(),
            skill.getManaCost(),
            skill.getCooldown(),
            skill.getDescription()
        );

        Tooltip tooltip = new Tooltip(tooltipText);
        tooltip.setStyle("""
            -fx-font-size: 14px;
            -fx-background-color: #2c3e50;
            -fx-text-fill: white;
            -fx-padding: 5px;
            """);
        
        tooltip.setShowDelay(Duration.millis(50));
        tooltip.setHideDelay(Duration.millis(100));
        tooltip.setShowDuration(Duration.INDEFINITE);
        
        skillButton.setTooltip(tooltip);

        // ปรับสีปุ่มตาม MP และ Cooldown
        int currentCooldown = skillCooldowns.getOrDefault(skill.getName(), 0);
        if (currentCooldown > 0) {
            skillButton.setStyle("""
                -fx-background-color: #7f8c8d;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                """);
            skillButton.setDisable(true);
            skillButton.setText(String.format("%s (CD: %d)", skill.getName(), currentCooldown));
        } else if (skill.getManaCost() > ((Player)player).getMp()) {
            skillButton.setStyle("""
                -fx-background-color: #95a5a6;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                """);
            skillButton.setDisable(true);
        } else {
            skillButton.setStyle("""
                -fx-background-color: #3498db;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                """);
        }

        skillButton.setOnAction(e -> {
            useSkill(skill.getName());
            showMainButtons();
        });

        return skillButton;
    }

    // เพิ่มเมธอดลด cooldown ทุกเทิร์น
    private void reduceCooldowns() {
        Map<String, Integer> updatedCooldowns = new HashMap<>();
        for (Map.Entry<String, Integer> entry : skillCooldowns.entrySet()) {
            int remainingCooldown = entry.getValue() - 1;
            if (remainingCooldown > 0) {
                updatedCooldowns.put(entry.getKey(), remainingCooldown);
            }
        }
        skillCooldowns = updatedCooldowns;
    }
}