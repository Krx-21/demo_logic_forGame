package effects;

import characters.Character;

public class SlowEffect extends BaseDotEffect {
    private int speedReduction;
    
    public SlowEffect(int duration, int speedReduction) {
        super(0.0f, duration, "Slow");
        this.speedReduction = speedReduction;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setSpd(target.getSpd() - speedReduction);
        System.out.println(target.getName() + "'s speed is reduced by " + speedReduction);
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " is still slowed.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + speedReduction);
        System.out.println(target.getName() + "'s speed returns to normal.");
    }
}
