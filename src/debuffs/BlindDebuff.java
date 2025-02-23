package debuffs;

import characters.Character;

public class BlindDebuff extends BaseDebuff {
    private int accuracyReduction;

    public BlindDebuff(int duration) {
        super("Blind", duration);
        this.accuracyReduction = 20; // Reduces accuracy by 20%
    }

    @Override
    public void apply(Character target) {
        target.setAccuracy(target.getAccuracy() - accuracyReduction);
        System.out.println(target.getName() + " is blinded!");
    }

    @Override
    public void remove(Character target) {
        target.setAccuracy(target.getAccuracy() + accuracyReduction);
        System.out.println(target.getName() + " is no longer blinded");
    }

    @Override
    public void onTurnStart(Character target) {
        System.out.println(target.getName() + " has reduced accuracy due to blind");
    }

    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}