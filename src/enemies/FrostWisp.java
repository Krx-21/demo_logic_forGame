package enemies;

import skills.ChillingTouch;
import skills.SnowfallAura;
import java.util.List;

public class FrostWisp extends Monster {
    public FrostWisp() {
        // กำหนดค่าพื้นฐานให้กับ Frost Wisp (ตัวอย่าง: HP, ATK, DEF, SPD)
        super("Frost Wisp", 220, 35, 20, 90);
        
        // กำหนดสกิลให้กับ Frost Wisp
        this.skills = List.of(
            new ChillingTouch(),
            new SnowfallAura()
        );
    }
}