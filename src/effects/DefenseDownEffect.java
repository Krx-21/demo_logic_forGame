package effects;

import characters.Character;

public class DefenseDownEffect extends BaseDotEffect {
    public DefenseDownEffect(int duration) {
        super(0.0f, duration, "Defense Down");
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setDef(target.getDef() - 10);
        System.out.println(target.getName() + "'s defense has been lowered.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setDef(target.getDef() + 10);
        System.out.println("Defense Down effect removed from " + target.getName());
    }
    
    @Override
    public void tickEffect(Character target) {
        // Logic สำหรับ tick effect ถ้าต้องการ
    }
}
