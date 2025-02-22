package enemies;

import skills.SteamJet;
import skills.GearWeb;
import java.util.List;

public class SteamSpider extends Monster {
    public SteamSpider() {
        super("Steam Spider", 110, 30, 18, 45);
        this.skills = List.of(
            new SteamJet(),
            new GearWeb()
        );
    }
}