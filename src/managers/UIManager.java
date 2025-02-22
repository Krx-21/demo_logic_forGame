package managers;

import javafx.stage.Stage;
import ui_screens.*;

public class UIManager {
    private Stage stage;

    public UIManager(Stage stage) {
        this.stage = stage;
    }

    public void showTitleScreen() {
        new UI_TitleScreen(stage);
    }

    public void showDifficultySelection() {
        new UI_DifficultySelection(stage);
    }

    public void showCombatScreen() {
        new UI_CombatScreen(stage);
    }

    public void showBattleMenu() {
        new UI_BattleMenu(stage);
    }

    public void showBattleResult() {
        new UI_BattleResult(stage);
    }

    public void showTownScreen() {
        new UI_Town(stage);
    }

    public void showInventoryScreen() {
        new UI_Inventory(stage);
    }
}