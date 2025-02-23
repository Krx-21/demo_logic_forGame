package debuffs;

import characters.Character;

public class StunDebuff extends BaseDebuff {
    
    public StunDebuff(int duration) {
        super("Stun", duration);
    }
    
    @Override
    public void apply(Character target) {
        target.setStunned(true);
        System.out.println(target.getName() + " is stunned!");
    }
    
    @Override
    public void remove(Character target) {
        target.setStunned(false);
        System.out.println(target.getName() + " is no longer stunned");
    }
    
    @Override
    public void onTurnStart(Character target) {
        System.out.println(target.getName() + " is stunned and cannot act!");
    }
    
    @Override
    public void onTurnEnd(Character target) {
        // No effect on turn end
    }
}