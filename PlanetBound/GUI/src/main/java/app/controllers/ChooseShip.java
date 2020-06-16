package app.controllers;

import app.App;
import config.Loader;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;

public class ChooseShip {

    public Text state;
    public ImageView background;
    public ImageView cockpitLeft;
    public ImageView cockpitRight;

    @FXML
    void initialize() {
        background.setImage(new Image(getClass().getResourceAsStream("sprites/convertBG.png")));
        cockpitLeft.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit.png")));
        cockpitRight.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit.png")));
    }

    @FXML
    public void selectMilitaryShip() throws IOException {
        Game.getInstance().chooseShip(2);
        App.setRoot("waitInSpaceView");
    }

    @FXML
    public void selectMiningShip() throws IOException {
        Game.getInstance().chooseShip(1);
        App.setRoot("waitInSpaceView");
    }

    @FXML
    public void loadGame() {
        Loader.loadGame();
    }
}
