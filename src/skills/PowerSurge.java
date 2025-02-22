package skills;

import buffs.AttackUp;
import characters.Character;

public class PowerSurge extends Skill {
    private int duration;
    private int attackIncrease;

    public PowerSurge(String name, int cooldown, int duration, int attackIncrease) {
        super(name, cooldown, duration, "Power Surge");
        this.duration = duration;
        this.attackIncrease = attackIncrease;
    }

    @Override
    public void use(Character user, Character target) {
        user.addBuff(new AttackUp(duration, attackIncrease));
    }
}