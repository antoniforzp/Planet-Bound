package app.controllers;

import app.App;
import app.Controller;
import game.Game;
import game.singletons.Data;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import resources.IResource;

import java.io.IOException;

public class GameOver extends Controller {

    public Text artefactsCollected;

    @FXML
    void initialize() {
        IResource[] artefacts = Data.getInstance().getShip().getCargo().getArtifacts();
        artefactsCollected.setText(String.valueOf(Data.getInstance().getShip().getCargo().getCapacity(artefacts)));
    }

    @FXML
    void goExit() {
        try {
            App.setRoot("menuView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
