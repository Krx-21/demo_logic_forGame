package debuffs;

import characters.Character;

public class SlowDebuff extends BaseDebuff {
    private int speedReduction;

    public SlowDebuff(int duration) {
        super("Slow", duration);
        this.speedReduction = 5;
    }

    @Override
    public void apply(Character target) {
        target.setSpd(target.getSpd() - speedReduction);
        System.out.println(target.getName() + " is slowed!");
    }

    @Override
    public void remove(Character target) {
        target.setSpd(target.getSpd() + speedReduction);
        System.out.println(target.getName() + " is no longer slowed");
    }

    @Override
    public void onTurnStart(Character target) {
        System.out.println(target.getName() + " is moving slower");
    }

    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}