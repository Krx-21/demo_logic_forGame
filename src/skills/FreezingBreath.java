package skills;

import characters.Character;
import effects.FreezeEffect;
import java.util.Random;

public class FreezingBreath extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;
    
    public FreezingBreath() {
        // ตัวอย่างค่า: manaCost = 25, cooldown = 6 (ปรับได้ตามความต้องการ)
        super("Freezing Breath", 25, 6, "Unleashes a cone of freezing air, dealing damage and possibly freezing the target briefly.");
        this.baseDamage = 28;
        this.multiplier = 1.8;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " breathes freezing air at " + target.getName() + ", dealing " + damage + " damage.");
        
        // โอกาส 35% ที่จะติดสถานะ Freeze เป็นเวลา 1 เทิร์น
        if(random.nextDouble() < 0.35) {
            target.applyEffect(new FreezeEffect(1));
            System.out.println(target.getName() + " is frozen by the chilling breath!");
        }
    }
}
