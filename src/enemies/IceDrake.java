package enemies;

import skills.FreezingBreath;
import skills.GlideSlash;
import java.util.List;

public class IceDrake extends Monster {
    public IceDrake() {
        // ปรับค่าพารามิเตอร์ (HP, ATK, DEF, SPD) ให้เหมาะสมกับเกมของคุณ
        super("Ice Drake", 350, 50, 30, 60);
        this.skills = List.of(
            new FreezingBreath(),
            new GlideSlash()
        );
    }

    public int performAttack() {
        // ใส่ตรรกะการคำนวณความเสียหายที่ต้องการ
        return 0; // เปลี่ยนเป็นค่าความเสียหายที่คำนวณได้
    }
}