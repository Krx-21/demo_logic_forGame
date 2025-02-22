package enemies;

import java.util.List;
import skills.Skill;
import skills.SkillRepository;

public class IceDrake extends Monster {
    public IceDrake() {
        super("Ice Drake", 200, 45, 25, 50);
        Skill iceBlast = SkillRepository.getSkill("Ice Blast");
        if (iceBlast == null) {
            throw new IllegalStateException("Skill 'Ice Blast' not found! Please register it in SkillRepository.");
        }
        this.skills = List.of(iceBlast);
    }
    
    // เมธอด performAttack สำหรับคำนวณดาเมจเฉพาะของ IceDrake
    public int performAttack() {
        // ตัวอย่าง: return ค่า atk + bonus จาก Ice Blast
        return getAtk(); 
    }
}