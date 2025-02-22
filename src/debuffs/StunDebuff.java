package debuffs;

public class StunDebuff extends BaseDebuff {

    public StunDebuff(int duration) {
        super("Stun", duration);
    }

    @Override
    public void applyDebuff(characters.Character target) {
        // Logic to apply stun debuff
    }

    @Override
    public void removeDebuff(characters.Character target) {
        // Logic to remove stun debuff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for stun debuff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for stun debuff
    }
}