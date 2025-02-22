package enemies;

import java.util.List;

public class SnowGoblin extends BaseEnemy {

    public SnowGoblin() {
        name = "Snow Goblin";
        hp = 90;
        atk = 25;
        def = 12;
        spd = 55;
        skills = List.of(new Skill("Ice Javelin"), new Skill("Bone Chill"));
    }
}