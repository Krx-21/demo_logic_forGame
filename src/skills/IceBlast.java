package skills;

import characters.Character;
import effects.FreezeEffect;

public class IceBlast extends Skill {
    private double multiplier;
    private int baseDamage;
  
    public IceBlast() {
        super("Ice Blast", 0, 15, "Deals ice damage based on user's Atk and applies freeze effect.");
        this.baseDamage = 20;
        this.multiplier = 1.3;
    }
  
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        target.applyEffect(new FreezeEffect(3));
        System.out.println(user.getName() + " uses " + getName() + " dealing " + damage + " damage.");
    }
}