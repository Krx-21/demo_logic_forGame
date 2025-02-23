package game;

public class GameStats {
    private int monstersDefeated;
    private int bossesDefeated;
    private int totalXP;
    private int totalDamageDealt;
    private int totalDamageTaken;
    private int skillsUsed;

    public void incrementMonstersDefeated() {
        monstersDefeated++;
    }

    public void incrementBossesDefeated() {
        bossesDefeated++;
    }

    public void addXP(int xp) {
        totalXP += xp;
    }

    public void addDamageDealt(int damage) {
        totalDamageDealt += damage;
    }

    public void addDamageTaken(int damage) {
        totalDamageTaken += damage;
    }

    public void incrementSkillsUsed() {
        skillsUsed++;
    }

    // Getters
    public int getMonstersDefeated() { return monstersDefeated; }
    public int getBossesDefeated() { return bossesDefeated; }
    public int getTotalXP() { return totalXP; }
    public int getTotalDamageDealt() { return totalDamageDealt; }
    public int getTotalDamageTaken() { return totalDamageTaken; }
    public int getSkillsUsed() { return skillsUsed; }
}