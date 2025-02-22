//// filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/src/skills/HowlOfEmbers.java
package skills;

import characters.Character;
import effects.AttackUpEffect;
import effects.DefenseDownEffect;

public class HowlOfEmbers extends Skill {
    public HowlOfEmbers() {
        super("Howl of Embers", 30, 4, "Increases user's attack and reduces target's defense");
    }
    
    @Override
    public void use(Character user, Character target) {
        // เพิ่มค่าโจมตีให้ผู้ใช้
        user.applyEffect(new AttackUpEffect(2, 10));  // duration 2 turns, เพิ่ม attack 10
        
        // ลดค่าป้องกันของเป้าหมาย
        target.applyEffect(new DefenseDownEffect(2, 10));  // duration 2 turns, ลด defense 10
        
        System.out.println(user.getName() + " howls with burning fury!");
    }
}
