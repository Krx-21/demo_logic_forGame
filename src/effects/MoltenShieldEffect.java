package effects;

import characters.Character;

public class MoltenShieldEffect extends BaseDotEffect {
    private int duration;
    
    public MoltenShieldEffect(int duration) {
        // ค่า burnRate 0 จะไม่ใช้ในที่นี้ และ description ให้ "Molten Shield"
        super(0.0f, duration, "Molten Shield");
        this.duration = duration;
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + "'s Molten Shield is now active. Incoming damage will be reduced and some melee damage may be reflected.");
        // การคำนวณลดความเสียหายหรือสะท้อนดาเมจสามารถจัดการในระบบคำนวณดาเมจหลักของเกม
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Molten Shield effect expired on " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        // ไม่จำเป็นต้องมี tick behavior สำหรับสกิลนี้
    }
}
