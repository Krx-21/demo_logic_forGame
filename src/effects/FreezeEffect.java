package effects;

import characters.Character;

public class FreezeEffect extends BaseDotEffect {
    private int duration;

    public FreezeEffect(int duration) {
        super(0.0f, duration, "Freeze");  // Adjust constructor parameters
        this.duration = duration;
    }

    @Override
    public void applyEffect(Character target) {
        // Logic to apply freeze effect (e.g., reduce speed or skip turns)
        target.setSpd(target.getSpd() - 10); // Example: reduce speed by 10
    }

    @Override
    public void removeEffect(Character target) {
        // Logic to remove freeze effect
        target.setSpd(target.getSpd() + 10); // Restore speed
    }

    @Override
    public void tickEffect(Character target) {
        // Implement tick effect logic if needed
    }
}