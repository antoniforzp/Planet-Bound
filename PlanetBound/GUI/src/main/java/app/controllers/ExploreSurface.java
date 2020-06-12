package app.controllers;

import app.App;
import app.Controller;
import app.ControllerFactory;
import game.Game;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import game.singletons.Data;
import resources.IResource;
import resources.types.*;
import walker.Coordinate;
import walker.alien.Alien;
import walker.alien.aliens.BlackAlien;
import walker.alien.aliens.BlueAlien;
import walker.alien.aliens.GreenAlien;
import walker.alien.aliens.RedAlien;
import walker.miningDrone.MiningDrone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class ExploreSurface extends Controller {

    public ImageView background;
    public ImageView controlPanel;
    public Pane fightView;

    public Text state;

    public Text alienType;
    public Text alienAttack;
    public Text alienDeath;
    public Rectangle alienRec;

    public Text resourceType;
    public Rectangle resourceRec;

    public Text maxShields;
    public Text shields;

    public GridPane grid;


    public ExploreSurface() {
        ControllerFactory.addController(this);

        //Bind alien met event
        Data.getInstance().currentStateProperty().addListener((oldVal, newVal) -> {

            if (newVal.getClass().getSimpleName().equals("Fight")) {
                fightView.setVisible(true);
                Game.getInstance().fight();
            }
        });

        //Bind coordinate of drone
        Data.getInstance().getShip().getDrone().positionCoordinateProperty().addListener((oldVal, newVal) -> {
            updateGrid(false);
        });

        //Bind coordinate of resource
        Data.getInstance().resCoordinateProperty().addListener((oldVal, newVal) -> {
            updateGrid(false);
        });

        //Bind coordinate of alien
        Data.getInstance().alienPosCoordinateProperty().addListener((oldVal, newVal) -> {
            updateGrid(false);
        });

        //Bind extraction point coordinate
        Data.getInstance().extPointCoordinateProperty().addListener((oldVal, newVal) -> {
            updateExtPointPosition();
        });

        //Bind alien
        Data.getInstance().alienObjectProperty().addListener((oldVal, newVal) -> {
            updateAlien();
        });

        //Bind current resource to collect
        Data.getInstance().resourceObjectProperty().addListener((oldVal, newVal) -> {
            updateResource();
        });

        //Bind drone shields
        Data.getInstance().getShip().getDrone().shieldsCapacityIntegerProperty().addListener((oldVal, newVal) -> {
            updateDrone();
        });
        Data.getInstance().getShip().getDrone().shieldsIntegerProperty().addListener((oldVal, newVal) -> {
            updateDrone();
        });
    }

    private void updateState() {
        state.setText(Game.getInstance().getState().toString());
    }

    private void updateAlien() {
        Alien alien = Data.getInstance().getAlien();
        alienType.setText(alien.getClass().getSimpleName());

        int attackUp = alien.getAttackUp();
        int attackDown = alien.getAttackDown();
        String chance = String.format("%.2f", (double) (attackUp - attackDown) / 6 * 100);
        alienAttack.setText("[" + attackDown + "-" + attackUp + "]" + " " + chance + "%");

        int deathUp = alien.getDeathUp();
        int deathDown = alien.getDeathDown();
        chance = String.format("%.2f", (double) (deathUp - deathDown) / 6 * 100);
        alienDeath.setText("[" + deathDown + "-" + deathUp + "]" + " " + chance + "%");

        if (alien.getClass() == BlueAlien.class) {
            alienRec.setFill(Color.rgb(141, 184, 252));
        } else if (alien.getClass() == BlackAlien.class) {
            alienRec.setFill(Color.rgb(55, 56, 59));
        } else if (alien.getClass() == GreenAlien.class) {
            alienRec.setFill(Color.rgb(107, 181, 47));
        } else if (alien.getClass() == RedAlien.class) {
            alienRec.setFill(Color.rgb(191, 63, 46));
        }
    }

    private void updateResource() {
        IResource resource = Data.getInstance().getResource();
        resourceType.setText(resource.getClass().getSimpleName());

        if (resource.getClass() == BlueResource.class) {
            resourceRec.setFill(Color.rgb(141, 184, 252));
        } else if (resource.getClass() == BlackResource.class) {
            resourceRec.setFill(Color.rgb(55, 56, 59));
        } else if (resource.getClass() == GreenResource.class) {
            resourceRec.setFill(Color.rgb(107, 181, 47));
        } else if (resource.getClass() == RedResource.class) {
            resourceRec.setFill(Color.rgb(191, 63, 46));
        }

    }

    private void updateDrone() {
        MiningDrone drone = Data.getInstance().getShip().getDrone();
        maxShields.setText(String.valueOf(drone.getShieldsCapacity()));
        shields.setText(String.valueOf(drone.getShields()));
    }

    private void initGrid() {

        grid.getChildren().clear();
        updateGrid(true);
        updateResourcePosition();
    }

    private void updateGrid(boolean newColors) {

        if (newColors) {
            grid.getChildren().clear();
            Random random = new Random();
            for (int y = 0; y < grid.getRowCount(); y++) {
                for (int x = 0; x < grid.getColumnCount(); x++) {

                    //117-128
                    int R = random.nextInt((128 - 117) + 1) + 117;
                    //81-72
                    int G = random.nextInt((81 - 72) + 1) + 72;
                    //40-18
                    int B = random.nextInt((40 - 18) + 1) + 18;

                    StackPane pane = new StackPane();

                    pane.setBackground(new Background(new BackgroundFill(Color.rgb(R, G, B), CornerRadii.EMPTY, Insets.EMPTY)));
                    pane.setOpacity(0.65);

                    pane.setPrefWidth(20);
                    pane.setPrefHeight(20);

                    grid.add(pane, x, y);
                }
            }
        }

        for (Node n : grid.getChildren()) {
            Pane p = (Pane) n;
            p.getChildren().clear();
        }

        updateExtPointPosition();
        updateAlienPosition();
        updateDronePosition();

        if (!Data.getInstance().isResourceTaken()) {
            updateResourcePosition();
        }
    }

    private void updateDronePosition() {
        MiningDrone drone = Data.getInstance().getShip().getDrone();
        int x = drone.getPosition().getX();
        int y = drone.getPosition().getY();

        System.out.println(drone.getPosition());

        Rectangle rec = new Rectangle(50, 50);
        rec.setFill(Color.rgb(62, 64, 62));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(rec, new Text("D"));

        grid.add(stack, x, y);
    }

    private void updateAlienPosition() {
        Alien alien = Data.getInstance().getAlien();
        int x = alien.getPosition().getX();
        int y = alien.getPosition().getY();

        System.out.println(alien.getPosition());

        Rectangle rec = new Rectangle(30, 30);
        rec.setFill(Color.rgb(50, 168, 82));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(rec, new Text("A"));

        grid.add(stack, x, y);
    }

    private void updateResourcePosition() {
        Coordinate resCoo = Data.getInstance().getResourceCoordinate();
        IResource resource = Data.getInstance().getResource();

        Rectangle rec = new Rectangle(40, 40);

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

        StackPane stack = new StackPane();
        stack.getChildren().addAll(rec, new Text("R"));

        grid.add(stack, resCoo.getX(), resCoo.getY());

    }

    private void updateExtPointPosition() {
        Coordinate extCoo = Data.getInstance().getExtractionPoint();
        Rectangle rec = new Rectangle(55, 55);

        rec.setFill(Color.rgb(201, 176, 14));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(rec, new Text("E"));

        grid.add(stack, extCoo.getX(), extCoo.getY());
    }

    @FXML
    void initialize() {

        try {
            background.setImage(new Image(new FileInputStream("sprites/planetTexture.png")));
            controlPanel.setImage(new Image(new FileInputStream("sprites/cockpit.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fightView.setVisible(false);
        updateState();
        updateDrone();
        updateAlien();
        updateResource();
        initGrid();
    }

    private void move(int direction) throws IOException {

        Coordinate droneCoo = Data.getInstance().getShip().getDrone().getPosition();
        switch (direction) {
            case 1:
                Game.getInstance().move(droneCoo.getX(), droneCoo.getY() - 1);
                break;
            case 2:
                Game.getInstance().move(droneCoo.getX(), droneCoo.getY() + 1);
                break;
            case 3:
                Game.getInstance().move(droneCoo.getX() - 1, droneCoo.getY());
                break;
            case 4:
                Game.getInstance().move(droneCoo.getX() + 1, droneCoo.getY());
                break;
        }

        if (Data.getInstance().isFinishedExploring()) {
            try {
                Game.getInstance().finish();
                App.setRoot("multiplyView");
            } catch (ConcurrentModificationException ignore) {
            }
        }

        updateGrid(false);
    }

    @FXML
    void moveUp() throws IOException {
        move(1);
    }

    @FXML
    void moveDown() throws IOException {
        move(2);
    }

    @FXML
    void moveLeft() throws IOException {
        move(3);
    }

    @FXML
    void moveRight() throws IOException {
        move(4);
    }
}
