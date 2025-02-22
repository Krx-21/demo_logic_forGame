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
    private Character player;
    private Character currentEnemy;
    
    public Game(Stage stage, Difficulty difficulty) {
        currentGame = this;
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
        player = new Character("Adventurer", 100, 30, 20, 60, 80);
        
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
        
        int damage = 0;
        boolean useEnemySkill = false;
        
        // หากระดับความยาก (gameDifficulty) สูง ให้คู่ต่อสู้มีโอกาสใช้สกิลโจมตีแทนพื้นฐาน
        if (gameDifficulty == Difficulty.HARD || gameDifficulty == Difficulty.NIGHTMARE) {
            useEnemySkill = randomGenerator.nextBoolean(); // 50% chance ใช้สกิล
        }
        
        if (useEnemySkill && currentEnemy.getSkills() != null && !currentEnemy.getSkills().isEmpty()) {
            // เลือกสกิลแบบสุ่ม
            int index = randomGenerator.nextInt(currentEnemy.getSkills().size());
            Skill enemySkill = currentEnemy.getSkills().get(index);
            System.out.println(currentEnemy.getName() + " uses " + enemySkill.getName() + "!");
            enemySkill.use(currentEnemy, player);
        } else {
            // ใช้การโจมตีพื้นฐาน
            if (doesAttackHit(currentEnemy, player)) {  // เพิ่มการเช็ค hit chance
                if (currentEnemy instanceof IceDrake) {
                    damage = ((IceDrake) currentEnemy).performAttack();
                } else {
                    damage = currentEnemy.getAtk();
                }
                System.out.println(currentEnemy.getName() + " attacks Player causing " + damage + " damage.");
                player.takeDamage(damage);
            } else {
                System.out.println(currentEnemy.getName() + "'s attack missed the Player!");
                return;  // ถ้าพลาด ไม่ต้องทำ damage
            }
        }
        
        // อัปเดตค่า HP ของผู้เล่น และ Label
        playerHP = player.getHp();
        playerHPLabel.setText("Player HP: " + playerHP);
        
        // ตัวอย่างปรับ Status เสริมจากคู่ต่อสู้ในระดับความยากสูง (เช่น บังคับให้เกิด Burn effect)
        if (gameDifficulty == Difficulty.HARD || gameDifficulty == Difficulty.NIGHTMARE) {
            System.out.println(currentEnemy.getName() + " inflicts additional Burn effect on the Player due to high difficulty.");
            // ใช้สกิลหรือเอฟเฟกต์ Burn โดยปรับค่าสำหรับระดับความยากนี้
            player.applyEffect(new effects.FireBurn(2.0f, 2));
        }
        
        if (playerHP <= 0) {
            System.out.println("Game Over! Returning to Main Menu...");
            MainMenu mainMenu = new MainMenu(gameStage);
            gameStage.setScene(mainMenu.getScene());
        }
    }
    
    private void showSkillDialog() {
        // สร้างรายชื่อสกิลจาก SkillRepository โดยใช้ explicit loop เพราะ getAllSkillNames() คืน Iterable<String>
        List<String> skillNames = new ArrayList<>();
        for (String skill : SkillRepository.getAllSkillNames()) {
            skillNames.add(skill);
        }
        
        // สร้าง ChoiceDialog โดยกำหนดค่า default เป็นสกิลตัวแรก
        ChoiceDialog<String> dialog = new ChoiceDialog<>(skillNames.get(0), skillNames);
        dialog.setTitle("Choose Skill");
        dialog.setHeaderText("Select a skill to use:");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(this::useSkill);
    }
    
    private void useSkill(String skillName) {
        Skill skill = SkillRepository.getSkill(skillName);
        if (skill != null) {
            System.out.println("Using skill: " + skill.getName());
            
            // บันทึกค่า HP ของผู้ถูกโจมตีก่อนใช้สกิล
            int enemyHpBefore = currentEnemy.getHp();
            // ใช้สกิลโจมตี
            skill.use(player, currentEnemy);
            // อัปเดตค่า HP ของศัตรูหลังโจมตี และคำนวณดาเมจที่ทำได้
            int enemyHpAfter = currentEnemy.getHp();
            int damageDone = enemyHpBefore - enemyHpAfter;
            System.out.println("Player deals " + damageDone + " damage to " + currentEnemy.getName());
            
            monsterHP = enemyHpAfter;
            monsterHPLabel.setText("Monster HP: " + monsterHP);

            if (monsterHP <= 0) {
                System.out.println("Enemy defeated!");
                fight();  // เริ่มรอบใหม่
            } else {
                System.out.println("Player turn complete. Now enemy's turn.");
                enemyAttack();
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
}