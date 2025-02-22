package skills;

import characters.Character;
import effects.SlowEffect;

public class GearWeb extends Skill {
    public GearWeb() {
        super("Gear Web", 20, 3, "Shoots mechanical web that slows target");
    }
    
    @Override
    public void use(Character user, Character target) {
        target.applyEffect(new SlowEffect(2, 15));
    }
}