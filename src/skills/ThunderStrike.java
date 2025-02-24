//// filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/javafx-project/src/skills/ThunderStrike.java
package skills;

import characters.Character;

public class ThunderStrike extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public ThunderStrike() {
        // manaCost = 25, cooldown = 0 (หรือปรับค่าให้เหมาะสม)
        super("Thunder Strike", 25, 0, "Deals thunder damage based on user's Atk and may stun the target.");
        this.baseDamage = 30;
        this.multiplier = 1.8;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.25)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
        // เพิ่มเอฟเฟกต์ stun หรืออื่น ๆ ได้ตามต้องการ
    }
}
