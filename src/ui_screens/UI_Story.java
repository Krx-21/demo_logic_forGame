package ui_screens;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.MainMenu;

public class UI_Story extends VBox {

    private final Scene scene;

    public UI_Story(Stage stage) {
        // ปุ่ม Back
        Button backButton = new Button("Back to Main Menu");
        backButton.setStyle("-fx-font-size: 24px; -fx-pref-width: 250px; -fx-pref-height: 60px; -fx-text-fill: #FFFFFF; -fx-background-color: #001f3f;");
        backButton.setOnAction(e -> backToMainMenu(stage));

        VBox vbox = new VBox(backButton);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(0, 0, 50, 0)); // ให้ปุ่มอยู่ห่างจากขอบล่าง

        StackPane stackPane = new StackPane(vbox);
        scene = new Scene(stackPane, 1280, 720);
    }

    public Scene getCustomScene() {
        return scene;
    }

    private void backToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu(stage);
        stage.setScene(mainMenu.getCustomScene());
    }
}
