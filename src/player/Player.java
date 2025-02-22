package player;

import characters.Character;
import items.Feather;
import items.FeatherOfBeginning;
import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private int level;
    private int currentXP;
    private int xpToNextLevel;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpd;
    private List<Feather> feathers;

    public Player() {
        super("Player", 100, 20, 15, 10);
        level = 1;
        currentXP = 0;
        xpToNextLevel = computeXPThreshold(level);
        baseHP = 100;
        baseAtk = 20;
        baseDef = 15;
        baseSpd = 10;
        feathers = new ArrayList<>();
        feathers.add(new FeatherOfBeginning());
        recalcStats();
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
        hp = (int) (baseHP + (baseHP * (level - 1) * 0.05));
        atk = (int) (baseAtk + (baseAtk * (level - 1) * 0.05));
        def = (int) (baseDef + (baseDef * (level - 1) * 0.05));
        spd = (int) (baseSpd + (baseSpd * (level - 1) * 0.05));
    }

    public List<Feather> getFeathers() {
        return feathers;
    }

    public void addFeather(Feather feather) {
        feathers.add(feather);
    }
}