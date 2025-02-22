package effects;

import characters.Character;

public class BurnEffect extends BaseDotEffect {
    private float damage;
    
    public BurnEffect(int duration, float damage) {
        super(damage, duration, "Burn");
        this.damage = damage;
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + " is burning!");
    }
    
    @Override
    public void tickEffect(Character target) {
        target.takeDamage((int)damage);
        System.out.println(target.getName() + " takes " + damage + " burn damage!");
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Burn effect wears off from " + target.getName());
    }
}