package effects;

import characters.Character;

public class FreezeEffect extends BaseDotEffect {
    public FreezeEffect(int duration) {
        super(0.0f, duration, "Freeze");
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + " is frozen for " + duration + " turns.");
    }

    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + 10); // Restore speed
    }

    @Override
    public void tickEffect(Character target) {
        // Implement tick effect logic if needed
    }
}