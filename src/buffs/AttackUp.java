package buffs;

import characters.Character;

public class AttackUp extends BaseBuff {
    private int attackIncrease;
    
    public AttackUp(int duration, int attackIncrease) {
        super("Attack Up", duration);
        this.attackIncrease = attackIncrease;
    }
    
    @Override
    public void apply(Character target) {
        target.setAtk(target.getAtk() + attackIncrease);
    }
    
    @Override
    public void remove(Character target) {
        target.setAtk(target.getAtk() - attackIncrease);
    }
    
    @Override
    public void onTurnStart(Character target) {}
    
    @Override
    public void onTurnEnd(Character target) {}
}