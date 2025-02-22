package effects;

import characters.Character;

public class MoltenSplashEffect extends BaseDotEffect {
    private float damage;
    
    public MoltenSplashEffect(float damage, int duration) {
        super(damage, duration, "Molten Splash");
        this.damage = damage;
    }
    
    public MoltenSplashEffect(int duration) {
        super(5.0f, duration, "Molten Splash");  // กำหนดค่า damage เป็น 5.0f
    }
    
    @Override
    public void applyEffect(Character target) {
        target.takeDamage((int)damage);
        System.out.println(target.getName() + " is hit by molten splash for " + damage + " damage!");
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " continues to burn from the molten splash!");
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Molten splash effect wears off from " + target.getName());
    }
}