package skills;

import characters.Character;

public class FlameWave extends Skill {
    private double multiplier;
    private int baseDamage;
    
    public FlameWave() {
        super("Flame Wave", 15, 8, "Releases a wave of fire in a straight line. If unguarded, it can deal heavy damage.");
        this.baseDamage = 25;
        this.multiplier = 1.7;
    }
    
    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.15)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " unleashes Flame Wave, scorching the target for " + damage + " damage!");
    }
}
