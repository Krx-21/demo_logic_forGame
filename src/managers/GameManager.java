package managers;

public class GameManager {
    private UIManager uiManager;

    public GameManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public void startGame() {
        uiManager.showTitleScreen();
    }

    public void onBattleEnd() {
        uiManager.showBattleResult();
    }

    public void onReturnToTown() {
        uiManager.showTownScreen();
    }
}