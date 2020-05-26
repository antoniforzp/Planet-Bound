package app.controllers;

import app.App;
import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;

import java.io.IOException;
import java.util.HashMap;

public class Fight extends Controller {

    public Text state;
    public TextArea textArea;

    public Fight() {
        Game.getInstance().addObserver(this);
        LogicConfig.getInstance().addObserver(this);
        this.updates = new HashMap<>();

        updates.put("fightLog", () -> {
            setMessage();
            return null;
        });
        updates.put("state", () -> {
            updateState();
            return null;
        });
    }

    @FXML
    void initialize() {
        setMessage();
        updateState();
    }

    private void updateState(){
        state.setText(Game.getState().getClass().getSimpleName());
    }

    private void setMessage() {
        textArea.setText(LogicConfig.getInstance().getFightLog());
    }

    @FXML
    public void goBack() throws IOException {
        if (Game.getState().getClass() == game.states.WaitInSpace.class) {
            App.setRoot("waitInSpaceView");
        } else {
            ExploreSurface root = (ExploreSurface) ControllerFactory.getController(ExploreSurface.class);
            root.fightView.setVisible(false);
        }
    }
}
