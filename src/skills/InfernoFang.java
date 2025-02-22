package skills;

import characters.Character;
import effects.FireBurn;

public class InfernoFang extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public InfernoFang() {
        // manaCost = 20, cooldown = 5 (ปรับได้ตามต้องการ)
        super("Inferno Fang", 20, 5, "Leaps to bite the target with intense flame power, dealing heavy damage and possibly inflicting Burn.");
        this.baseDamage = 35;
        this.multiplier = 2.2;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจโดยอิงจาก baseDamage, ค่า Atk ของผู้ใช้ และหักลดด้วยค่า Def ของเป้าหมาย
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " leaps and bites " + target.getName() + " with Inferno Fang, dealing " + damage + " damage.");
        
        // โอกาส 50% ที่จะทำให้เป้าหมายติด Burn ด้วยพลังไฟ
        if (Math.random() < 0.5) {
            target.applyEffect(new FireBurn(3.0f, 3));
            System.out.println(target.getName() + " is burning from Inferno Fang!");
        }
    }
}