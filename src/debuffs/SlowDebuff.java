package debuffs;

public class SlowDebuff extends BaseDebuff {

    public SlowDebuff(int duration) {
        super("Slow", duration);
    }

    @Override
    public void applyDebuff(characters.Character target) {
        // Logic to apply slow debuff
    }

    @Override
    public void removeDebuff(characters.Character target) {
        // Logic to remove slow debuff
    }

    @Override
    public void onTurnStart(characters.Character target) {
        // Logic to handle turn start for slow debuff
    }

    @Override
    public void onTurnEnd(characters.Character target) {
        // Logic to handle turn end for slow debuff
    }
}