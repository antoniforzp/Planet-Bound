package app.controllers;

import app.App;
import app.Controller;
import config.Loader;
import game.Game;
import game.states.ChooseShip;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu extends Controller {

    public ImageView background;
    public ImageView poster;
    public ImageView cockpit;

    @FXML
    void initialize() {
        background.setImage(new Image(getClass().getResourceAsStream("sprites/space.png")));
        poster.setImage(new Image(getClass().getResourceAsStream("sprites/logoPlanetBound.jpg")));
        cockpit.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit.png")));
    }

    @FXML
    void startGame() throws IOException {
        Game.getInstance().setState(new ChooseShip());
        App.setRoot("chooseShipView");
    }

    @FXML
    void loadGame() throws IOException {

        System.out.println(Loader.loadGame());
        App.setRoot("waitInSpaceView");
    }

    @FXML
    void exit() {
        System.exit(0);
    }
}
