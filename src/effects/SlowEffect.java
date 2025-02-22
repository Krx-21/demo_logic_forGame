package effects;

import characters.Character;

public class SlowEffect extends BaseDotEffect {
    private int duration;
    
    public SlowEffect(int duration) {
        // สมมุติว่า BaseDotEffect มี constructor แบบ (float, int, String)
        super(0.0f, duration, "Slow");
        this.duration = duration;
    }
    
    @Override
    public void applyEffect(Character target) {
        // ลด spd ของ target ลง 15 หน่วย
        target.setSpd(target.getSpd() - 15);
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + 15);
    }
    
    @Override
    public void tickEffect(Character target) {
        // สามารถเพิ่ม Logic สำหรับ tick effect ได้หากต้องการ
    }
}
