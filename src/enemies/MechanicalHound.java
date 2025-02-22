package enemies;

import java.util.List;

public class MechanicalHound extends BaseEnemy {

    public MechanicalHound() {
        name = "Mechanical Hound";
        hp = 130;
        atk = 40;
        def = 22;
        spd = 50;
        skills = List.of(new Skill("Gear Bite"), new Skill("High Pitch Whirr"));
    }
}