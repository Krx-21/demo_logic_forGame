package enemies;

import java.util.List;

public class PolarYeti extends BaseEnemy {

    public PolarYeti() {
        name = "Polar Yeti";
        hp = 220;
        atk = 50;
        def = 30;
        spd = 35;
        skills = List.of(new Skill("Frost Pound"), new Skill("Snow Boulder"));
    }
}