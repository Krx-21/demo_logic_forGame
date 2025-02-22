package skills;

import characters.Character;
import effects.FireBurn;

public class EruptionSmash extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public EruptionSmash() {
        super("Eruption Smash", 20, 10, "Smashes the ground violently, dealing fire AoE damage and may cause Burn.");
        this.baseDamage = 30;
        this.multiplier = 1.6;
    }
    
    @Override
    public void use(Character user, Character target) {
        // คำนวณดาเมจโดยอิงจากค่า Atk ของผู้ใช้และหัก Def ของเป้าหมาย
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        // มีโอกาสให้เป้าหมายติดสถานะ Burn (50%)
        if (Math.random() < 0.5) {
            target.applyEffect(new FireBurn(3.0f, 3));
            System.out.println(target.getName() + " is burned by the eruption!");
        }
        System.out.println(user.getName() + " unleashes Eruption Smash, dealing " + damage + " fire damage.");
    }
}
