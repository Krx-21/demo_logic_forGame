package skills;

import characters.Character;

public class Reconstruct extends Skill {
    private int hpRecovery;
    private int defBoost;
    
    public Reconstruct() {
        // manaCost และ cooldown ปรับได้ตามที่คุณต้องการ
        super("Reconstruct", 0, 0, "Heals itself by absorbing energy from surrounding gears, increasing both HP and DEF.");
        this.hpRecovery = 50;  // ฟื้นฟู HP 50 คะแนน
        this.defBoost = 10;    // เพิ่ม DEF 10 คะแนน
    }
    
    @Override
    public void use(Character user, Character target) {
        // สกิลนี้ใช้กับตัวเอง (ไม่ต้องใช้ target)
        user.heal(hpRecovery);
        user.increaseDef(defBoost);
        System.out.println(user.getName() + " uses Reconstruct and absorbs energy, recovering " + hpRecovery + " HP and increasing DEF by " + defBoost + ".");
    }
}
