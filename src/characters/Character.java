package characters;

import java.util.List;
import effects.BaseDotEffect;
import buffs.BaseBuff;
import debuffs.BaseDebuff;
import effects.FireBurn;

/*
 * Character: คลาสพื้นฐานสำหรับตัวละครทั้งผู้เล่นและศัตรู
 * เก็บค่าสถานะ (HP, ATK, DEF, SPD) และเมธอดสำหรับรับ/สร้างความเสียหาย
 */

public class Character {
    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected int spd;
    protected List<BaseDotEffect> dotEffects;
    protected List<BaseBuff> buffs;
    protected List<BaseDebuff> debuffs;

    public Character(String name, int hp, int atk, int def, int spd) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void applyEffect(BaseDotEffect effect) {
        dotEffects.add(effect);
        effect.applyEffect(this);
    }

    public void applyBuff(BaseBuff buff) {
        buffs.add(buff);
        buff.applyBuff(this);
    }

    public void applyDebuff(BaseDebuff debuff) {
        debuffs.add(debuff);
        debuff.applyDebuff(this);
    }

    public void applyEffect(FireBurn effect) {
        // Implement effect application logic
    }

    public boolean hasEnoughCost(int cost) {
        // Logic to check if character has enough cost (e.g., MP)
        return true;
    }

    public int getAtk() {
        return atk;
    }
}