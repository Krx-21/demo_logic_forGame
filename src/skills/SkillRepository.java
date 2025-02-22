package skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * SkillRepository: จัดการคลังสกิลทั้งหมดในเกม
 * มีเมธอด getSkill(...) เพื่อคืนอินสแตนซ์ของสกิลตามชื่อที่ต้องการ
 */

public class SkillRepository {
    private static final Map<String, Skill> skills = new HashMap<>();

    static {
        // Initialize all skills
        Skill iceBlast = new IceBlast(); // ตรวจสอบว่า IceBlast constructor ไม่มีปัญหา
        skills.put(iceBlast.getName(), iceBlast);
        skills.put("Fire Toss", new FireToss());
        skills.put("Fireball", new Skill("Fireball", 20, 10, "A powerful fireball attack."));
        skills.put("Ice Shard", new Skill("Ice Shard", 15, 8, "A sharp ice shard attack."));
        skills.put("Thunder Strike", new Skill("Thunder Strike", 25, 12, "A powerful thunder strike."));
        skills.put("Eruption Smash", new Skill("Eruption Smash", 20, 10, "Smashes the ground violently, dealing fire damage (AoE) to the player and may cause 'Burn' status."));
        skills.put("Molten Shield", new Skill("Molten Shield", 10, 5, "Creates a lava shield around the user, reducing damage received in the next turn and reflecting some damage if attacked in melee."));
        skills.put("Flame Wave", new Skill("Flame Wave", 15, 8, "Spews a wave of fire forward, dealing damage in a straight line. If not defended against or protected by a fire skill, it can cause severe damage."));
        skills.put("Lava Surge", new Skill("Lava Surge", 30, 15, "Summons a lava flow to flood the battlefield, reducing the player's HP each turn until the boss is defeated or the lava field is destroyed with an item or skill."));
        skills.put("Frost Howl", new Skill("Frost Howl", 20, 10, "Howls loudly, creating an ice shockwave that attacks the entire team and may 'Freeze' the player, slowing down or limiting actions in the next turn."));
        skills.put("Snowstorm Veil", new Skill("Snowstorm Veil", 10, 5, "Creates a snowstorm that obscures vision, causing the player to have a higher chance of missing attacks (reduced accuracy)."));
        skills.put("Icicle Fang", new Skill("Icicle Fang", 15, 8, "Leaps to attack a single target with icy fangs, dealing high damage and may cause 'Bleed' status due to ice spikes piercing the skin."));
        skills.put("Glacial Domain", new Skill("Glacial Domain", 30, 15, "Transforms the battlefield into an ice field, slowing down the player's movement (reduced speed) and dealing ice damage periodically."));
        skills.put("Time Distortion", new Skill("Time Distortion", 0, 0, "Causes the boss's attack turn to repeat or skips some of the player's turns, randomly resulting in the boss gaining extra turns or the player losing a turn."));
        skills.put("Gear Cannon", new Skill("Gear Cannon", 0, 0, "Transforms gears into a steam-powered cannon, firing high-powered bullets at a single target or sometimes as an AoE scatter shot."));
        skills.put("Reconstruct", new Skill("Reconstruct", 0, 0, "Heals itself by absorbing energy from surrounding gears, increasing the boss's HP or armor."));
        skills.put("Clockwork Catastrophe", new Skill("Clockwork Catastrophe", 0, 0, "Summons 'Gear Catastrophe', causing all mechanisms in the tower to activate simultaneously, dealing multiple waves of damage (2-3 turns) and may randomly cause 'Jammed' status (limited movement or skill usage)."));
    }

    public static Skill getSkill(String name) {
        return skills.get(name);
    }

    public static Set<String> getAllSkillNames() {
        return skills.keySet();
    }
}