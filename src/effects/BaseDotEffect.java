package effects;

import characters.Character;

public abstract class BaseDotEffect {
    private String name;
    
    public BaseDotEffect(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    protected float rate;
    protected int duration;
    
    public BaseDotEffect(float rate, int duration, String name) {
        this.rate = rate;
        this.duration = duration;
        this.name = name;
    }
    
    public abstract void applyEffect(Character target);
    public abstract void tickEffect(Character target);
    public abstract void removeEffect(Character target);
    
    public int getDuration() {
        return duration;
    }
    
    public void decrementDuration() {
        duration--;
    }
}