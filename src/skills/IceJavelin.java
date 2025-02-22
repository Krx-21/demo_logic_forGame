package skills;

import characters.Character;
import effects.SlowEffect;
import java.util.Random;

public class IceJavelin extends Skill {
    private double multiplier;
    private int baseDamage;
    private Random random;

    public IceJavelin() {
        super("Ice Javelin", 15, 4, "Throws an ice javelin at a distant target, dealing damage and possibly reducing their speed.");
        this.baseDamage = 20;
        this.multiplier = 1.7;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " throws an Ice Javelin at " + target.getName() + ", dealing " + damage + " damage.");
        
        // 40% chance to apply slow effect reducing target's speed
        if (random.nextDouble() < 0.4) {
            target.applyEffect(new SlowEffect(2, 15)); // duration 2 turns, reduce speed by 15 units
            System.out.println(target.getName() + " is slowed by the impact of the Ice Javelin!");
        }
    }
}