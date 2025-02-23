package effects;

import characters.Character;

public class DefenseDownEffect extends BaseDotEffect {
    private int defenseReduction;

    public DefenseDownEffect(int duration, int defenseReduction) {
        super(0.0f, duration, "Defense Down");
        this.defenseReduction = defenseReduction;
    }

    @Override
    public void applyEffect(Character target) {
        target.setDef(target.getDef() - defenseReduction);
    }

    @Override
    public void removeEffect(Character target) {
        target.setDef(target.getDef() + defenseReduction);
    }

    @Override
    public void tickEffect(Character target) {
        // No periodic effect
    }
}
