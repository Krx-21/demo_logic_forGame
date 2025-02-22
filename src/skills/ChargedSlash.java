package skills;

import characters.Character;
import effects.ChargedEffect;
import java.util.Random;

public class ChargedSlash extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;
    
    public ChargedSlash() {
        // ปรับค่า manaCost และ cooldown ตามสมดุลของเกม
        super("Charged Slash", 22, 5, "Strikes with a scythe-arm and unleashes an electric surge. On hit, the target is charged, reducing their MP or delaying their next turn.");
        this.baseDamage = 30;
        this.multiplier = 2.0;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจจากค่า baseDamage และค่านักโจมตีของผู้ใช้
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.25)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " slashes with a charged scythe-arm at " + target.getName() 
                           + ", dealing " + damage + " damage.");
        
        // โอกาส 50% ที่จะทำให้เป้าหมายติด Charged effect (ลด MP หรือชะลอการกระทำในเทิร์นถัดไป)
        if(random.nextDouble() < 0.5) {
            target.applyEffect(new ChargedEffect(1, 15)); // duration 1 turn, ลด MP 15 หน่วย
            System.out.println(target.getName() + " is electrocuted and charged!");
        }
    }
}