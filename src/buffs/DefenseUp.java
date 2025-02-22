package buffs;

public class DefenseUp extends BaseBuff {

    public DefenseUp(int duration) {
        super("Defense Up", duration);
    }

    @Override
    public void applyBuff(characters.Character target) {
        // Logic to apply defense up buff
    }

    @Override
    public void removeBuff(characters.Character target) {
        // Logic to remove defense up buff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for defense up buff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for defense up buff
    }
}