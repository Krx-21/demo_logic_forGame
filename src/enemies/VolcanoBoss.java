package enemies;

import fields.LavaFieldEffect;
import skills.Skill;
import java.util.List;

public class VolcanoBoss extends BaseBoss {

    public VolcanoBoss() {
        super("อัคคีวิญญาณ", 1000, 150, 100, 80, List.of(
            new Skill("Eruption Smash", 0, 0, "ทุบพื้นอย่างรุนแรง สร้างความเสียหายธาตุไฟ (AoE) ให้กับผู้เล่น และอาจทำให้ติดสถานะ “เผาไหม้” (Burn)"),
            new Skill("Molten Shield", 0, 0, "สร้างเกราะลาวารอบตัว ลดความเสียหายที่ได้รับในเทิร์นถัดไป และสะท้อนความเสียหายกลับเล็กน้อยหากผู้เล่นโจมตีระยะประชิด"),
            new Skill("Flame Wave", 0, 0, "พ่นคลื่นไฟไปด้านหน้า โจมตีตัวละครเป็นเส้นตรง หากไม่ตั้งรับหรือไม่มีสกิลกันไฟ อาจโดนดาเมจอย่างรุนแรง"),
            new Skill("Lava Surge", 0, 0, "ร่ายพลังเรียกธารลาวาให้ไหลเข้ามาท่วมสนาม ลด HP ของผู้เล่นในทุกเทิร์นต่อเนื่องจนกว่าจะเอาชนะบอสหรือทำลายสนามลาวาได้ด้วยไอเทมหรือสกิลบางอย่าง")
        ));
        fieldEffect = new LavaFieldEffect(3, 20);
    }
}