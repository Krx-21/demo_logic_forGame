package enemies;

import skills.FireToss;
import skills.MoltenSplash;
import java.util.List;

public class FlameImp extends Monster {
    public FlameImp() {
        super("Flame Imp", 280, 45, 20, 85);
        this.skills = List.of(
            new FireToss(),
            new MoltenSplash()
        );
    }
}