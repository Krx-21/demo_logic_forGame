package skills;

import characters.Character;
import effects.ChillEffect;

public class ChillingTouch extends Skill {
    private int baseDamage;
    private double multiplier;
    
    public ChillingTouch() {
        // ตัวอย่างค่า: manaCost = 14, cooldown = 4 (ปรับได้ตามต้องการ)
        super("Chilling Touch", 14, 4, "Rushes towards the target, dealing moderate ice damage and reducing enemy speed.");
        this.baseDamage = 24;
        this.multiplier = 1.6;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Chilling Touch on " + target.getName() 
                           + ", dealing " + damage + " ice damage.");
        
        // ลดความเร็วของเป้าหมายด้วย ChillEffect (duration 2 turns, ลดความเร็ว 10 หน่วย)
        target.applyEffect(new ChillEffect(2, 10));
        System.out.println(target.getName() + "'s speed is reduced by Chilling Touch.");
    }
}
