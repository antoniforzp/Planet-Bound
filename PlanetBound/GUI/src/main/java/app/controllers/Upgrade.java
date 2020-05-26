package app.controllers;

import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;

import java.util.HashMap;

public class Upgrade extends Controller {

    public Text state;
    public Text alert;

    public Upgrade() {
        this.updates = new HashMap<>();
        updates.put("state", () -> {
            updateState();
            return null;
        });
    }

    private void updateState() {
        state.setText(String.valueOf(Game.getState()));
    }

    @FXML
    void initialize() {
        alert.setText("");
        updateState();
    }

    @FXML
    public void rechargeDrone() {
        alert.setText("");
        if (!Game.upgrade(1)) {
            alert.setText("Your drone is charged or not enough resources.");
        }
    }

    @FXML
    public void upgradeCargoHold() {
        alert.setText("");
        if (!Game.upgrade(2)) {
            alert.setText("You reached max level or not enough resources.");
        }
    }

    @FXML
    public void hireMember() {
        alert.setText("");
        if (!Game.upgrade(3)) {
            alert.setText("You have full crew or not enough resources.");
        }
    }

    @FXML
    public void upgradeWeaponSystem() {
        alert.setText("");
        if (!Game.upgrade(4)) {
            alert.setText("You cannot upgrade your ship or not enough resources.");
        }
    }

    @FXML
    public void buyNewMiningDrone() {
        alert.setText("");
        if (!Game.upgrade(5)) {
            alert.setText("Not enough resources.");
        }
    }

    @FXML
    public void refill(){
        IResource[] resource = new IResource[]{new BlackResource(), new BlueResource(), new RedResource(), new GreenResource()};
        LogicConfig.getInstance().getShip().getCargo().loadResources(resource);
    }

    @FXML
    public void goBack() {
        WaitInSpace wait = (WaitInSpace) ControllerFactory.getController(WaitInSpace.class);
        wait.upgradeView.setVisible(false);
        Game.finish();
    }
}
