package skills;

import characters.Character;
import effects.MoltenShieldEffect;

public class MoltenShield extends Skill {
    public MoltenShield() {
        super("Molten Shield", 10, 5, "Creates a lava shield that reduces incoming damage next turn and reflects some melee damage.");
    }
    
    @Override
    public void use(Character user, Character target) {
        // สกิลนี้ใช้กับตัวเอง
        user.applyEffect(new MoltenShieldEffect(1)); // เอฟเฟกต์นี้อยู่ 1 เทิร์น
        System.out.println(user.getName() + " casts Molten Shield, preparing to reduce and reflect damage.");
    }
}
