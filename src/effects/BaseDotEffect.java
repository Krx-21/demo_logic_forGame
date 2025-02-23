package effects;

import characters.Character;

public abstract class BaseDotEffect {
    protected String name;
    protected int duration;
    protected float damage;

    public BaseDotEffect(float damage, int duration, String name) {
        this.damage = damage;
        this.duration = duration;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }

    public abstract void applyEffect(Character target);
    public abstract void removeEffect(Character target);
    public abstract void tickEffect(Character target);
}