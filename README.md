# Song of Twelve Feathers

**Version 0.0.2b (Current)**

## Version History

### v0.0.2b (Latest)
**New Features & Improvements:**
- **Combat System Overhaul**:
  - Fixed boss fight progression system
  - Implemented proper monster encounter system without duplicates
  - Enhanced theme progression system
  - Added developer testing tool (DEV WANT TO KILL YOU!! button)

**Bug Fixes:**
- Fixed boss encounters not triggering after defeating all monsters
- Fixed duplicate monster encounters within the same theme
- Fixed monsters respawning with 0 HP
- Fixed infinite combat loops
- Fixed dev tool not affecting bosses
- Fixed experience point calculations

### v0.0.2a
**New Features & Improvements:**
- Enhanced Player Character:
  - Increased base stats (HP: 500, ATK: 70, DEF: 45, SPD: 80)
  - Improved stat growth per level
  - Added 6 unique skills:
    - Basic Slash (No cost, No cooldown)
    - Power Strike (200% ATK damage)
    - Flaming Blade (180% ATK + Burn effect)
    - Defensive Stance (Increases DEF)
    - Whirlwind Slash (250% ATK damage)
    - Arcane Slash (200% ATK, 50% defense penetration)

- Improved Combat UI:
  - Redesigned battle interface
  - Dynamic skill selection system
  - Added tooltips showing skill details
  - Improved victory notifications
  - Added XP gain display
  - Direct battle start after difficulty selection

- Enhanced Battle Mechanics:
  - Added speed-based turn order
  - Implemented random initiative for equal speeds
  - Increased skill damage scaling
  - Added proper effect system integration

### v0.0.2
- Added themed monster system (Frost, Lava, Steampunk)
- Implemented status effects system
- Enhanced combat mechanics with hit chance and critical hits

### v0.0.1 (Initial Release)
- Basic game concept and structure
- Initial combat system implementation
- Basic character creation
- Preliminary UI elements

## Overview
**Song of Twelve Feathers** is an immersive roguelite adventure game set in a fantasy world filled with magic, mystery, and epic battles. The game revolves around the legend of the "Twelve Sacred Feathers," scattered across different lands. Whoever collects all twelve feathers will gain the power to control time and unlock the secrets of the world's creation and destruction.

## Game Overview
You play as an unnamed adventurer who has unknowingly obtained the "Feather of Beginnings." Upon touching the feather, you hear a mysterious song—a sweet yet enigmatic melody that calls you to embark on a quest to collect the remaining feathers and save the world from impending darkness.

Every journey is different, as the world changes each time you venture forth. The game follows a roguelite style, where the world reshapes itself after every defeat or restart. However, you will retain some experience and upgrades to aid in your continued adventure.

## Gameplay
- **Turn-Based Combat**: Engage in strategic battles where you must wisely use skills, magic, and items to defeat enemies.
- **Ever-Changing World**: Each time you lose or restart, the world changes, presenting new challenges and opportunities. Adapt your strategy to overcome ever-evolving obstacles.
- **Boss Fights**: Battle powerful guardians that protect the feathers, each with their unique abilities and tricks.
- **Character Development**: Choose rewards to upgrade your character. Whether it's new weapons, healing items, or funds to improve skills, shape your adventurer to match your playstyle.

## Features
- **Dynamic Story**: Follow the Song of Twelve Feathers as it narrates your journey, filled with sacrifices, victories, and mysteries.
- **Challenging Turn-Based Strategy**: Plan your moves carefully in each battle. Each skill, spell, and item can make the difference between life and death.
- **Roguelite Mechanics**: The world changes after every defeat or restart, offering fresh experiences and requiring constant adaptation.
- **Multiple Playstyles**: Choose your path with a wide range of rewards—focus on offense, defense, magic, or resource management.

## Installation
1. ติดตั้ง [JavaFX SDK](https://openjfx.io/) ให้ตรงกับเวอร์ชัน JDK
2. ดาวน์โหลดหรือโคลนโปรเจกต์นี้จาก GitHub
3. คอมไพล์ด้วยคำสั่ง:
   ```bash
   javac --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -d out [ไฟล์ .java ที่เกี่ยวข้อง]
4. รันเกมด้วยคำสั่ง:
   java --module-path "path/to/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -cp out application.MainApp

## Contributing
สามารถ Fork โปรเจกต์หรือเปิด Pull Request เพื่อมีส่วนร่วมในการพัฒนาได้

# Demo Logic for Game

## Current Features
- Character progression system
- Turn-based combat
- Themed monsters and bosses
- Status effects system
- Skill system with cooldowns
- Victory/Defeat conditions

## Planned Features
- [ ] Item system implementation
- [ ] More monster themes
- [ ] Advanced AI behaviors
- [ ] Save/Load system
- [ ] Character customization

## Requirements
- Java 17 or higher
- JavaFX SDK 23.0.2
- Windows OS

## How to Run
```bash

