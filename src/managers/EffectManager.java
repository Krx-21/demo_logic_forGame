package managers;

import java.util.ArrayList;
import java.util.List;
import buffs.BaseBuff;
import debuffs.BaseDebuff;
import characters.Character;
import effects.BaseDotEffect;

public class EffectManager {
    private List<BaseBuff> buffs;
    private List<BaseDebuff> debuffs;
    private List<BaseDotEffect> dotEffects;

    public EffectManager() {
        buffs = new ArrayList<>();
        debuffs = new ArrayList<>();
        dotEffects = new ArrayList<>();
    }

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

    public void processTurn(Character target) {
        // Process DoT effects
        for (BaseDotEffect effect : dotEffects) {
            effect.tickEffect(target);
        }
        
        // Process buffs
        for (BaseBuff buff : buffs) {
            buff.onTurnEnd(target);
        }
        
        // Process debuffs
        for (BaseDebuff debuff : debuffs) {
            debuff.onTurnEnd(target);
        }
        
        // Remove expired effects
        removeExpiredEffects();
    }

    private void removeExpiredEffects() {
        buffs.removeIf(buff -> buff.getDuration() <= 0);
        debuffs.removeIf(debuff -> debuff.getDuration() <= 0);
        dotEffects.removeIf(effect -> effect.getDuration() <= 0);
    }
}