package enemies;

import skills.LavaSurge;
import skills.MoltenCore;
import skills.EruptionSmash;
import java.util.List;

public class VolcanoBoss extends BaseBoss {
    public VolcanoBoss() {
        super("Volcano Boss", 1500, 160, 120, 70, List.of(
            new LavaSurge(),
            new MoltenCore(),
            new EruptionSmash()
        ), null);
    }
}