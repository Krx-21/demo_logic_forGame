package effects;

import characters.Character;

public class DefenseDownEffect extends BaseDotEffect {
    private int defenseReduction;
    
    public DefenseDownEffect(int duration, int defenseReduction) {
        super(0.0f, duration, "Defense Down");
        this.defenseReduction = defenseReduction;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setDef(target.getDef() - defenseReduction);
        System.out.println(target.getName() + "'s defense decreased by " + defenseReduction + "!");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setDef(target.getDef() + defenseReduction);
        System.out.println(target.getName() + "'s defense returns to normal.");
    }
    
    @Override
    public void tickEffect(Character target) {
        // Logic สำหรับ tick effect ถ้าต้องการ
    }
}
