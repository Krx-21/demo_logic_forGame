package skills;

import characters.Character;
import effects.DefenseDownEffect;

public class EarthQuake extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public EarthQuake() {
        // manaCost = 30, cooldown = 0
        super("Earth Quake", 30, 0, "Deals earth damage based on user's Atk and lowers target's defense.");
        this.baseDamage = 40;
        this.multiplier = 1.2;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.30)), 1);
        target.takeDamage(damage);
        target.applyEffect(new DefenseDownEffect(3, 10)); // ลด DEF 10 เป็นเวลา 3 เทิร์น
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}