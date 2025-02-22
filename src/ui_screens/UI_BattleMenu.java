package ui_screens;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI_BattleMenu {
    private Stage stage;

    public UI_BattleMenu(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

        Button attackButton = new Button("Attack");
        attackButton.setOnAction(e -> attack());
        layout.getChildren().add(attackButton);

        Button skillButton = new Button("Skill");
        skillButton.setOnAction(e -> useSkill());
        layout.getChildren().add(skillButton);

        Button itemButton = new Button("Item");
        itemButton.setOnAction(e -> useItem());
        layout.getChildren().add(itemButton);

        Button runButton = new Button("Run");
        runButton.setOnAction(e -> run());
        layout.getChildren().add(runButton);

        Scene scene = new Scene(layout, 1280, 720);
        stage.setScene(scene);
        stage.setTitle("Battle Menu");
        stage.show();
    }

    private void attack() {
        // Logic to attack
    }

    private void useSkill() {
        // Logic to use skill
    }

    private void useItem() {
        // Logic to use item
    }

    private void run() {
        // Logic to run
    }
}