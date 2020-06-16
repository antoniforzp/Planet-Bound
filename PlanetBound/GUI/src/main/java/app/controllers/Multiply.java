package app.controllers;

import app.App;
import app.Controller;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import game.singletons.Data;
import resources.IResource;
import resources.types.*;
import walker.miningDrone.MiningDrone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Multiply extends Controller {

    public ImageView background;

    public Text state;
    public Text amount;
    public Text resType;
    public Text killedAliens;
    public Text lostShields;
    public Rectangle rec;

    public Multiply() {
        Data.getInstance().resourceObjectProperty().addListener((oldVal, newVal) -> {
            updateResType();
            updateRectangle();
        });
    }

    private void updateState() {
        state.setText(Game.getInstance().getState().getClass().getSimpleName());
    }

    private void updateResType() {
        resType.setText(Data.getInstance().getResource().getClass().getSimpleName());
    }

    private void updateAmount() {
        amount.setText(String.valueOf(Data.getInstance().getAmount()));
    }

    private void updateRectangle() {
        IResource resource = Data.getInstance().getResource();

        if (resource.getClass() == BlueResource.class) {
            rec.setFill(Color.rgb(141, 184, 252));
        } else if (resource.getClass() == BlackResource.class) {
            rec.setFill(Color.rgb(55, 56, 59));
        } else if (resource.getClass() == GreenResource.class) {
            rec.setFill(Color.rgb(107, 181, 47));
        } else if (resource.getClass() == RedResource.class) {
            rec.setFill(Color.rgb(191, 63, 46));
        } else if (resource.getClass() == Artefact.class) {
            rec.setFill(Color.rgb(177, 33, 194));
        }
    }

    private void updateShields() {
        MiningDrone drone = Data.getInstance().getShip().getDrone();
        lostShields.setText(String.valueOf((drone.getShieldsCapacity() - drone.getShields())));
    }

    private void updateAliensKilled() {
        killedAliens.setText(String.valueOf(Data.getInstance().getAliensKilled()));
    }

    @FXML
    void initialize() {
        updateState();
        updateResType();
        updateAmount();
        updateRectangle();
        updateShields();
        updateAliensKilled();

        background.setImage(new Image(getClass().getResourceAsStream("sprites/multiplyBG.png")));
    }

    @FXML
    void exit() throws IOException {
        App.setRoot("waitInSpaceView");
    }
}
