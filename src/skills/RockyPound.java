//// filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/javafx-project/src/skills/RockyPound.java
package skills;

import characters.Character;

public class RockyPound extends Skill {
    private int baseDamage;
    private double multiplier;
    
    public RockyPound() {
        // ตัวอย่างค่า: manaCost = 25, cooldown = 6 (ปรับตามสมดุลได้)
        super("Rocky Pound", 25, 6, "Slam the ground forcefully, dealing heavy damage to the target.");
        this.baseDamage = 30;
        this.multiplier = 1.8;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses Rocky Pound and slams the ground, dealing " + damage + " damage to " + target.getName() + ".");
    }
}
