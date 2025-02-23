package debuffs;

import characters.Character;

public class FreezeDebuff extends BaseDebuff {

    public FreezeDebuff(int duration) {
        super("Freeze", duration);
    }

    @Override
    public void apply(Character target) {
        target.setFrozen(true);
        System.out.println(target.getName() + " is frozen!");
    }

    @Override
    public void remove(Character target) {
        target.setFrozen(false);
        System.out.println(target.getName() + " is no longer frozen");
    }

    @Override
    public void onTurnStart(Character target) {
        System.out.println(target.getName() + " is frozen and loses their turn!");
    }

    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}