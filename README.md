# Song of Twelve Feathers

**Version 0.0.4a (Current)**

## Version History

### v0.0.4a
**New Features & Improvements:**
- **Effect System Overhaul**:
  - Complete refactor of status effect implementation
  - Added effect duration tracking system
  - Improved effect application and removal logic
  - Added visual effect duration indicators
  - Enhanced effect stacking management

- **Combat System Updates**:
  - Improved skill effect integration
  - Enhanced effect processing order
  - Better turn-based effect handling
  - Refined damage calculation with effects

- **UI Improvements**:
  - Added status effect display in battle UI
  - Enhanced HP/MP ratio display (current/max)
  - Improved battle status visibility
  - Better effect duration feedback

**Technical Updates:**
- Refactored BaseDotEffect system
- Improved effect manager architecture
- Enhanced effect inheritance structure
- Added proper effect cleanup
- Optimized memory management for effects

**Bug Fixes:**
- Fixed effect duration not decreasing properly
- Fixed UI not updating after effect changes
- Fixed effect stacking issues
- Fixed effect removal on death/battle end
- Fixed effect persistence between battles
- Resolved memory leaks from lingering effects

### v0.0.4
**New Features & Improvements:**
- **Enhanced Combat System**:
  - Added status effect display in battle UI
  - Improved skill cooldown visualization
  - Streamlined UI for better combat flow
  - Refined turn-based combat mechanics

- **Enhanced Status Effect System**:
  - Added visual indicators for active effects
  - Improved effect duration tracking
  - Added effect stack management
  - Enhanced effect application and removal logic

- **UI/UX Improvements**:
  - Added HP/MP ratio display (current/max)
  - Improved skill button feedback
  - Enhanced battle status visibility
  - Better game completion screen
  - Improved game summary presentation
  - Cleaner battle interface layout

- **Battle Flow Improvements**:
  - Better effect processing order
  - Enhanced turn transition logic
  - Improved victory/defeat flow
  - Added proper game summary before main menu return

**Bug Fixes:**
- Fixed status effects not showing in battle UI
- Fixed cooldown system not working properly
- Fixed multiple back buttons in game summary
- Fixed effect duration not decreasing correctly
- Fixed UI elements not updating after effects
- Fixed battle state inconsistencies
- Fixed skill button state management
- Fixed HP/MP display format

### v0.0.3 (Latest)
**New Features & Improvements:**
- **Enhanced Level-Up System**:
  - Added manual stat point allocation
  - Separate automatic and manual stat increases
  - Improved stat growth visualization
  - Real-time stat update display

- **Game Summary System**:
  - Added end-game statistics screen
  - Tracks monsters and bosses defeated
  - Displays total experience gained
  - Shows damage dealt and taken
  - Counts skills used throughout playthrough
  - Final character status display

- **Enhanced Battle System**:
  - Improved boss experience rewards
  - Added battle statistics tracking
  - Fixed boss fight experience calculations

**Bug Fixes:**
- Fixed boss experience point rewards
- Fixed monster duplicate encounters
- Improved theme progression system
- Enhanced battle completion logic

### v0.0.2b
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
1. Install [JavaFX SDK 23.0.2](https://openjfx.io/)
2. Download or clone this project from GitHub
3. Extract JavaFX SDK and copy the `javafx-sdk-23.0.2` folder to the project directory:
   ```
   javafx-project/
   ├── src/
   ├── bin/
   ├── javafx-sdk-23.0.2/    <- Copy JavaFX SDK here
   │   └── lib/
   │       ├── javafx.base.jar
   │       ├── javafx.controls.jar
   │       ├── javafx.fxml.jar
   │       └── ...
   ├── compile_and_run.bat
   └── README.md
   ```
4. Double-click `compile_and_run.bat` to compile and run the game

### Alternative Manual Run
If the batch file doesn't work, you can run these commands manually in the project directory:
```bash
# Compile
javac --module-path "javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -d bin -sourcepath src src/application/MainApp.java

# Run
java --module-path "javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp bin application.MainApp
```

### Troubleshooting
- Make sure Java 17 or higher is installed and set in PATH
- Verify JavaFX SDK 23.0.2 is copied directly into the project folder
- All commands should be run from the `javafx-project` directory
- If using different JavaFX version, update the folder name in compile_and_run.bat

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
# Compile
javac --module-path "javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -d bin -sourcepath src src/application/MainApp.java

# Run
java --module-path "javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp bin application.MainApp
