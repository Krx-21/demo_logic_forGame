package enemies;

import java.util.List;

public class MagmaWolf extends BaseEnemy {

    public MagmaWolf() {
        name = "Magma Wolf";
        hp = 180;
        atk = 40;
        def = 20;
        spd = 60;
        skills = List.of(new Skill("Inferno Fang"), new Skill("Howl of Embers"));
    }
}