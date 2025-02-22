package effects;

import characters.Character;

public class HighPitchWhirrEffect extends BaseDotEffect {
    private int accuracyReduction;
    
    public HighPitchWhirrEffect(int duration, int accuracyReduction) {
        super(0, duration, "High Pitch Whirr");
        this.accuracyReduction = accuracyReduction;
    }
    
    @Override
    public void applyEffect(Character target) {  // แก้จาก apply เป็น applyEffect
        target.setAccuracy(target.getAccuracy() - accuracyReduction);
        System.out.println(target.getName() + " is disoriented by the high-pitched sound!");
    }
    
    @Override
    public void tickEffect(Character target) {
        System.out.println(target.getName() + " continues to be disturbed by the high-frequency noise.");
    }
    
    @Override
    public void removeEffect(Character target) {
        target.setAccuracy(target.getAccuracy() + accuracyReduction);
        System.out.println("High-Pitch Whirr effect wears off from " + target.getName() + ". Accuracy is restored.");
    }
}