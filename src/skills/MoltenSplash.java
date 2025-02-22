package skills;

import characters.Character;
import effects.MoltenSplashEffect;
import java.util.Random;

public class MoltenSplash extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;

    public MoltenSplash() {
        // ตัวอย่าง: manaCost = 15, cooldown = 5
        super("Molten Splash", 15, 5, "Flings molten lava at the target, with a chance to inflict a 'Scorched' status that temporarily reduces DEF.");
        this.baseDamage = 20;
        this.multiplier = 1.4;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Molten Splash on " + target.getName() + ", dealing " + damage + " damage.");
        
        // โอกาส 40% ที่จะทำให้เป้าหมายติดสถานะ "Scorched" (ลด DEF ชั่วคราว)
        if (random.nextDouble() < 0.4) {
            target.applyEffect(new MoltenSplashEffect(2)); // ลด DEF เป็นเวลา 2 เทิร์น
            System.out.println(target.getName() + " is scorched! DEF is temporarily reduced.");
        }
    }
}
