package enemies;

import java.util.List;

public class LavaSlime extends BaseEnemy {

    public LavaSlime() {
        name = "Lava Slime";
        hp = 150;
        atk = 20;
        def = 15;
        spd = 30;
        skills = List.of(new Skill("Molten Splash"), new Skill("Split"));
    }
}