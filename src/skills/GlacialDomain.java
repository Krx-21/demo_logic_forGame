package skills;

import characters.Character;
import effects.BlizzardField;

public class GlacialDomain extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public GlacialDomain() {
        // manaCost = 30, cooldown = 15 (ปรับได้ตามความต้องการ)
        super("Glacial Domain", 30, 15, "Transforms the battlefield into an icy plain. Reduces the target's speed and inflicts periodic ice damage (Blizzard Field).");
        this.baseDamage = 40;
        this.multiplier = 1.4;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจทันที (ถ้าต้องการให้เกิดดาเมจเริ่มแรก)
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        
        // ใช้เอฟเฟกต์ BlizzardField ส่งผลให้ target เคลื่อนไหวช้าลงและได้รับดาเมจเป็นระยะ
        target.applyEffect(new BlizzardField(3)); // ให้เอฟเฟกต์อยู่ 3 เทิร์น (ปรับค่าได้)
        
        System.out.println(user.getName() + " unleashes Glacial Domain, dealing an initial " + damage + " damage and transforming the battlefield into an icy field!");
    }
}
