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
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import effects.BaseDotEffect;

public class Player extends Character {
    private int level;
    private int currentXP;
    private int xpToNextLevel;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpd;
    private int baseMP;      // MP พื้นฐาน
    private int maxMp;       // MP สูงสุด
    private int mp;          // MP ปัจจุบัน
    private double hpGrowth;
    private double atkGrowth;
    private double defGrowth;
    private double spdGrowth;
    private double mpGrowth; // อัตราการเพิ่ม MP ต่อเลเวล
    private List<Feather> feathers;
    private List<Skill> skills;
    private static final int BASE_MP = 100;
    private static final double MP_GROWTH = 0.1;
    private static final int MAX_MP_BONUS = 50; // MP เพิ่มเติมสูงสุด

    public Player(String name) {
        super(name, 500, 70, 45, 80);
        
        // Initialize base stats
        level = 1;
        currentXP = 0;
        xpToNextLevel = computeXPThreshold(level);
        
        baseHP = 300;
        baseAtk = 45;
        baseDef = 30;
        baseSpd = 40;
        this.baseMP = BASE_MP;  // Initialize with constant
        
        // Set growth rates
        hpGrowth = 0.12;
        atkGrowth = 0.10;
        defGrowth = 0.08;
        spdGrowth = 0.10;
        this.mpGrowth = MP_GROWTH;
        
        // Initialize MP
        this.maxMp = BASE_MP;
        this.mp = BASE_MP;  // Start with full MP
        
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
        recalcStats();  // Calculate all stats properly
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

    @Override
    protected void recalcStats() {
        // Calculate base stats first
        maxHp = (int) (baseHP + (baseHP * (level - 1) * hpGrowth));
        hp = maxHp;  // Reset HP to max after recalc
        
        // Calculate MP with limit
        maxMp = Math.min(BASE_MP + (int)(BASE_MP * (level - 1) * mpGrowth), BASE_MP + MAX_MP_BONUS);
        mp = Math.min(mp, maxMp);  // Limit current MP to new max
        
        // Calculate other stats
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

    public int getMp() {
        return mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMp(int mp) {
        this.mp = Math.max(0, Math.min(mp, maxMp));
    }

    public boolean useMp(int amount) {
        if (mp >= amount) {
            mp -= amount;
            return true;
        }
        return false;
    }

    public void restoreMp(int amount) {
        int maxPossibleMp = BASE_MP + MAX_MP_BONUS; // MP สูงสุดที่เป็นไปได้
        mp = Math.min(Math.min(mp + amount, maxMp), maxPossibleMp);
    }

    private void showStatSelectionDialog() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("HP", "HP", "ATK", "DEF", "SPD");
        dialog.setTitle("Level Up!");
        dialog.setHeaderText("Level " + level + " - Choose a stat to increase");
        dialog.setContentText(
            String.format("Current Stats:\nHP: %d/%d\nMP: %d/%d\nATK: %d\nDEF: %d\nSPD: %d", 
                hp, maxHp, mp, maxMp, atk, def, spd)  // Added MP display
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
        
        statsAlert.setContentText(
            String.format("HP: %d/%d (Base: %d)\n", hp, maxHp, baseHP) +
            String.format("MP: %d/%d (Base: %d)\n", mp, maxMp, BASE_MP) +  // Added MP display
            String.format("ATK: %d (Base: %d)\n", atk, baseAtk) +
            String.format("DEF: %d (Base: %d)\n", def, baseDef) +
            String.format("SPD: %d (Base: %d)", spd, baseSpd)
        );
        statsAlert.showAndWait();
    }
    
    @Override
    public List<BaseDotEffect> getActiveEffects() {
        return super.getActiveEffects();
    }
}