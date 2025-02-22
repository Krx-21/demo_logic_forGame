package difficulty;

import enemies.Monster;

public class DifficultyManager {
    public enum DifficultyLevel {
        EASY,
        NORMAL,
        HARD,
        NIGHTMARE
    }

    private DifficultyLevel difficulty;
    private float hpMultiplier;
    private float atkMultiplier;
    private float defMultiplier;
    private float spdMultiplier;
    private float expMultiplier;

    public DifficultyManager(DifficultyLevel selectedDifficulty) {
        this.difficulty = selectedDifficulty;
        setMultipliers();
        setExpMultiplier();
    }

    private void setExpMultiplier() {
        switch (difficulty) {
            case EASY:
                expMultiplier = 1.0f;
                break;
            case NORMAL:
                expMultiplier = 1.2f;
                break;
            case HARD:
                expMultiplier = 1.5f;
                break;
            case NIGHTMARE:
                expMultiplier = 2.0f;
                break;
        }
    }

    private void setMultipliers() {
        switch (difficulty) {
            case EASY:
                hpMultiplier = 1.0f;
                atkMultiplier = 1.0f;
                defMultiplier = 1.0f;
                spdMultiplier = 1.0f;
                break;
            case NORMAL:
                hpMultiplier = 1.2f;
                atkMultiplier = 1.2f;
                defMultiplier = 1.2f;
                spdMultiplier = 1.1f;
                break;
            case HARD:
                hpMultiplier = 1.5f;
                atkMultiplier = 1.5f;
                defMultiplier = 1.5f;
                spdMultiplier = 1.3f;
                break;
            case NIGHTMARE:
                hpMultiplier = 2.0f;
                atkMultiplier = 2.0f;
                defMultiplier = 2.0f;
                spdMultiplier = 1.5f;
                break;
        }
    }

    public Monster scaleMonsterStats(Monster baseMonster) {
        Monster scaledMonster = new Monster(
            baseMonster.name,
            (int) (baseMonster.hp * hpMultiplier),
            (int) (baseMonster.atk * atkMultiplier),
            (int) (baseMonster.def * defMultiplier),
            (int) (baseMonster.spd * spdMultiplier),
            baseMonster.skills
        );
        return scaledMonster;
    }
}