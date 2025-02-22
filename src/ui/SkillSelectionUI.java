package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skills.SkillRepository;

public class SkillSelectionUI {
    public void show(Stage stage) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Example: Add buttons for each skill
        Button iceBlastButton = new Button("Ice Blast");
        iceBlastButton.setOnAction(e -> {
            // Logic to use Ice Blast skill
            SkillRepository.getSkill("Ice Blast").use(player, currentEnemy);
        });

        vbox.getChildren().add(iceBlastButton);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}