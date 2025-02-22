package buffs;

public class AttackUp extends BaseBuff {

    public AttackUp(int duration) {
        super("Attack Up", duration);
    }

    @Override
    public void applyBuff(characters.Character target) {
        // Logic to apply attack up buff
    }

    @Override
    public void removeBuff(characters.Character target) {
        // Logic to remove attack up buff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for attack up buff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for attack up buff
    }
}