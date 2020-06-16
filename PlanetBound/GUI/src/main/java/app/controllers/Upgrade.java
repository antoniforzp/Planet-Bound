package app.controllers;

import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import game.singletons.Data;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Upgrade extends Controller {

    public Text state;
    public Text alert;
    public ImageView background;

    private void updateState() {
        state.setText(String.valueOf(Game.getInstance().getState()));
    }

    @FXML
    void initialize() {
        background.setImage(new Image(getClass().getResourceAsStream("sprites/upgradeBG.png")));
        alert.setText("");
        updateState();
    }

    @FXML
    public void rechargeDrone() {
        Game.getInstance().upgrade(1);
    }

    @FXML
    public void upgradeCargoHold() {
        Game.getInstance().upgrade(2);
    }

    @FXML
    public void hireMember() {
        Game.getInstance().upgrade(3);
    }

    @FXML
    public void upgradeWeaponSystem() {
        Game.getInstance().upgrade(4);
    }

    @FXML
    public void buyNewMiningDrone() {
        Game.getInstance().upgrade(5);
    }

    @FXML
    public void goBack() {
        WaitInSpace wait = (WaitInSpace) ControllerFactory.getController(WaitInSpace.class);
        wait.upgradeView.setVisible(false);
        Game.getInstance().finish();
    }

    //test button
    @FXML
    public void refill() {
        IResource[] resource = new IResource[]{new BlackResource(), new BlueResource(), new RedResource(), new GreenResource()};
        Data.getInstance().getShip().getCargo().loadResources(resource);
    }
}
