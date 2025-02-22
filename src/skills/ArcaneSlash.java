package skills;

import characters.Character;

public class ArcaneSlash extends Skill {
    public ArcaneSlash() {
        super("Arcane Slash", 45, 6, "Magical sword attack that deals 200% ATK damage and ignores 50% of target's defense");
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(user.getAtk() * 2.0) - (int)(target.getDef() * 0.5), 1); // เพิ่มดาเมจและการเจาะเกราะ
        target.takeDamage(damage);
        System.out.println(user.getName() + " unleashes an arcane-enhanced slash dealing " + damage + " damage!");
    }
}
