package app.controllers;

import app.App;
import app.Controller;
import dice.Dice6;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Event extends Controller {

    public Text state;
    public TextArea messagePrompt;

    public Event() {
        LogicConfig.getInstance().addObserver(this);
        updates = new HashMap<>();

        updates.put("state", () -> {
            updateState();
            return null;
        });
        updates.put("eventId", () -> {
            getMessage();
            return null;
        });
    }

    private void updateState() {
        state.setText(String.valueOf(Game.getState()));
    }

    private void getMessage() {
        Map<Integer, String> messages = LogicConfig.getInstance().getMessages();
        int eventId = LogicConfig.getInstance().getEventId();
        messagePrompt.setText(messages.getOrDefault(eventId, "something went wrong!"));
    }

    @FXML
    void initialize() {
        updateState();
        getMessage();
    }

    @FXML
    public void goBack() throws IOException {
        Game.processEvent( LogicConfig.getInstance().getEventId());
        App.setRoot("waitInSpaceView");
    }
}
