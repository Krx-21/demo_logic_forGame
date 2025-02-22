package enemies;

import fields.BaseFieldEffect;
import skills.Skill;
import java.util.List;

/*
 * BaseBoss: ขยายจาก BaseEnemy เพื่อรองรับบอสในเกม
 * มีตัวแปร fieldEffect สำหรับการคุมเอฟเฟกต์สนาม (FieldEffect)
 */

public class BaseBoss extends BaseEnemy {
    protected BaseFieldEffect fieldEffect;

    public BaseBoss(String name, int hp, int atk, int def, int spd,
                    List<Skill> skills,
                    BaseFieldEffect fieldEffect) {
        super(name, hp, atk, def, spd, skills);
        this.fieldEffect = fieldEffect;
    }

    public BaseFieldEffect getFieldEffect() {
        return fieldEffect;
    }

    public void setFieldEffect(BaseFieldEffect fieldEffect) {
        this.fieldEffect = fieldEffect;
    }
}