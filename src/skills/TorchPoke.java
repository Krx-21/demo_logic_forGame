package skills;

import characters.Character;
import effects.FireBurn;

public class TorchPoke extends Skill {
    private int baseDamage;
    
    public TorchPoke() {
        // manaCost = 10, cooldown = 5 สามารถปรับแต่งได้ตามต้องการ
        super("Torch Poke", 10, 5, "A close-range attack with a blazing stick that inflicts a mild Burn status effect.");
        this.baseDamage = 15; // ค่าดาเมจพื้นฐานสำหรับโจมตีประชิด
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจ: ใช้ค่า baseDamage บวกกับครึ่งหนึ่งของ Atk และหักลดจากค่า Def (10% ของ Def)
        int damage = Math.max(baseDamage + (user.getAtk() / 2) - (int)(target.getDef() * 0.10), 1);
        target.takeDamage(damage);
        // ใช้เอฟเฟกต์ FireBurn แบบอ่อน โดยกำหนด burnRate 1.0f และ duration 2 เทิร์น
        target.applyEffect(new FireBurn(1.0f, 2));
        System.out.println(user.getName() + " uses Torch Poke on " + target.getName() + ", dealing " + damage + " damage and inflicting a mild Burn.");
    }
}
