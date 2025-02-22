package effects;

import characters.Character;

public class BlizzardField extends BaseDotEffect {
    private int duration;
    
    public BlizzardField(int duration) {
        // burnRate 0.0f เพราะเราไม่ใช้ค่านี้ในการคำนวณดาเมจ แต่ใช้เพื่อบอกชื่อ effect
        super(0.0f, duration, "Blizzard Field");
        this.duration = duration;
    }
    
    @Override
    public void applyEffect(Character target) {
        // ลดค่า Speed ของ target เมื่อเริ่ม effect
        target.setSpd(target.getSpd() - 15);
        System.out.println(target.getName() + " is trapped in a Blizzard Field! Speed is reduced by 15.");
    }
    
    @Override
    public void tickEffect(Character target) {
        // ดาเมจประจำเทิร์นจากพายุหิมะ (ตัวอย่าง 10 ดาเมจต่อเทิร์น)
        int damage = 10;
        target.takeDamage(damage);
        System.out.println(target.getName() + " takes " + damage + " ice damage from Blizzard Field.");
    }
    
    @Override
    public void removeEffect(Character target) {
        // คืนค่า Speed ที่ลดไปกลับคืนให้กับ target เมื่อ effect หมด
        target.setSpd(target.getSpd() + 15);
        System.out.println("Blizzard Field dissipates. " + target.getName() + "'s speed returns to normal.");
    }
}
