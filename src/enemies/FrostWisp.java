package enemies;

import java.util.List;

public class FrostWisp extends BaseEnemy {

    public FrostWisp() {
        name = "Frost Wisp";
        hp = 80;
        atk = 20;
        def = 10;
        spd = 65;
        skills = List.of(new Skill("Chilling Touch"), new Skill("Snowfall Aura"));
    }
}