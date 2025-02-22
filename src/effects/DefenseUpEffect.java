package effects;

import characters.Character;

public class DefenseUpEffect extends BaseDotEffect {
    private int defenseIncrease;
    
    public DefenseUpEffect(int duration, int defenseIncrease) {
        super(0.0f, duration, "Defense Up");
        this.defenseIncrease = defenseIncrease;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setDef(target.getDef() + defenseIncrease);
        System.out.println(target.getName() + "'s defense increased by " + defenseIncrease + "!");
    }
    
    @Override
    public void tickEffect(Character target) {
        // No tick effect needed for defense buff
        System.out.println(target.getName() + " maintains increased defense.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setDef(target.getDef() - defenseIncrease);
        System.out.println(target.getName() + "'s defense returns to normal.");
    }
}