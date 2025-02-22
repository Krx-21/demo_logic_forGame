package enemies;

import characters.Character;
import fields.BaseFieldEffect;
import skills.Skill;
import java.util.List;

public class BaseBoss extends Character {
    protected BaseFieldEffect fieldEffect;

    public BaseBoss(String name, int hp, int atk, int def, int spd,
                    List<Skill> skills,
                    BaseFieldEffect fieldEffect) {
        super(name, hp, atk, def, spd);
        this.fieldEffect = fieldEffect;
    }

    public BaseFieldEffect getFieldEffect() {
        return fieldEffect;
    }

    public void setFieldEffect(BaseFieldEffect fieldEffect) {
        this.fieldEffect = fieldEffect;
    }
}