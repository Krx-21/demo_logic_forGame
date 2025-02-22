package enemies;

import characters.Character;
import java.util.List;
import skills.Skill;

public class Monster extends Character {
    public Monster(String name, int hp, int atk, int def, int spd) {
        // ลดค่า ATK ของมอนสเตอร์ลง 20%
        super(name, hp, (int)(atk * 0.8), def, spd);
    }
    
    // Constructor ที่รับ List<Skill>
    public Monster(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        super(name, hp, atk, def, spd, skills);
    }
    
    // หากมีเมธอดเพิ่มเติม สามารถเพิ่มได้
}