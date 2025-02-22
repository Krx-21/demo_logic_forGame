package enemies;

import java.util.List;

public class SteamSpider extends BaseEnemy {

    public SteamSpider() {
        name = "Steam Spider";
        hp = 110;
        atk = 30;
        def = 18;
        spd = 45;
        skills = List.of(new Skill("Steam Jet"), new Skill("Gear Web"));
    }
}