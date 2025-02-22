package game;

public class DifficultyManager {
    private Difficulty difficulty;
    
    public DifficultyManager(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    public double getExpMultiplier() {
        switch (difficulty) {
            case EASY: return 0.8;
            case MEDIUM: return 1.0;  // เปลี่ยนจาก NORMAL เป็น MEDIUM
            case HARD: return 1.2;
            case NIGHTMARE: return 1.5;
            default: return 1.0;
        }
    }

    public float getStatMultiplier() {
        switch (difficulty) {
            case EASY: return 0.8f;
            case MEDIUM: return 1.0f;
            case HARD: return 1.2f;
            case NIGHTMARE: return 1.5f;
            default: return 1.0f;
        }
    }
}