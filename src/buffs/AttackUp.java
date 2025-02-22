package buffs;

import characters.Character;

public class AttackUp extends BaseBuff {
    private int attackIncrease;

    public AttackUp(int duration, int attackIncrease) {
        super(duration);
        this.attackIncrease = attackIncrease;
    }

    @Override
    public void apply(characters.Character target) {  // ระบุ package ให้ชัดเจน
        target.setAttack(target.getAttack() + attackIncrease);
    }
}