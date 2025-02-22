package skills;

import characters.Character;

public class BasicSlash extends Skill {
    public BasicSlash() {
        super("Basic Slash", 0, 0, "A basic sword attack that deals 120% ATK damage");
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(user.getAtk() * 1.2) - target.getDef(), 1); // เพิ่มจาก 1.0 เป็น 1.2
        target.takeDamage(damage);
        System.out.println(user.getName() + " performs a basic slash dealing " + damage + " damage!");
    }
}
