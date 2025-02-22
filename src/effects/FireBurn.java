package effects;

/*
 * FireBurn: เอฟเฟกต์เผาไหม้ ศัตรูจะได้รับดาเมจต่อเนื่องเมื่อโดนสกิลไฟ
 */

public class FireBurn extends BaseDotEffect {

    public FireBurn(float damagePerTurn, int duration) {
        super(damagePerTurn, duration, "Fire Burn");
    }

    @Override
    public void applyEffect(characters.Character target) {
        // Logic to apply fire burn effect
    }

    @Override
    public void removeEffect(characters.Character target) {
        // Logic to remove fire burn effect
    }

    @Override
    public void tickEffect(characters.Character target) {
        // Logic to apply damage per turn
    }
}