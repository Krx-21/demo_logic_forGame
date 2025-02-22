package enemies;

import fields.TimeDistortionEffect;

public class SteampunkBoss extends BaseBoss {

    public SteampunkBoss() {
        name = "กงล้อจักรกลอมตะ";
        hp = 1100;
        atk = 140;
        def = 120;
        spd = 90;
        skills = List.of(new Skill("Time Distortion"), new Skill("Gear Cannon"), new Skill("Reconstruct"), new Skill("Clockwork Catastrophe"));
        fieldEffect = new TimeDistortionEffect(2, "เพิ่มเทิร์นให้บอส");
    }
}