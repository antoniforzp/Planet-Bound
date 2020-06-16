package app.controllers;

import app.App;
import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import game.singletons.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Fight extends Controller {

    public Text state;
    public TextArea textArea;
    public ImageView background;
    public ImageView drone;
    public ImageView alien;

    public Fight() {
        Data.getInstance().fightLogStringProperty().addListener((oldVal, newVal) -> {
            setMessage();
        });
    }

    @FXML
    void initialize() {

        background.setImage(new Image(getClass().getResourceAsStream("sprites/upgradeBG.png")));
        drone.setImage(new Image(getClass().getResourceAsStream("sprites/drone.png")));
        alien.setImage(new Image(getClass().getResourceAsStream("sprites/alien.png")));

        setMessage();
        updateState();
    }

    private void updateState() {
        state.setText(Game.getInstance().getState().getClass().getSimpleName());
    }

    private void setMessage() {
        textArea.setText(Data.getInstance().getFightLog());
    }

    @FXML
    public void goBack() throws IOException {
        if (Game.getInstance().getState().getClass() == game.states.WaitInSpace.class) {
            App.setRoot("waitInSpaceView");
        } else {
            ExploreSurface root = (ExploreSurface) ControllerFactory.getController(ExploreSurface.class);
            root.fightView.setVisible(false);
        }
    }
}
