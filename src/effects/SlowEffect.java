package effects;

import characters.Character;

public class SlowEffect extends BaseDotEffect {
    public SlowEffect(int duration) {
        super(0.0f, duration, "Slow");
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setSpd(target.getSpd() - 15);
        System.out.println(target.getName() + " has been slowed.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + 15);
        System.out.println("Slow effect removed from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        // Logic สำหรับ tick effect ถ้าต้องการ
    }
}
