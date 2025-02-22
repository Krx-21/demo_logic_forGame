package skills;

import effects.FireBurn;
import characters.Character;

/*
 * FireToss: สกิลโฟกัสด้านไฟ โจมตีเป้าหมายด้วยดาเมจและติด FireBurn effect
 */

public class FireToss extends Skill {
    private int baseDamage;

    public FireToss() {
        super("Fire Toss", 30, 10, "Throws a fireball to deal damage and apply burn effect.");
        this.baseDamage = 30;
    }

    @Override
    public void use(characters.Character user, characters.Character target) {
        int damage = baseDamage + (int) (user.getAtk() * 0.5);
        target.takeDamage(damage);
        target.applyEffect(new FireBurn(5, 3));
    }
}