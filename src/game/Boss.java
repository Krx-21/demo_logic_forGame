package game;

import enemies.Monster;
import java.util.List;
import skills.Skill;

public class Boss extends Monster {
    public Boss(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        super(name, hp, atk, def, spd, skills);
    }
}