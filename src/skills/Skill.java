package skills;

/*
 * Skill: คลาสพื้นฐานสำหรับสกิล
 * มีชื่อ ดาเมจ ค่าคอสต์ คำอธิบาย และเมธอด use(...) สำหรับใช้งานสกิล
 */


public class Skill {
    private String name;
    private int damage;
    private int cost;
    private String description;

    public Skill(String name, int damage, int cost, String description) {
        this.name = name;
        this.damage = damage;
        this.cost = cost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public void use(characters.Character user, characters.Character target) {
        // Default or abstract logic, depending on your design
    }
}