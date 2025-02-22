package skills;

import characters.Character;
import effects.AccuracyDebuff;
import java.util.Random;

public class SteamJet extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;
    
    public SteamJet() {
        // ในที่นี้ manaCost = 18, cooldown = 4 (สามารถปรับค่าตามต้องการ)
        super("Steam Jet", 18, 4, "Unleashes a blast of hot steam, dealing moderate damage and possibly reducing enemy accuracy.");
        this.baseDamage = 22;
        this.multiplier = 1.6;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " unleashes Steam Jet on " + target.getName() 
                + ", dealing " + damage + " damage.");
        
        // โอกาส 40% ที่จะลด Accuracy ของเป้าหมาย
        if (random.nextDouble() < 0.4) {
            target.applyEffect(new AccuracyDebuff(2, 20)); // Duration 2 turns, ลด Accuracy 20 หน่วย
            System.out.println(target.getName() + " is disoriented by the hot steam! Accuracy is reduced.");
        }
    }
}