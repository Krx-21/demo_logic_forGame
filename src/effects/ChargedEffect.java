package effects;

import characters.Character;

public class ChargedEffect extends BaseDotEffect {
    private int mpReduction;
    
    // duration เป็นจำนวนเทิร์นที่จะใช้งาน effect, mpReduction คือจำนวน MP ที่ลดลง
    public ChargedEffect(int duration, int mpReduction) {
        // ไม่ใช้ rate ในการคำนวณ damage ของเอฟเฟกต์นี้
        super(0.0f, duration, "Charged");
        this.mpReduction = mpReduction;
    }
    
    @Override
    public void applyEffect(Character target) {
        // สมมุติว่ามีระบบ MP ใน Character, หากมีให้ลด MP
        // target.setMp(target.getMp() - mpReduction);
        System.out.println(target.getName() + " is charged! Their MP is reduced by " + mpReduction + 
                           " or their next turn is delayed.");
    }
    
    @Override
    public void tickEffect(Character target) {
        // ไม่มีการทำงานเพิ่มเติมในแต่ละเทิร์น (สามารถเพิ่มเติม logic ได้)
        System.out.println(target.getName() + " remains affected by the electric surge.");
    }
    
    @Override
    public void removeEffect(Character target) {
        System.out.println("The charged effect wears off from " + target.getName() + ".");
    }
}