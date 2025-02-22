package enemies;

import fields.TimeDistortionEffect;
import skills.SkillRepository;
import skills.Skill;
import java.util.List;

public class ClockworkRequiem extends BaseBoss {

    public ClockworkRequiem() {
        super("Clockwork Requiem", 1100, 140, 120, 90, List.of(
            SkillRepository.getSkill("Time Distortion"),
            SkillRepository.getSkill("Gear Cannon"),
            SkillRepository.getSkill("Reconstruct"),
            SkillRepository.getSkill("Clockwork Catastrophe")
        ), new TimeDistortionEffect(2, "Increases the boss's turns."));
    }
}