//// filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/src/enemies/IgneousGolem.java
package enemies;

import java.util.List;
import skills.EruptionSmash;
import skills.MoltenCore;

public class IgneousGolem extends Monster {
    private int accumulatedHeat = 0;
    
    public IgneousGolem() {
        super("Igneous Golem", 500, 60, 45, 30);
        // กำหนดสกิลให้กับ Igneous Golem
        this.skills = List.of(
            new EruptionSmash(),
            new MoltenCore()
        );
    }
    
    // เมธอดสำหรับสะสมความร้อน เมื่อได้รับดาเมจจากธาตุไฟ
    public void accumulateHeat(int heat) {
        accumulatedHeat += heat;
        System.out.println(getName() + " accumulates " + heat + " heat. Total heat: " + accumulatedHeat);
    }
    
    public int getAccumulatedHeat() {
        return accumulatedHeat;
    }
    
    public void resetAccumulatedHeat() {
        System.out.println(getName() + " releases all accumulated heat.");
        accumulatedHeat = 0;
    }
    
    // เมธอดสำหรับรับดาเมจจากธาตุไฟโดยเฉพาะ
    public void applyFireDamage(int damage) {
        // รับดาเมจตามปกติ
        takeDamage(damage);
        // สะสมความร้อน (ตัวอย่างสะสมเท่ากับ damage ที่ได้รับ)
        accumulateHeat(damage);
    }
}