package effects;

public abstract class BaseDotEffect {
    protected float damagePerTurn;
    protected int duration;
    protected String effectName;

    public BaseDotEffect(float damagePerTurn, int duration, String effectName) {
        this.damagePerTurn = damagePerTurn;
        this.duration = duration;
        this.effectName = effectName;
    }

    public abstract void applyEffect(characters.Character target);
    public abstract void removeEffect(characters.Character target);
    public abstract void tickEffect(characters.Character target);
}