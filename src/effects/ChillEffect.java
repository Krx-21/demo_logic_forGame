package effects;

import characters.Character;

public class ChillEffect extends BaseDotEffect {
    private int slowAmount;
    
    public ChillEffect(int duration, int slowAmount) {
        // ไม่ได้คำนวณ dot damage โดยตรง (rate = 0.0f)
        super(0.0f, duration, "Chill");
        this.slowAmount = slowAmount;
    }
    
    @Override
    public void applyEffect(Character target) {
        target.setSpd(target.getSpd() - slowAmount);
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " remains chilled.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setSpd(target.getSpd() + slowAmount);
    }
}