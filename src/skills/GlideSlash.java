package skills;

import characters.Character;

public class GlideSlash extends Skill {
    private int baseDamage;
    private double multiplier;

    public GlideSlash() {
        // manaCost = 15, cooldown = 3 (ปรับได้ตามความเหมาะสม)
        super("Glide Slash", 15, 3, "ร่อนตัวพุ่งเฉือนเป้าหมายด้วยกรงเล็บน้ำแข็ง");
        this.baseDamage = 20;
        this.multiplier = 1.7;
    }

    @Override
    public void use(Character user, Character target) {
        int damage = Math.max((int)(baseDamage + (user.getAtk() * multiplier) - (target.getDef() * 0.2)), 1);
        target.takeDamage(damage);
        System.out.println(user.getName() + " ร่อนตัวพุ่งเฉือน " + target.getName() +
                           " ด้วยกรงเล็บน้ำแข็ง ทำดาเมจ " + damage + " ให้กับศัตรู");
    }
}