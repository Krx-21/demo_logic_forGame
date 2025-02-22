package skills;

import characters.Character;
import effects.SnowfallAuraEffect;

public class SnowfallAura extends Skill {
    private int healAmount;
    
    public SnowfallAura() {
        // ตัวอย่าง: manaCost = 20, cooldown = 5
        super("Snowfall Aura", 20, 5, "Emits a chilling aura that heals the Wisp slightly and continuously damages enemies with biting snow.");
        this.healAmount = 10; // ฟื้นฟู HP ของ Wisp 10 หน่วย
    }
    
    @Override
    public void use(Character user, Character target) {
        // ฟื้นฟู HP ให้กับ Wisp
        user.heal(healAmount);
        System.out.println(user.getName() + " is enveloped in a Snowfall Aura and heals " + healAmount + " HP.");
        
        // ใช้เอฟเฟกต์ SnowfallAuraEffect กับเป้าหมาย (ศัตรูในระยะ)
        target.applyEffect(new SnowfallAuraEffect(3, 5)); // duration 3 เทิร์น, ดาเมจ 5 ต่อเทิร์น
        System.out.println(target.getName() + " is battered by biting snow from the aura!");
    }
}