package enemies;

import skills.MoltenSplash;
import java.util.List;

public class LavaSlime extends Monster {
    public LavaSlime() {
        super("Lava Slime", 300, 40, 25, 30);
        this.skills = List.of(new MoltenSplash());
    }
}