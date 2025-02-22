package enemies;

import skills.HighPitchWhirr;
import skills.MetalPunch;
import java.util.List;

public class MechanicalHound extends Monster {
    public MechanicalHound() {
        super("Mechanical Hound", 320, 50, 25, 75);
        this.skills = List.of(
            new HighPitchWhirr(),
            new MetalPunch()
        );
    }
}