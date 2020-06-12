package app.controllers;

import app.Controller;
import app.ControllerFactory;
import game.Game;
import game.singletons.Data;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Convert extends Controller {

    public Text state;
    public Text alert;
    public ImageView background;

    public ComboBox<String> from;
    public ComboBox<String> to;

    public Convert() {
        ControllerFactory.addController(this);

        Data.getInstance().alertProperty().addListener((oldVal, newVal) -> {
            alert.setText(String.valueOf(newVal));
        });
    }

    @FXML
    public void initialize() {
        try {
            background.setImage(new Image(new FileInputStream("sprites/convertBG.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        updateState();
        alert.setText("");
    }

    private void updateState() {
        state.setText(String.valueOf(Game.getInstance().getState()));
    }

    @FXML
    public void shieldCell() {
        alert.setText("");
        Game.getInstance().convert(1);
    }

    @FXML
    public void ammunition() {
        alert.setText("");
        Game.getInstance().convert(2);
    }

    @FXML
    public void fuel() {
        alert.setText("");
        Game.getInstance().convert(3);
    }

    @FXML
    public void refill() {
        IResource[] resource = new IResource[]{new BlackResource(), new BlueResource(), new RedResource(), new GreenResource()};
        Data.getInstance().getShip().getCargo().loadResources(resource);
    }

    @FXML
    public void goBack() {
        Game.getInstance().finish();

        WaitInSpace wait = (WaitInSpace) ControllerFactory.getController(WaitInSpace.class);
        wait.convertView.setVisible(false);
    }
}
