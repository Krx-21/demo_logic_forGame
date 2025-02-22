package effects;

import characters.Character;

public class ClockworkCatastropheEffect extends BaseDotEffect {
    private int turnsTriggered;
    
    public ClockworkCatastropheEffect() {
        super(0.0f, 3, "Clockwork Catastrophe");
        this.turnsTriggered = 0;
    }
    
    @Override
    public void applyEffect(Character target) {
        System.out.println(target.getName() + " is caught in Clockwork Catastrophe! The tower mechanisms are in chaos!");
    }
    
    @Override
    public void tickEffect(Character target) {
        int waveDamage = Math.max((int)(10 + turnsTriggered * 5) - (target.getDef() / 5), 1);
        target.takeDamage(waveDamage);
        System.out.println(target.getName() + " takes " + waveDamage + " damage from the ongoing Clockwork Catastrophe effect!");
        turnsTriggered++;
        
        if (Math.random() < 0.3) {
            System.out.println(target.getName() + " is jammed by the explosive gears!");
        }
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("Clockwork Catastrophe effect has ended on " + target.getName() + ".");
    }
}
