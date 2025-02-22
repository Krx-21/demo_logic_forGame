package enemies;

import skills.Skill;
import skills.PowerSurge;

public class BatteryMantis extends Monster {
    private Skill skill;

    public BatteryMantis() {
        super("Battery Mantis", 100, 50, 30, 40);  // ส่งค่าที่เหมาะสม
        this.skill = new PowerSurge("Power Surge", 5, 3, 30);
    }
}