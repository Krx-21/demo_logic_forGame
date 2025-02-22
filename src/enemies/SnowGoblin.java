package enemies;

import skills.IceJavelin;
import skills.BoneChill;
import java.util.List;

public class SnowGoblin extends Monster {
    public SnowGoblin() {
        super("Snow Goblin", 250, 40, 20, 80);
        
        // กำหนดสกิลให้กับ Snow Goblin
        this.skills = List.of(
            new IceJavelin(),
            new BoneChill()
        );
    }
}