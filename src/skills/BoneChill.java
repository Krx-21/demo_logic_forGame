package skills;

import characters.Character;
import effects.ChillEffect;
import java.util.Random;

public class BoneChill extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;
    
    public BoneChill() {
        // ตัวอย่างค่า: manaCost = 12, cooldown = 4
        super("Bone Chill", 12, 4, "Strikes with an icy baton causing scattered fragments of ice. The target may be afflicted with Chill, reducing their speed.");
        this.baseDamage = 18;
        this.multiplier = 1.5;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.1)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Bone Chill on " + target.getName() + ", dealing " + damage + " damage.");
        
        // โอกาส 30% ที่จะทำให้เป้าหมายติดสถานะ Chill (ลด Speed)
        if(random.nextDouble() < 0.3) {
            target.applyEffect(new ChillEffect(2, 10)); // duration 2 turns, ลด speed 10 หน่วย
            System.out.println(target.getName() + " is chilled and becomes sluggish!");
        }
    }
}