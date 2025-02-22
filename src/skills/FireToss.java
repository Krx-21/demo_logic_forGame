package skills;

import characters.Character;
import effects.FireBurn;

/*
 * FireToss: สกิลโฟกัสด้านไฟ โจมตีเป้าหมายด้วยดาเมจและติด FireBurn effect
 */

public class FireToss extends Skill {
    private double multiplier;
    private int baseDamage;

    public FireToss() {
        super("Fire Toss", 20, 0, "Deals fire damage based on user's Atk and applies burning effect.");
        this.baseDamage = 25;
        this.multiplier = 1.5;
    }

    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.15)), 1);
        target.takeDamage(damage);
        // FireBurn ต้องมี constructor รับ (float burnRate, int duration)
        target.applyEffect(new FireBurn(3.0f, 3));
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}