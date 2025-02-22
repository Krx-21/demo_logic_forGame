package effects;
import characters.Character;

public abstract class BaseDotEffect {
    protected float rate;
    protected int duration;
    protected String name;
    
    public BaseDotEffect(float rate, int duration, String name) {
        this.rate = rate;
        this.duration = duration;
        this.name = name;
    }
    
    public abstract void applyEffect(Character target);
    public abstract void tickEffect(Character target);
    public abstract void removeEffect(Character target);
}