package skills;

import characters.Character;
import effects.FreezeEffect;

public class FrostHowl extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public FrostHowl() {
        super("Frost Howl", 20, 10, "Emits a resonating howl that creates an icy shockwave, damaging all targets and may freeze them.");
        // กำหนดค่าตัวแปรสำหรับสูตรคำนวณดาเมจ
        this.baseDamage = 25;
        this.multiplier = 1.4;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจโดยอิงจากค่า Atk ของผู้ใช้ หัก Def ของเป้าหมายด้วย factor 0.2
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        
        // โอกาส 40% ให้เป้าหมายติดสถานะ Freeze
        if (Math.random() < 0.4) {
            target.applyEffect(new FreezeEffect(1));
            System.out.println(target.getName() + " is frozen by Frost Howl!");
        }
        System.out.println(user.getName() + " uses Frost Howl, dealing " + damage + " damage to " + target.getName());
    }
}
