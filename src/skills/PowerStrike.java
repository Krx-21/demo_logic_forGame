package skills;

import characters.Character;

public class PowerStrike extends Skill {
    public PowerStrike() {
        super("Power Strike", 25, 3, "A powerful strike that deals 200% ATK damage");
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(user.getAtk() * 2.0) - target.getDef(), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " unleashes a powerful strike dealing " + damage + " damage!");
    }
}
