package skills;

import characters.Character;
import effects.LavaSurgeEffect;

public class LavaSurge extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public LavaSurge() {
        super("Lava Surge", 30, 15, "Summons a torrent of lava that continuously damages the target each turn until dispelled.");
        this.baseDamage = 35;
        this.multiplier = 1.5;
    }
    
    @Override
    public void use(Character user, Character target) {
        target.applyEffect(new LavaSurgeEffect(20.0f, 3));  // damage 20, duration 3 turns
    }
}
