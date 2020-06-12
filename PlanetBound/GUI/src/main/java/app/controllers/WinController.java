package app.controllers;

import app.App;
import app.Controller;
import game.Game;
import javafx.fxml.FXML;

import java.io.IOException;

public class WinController extends Controller {

    @FXML
    void goExit() {
        try {
            App.setRoot("menuView");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
