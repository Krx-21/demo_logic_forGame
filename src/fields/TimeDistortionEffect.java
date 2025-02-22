package fields;

/*
 * TimeDistortionEffect: สนามที่ทำให้เทิร์นของบอสเพิ่มขึ้น หรือบิดเบือนเวลา
 * สืบทอดจาก BaseFieldEffect
 */

public class TimeDistortionEffect extends BaseFieldEffect {
    private String description;

    public TimeDistortionEffect(int duration, String description) {
        super("Time Distortion", duration, false);  
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}