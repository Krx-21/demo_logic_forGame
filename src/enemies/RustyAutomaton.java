package enemies;

import java.util.List;

public class RustyAutomaton extends BaseEnemy {

    public RustyAutomaton() {
        name = "Rusty Automaton";
        hp = 120;
        atk = 35;
        def = 20;
        spd = 40;
        skills = List.of(new Skill("Metal Punch"), new Skill("Overheat Spark"));
    }
}