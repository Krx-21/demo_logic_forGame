package skills;

import characters.Character;
import java.util.Random;

public class GearCannon extends Skill {
    private double multiplier;
    private int baseDamage;
    private Random random;
    
    public GearCannon() {
        // ในที่นี้ manaCost และ cooldown ตั้งเป็น 0 สามารถปรับได้ตามต้องการ
        super("Gear Cannon", 0, 0, "Transforms gears into a steam-powered cannon, firing high-powered bullets at a single target or as an AoE scatter shot.");
        this.baseDamage = 35;
        this.multiplier = 2.0;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        double modeChance = random.nextDouble();
        if(modeChance < 0.3) {
            System.out.println(user.getName() + " fires Gear Cannon in AoE mode (scatter shot)!");
            int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier * 0.8) - (target.getDef() * 0.15)), 1);
            target.takeDamage(damage);
            System.out.println("Scatter shot deals " + damage + " damage to " + target.getName() + " (and possibly others)!");
        } else {
            System.out.println(user.getName() + " fires Gear Cannon at a single target!");
            int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
            target.takeDamage(damage);
            System.out.println("Gear Cannon hits " + target.getName() + " for " + damage + " damage!");
        }
    }
}