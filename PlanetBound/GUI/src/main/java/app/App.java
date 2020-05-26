package app;

import game.Game;
import game.states.ChooseShip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX app.App
 */
public class App extends Application {

    private static Scene scene;

    private void initGame() {
        Game.setState(ChooseShip.getInstance());
    }

    @Override
    public void start(Stage stage) throws IOException {
        initGame();

        scene = new Scene(loadFXML("chooseShipView"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}