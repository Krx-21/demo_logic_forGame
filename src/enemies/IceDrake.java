package enemies;

import skills.Skill;
import java.util.List;

public class IceDrake extends Monster {

    public IceDrake() {
        super("Ice Drake", 200, 45, 25, 50, List.of(
            new Skill("Freezing Breath", 30, 10, "Breathes freezing air to deal damage and slow the target."),
            new Skill("Glide Slash", 20, 5, "Glides and slashes the target with sharp claws.")
        ));
    }
}