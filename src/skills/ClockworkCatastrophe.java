package skills;

import characters.Character;
import effects.ClockworkCatastropheEffect;

public class ClockworkCatastrophe extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public ClockworkCatastrophe() {
        // ค่า manaCost และ cooldown สามารถปรับได้ตามความต้องการ
        super("Clockwork Catastrophe", 0, 0, "Triggers a mechanical catastrophe where all tower mechanisms activate simultaneously, causing multiple waves of damage over 2-3 turns and may cause the target to become Jammed.");
        this.baseDamage = 30;
        this.multiplier = 2.5;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจเริ่มแรก
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - target.getDef()), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " triggers Clockwork Catastrophe and deals an initial " + damage + " damage to " + target.getName() + ".");
        
        // ใช้เอฟเฟกต์ที่ทำให้เกิดความเสียหายซ้ำและอาจทำให้ติดสถานะ Jammed
        target.applyEffect(new ClockworkCatastropheEffect());
    }
}
