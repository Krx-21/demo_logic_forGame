package skills;

import characters.Character;
import effects.DefenseDownEffect;

public class EarthQuake extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public EarthQuake() {
        super("Earth Quake", 0, 30, "Deals earth damage based on user's Atk and lowers target's defense.");
        // กำหนดค่าพารามิเตอร์สำหรับสูตร
        this.baseDamage = 40;
        this.multiplier = 1.2;
    }
    
    @Override
    public void use(Character user, Character target) {
        // สูตรคำนวณ: damage = max( baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.30), 1 )
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.30)), 1);
        target.takeDamage(damage);
        // ใช้เอฟเฟกต์ลดค่า def เป็นเวลา 3 เทิร์น
        target.applyEffect(new DefenseDownEffect(3));
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}