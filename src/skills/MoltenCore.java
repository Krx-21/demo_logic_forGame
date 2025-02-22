package skills;

import characters.Character;
import enemies.IgneousGolem;

public class MoltenCore extends Skill {

    public MoltenCore() {
        // manaCost และ cooldown สามารถปรับได้
        super("Molten Core", 0, 4, "Accumulates core heat when hit by fire; when activated, releases the accumulated heat as a retaliatory wave (small damage).");
    }
    
    @Override
    public void use(Character user, Character target) {
        if(user instanceof IgneousGolem) {
            IgneousGolem golem = (IgneousGolem) user;
            int heat = golem.getAccumulatedHeat();
            if(heat > 0) {
                int damage = Math.max(heat / 2, 1); // คำนวณดาเมจโดยหารความร้อนสะสมด้วย 2 (อย่างน้อย 1)
                target.takeDamage(damage);
                System.out.println(golem.getName() + " releases its accumulated heat from the Molten Core, dealing " 
                                    + damage + " fire damage to " + target.getName() + "!");
                golem.resetAccumulatedHeat();
            } else {
                System.out.println(golem.getName() + " has no accumulated heat to release.");
            }
        } else {
            System.out.println("Molten Core can only be used by Igneous Golem.");
        }
    }
}