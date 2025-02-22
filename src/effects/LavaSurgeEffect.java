package effects;

import characters.Character;

public class LavaSurgeEffect extends BaseDotEffect {
    private int turnCount = 0;
    
    // ในที่นี้เราจะให้ effect นี้มีอายุยาวมาก จนกว่าจะถูกลบออกด้วยไอเทมหรือสกิลบางอย่าง
    public LavaSurgeEffect() {
        super(0.0f, 9999, "Lava Surge");
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + " is engulfed by a continuous lava flow!");
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Lava Surge effect has been dispelled from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        // ทุกเทิร์นให้ทำดาเมจต่อเนื่อง ตัวอย่าง 10 ดาเมจต่อเทิร์น
        int damage = 10;
        target.takeDamage(damage);
        System.out.println(target.getName() + " takes " + damage + " lava damage due to Lava Surge.");
        turnCount++;
    }
}
