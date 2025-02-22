package skills;

import characters.Character;

public class GearCannon extends Skill {
    private int baseDamage;
    private double multiplier;
    
    public GearCannon() {
        super("Gear Cannon", 25, 3, "Fires a barrage of mechanical gears at the target");
        this.baseDamage = 20;
        this.multiplier = 1.5;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " fires Gear Cannon at " + target.getName() + ", dealing " + damage + " damage!");
    }
}