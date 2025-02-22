package effects;

import characters.Character;

public class LavaSurgeEffect extends BaseDotEffect {
    private float damage;
    
    public LavaSurgeEffect(float damage, int duration) {
        super(damage, duration, "Lava Surge");
        this.damage = damage;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.takeDamage((int)damage);
        System.out.println(target.getName() + " is burned by lava surge for " + damage + " damage!");
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Lava Surge effect has been dispelled from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " continues to burn from the lava!");
    }
}
