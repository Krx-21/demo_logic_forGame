package skills;

import java.util.HashMap;
import java.util.Map;
import characters.Character;

public class SkillRepository {
    private static Map<String, Skill> skills = new HashMap<>();
    
    static {
        // ตรวจสอบให้แน่ใจว่ามีการลงทะเบียนสกิล "Ice Blast"
        skills.put("Ice Blast", new IceBlast());
        
        // ลงทะเบียนสกิลอื่นๆ ตามที่คุณต้องการ
        skills.put("Fireball", new Skill("Fireball", 20, 10, "A powerful fireball attack.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.4) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " casts Fireball, dealing " + damage + " damage.");
            }
        });
        skills.put("Ice Shard", new Skill("Ice Shard", 15, 8, "A sharp ice shard attack.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.2) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " hurls Ice Shard, dealing " + damage + " damage.");
            }
        });
        skills.put("Thunder Strike", new Skill("Thunder Strike", 25, 12, "A powerful thunder strike.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.8) - (int)(target.getDef() * 0.25), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " casts Thunder Strike, dealing " + damage + " damage.");
            }
        });
        skills.put("Eruption Smash", new Skill("Eruption Smash", 20, 10, "Smashes the ground violently, dealing fire damage (AoE) to the player and may cause 'Burn' status.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.4) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " uses Eruption Smash, dealing " + damage + " damage.");
            }
        });
        skills.put("Molten Shield", new Skill("Molten Shield", 10, 5, "Creates a lava shield around the user, reducing damage received in the next turn and reflecting some damage if attacked in melee.") {
            @Override
            public void use(Character user, Character target) {
                // นี่คือสกิลป้องกัน ยกตัวอย่างแค่ log
                System.out.println(user.getName() + " uses Molten Shield to reduce incoming damage next turn.");
            }
        });
        skills.put("Flame Wave", new Skill("Flame Wave", 15, 8, "Spews a wave of fire forward, dealing damage in a straight line.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.6) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " unleashes Flame Wave, dealing " + damage + " damage.");
            }
        });
        skills.put("Lava Surge", new Skill("Lava Surge", 30, 15, "Summons a lava flow to reduce the player's HP each turn until removed.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.5) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " calls Lava Surge, dealing " + damage + " damage.");
            }
        });
        skills.put("Frost Howl", new Skill("Frost Howl", 20, 10, "Creates an ice shockwave that may 'Freeze' the target.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.3) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " uses Frost Howl, dealing " + damage + " damage.");
            }
        });
        skills.put("Snowstorm Veil", new Skill("Snowstorm Veil", 10, 5, "Creates a snowstorm that reduces accuracy.") {
            @Override
            public void use(Character user, Character target) {
                // สกิลนี้อาจเป็นสกิลที่มีผลด้านสเตตัส
                System.out.println(user.getName() + " uses Snowstorm Veil to obscure vision.");
            }
        });
        skills.put("Icicle Fang", new Skill("Icicle Fang", 15, 8, "Attacks with icy fangs, possibly causing 'Bleed'.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.7) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " uses Icicle Fang, dealing " + damage + " damage.");
            }
        });
        skills.put("Glacial Domain", new Skill("Glacial Domain", 30, 15, "Turns the battlefield into an ice field, dealing periodic damage.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 1.4) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " invokes Glacial Domain, dealing " + damage + " damage.");
            }
        });
        skills.put("Time Distortion", new Skill("Time Distortion", 0, 0, "Alters turn order by repeating boss's attack turn or skipping player's turn.") {
            @Override
            public void use(Character user, Character target) {
                System.out.println(user.getName() + " uses Time Distortion to alter the turn order.");
            }
        });
        skills.put("Gear Cannon", new Skill("Gear Cannon", 0, 0, "Fires high-powered bullets at a target or as AoE scatter shot.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 2.0) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " fires Gear Cannon, dealing " + damage + " damage.");
            }
        });
        skills.put("Reconstruct", new Skill("Reconstruct", 0, 0, "Heals itself by absorbing energy from surrounding gears.") {
            @Override
            public void use(Character user, Character target) {
                // สำหรับสกิลนี้ สมมติเป็น self-healing
                System.out.println(user.getName() + " uses Reconstruct to heal itself.");
            }
        });
        skills.put("Clockwork Catastrophe", new Skill("Clockwork Catastrophe", 0, 0, "Activates all mechanisms to deal multiple waves of damage, may cause 'Jammed' status.") {
            @Override
            public void use(Character user, Character target) {
                int damage = Math.max((int)(user.getAtk() * 2.5) - target.getDef(), 1);
                target.takeDamage(damage);
                System.out.println(user.getName() + " unleashes Clockwork Catastrophe, dealing " + damage + " damage.");
            }
        });
    }
    
    public static Skill getSkill(String name) {
        return skills.get(name);
    }
    
    public static Iterable<String> getAllSkillNames() {
        return skills.keySet();
    }
}