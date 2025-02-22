package enemies;

import skills.Skill;
import java.util.List;

/*
 * BaseEnemy: คลาสแม่สำหรับศัตรูพื้นฐาน
 * เก็บค่าสถานะ HP, ATK, DEF, SPD รวมถึงสกิลที่ศัตรูมี
 */

public class BaseEnemy {
    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected int spd;
    protected List<Skill> skills;

    public BaseEnemy(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void useSkill(Skill skill, Character target) {
        // Implement skill usage logic
    }
}
