package effects;

import characters.Character;

public class FireBurn extends BaseDotEffect {
    private float burnRate;
    
    public FireBurn(float burnRate, int duration) {
        super(burnRate, duration, "Fire Burn");
        this.burnRate = burnRate;
    }
    
    @Override
    public void applyEffect(Character target) {
        // ตัวอย่าง: ลด HP ทีละนิดใน tick หรือเพิ่มเอฟเฟกต์ burning อื่น ๆ
        System.out.println(target.getName() + " is burning with a rate of " + burnRate);
    }
    
    @Override
    public void removeEffect(Character target) {
        // กำจัดเอฟเฟกต์ burning เมื่อหมด duration
    }
    
    @Override
    public void tickEffect(Character target) {
        // ดำเนินการ effect ทีละ tick
    }
}