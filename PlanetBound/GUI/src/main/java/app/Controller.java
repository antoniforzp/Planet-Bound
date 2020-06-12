package app;

import game.singletons.Data;

import java.io.IOException;
import java.io.Serializable;

public abstract class Controller implements Serializable {

    public Controller() {
        Data.getInstance().currentStateProperty().addListener((oldVal, newVal) -> {

            if (newVal.getClass().getSimpleName().equals("GameOver")) {
                try {
                    App.setRoot("gameOverView");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (newVal.getClass().getSimpleName().equals("Win")) {
                try {
                    App.setRoot("winView");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
