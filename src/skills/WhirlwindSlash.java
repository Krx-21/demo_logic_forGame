package skills;

import characters.Character;

public class WhirlwindSlash extends Skill {
    public WhirlwindSlash() {
        super("Whirlwind Slash", 40, 5, "Spin attack dealing 250% ATK damage");
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(user.getAtk() * 2.5) - target.getDef(), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " performs a spinning attack dealing " + damage + " damage!");
    }
}
