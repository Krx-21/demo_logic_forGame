package fields;

public class LavaFieldEffect extends BaseFieldEffect {
    private int damagePerTurn;

    public LavaFieldEffect(int duration, int damagePerTurn) {
        super("Lava Field", duration, false);
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void applyEffectToField() {
        // Logic to apply lava field effect
    }

    @Override
    public void removeEffectFromField() {
        // Logic to remove lava field effect
    }

    @Override
    public void onTurnStart() {
        // Logic to handle turn start for lava field effect
    }

    @Override
    public void onTurnEnd() {
        // Logic to handle turn end for lava field effect
    }
}