package skills;

import effects.SlowEffect;
import characters.Character;

public class SnowstormVeil extends Skill {
    private int duration;
    private int accuracyReduction;

    public SnowstormVeil(String name, int cooldown, int duration, int accuracyReduction) {
        super(name, cooldown, duration, "Snowstorm Veil");
        this.duration = duration;
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void use(Character user, Character target) {
        target.applyEffect(new SlowEffect(duration, accuracyReduction));
    }
}