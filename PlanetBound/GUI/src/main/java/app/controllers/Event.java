package app.controllers;

import app.App;
import app.Controller;
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
import java.util.Map;

public class Event extends Controller {

    public ImageView background;
    public ImageView prompt;
    public ImageView alertIcon;
    public Text state;
    public TextArea messagePrompt;

    public Event() {
        Data.getInstance().eventIdIntegerProperty().addListener((oldVal, newVal) -> {
            getMessage();
        });
    }

    private void updateState() {
        state.setText(String.valueOf(Game.getInstance().getState()));
    }

    private void getMessage() {
        Map<Integer, String> messages = Data.getInstance().getMessages();
        int eventId = Data.getInstance().getEventId();
        messagePrompt.setText(messages.getOrDefault(eventId, "something went wrong!"));
    }

    @FXML
    void initialize() {
        try {
            background.setImage(new Image(new FileInputStream("sprites/upgradeBG.png")));
            prompt.setImage(new Image(new FileInputStream("sprites/prompt.png")));
            alertIcon.setImage(new Image(new FileInputStream("sprites/alert.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        updateState();
        getMessage();
    }

    @FXML
    public void goBack() throws IOException {
        Game.getInstance().processEvent(Data.getInstance().getEventId());
        App.setRoot("waitInSpaceView");
    }
}
