package enemies;

import skills.FrostPound;
import skills.SnowBoulder;
import java.util.List;

public class PolarYeti extends Monster {
    public PolarYeti() {
        // ปรับค่าพารามิเตอร์ (HP, ATK, DEF, SPD) ให้เหมาะสมกับสมดุลของเกม
        super("Polar Yeti", 500, 65, 45, 50);
        this.skills = List.of(
            new FrostPound(),
            new SnowBoulder()
        );
    }
}