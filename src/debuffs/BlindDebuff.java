package debuffs;

public class BlindDebuff extends BaseDebuff {

    public BlindDebuff(int duration) {
        super("Blind", duration);
    }

    @Override
    public void applyDebuff(characters.Character target) {
        // Logic to apply blind debuff
    }

    @Override
    public void removeDebuff(characters.Character target) {
        // Logic to remove blind debuff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for blind debuff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for blind debuff
    }
}