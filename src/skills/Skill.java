package skills;

import characters.Character;

/*
 * Skill: คลาสพื้นฐานสำหรับสกิล
 * มีชื่อ ดาเมจ ค่าคอสต์ คำอธิบาย และเมธอด use(...) สำหรับใช้งานสกิล
 */

public abstract class Skill {
    private String name;
    private int manaCost;
    private int cooldown;
    private String description;

    public Skill(String name, int manaCost, int cooldown, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public String getDescription() {
        return description;
    }

    public abstract void use(Character user, Character target);
}