package enemies;

import skills.TimeDistortion;
import skills.GearCannon;
import skills.Reconstruct;
import skills.ClockworkCatastrophe;
import skills.PowerSurge;
import skills.SnowstormVeil;
import java.util.List;

public class ClockworkRequiem extends BaseBoss {
    public ClockworkRequiem() {
        super("Clockwork Requiem", 1300, 130, 100, 85, List.of(
            new TimeDistortion(),
            new ClockworkCatastrophe(),
            new GearCannon()
        ), null);
    }
}