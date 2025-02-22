package skills;

import characters.Character;

public class FrostPound extends Skill {
    private int baseDamage;
    private double multiplier;
    
    public FrostPound() {
        // ตัวอย่างค่า: manaCost = 30, cooldown = 7 (ปรับแก้ได้ตามต้องการ)
        super("Frost Pound", 30, 7, "Delivers a powerful melee blow with a massive icy arm, dealing high damage.");
        this.baseDamage = 40;
        this.multiplier = 2.5;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจโดยใช้ baseDamage + (Atk * multiplier) หักลบด้วยผลกระทบจาก Def ของเป้าหมาย
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.3)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Frost Pound on " + target.getName() 
                            + ", dealing " + damage + " damage!");
    }
}