package app.controllers;

import app.App;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ChooseShip {

    public Text state;

    @FXML
    void initialize() {
        state.setText(String.valueOf(Game.getState()));
    }

    @FXML
    public void selectMilitaryShip() throws IOException {
        Game.chooseShip(2);
        App.setRoot("waitInSpaceView");
    }

    @FXML
    public void selectMiningShip() throws IOException {
        Game.chooseShip(1);
        App.setRoot("waitInSpaceView");
    }
}
