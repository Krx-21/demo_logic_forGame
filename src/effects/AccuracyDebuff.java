package effects;

import characters.Character;

public class AccuracyDebuff extends BaseDotEffect {
    private int accuracyReduction;

    public AccuracyDebuff(int duration, int accuracyReduction) {
        super(0.0f, duration, "Accuracy Down");
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void applyEffect(Character target) {
        target.setAccuracy(target.getAccuracy() - accuracyReduction);
    }

    @Override
    public void tickEffect(Character target) {
        // ไม่มีผลต่อเนื่องในแต่ละเทิร์น
    }

    @Override
    public void removeEffect(Character target) {
        target.setAccuracy(target.getAccuracy() + accuracyReduction);
    }
}