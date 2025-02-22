package enemies;

import java.util.List;
import skills.PowerSurge;
import skills.SnowstormVeil;

public class FrostfangQueen extends BaseBoss {
    public FrostfangQueen() {
        super("Frostfang Queen", 1200, 140, 110, 90, List.of(
            new SnowstormVeil("Snowstorm Veil", 5, 3, 20),
            new PowerSurge("Power Surge", 5, 3, 30)
        ), null);
    }
}