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
        super("Fire Toss", 20, 0, "Throws a fireball at a long range, dealing initial fire damage.");
        this.baseDamage = 25;
        this.multiplier = 1.5;
    }

    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.15)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " throws a fireball at " + target.getName() + ", dealing " + damage + " fire damage.");
    }
}