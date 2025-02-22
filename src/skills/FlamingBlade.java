package skills;

import characters.Character;
import effects.BurnEffect;

public class FlamingBlade extends Skill {
    public FlamingBlade() {
        super("Flaming Blade", 35, 4, "Enchants blade with fire, dealing 180% ATK damage and causing burn");
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(user.getAtk() * 1.8) - target.getDef(), 1); // เพิ่มจาก 1.3 เป็น 1.8
        target.takeDamage(damage);
        target.applyEffect(new BurnEffect(2, 25));  // เพิ่มดาเมจ DoT จาก 15 เป็น 25
        System.out.println(user.getName() + " strikes with a flame-enchanted blade dealing " + damage + " damage!");
    }
}
