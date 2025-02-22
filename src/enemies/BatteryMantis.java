package enemies;

import skills.Skill;
import java.util.List;

public class BatteryMantis extends Monster {

    public BatteryMantis() {
        super("Battery Mantis", 150, 35, 20, 40, List.of(
            new Skill("Charged Slash", 25, 10, "Slashes the target with charged claws."),
            new Skill("Power Surge", 30, 15, "Unleashes a surge of power to deal damage to all enemies.")
        ));
    }
}