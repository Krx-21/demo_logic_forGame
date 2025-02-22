package buffs;

import characters.Character;

public class SpeedBuff extends BaseBuff {
    private int duration;
    private int speedIncrease;
    
    public SpeedBuff(int duration, int speedIncrease) {
        this.duration = duration;
        this.speedIncrease = speedIncrease;
    }
    
    @Override
    public void apply(Character target) {
        target.setSpd(target.getSpd() + speedIncrease);
        System.out.println(target.getName() + "'s speed increases by " + speedIncrease 
                           + " for " + duration + " turns.");
    }
    
    // ถ้าต้องการเพิ่มเมธอดเพื่อลบ buff หลังหมดระยะ ให้เพิ่ม remove หรือ logic ในระบบ turn ของคุณ
}