package effects;

import characters.Character;

public class BleedEffect extends BaseDotEffect {
    private int duration;
    
    public BleedEffect(int duration) {
        // burnRate 0.0f เพราะไม่ใช้ในการคำนวณดาเมจหลักของ effect นี้
        super(0.0f, duration, "Bleed");
        this.duration = duration;
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + " starts bleeding!");
        // โค้ดเพิ่มเติมเพื่อเริ่มต้น effect หากต้องการ
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Bleed effect wears off from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        // ให้เกิดดาเมจเลือดออกทุกเทิร์น ตัวอย่าง 5 ดาเมจต่อเทิร์น
        int bleedDamage = 5;
        target.takeDamage(bleedDamage);
        System.out.println(target.getName() + " takes " + bleedDamage + " damage from Bleed.");
    }
}