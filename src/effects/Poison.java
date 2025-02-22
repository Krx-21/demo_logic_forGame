package effects;

public class Poison extends BaseDotEffect {

    public Poison(float damagePerTurn, int duration) {
        super(damagePerTurn, duration, "Poison");
    }

    @Override
    public void applyEffect(characters.Character target) {
        // Logic to apply poison effect
    }

    @Override
    public void removeEffect(characters.Character target) {
        // Logic to remove poison effect
    }

    @Override
    public void tickEffect(characters.Character target) {
        // Logic to apply damage per turn
    }
}