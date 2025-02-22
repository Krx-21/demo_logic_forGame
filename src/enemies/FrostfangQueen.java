package enemies;

import fields.BlizzardFieldEffect;
import skills.SkillRepository;
import skills.Skill;
import java.util.List;

public class FrostfangQueen extends BaseBoss {

    public FrostfangQueen() {
        super("Frostfang Queen", 900, 130, 110, 70, List.of(
            SkillRepository.getSkill("Frost Howl"),
            SkillRepository.getSkill("Snowstorm Veil"),
            SkillRepository.getSkill("Icicle Fang"),
            SkillRepository.getSkill("Glacial Domain")
        ), new BlizzardFieldEffect(3, 10));
    }
}