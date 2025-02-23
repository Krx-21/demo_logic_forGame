package buffs;

import characters.Character;

public class SpeedBuff extends BaseBuff {
    private int speedIncrease;
    
    public SpeedBuff(int duration, int speedIncrease) {
        super("Speed Up", duration);
        this.speedIncrease = speedIncrease;
    }
    
    @Override
    public void apply(Character target) {
        target.setSpd(target.getSpd() + speedIncrease);
        System.out.println(target.getName() + "'s speed increases by " + speedIncrease);
    }
    
    @Override
    public void remove(Character target) {
        target.setSpd(target.getSpd() - speedIncrease);
        System.out.println(target.getName() + "'s speed returns to normal");
    }
    
    @Override
    public void onTurnStart(Character target) {
        // No effect on turn start
    }
    
    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}