package items;

public class HealthPotion extends Item {
    public HealthPotion() {
        super("Health Potion", "Restores 50 HP", 50);
    }
    
    public void use(characters.Character target) {
        target.setHp(Math.min(target.getHp() + getValue(), target.getMaxHp()));
    }
}
