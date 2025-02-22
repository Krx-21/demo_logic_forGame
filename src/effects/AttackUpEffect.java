package effects;

import characters.Character;

public class AttackUpEffect extends BaseDotEffect {
    private int attackIncrease;
    
    public AttackUpEffect(int duration, int attackIncrease) {
        super(0.0f, duration, "Attack Up");
        this.attackIncrease = attackIncrease;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setAttack(target.getAttack() + attackIncrease);
        System.out.println(target.getName() + "'s attack increased by " + attackIncrease + "!");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setAttack(target.getAttack() - attackIncrease);
        System.out.println(target.getName() + "'s attack boost wears off.");
    }

    @Override
    public void tickEffect(Character target) {
        // ไม่มีผลต่อเนื่องในแต่ละเทิร์น
    }
}