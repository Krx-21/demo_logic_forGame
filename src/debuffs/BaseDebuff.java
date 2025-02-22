package debuffs;

public abstract class BaseDebuff {
    protected String debuffName;
    protected int duration;

    public BaseDebuff(String debuffName, int duration) {
        this.debuffName = debuffName;
        this.duration = duration;
    }

    public abstract void applyDebuff(characters.Character target);
    public abstract void removeDebuff(characters.Character target);
    public abstract void onTurnStart(characters.Character target);
    public abstract void onTurnEnd(characters.Character target);
}