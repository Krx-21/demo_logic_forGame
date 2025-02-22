package enemies;

import java.util.List;

public class IgneousGolem extends BaseEnemy {

    public IgneousGolem() {
        name = "Igneous Golem";
        hp = 250;
        atk = 35;
        def = 40;
        spd = 20;
        skills = List.of(new Skill("Rocky Pound"), new Skill("Molten Core"));
    }
}