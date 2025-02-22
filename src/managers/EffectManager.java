package managers;

import java.util.List;
import effects.BaseDotEffect;
import buffs.BaseBuff;
import debuffs.BaseDebuff;

public class EffectManager {
    private List<BaseDotEffect> dotEffects;
    private List<BaseBuff> buffs;
    private List<BaseDebuff> debuffs;

    public void addDotEffect(BaseDotEffect effect) {
        dotEffects.add(effect);
    }

    public void removeDotEffect(BaseDotEffect effect) {
        dotEffects.remove(effect);
    }

    public void addBuff(BaseBuff buff) {
        buffs.add(buff);
    }

    public void removeBuff(BaseBuff buff) {
        buffs.remove(buff);
    }

    public void addDebuff(BaseDebuff debuff) {
        debuffs.add(debuff);
    }

    public void removeDebuff(BaseDebuff debuff) {
        debuffs.remove(debuff);
    }

    public void tickEffects(Character target) {
        for (BaseDotEffect effect : dotEffects) {
            effect.tickEffect(target);
        }
        for (BaseBuff buff : buffs) {
            buff.onTurnEnd(target);
        }
        for (BaseDebuff debuff : debuffs) {
            debuff.onTurnEnd(target);
        }
    }
}