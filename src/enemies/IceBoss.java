package enemies;

import fields.BlizzardFieldEffect;

public class IceBoss extends BaseBoss {

    public IceBoss() {
        name = "ราชินีหมาป่าน้ำแข็ง";
        hp = 900;
        atk = 130;
        def = 110;
        spd = 70;
        skills = List.of(new Skill("Frost Howl"), new Skill("Snowstorm Veil"), new Skill("Icicle Fang"), new Skill("Glacial Domain"));
        fieldEffect = new BlizzardFieldEffect(3, 10);
    }
}