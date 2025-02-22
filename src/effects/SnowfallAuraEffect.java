package effects;

import characters.Character;

public class SnowfallAuraEffect extends BaseDotEffect {
    private int speedReduction;
    
    public SnowfallAuraEffect(int duration, int speedReduction) {
        super(0.0f, duration, "Snowfall Aura");
        this.speedReduction = speedReduction;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setSpd(target.getSpd() - speedReduction);
        System.out.println(target.getName() + " is slowed by the snowfall!");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + speedReduction);
        System.out.println("Snowfall effect wears off from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " is still affected by the snowfall.");
    }
}