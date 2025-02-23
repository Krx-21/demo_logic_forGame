package player;

import characters.Character;
import skills.*;
import items.*;
import java.util.ArrayList;
import java.util.List;
import items.Feather;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;

public class Player extends Character {
    private int level;
    private int currentXP;
    private int xpToNextLevel;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpd;
    private double hpGrowth;
    private double atkGrowth;
    private double defGrowth;
    private double spdGrowth;
    private List<Feather> feathers;
    private List<Skill> skills;

    public Player(String name) {
        // ปรับค่าสถานะเริ่มต้นให้สูงขึ้น
        super(name, 500, 70, 45, 80);  // HP:500, ATK:70, DEF:45, SPD:80
        
        level = 1;
        currentXP = 0;
        xpToNextLevel = computeXPThreshold(level);
        
        // เพิ่มค่าเบสให้สูงขึ้น
        baseHP = 300;    // เพิ่มจาก 150
        baseAtk = 45;    // เพิ่มจาก 30
        baseDef = 30;    // เพิ่มจาก 20
        baseSpd = 40;    // เพิ่มจาก 25
        
        // เพิ่มอัตราการเพิ่มค่าสถานะต่อเลเวล
        hpGrowth = 0.12;    // เพิ่มจาก 0.08
        atkGrowth = 0.10;   // เพิ่มจาก 0.07
        defGrowth = 0.08;   // เพิ่มจาก 0.06
        spdGrowth = 0.10;   // เพิ่มจาก 0.06
        
        // คงรายการสกิลเดิม
        this.skills = new ArrayList<>();
        this.skills.add(new BasicSlash());
        this.skills.add(new PowerStrike());
        this.skills.add(new FlamingBlade());
        this.skills.add(new DefensiveStance());
        this.skills.add(new WhirlwindSlash());
        this.skills.add(new ArcaneSlash());
        
        feathers = new ArrayList<>();
        feathers.add(new FeatherOfBeginning());
        recalcStats();
    }

    // Getter สำหรับ skills
    @Override
    public List<Skill> getSkills() {
        return this.skills;
    }

    public void addXP(int xp) {
        currentXP += xp;
        while (currentXP >= xpToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        currentXP -= xpToNextLevel;
        xpToNextLevel = computeXPThreshold(level);
        
        // คำนวณสเตตัสที่เพิ่มขึ้นตามเลเวล
        recalcStats();
        
        System.out.println("Level Up! Current Level: " + level);
        
        // แสดงหน้าต่างให้เลือกอัพสเตตัส
        Platform.runLater(() -> showStatSelectionDialog());
    }

    private int computeXPThreshold(int currentLevel) {
        return (int) (100 * Math.pow(currentLevel, 1.2));
    }

    private void recalcStats() {
        maxHp = (int) (baseHP + (baseHP * (level - 1) * hpGrowth));
        hp = maxHp;  // รีเซ็ต HP ให้เต็มเมื่อเลเวลอัพ
        atk = (int) (baseAtk + (baseAtk * (level - 1) * atkGrowth));
        def = (int) (baseDef + (baseDef * (level - 1) * defGrowth));
        spd = (int) (baseSpd + (baseSpd * (level - 1) * spdGrowth));
    }

    public List<Feather> getFeathers() {
        return feathers;
    }

    public void addFeather(Feather feather) {
        feathers.add(feather);
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getCurrentXP() {
        return currentXP;
    }
    
    public int getXPToNextLevel() {
        return xpToNextLevel;
    }

    private void showStatSelectionDialog() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("HP", "HP", "ATK", "DEF", "SPD");
        dialog.setTitle("Level Up!");
        dialog.setHeaderText("Level " + level + " - Choose a stat to increase");
        dialog.setContentText(
            String.format("Current Stats:\nHP: %d/%d\nATK: %d\nDEF: %d\nSPD: %d", 
                hp, maxHp, atk, def, spd)
        );

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(stat -> {
            String message = "";
            switch(stat) {
                case "HP":
                    baseHP += 20;
                    message = "Base HP increased by 20!";
                    break;
                case "ATK":
                    baseAtk += 5;
                    message = "Base ATK increased by 5!";
                    break;
                case "DEF":
                    baseDef += 5;
                    message = "Base DEF increased by 5!";
                    break;
                case "SPD":
                    baseSpd += 3;
                    message = "Base SPD increased by 3!";
                    break;
            }
            System.out.println(message);
            
            recalcStats();
            showCurrentStats();
        });
    }

    private void showCurrentStats() {
        Alert statsAlert = new Alert(Alert.AlertType.INFORMATION);
        statsAlert.setTitle("Current Stats");
        statsAlert.setHeaderText("Level " + level + " Stats");
        
        // แสดงทั้งค่าพื้นฐานและค่าที่เพิ่มขึ้น
        statsAlert.setContentText(
            String.format("HP: %d/%d (Base: %d)\n", hp, maxHp, baseHP) +
            String.format("ATK: %d (Base: %d)\n", atk, baseAtk) +
            String.format("DEF: %d (Base: %d)\n", def, baseDef) +
            String.format("SPD: %d (Base: %d)", spd, baseSpd)
        );
        statsAlert.showAndWait();
    }
}