package skills;

import characters.Character;

public class MetalPunch extends Skill {
    private int baseDamage;
    private double multiplier;
    
    public MetalPunch() {
        // ปรับค่า manaCost และ cooldown ตามต้องการ
        super("Metal Punch", 20, 4, "Delivers a heavy melee strike with a metallic fist, dealing significant damage.");
        this.baseDamage = 25;
        this.multiplier = 2.0;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจ: baseDamage + (user.getAtk() * multiplier) หักลบด้วยผลกระทบจาก target.getDef()
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.25)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Metal Punch on " + target.getName() + ", dealing " + damage + " damage!");
    }
}