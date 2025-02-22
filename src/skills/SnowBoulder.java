package skills;

import characters.Character;
import java.util.Random;

public class SnowBoulder extends Skill {
    private int baseDamage;
    private double multiplier;
    private Random random;
    
    public SnowBoulder() {
        // ตัวอย่างค่า: manaCost = 35, cooldown = 8 (ปรับเปลี่ยนได้ตามต้องการ)
        super("Snow Boulder", 35, 8, "Hurls a giant snow boulder causing AoE damage with a chance to stun, making enemies lose their turn.");
        this.baseDamage = 40;
        this.multiplier = 2.0;
        this.random = new Random();
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจพื้นฐาน โดยใช้ target เป็นตัวแทน AoE (ในระบบจริงจะวนลูปกับศัตรูในด่าน)
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " hurls a giant snow boulder, dealing " + damage + " damage to " + target.getName() + ".");
        
        // โอกาส 30% ที่จะทำให้เป้าหมายสตัน (เสียเทิร์น)
        if(random.nextDouble() < 0.3) {
            System.out.println(target.getName() + " is stunned by the impact and loses a turn!");
            // ในระบบจริงคุณอาจต้องเรียกใช้เอฟเฟกต์ Stun เช่น:
            // target.applyEffect(new StunEffect(1)); // ระยะเวลา 1 เทิร์น
        }
    }
}
