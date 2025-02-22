package effects;

import characters.Character;

public class DefenseDownEffect extends BaseDotEffect {
    private int duration;
    
    public DefenseDownEffect(int duration) {
        super(0.0f, duration, "Defense Down");
        this.duration = duration;
    }
    
    @Override
    public void applyEffect(Character target) {
        // สมมุติว่าเราต้องการลด def ลง 10 หน่วย
        target.setDef(target.getDef() - 10);
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setDef(target.getDef() + 10);
    }
    
    @Override
    public void tickEffect(Character target) {
        // สามารถเพิ่ม Logic สำหรับ tick effect ได้หากต้องการ
    }
}
