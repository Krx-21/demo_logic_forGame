package skills;

import characters.Character;
import effects.SlowEffect;

public class WindSlash extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public WindSlash() {
        // manaCost = 18, cooldown = 0
        super("Wind Slash", 18, 0, "Deals wind damage based on user's Atk and reduces target's speed.");
        this.baseDamage = 15;
        this.multiplier = 1.4;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.10)), 1);
        target.takeDamage(damage);
        target.applyEffect(new SlowEffect(2, 5)); // ลด SPD 5 เป็นเวลา 2 เทิร์น
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}