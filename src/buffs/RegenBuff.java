package buffs;

import characters.Character;

public class RegenBuff extends BaseBuff {
    private int healAmount;

    public RegenBuff(int duration, int healAmount) {
        super("Regen", duration);
        this.healAmount = healAmount;
    }

    @Override
    public void apply(Character target) {
        // Apply regen buff
        System.out.println(target.getName() + " gains Regen buff for " + duration + " turns");
    }

    @Override
    public void remove(Character target) {
        // Remove regen buff
        System.out.println(target.getName() + "'s Regen buff expires");
    }

    @Override
    public void onTurnStart(Character target) {
        target.heal(healAmount);
        System.out.println(target.getName() + " regenerates " + healAmount + " HP");
    }

    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}