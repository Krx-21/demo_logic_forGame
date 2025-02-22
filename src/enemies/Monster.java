package enemies;

import characters.Character;
import java.util.List;
import skills.Skill;

public class Monster extends Character {
    // Constructor ที่ไม่รับ List<Skill>
    public Monster(String name, int hp, int atk, int def, int spd) {
        super(name, hp, atk, def, spd);
    }
    
    // Constructor ที่รับ List<Skill>
    public Monster(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        super(name, hp, atk, def, spd, skills);
    }
    
    // หากมีเมธอดเพิ่มเติม สามารถเพิ่มได้
}