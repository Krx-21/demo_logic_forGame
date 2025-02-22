package player;

import characters.Character;
import skills.*;
import items.*;
import java.util.ArrayList;
import java.util.List;
import items.Feather;

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
        recalcStats();
        System.out.println("เลเวลขึ้น! ตอนนี้เลเวล: " + level);
    }

    private int computeXPThreshold(int currentLevel) {
        return (int) (100 * Math.pow(currentLevel, 1.2));
    }

    private void recalcStats() {
        hp = (int) (baseHP + (baseHP * (level - 1) * hpGrowth));
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
}