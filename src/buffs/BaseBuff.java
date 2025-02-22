package buffs;

public abstract class BaseBuff {
    protected String buffName;
    protected int duration;

    public BaseBuff(String buffName, int duration) {
        this.buffName = buffName;
        this.duration = duration;
    }

    public abstract void applyBuff(characters.Character target);
    public abstract void removeBuff(characters.Character target);
    public abstract void onTurnStart(characters.Character target);
    public abstract void onTurnEnd(characters.Character target);
}