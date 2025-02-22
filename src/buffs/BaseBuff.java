package buffs;

import characters.Character;

public abstract class BaseBuff {
    private int duration;

    public BaseBuff(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public abstract void apply(Character target);
}