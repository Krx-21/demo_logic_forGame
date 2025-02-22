package skills;

import characters.Character;
import effects.BleedEffect;

public class IcicleFang extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public IcicleFang() {
        // manaCost = 15, cooldown = 8 (ปรับได้ตามความต้องการ)
        super("Icicle Fang", 15, 8, "Jumps to attack a single target with icy fangs, dealing high damage and may cause Bleed.");
        this.baseDamage = 30;
        this.multiplier = 2.0; // คูณเพิ่มดาเมจให้สูงขึ้น
    }
    
    @Override
    public void use(Character user, Character target) {
        // สูตรคำนวณดาเมจ: damage = max( baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.25), 1 )
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.25)), 1);
        target.takeDamage(damage);
        
        // โอกาส 50% ให้เป้าหมายติดสถานะ Bleed
        if (Math.random() < 0.5) {
            target.applyEffect(new BleedEffect(3)); // ให้ effect อยู่ 3 เทิร์น
            System.out.println(target.getName() + " is bleeding due to ice spikes!");
        }
        
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}
