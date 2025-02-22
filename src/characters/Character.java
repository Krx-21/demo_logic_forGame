package characters;

import java.util.ArrayList;
import java.util.List;
import skills.Skill;
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
    protected List<Skill> skills; // ถ้าต้องการ

    public Character(String name, int hp, int atk, int def, int spd) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        // ตรวจสอบให้แน่ใจว่าตัวแปรอื่น ๆ ถูก initialize แล้ว
        this.dotEffects = new ArrayList<>();
        this.buffs = new ArrayList<>();
        this.debuffs = new ArrayList<>();
    }
    
    // Overloaded constructor เมื่อมีสกิล
    public Character(String name, int hp, int atk, int def, int spd, List<Skill> skills) {
        this(name, hp, atk, def, spd);
        this.skills = skills;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public int getHp() {
        return hp;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAtk() {
        return atk;
    }
    
    public int getSpd() {
        return spd;
    }
    
    public void setSpd(int spd) {
        this.spd = spd;
    }
    
    // เพิ่มเมธอด getDef() และ setDef()
    public int getDef() {
        return def;
    }
    
    public void setDef(int def) {
        this.def = def;
    }
    
    // ตัวอย่าง applyEffect ที่เกิดปัญหา
    public void applyEffect(BaseDotEffect effect) {
        // dotEffects ต้องไม่เป็น null
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
        // ตรวจสอบค่า MP เป็นต้น
        return true;
    }
}