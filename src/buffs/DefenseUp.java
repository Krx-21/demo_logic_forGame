package buffs;

import characters.Character;

public class DefenseUp extends BaseBuff {
    private int defenseIncrease;

    public DefenseUp(int duration, int defenseIncrease) {
        super("Defense Up", duration);
        this.defenseIncrease = defenseIncrease;
    }

    @Override
    public void apply(Character target) {
        target.setDef(target.getDef() + defenseIncrease);
    }

    @Override
    public void remove(Character target) {
        target.setDef(target.getDef() - defenseIncrease);
    }

    @Override
    public void onTurnStart(Character target) {}

    @Override
    public void onTurnEnd(Character target) {}
}