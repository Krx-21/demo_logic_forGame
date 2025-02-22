package enemies;

import skills.InfernoFang;
import skills.HowlOfEmbers;
import java.util.List;

public class MagmaWolf extends Monster {
    public MagmaWolf() {
        super("Magma Wolf", 450, 55, 35, 80);
        this.skills = List.of(
            new InfernoFang(),
            new HowlOfEmbers()
        );
    }
}