package effects;

import characters.Character;

public class SnowstormVeilEffect extends BaseDotEffect {
    private int duration;
    private int accuracyReduction; // ระบุ % ที่จะลดลง เช่น 20%

    public SnowstormVeilEffect(int duration) {
        // burnRate 0 เพราะไม่ใช้ในการคำนวณดาเมจในที่นี้
        super(0.0f, duration, "Snowstorm Veil");
        this.duration = duration;
        this.accuracyReduction = 20;
    }
    
    @Override
    public void applyEffect(Character target) {
        // สมมุติให้ target มี property accuracy หรือใช้ method อื่นเพื่อปรับความแม่นยำ
        // ในที่นี้จะแค่แสดงข้อความใน Console
        System.out.println(target.getName() + " is affected by Snowstorm Veil. Accuracy is reduced by " + accuracyReduction + "%.");
    }
    
    @Override
    public void removeEffect(Character target) {
        // คืนค่าความแม่นยำของ target กลับสู่ปกติ
        System.out.println("Snowstorm Veil effect wears off from " + target.getName() + ".");
    }
    
    @Override
    public void tickEffect(Character target) {
        // ในแต่ละเทิร์น สามารถเลือกพิมพ์ log หรือคำนวณเพิ่มเติมตามระบบเกมได้
    }
}
