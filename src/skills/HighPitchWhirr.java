package skills;

import characters.Character;
import effects.HighPitchWhirrEffect;

public class HighPitchWhirr extends Skill {
    public HighPitchWhirr() {
        super("High Pitch Whirr", 30, 4, "Emits a high-pitched sound that disorients the target");
    }
    
    @Override
    public void use(Character user, Character target) {
        target.applyEffect(new HighPitchWhirrEffect(2, 15));  // 2 turns duration, 15 accuracy reduction
        System.out.println(user.getName() + " uses High Pitch Whirr on " + target.getName() + "!");
    }
}