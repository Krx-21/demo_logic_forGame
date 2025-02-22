package fields;

public class BaseFieldEffect {
    protected String name;
    protected int duration;
    protected boolean isStackable; // ตัวอย่างการตั้งชื่อ

    public BaseFieldEffect(String name, int duration, boolean isStackable) {
        this.name = name;
        this.duration = duration;
        this.isStackable = isStackable;
    }

    public void applyEffectToField() {
        // Default หรือ abstract logic
    }

    public void removeEffectFromField() {
        // Default หรือ abstract logic
    }

    public void onTurnStart() {
        // Default หรือ abstract logic
    }

    public void onTurnEnd() {
        // Default หรือ abstract logic
    }
}