package fields;

/*
 * BlizzardFieldEffect: สนามพายุหิมะ ลด SPD ของยูนิตหรือศัตรูในสนาม
 * สืบทอดจาก BaseFieldEffect 
 */

public class BlizzardFieldEffect extends BaseFieldEffect {
    private int spdReduction;

    public BlizzardFieldEffect(int duration, int spdReduction) {
        super("Blizzard Field", duration, false);
        this.spdReduction = spdReduction;
    }

    @Override
    public void applyEffectToField() {
        // Logic to apply blizzard field effect
    }

    @Override
    public void removeEffectFromField() {
        // Logic to remove blizzard field effect
    }

    @Override
    public void onTurnStart() {
        // Logic to handle turn start for blizzard field effect
    }

    @Override
    public void onTurnEnd() {
        // Logic to handle turn end for blizzard field effect
    }
}