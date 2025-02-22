package effects;

public class Bleeding extends BaseDotEffect {

    public Bleeding(float damagePerTurn, int duration) {
        super(damagePerTurn, duration, "Bleeding");
    }

    @Override
    public void applyEffect(characters.Character target) {
        // Logic to apply bleeding effect
    }

    @Override
    public void removeEffect(characters.Character target) {
        // Logic to remove bleeding effect
    }

    @Override
    public void tickEffect(characters.Character target) {
        // Logic to apply damage per turn
    }
}