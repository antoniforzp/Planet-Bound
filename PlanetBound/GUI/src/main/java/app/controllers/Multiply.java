package app.controllers;

import app.App;
import app.Controller;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;
import resources.IResource;
import resources.types.*;
import walker.miningDrone.MiningDrone;

import java.io.IOException;
import java.util.HashMap;

public class Multiply extends Controller {


    public Text state;
    public Text amount;
    public Text resType;
    public Text killedAliens;
    public Text lostShields;
    public Rectangle rec;

    public Multiply() {
        Game.getInstance().addObserver(this);
        LogicConfig.getInstance().addObserver(this);
        LogicConfig.getInstance().getShip().getDrone().addObserver(this);

        this.updates = new HashMap<>();

        updates.put("state", () -> {
            updateState();
            return null;
        });
        updates.put("currResource", () -> {
            updateResType();
            updateRectangle();
            return null;
        });
        updates.put("amount", () -> {
            updateAmount();
            return null;
        });
        updates.put("droneShields", () -> {
            updateShields();
            return null;
        });
        updates.put("aliensKilled", ()->{
            updateAliensKilled();
            return null;
        });
    }

    private void updateState() {
        state.setText(Game.getState().getClass().getSimpleName());
    }

    private void updateResType() {
        resType.setText(LogicConfig.getInstance().getResource().getClass().getSimpleName());
    }

    private void updateAmount() {
        amount.setText(String.valueOf(LogicConfig.getInstance().getAmount()));
    }

    private void updateRectangle() {
        IResource resource = LogicConfig.getInstance().getResource();

        if (resource.getClass() == BlueResource.class) {
            rec.setFill(Color.rgb(141, 184, 252));
        } else if (resource.getClass() == BlackResource.class) {
            rec.setFill(Color.rgb(55, 56, 59));
        } else if (resource.getClass() == GreenResource.class) {
            rec.setFill(Color.rgb(107, 181, 47));
        } else if (resource.getClass() == RedResource.class) {
            rec.setFill(Color.rgb(191, 63, 46));
        } else if(resource.getClass() == Artefact.class){
            rec.setFill(Color.rgb(177, 33, 194));
        }
    }

    private void updateShields() {
        MiningDrone drone = LogicConfig.getInstance().getShip().getDrone();
        lostShields.setText(String.valueOf((drone.getShieldsCapacity() - drone.getShields())));
    }

    private void updateAliensKilled(){
        killedAliens.setText(String.valueOf(LogicConfig.getInstance().getAliensKilled()));
    }

    @FXML
    void initialize() {
        updateState();
        updateResType();
        updateAmount();
        updateRectangle();
        updateShields();
        updateAliensKilled();
    }

    @FXML
    void exit() throws IOException {
        App.setRoot("waitInSpaceView");
    }
}
