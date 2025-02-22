package skills;

import characters.Character;
import effects.SlowEffect;

public class WindSlash extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public WindSlash() {
        super("Wind Slash", 0, 18, "Deals wind damage based on user's Atk and reduces target's speed.");
        // กำหนดค่าพารามิเตอร์สำหรับสูตร
        this.baseDamage = 15;
        this.multiplier = 1.4;
    }
    
    @Override
    public void use(Character user, Character target) {
        // สูตรคำนวณ: damage = max( baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.10), 1 )
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.10)), 1);
        target.takeDamage(damage);
        // ใช้เอฟเฟกต์ลดความเร็วเป็นเวลา 2 เทิร์น
        target.applyEffect(new SlowEffect(2));
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}