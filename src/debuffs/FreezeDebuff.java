package debuffs;

public class FreezeDebuff extends BaseDebuff {

    public FreezeDebuff(int duration) {
        super("Freeze", duration);
    }

    @Override
    public void applyDebuff(characters.Character target) {
        // Logic to apply freeze debuff
    }

    @Override
    public void removeDebuff(characters.Character target) {
        // Logic to remove freeze debuff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for freeze debuff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for freeze debuff
    }
}