package buffs;

public class RegenBuff extends BaseBuff {

    public RegenBuff(int duration) {
        super("Regen", duration);
    }

    @Override
    public void applyBuff(characters.Character target) {
        // Logic to apply regen buff
    }

    @Override
    public void removeBuff(characters.Character target) {
        // Logic to remove regen buff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for regen buff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for regen buff
    }
}