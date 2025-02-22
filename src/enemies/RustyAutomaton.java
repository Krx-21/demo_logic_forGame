package enemies;

import skills.MetalPunch;
import skills.GearCannon;
import java.util.List;

public class RustyAutomaton extends Monster {
    public RustyAutomaton() {
        super("Rusty Automaton", 350, 40, 35, 40);
        this.skills = List.of(
            new MetalPunch(),
            new GearCannon()
        );
    }
}