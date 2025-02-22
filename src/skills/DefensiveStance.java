package skills;

import characters.Character;
import effects.DefenseUpEffect;

public class DefensiveStance extends Skill {
    public DefensiveStance() {
        super("Defensive Stance", 20, 4, "Increases defense for 2 turns");
    }
    
    @Override
    public void use(Character user, Character target) {
        user.applyEffect(new DefenseUpEffect(2, 20));  // 2 turns, +20 DEF
        System.out.println(user.getName() + " takes a defensive stance, increasing defense!");
    }
}
