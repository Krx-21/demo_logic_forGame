package effects;

import characters.Character;

public class FireBurn extends BaseDotEffect {
    private float damage;
    
    public FireBurn(float damage, int duration) {
        super(damage, duration, "Fire Burn");
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
        System.out.println("Fire burn effect wears off from " + target.getName());
    }
}