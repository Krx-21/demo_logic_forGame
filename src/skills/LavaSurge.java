package skills;

import characters.Character;
import effects.LavaSurgeEffect;

public class LavaSurge extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public LavaSurge() {
        super("Lava Surge", 30, 15, "Summons a torrent of lava that continuously damages the target each turn until dispelled.");
        this.baseDamage = 35;
        this.multiplier = 1.5;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        // นำเอฟเฟกต์ที่ส่งผลดาเมจต่อเนื่องไปใช้กับ target
        target.applyEffect(new LavaSurgeEffect());
        System.out.println(user.getName() + " casts Lava Surge, dealing an initial " + damage + " damage and applying a continuous lava field!");
    }
}
