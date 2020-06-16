package app.controllers;

import app.App;
import app.Controller;
import app.ControllerFactory;
import config.Loader;
import dice.Dice6;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import game.Game;
import game.states.GameOver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import resources.types.Artefact;
import ship.CrewMembers;
import ship.cargo.Cargo;
import ship.weapons.BasicWeaponSystem;
import space.SpaceSector;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;
import space.planet.planets.BluePlanet;
import space.planet.planets.GreenPlanet;
import space.planet.planets.RedPlanet;
import space.spaceObject.RouteOut;
import space.spaceObject.Wormhole;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.List;

import static ship.CrewMembers.*;

public class WaitInSpace extends Controller {

    private Thread eventProcess = new Thread();

    //sptites
    public ImageView spaceBackground;
    public ImageView cockpitLeft;
    public ImageView cockpitTop;
    public ImageView cockpitBottom;

    public Pane convertView;
    public Pane upgradeView;


    public Label state;
    public Text alert;

    //region ShipInfo
    //ship type
    public Label type;

    //crew members
    public Label captain;
    public Label navigator;
    public Label landingParty;
    public Label shield;
    public Label weapon;
    public Label cargo;

    public Rectangle captainRec;
    public Rectangle navigatorRec;
    public Rectangle landingPartyRec;
    public Rectangle shieldRec;
    public Rectangle weaponRec;
    public Rectangle cargoRec;

    //fuel
    public Label fuel;
    public Label maxFuel;

    //shields
    public Label shields;
    public Label maxShields;

    //ammunition
    public Label ammunition;
    public Label maxAmmunition;
    public Label weaponType;

    //mining drone
    public Label droneShields;
    public Label maxDroneShields;

    //cargo
    public Label level;

    public Label blacks;
    public Label maxBlacks;

    public Label blues;
    public Label maxBlues;

    public Label greens;
    public Label maxGreens;

    public Label reds;
    public Label maxReds;

    public Label artifacts;
    public Label maxArtifacts;
    //endregion

    //region WaitInSpace
    public Text position;
    public Text spaceSector;
    public Text spaceStructure;

    public Polygon ship0;
    public Polygon ship1;
    public Polygon ship2;
    public Polygon ship3;

    public Line wayOut;
    public Line wormhole;

    public Text wayOutText;
    public Text wormholeText;

    public ImageView planetImg;
    public ImageView stationImg;

    public Text planetText;
    public Text stationText;
    //endregion

    public WaitInSpace() {

        Data.getInstance().planetVisitedProperty().addListener((oldVal, newVal) -> System.out.println("planet visited: " + newVal));

        //add controller to list of controllers
        ControllerFactory.addController(this);

        //Bind ship info
        Data.getInstance().getShip().fuelProperty().addListener((oldVal, newVal) -> setFuel());
        Data.getInstance().getShip().maxFuelProperty().addListener((oldVal, newVal) -> setFuel());
        Data.getInstance().getShip().crewMembersListProperty().addListener((oldVal, newVal) -> setCrewMembers());

        //Bind cargo info
        Data.getInstance().getShip().getCargo().artefactArrayProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().blackResourceArrayProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().redResourceArrayProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().blueResourceArrayProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().greenResourceArrayProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().levelIntegerProperty().addListener((oldVal, newVal) -> updateCargo());
        Data.getInstance().getShip().getCargo().maxLevelIntegerProperty().addListener((oldVal, newVal) -> updateCargo());

        //Bind weapon info
        Data.getInstance().getShip().getWeapon().ammunitionIntegerProperty().addListener((oldVal, newVal) -> setAmmunition());
        Data.getInstance().getShip().getWeapon().capacityIntegerProperty().addListener((oldVal, newVal) -> setAmmunition());

        //Bind shield info
        Data.getInstance().getShip().getShield().capacityIntegerProperty().addListener((oldVal, newVal) -> setShield());
        Data.getInstance().getShip().getShield().cellsIntegerProperty().addListener((oldVal, newVal) -> setShield());

        //Bind drone info
        Data.getInstance().getShip().getDrone().shieldsIntegerProperty().addListener((oldVal, newVal) -> setMiningDrone());
        Data.getInstance().getShip().getDrone().shieldsCapacityIntegerProperty().addListener((oldVal, newVal) -> setMiningDrone());

        //Bind ship position
        Data.getInstance().positionProperty().addListener((oldVal, newVal) -> updatePosition());

        //Bind space sector
        Data.getInstance().spaceSectorObjectProperty().addListener((oldVal, newVal) -> updateSpaceSector());

        //alert
        Data.getInstance().alertProperty().addListener((oldVal, newVal) -> alert.setText(String.valueOf(newVal)));
    }

