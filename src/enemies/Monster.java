package enemies;

import skills.Skill;
import java.util.List;

/*
 * Monster: ศัตรูทั่วไปที่ไม่ใช่บอส
 * อาจมีลักษณะและสกิลที่ง่ายกว่าบอส
 */

public class Monster extends BaseEnemy {

    public Monster(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        super(name, hp, atk, def, spd, skills);
    }
}