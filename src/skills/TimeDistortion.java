//// filepath: /c:/Users/godof/Documents/CEDT/ProgMeth/ZPRO/new_game/javafx-project/src/skills/TimeDistortion.java
package skills;

import characters.Character;
import game.Game;

public class TimeDistortion extends Skill {

    public TimeDistortion() {
        super("Time Distortion", 0, 0, "Grants an extra turn to the user.");
    }
    
    @Override
    public void use(Character user, Character target) {
        System.out.println(user.getName() + " uses Time Distortion: Extra turn granted!");
        Game.grantUserExtraTurn();
    }
}