package debuffs;

import characters.Character;

public abstract class BaseDebuff {
    protected String name;
    protected int duration;

    public BaseDebuff(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public abstract void apply(Character target);
    public abstract void remove(Character target);
    public abstract void onTurnStart(Character target);
    public abstract void onTurnEnd(Character target);

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        duration--;
    }
}