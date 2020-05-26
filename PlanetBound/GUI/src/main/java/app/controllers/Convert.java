package app.controllers;

import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;

import java.util.ArrayList;
import java.util.HashMap;

public class Convert extends Controller {

    public Text state;
    public Text alert;

    public ComboBox<String> from;
    public ComboBox<String> to;
    private final ArrayList<String> resources;

    public Convert() {
        ControllerFactory.addController(this);
        Game.getInstance().addObserver(this);

        updates = new HashMap<>();
        resources = new ArrayList<>();

        updates.put("state", () -> {
            updateState();
            return null;
        });
        initRectangles();
    }

    @FXML
    public void initialize() {
        updateState();
        from.getItems().addAll(resources);
        to.getItems().addAll(resources);
        alert.setText("");
    }

    private void initRectangles() {
        String text = "";

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    text = "Black";
                    break;
                case 1:
                    text = "Blue";
                    break;
                case 2:
                    text = "Green";
                    break;
                case 3:
                    text = "Red";
                    break;
            }
            resources.add(text);
        }
    }

    private void updateState() {
        state.setText(String.valueOf(Game.getState()));
    }

    @FXML
    public void shieldCell() {
        alert.setText("");
        if (!Game.convert(1)) {
            alert.setText("You are not able to do that!");
        }
    }

    @FXML
    public void ammunition() {
        alert.setText("");
        if (!Game.convert(2)) {
            alert.setText("You are not able to do that!");
        }
    }

    @FXML
    public void fuel() {
        alert.setText("");
        if (!Game.convert(3)) {
            alert.setText("You are not able to do that!");
        }
    }

    @FXML
    public void refill() {
        IResource[] resource = new IResource[]{new BlackResource(), new BlueResource(), new RedResource(), new GreenResource()};
        LogicConfig.getInstance().getShip().getCargo().loadResources(resource);
    }

    @FXML
    public void goBack() {
        if (Game.finish()) {
            WaitInSpace wait = (WaitInSpace) ControllerFactory.getController(WaitInSpace.class);
            wait.convertView.setVisible(false);
        }else {
            alert.setText("You have no fuel left!");
        }
    }
}