    @FXML
    void initialize() {

        stationImg.setImage(new Image(getClass().getResourceAsStream("sprites/spaceStation.png")));
        spaceBackground.setImage(new Image(getClass().getResourceAsStream("sprites/space.png")));
        cockpitLeft.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit.png")));
        cockpitTop.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit3.png")));
        cockpitBottom.setImage(new Image(getClass().getResourceAsStream("sprites/cockpit2.png")));

        alert.setText("");
        convertView.setVisible(false);
        upgradeView.setVisible(false);

        updateState();
        updatePosition();
        updateSpaceSector();
        initShipInfo();
    }

    @FXML
    void travel() {
        alert.setText("");
        if (!eventProcess.isAlive()) {
            Game.getInstance().travel();
            if (Data.getInstance().getPosition() == 1) {
                Data.getInstance().setEventId(Dice6.roll());
                eventProcess = new Thread(() -> {
                    try {
                        Thread.sleep(75);
                        App.setRoot("eventView");
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });
                eventProcess.start();
            }
        }
    }

    @FXML
    void goConvert() {
        Game.getInstance().startConvert();

        if (Data.getInstance().isCanStartConvert()) {
            convertView.setVisible(true);
        }
    }

    @FXML
    void goUpgrade() {
        Game.getInstance().startUpgrade();

        if (Data.getInstance().isCanStartUpgrade()) {
            upgradeView.setVisible(true);
        }
    }

    @FXML
    void goSurface() throws IOException {
        Game.getInstance().dropOnSurface();

        System.out.println(Data.getInstance().isCanDropOnSurface());
        alert.setText("");
        if (Data.getInstance().isCanDropOnSurface()) {
            App.setRoot("exploreView");
        }
    }

    //TESTING
    @FXML
    void looseMember() {
        try {
            Data.getInstance().getShip().looseCrewMember();

            //TESTING
        } catch (CaptainDeletedException e) {
            try {
                Game.getInstance().setState(new GameOver());
            } catch (ConcurrentModificationException ignore) {
            }
        }
    }

    //TESTING
    @FXML
    void looseFuel() throws OutOfFuelException {
        Data.getInstance().getShip().consumeFuel(1);
    }

    //TESTING
    @FXML
    void addArtefact() {
        Artefact[] art = new Artefact[]{
                new Artefact()
        };
        Data.getInstance().getShip().getCargo().loadResources(art);
    }

    @FXML
    void goMenu() {
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save the game?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Loader.saveGame();
                App.setRoot("menuView");
            } else if (alert.getResult() == ButtonType.NO) {
                App.setRoot("menuView");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initShipInfo() {
        updateState();
        updatePosition();
        updateSpaceSector();
        setType();
        setCrewMembers();
        setFuel();
        setShield();
        setAmmunition();
        setMiningDrone();
        updateCargo();
    }

    private void updateState() {
        state.setText(Game.getInstance().getState().toString());
    }

    private void updatePosition() {
        position.setText(String.valueOf(Data.getInstance().getPosition()));

        ship0.setVisible(false);
        ship1.setVisible(false);
        ship2.setVisible(false);
        ship3.setVisible(false);

        switch (Data.getInstance().getPosition()) {
            case 0:
                ship0.setVisible(true);
                break;
            case 1:
                ship1.setVisible(true);
                break;
            case 2:
                ship2.setVisible(true);
                break;
            case 3:
                ship3.setVisible(true);
                break;
        }
    }

    private void updateSpaceSector() {
        SpaceSector sector = Data.getInstance().getSpaceSector();
        spaceSector.setText(sector.toString());
        spaceStructure.setText(sector.getStructure());

        wormhole.setVisible(false);
        wormholeText.setVisible(false);

        wayOut.setVisible(false);
        wayOutText.setVisible(false);

        if (sector.getObjects()[3].getClass() == Wormhole.class) {
            wormhole.setVisible(true);
            wormholeText.setVisible(true);
        } else if (sector.getObjects()[3].getClass() == RouteOut.class) {
            wayOut.setVisible(true);
            wayOutText.setVisible(true);
        }

        stationImg.setVisible(false);
        stationText.setVisible(false);

        Planet planet = (Planet) sector.getObjects()[2];
        if (planet.withSpaceStation()) {
            stationImg.setVisible(true);
            stationText.setVisible(true);
        }

        planetText.setText(planet.getClass().getSimpleName());
        if (planet.getClass() == BluePlanet.class) {
            planetImg.setImage(new Image(getClass().getResourceAsStream("sprites/planets/blue.png")));
        } else if (planet.getClass() == BlackPlanet.class) {
            planetImg.setImage(new Image(getClass().getResourceAsStream("sprites/planets/black.png")));
        } else if (planet.getClass() == GreenPlanet.class) {
            planetImg.setImage(new Image(getClass().getResourceAsStream("sprites/planets/green.png")));
        } else if (planet.getClass() == RedPlanet.class) {
            planetImg.setImage(new Image(getClass().getResourceAsStream("sprites/planets/red.png")));
        }
    }

    private void setType() {
        type.setText(Data.getInstance().getShip().getClass().getSimpleName());
    }

    private void setCrewMembers() {
        List<CrewMembers> crew = Data.getInstance().getShip().getCrew();

        captainRec.setVisible(false);
        navigatorRec.setVisible(false);
        landingPartyRec.setVisible(false);
        shieldRec.setVisible(false);
        weaponRec.setVisible(false);
        cargoRec.setVisible(false);

        captain.setText("");
        navigator.setText("");
        landingParty.setText("");
        shield.setText("");
        weapon.setText("");
        cargo.setText("");

        if (crew.contains(Captain)) {
            captain.setText("Captain");
            captainRec.setVisible(true);
        }
        if (crew.contains(NavigationOfficer)) {
            navigator.setText("Navigation officer");
            navigatorRec.setVisible(true);
        }
        if (crew.contains(LandingPartyOfficer)) {
            landingParty.setText("Landing party officer");
            landingPartyRec.setVisible(true);
        }
        if (crew.contains(ShieldOfficer)) {
            shield.setText("Shield officer");
            shieldRec.setVisible(true);
        }
        if (crew.contains(WeaponOfficer)) {
            weapon.setText("Weapon officer");
            weaponRec.setVisible(true);
        }
        if (crew.contains(CargoOfficer)) {
            cargo.setText("Cargo officer");
            cargoRec.setVisible(true);
        }
    }

    private void setFuel() {
        fuel.setText(String.valueOf(Data.getInstance().getShip().getFuel()));
        maxFuel.setText(String.valueOf(Data.getInstance().getShip().getMaxFuel()));
    }

    private void setShield() {
        shields.setText(String.valueOf(Data.getInstance().getShip().getShield().getCells()));
        maxShields.setText(String.valueOf(Data.getInstance().getShip().getShield().getCapacity()));
    }

    private void setAmmunition() {
        ammunition.setText(String.valueOf(Data.getInstance().getShip().getWeapon().getAmmunition()));
        maxAmmunition.setText(String.valueOf(Data.getInstance().getShip().getWeapon().getCapacity()));

        if (Data.getInstance().getShip().getWeapon().getClass() == BasicWeaponSystem.class) {
            weaponType.setText("basic W/S");
        } else {
            weaponType.setText("advan W/S");
        }
    }

    private void setMiningDrone() {
        droneShields.setText(String.valueOf(Data.getInstance().getShip().getDrone().getShields()));
        maxDroneShields.setText(String.valueOf(Data.getInstance().getShip().getDrone().getShieldsCapacity()));
    }

    private void updateCargo() {

        level.setText(String.valueOf(Data.getInstance().getShip().getCargo().getLevel()));

        setBlacks();
        setBlues();
        setGreens();
        setReds();
        setArtifacts();
    }

    private void setBlacks() {
        Cargo cargo = Data.getInstance().getShip().getCargo();
        blacks.setText(String.valueOf(cargo.getCapacity(cargo.getBlacks())));
        maxBlacks.setText(String.valueOf(cargo.getBlacks().length));
    }

    private void setBlues() {
        Cargo cargo = Data.getInstance().getShip().getCargo();
        blues.setText(String.valueOf(cargo.getCapacity(cargo.getBlues())));
        maxBlues.setText(String.valueOf(cargo.getBlues().length));
    }

    private void setGreens() {
        Cargo cargo = Data.getInstance().getShip().getCargo();
        greens.setText(String.valueOf(cargo.getCapacity(cargo.getGreens())));
        maxGreens.setText(String.valueOf(cargo.getGreens().length));
    }

    private void setReds() {
        Cargo cargo = Data.getInstance().getShip().getCargo();
        reds.setText(String.valueOf(cargo.getCapacity(cargo.getReds())));
        maxReds.setText(String.valueOf(cargo.getReds().length));
    }

    private void setArtifacts() {
        Cargo cargo = Data.getInstance().getShip().getCargo();
        artifacts.setText(String.valueOf(cargo.getCapacity(cargo.getArtifacts())));
        maxArtifacts.setText(String.valueOf(cargo.getArtifacts().length));
    }
}
