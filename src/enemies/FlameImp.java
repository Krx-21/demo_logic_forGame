package enemies;

import java.util.List;

public class FlameImp extends BaseEnemy {

    public FlameImp() {
        name = "Flame Imp";
        hp = 100;
        atk = 30;
        def = 10;
        spd = 50;
        skills = List.of(new Skill("Fire Toss"), new Skill("Torch Poke"));
    }
}