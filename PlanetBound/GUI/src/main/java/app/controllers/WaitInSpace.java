package app.controllers;

import app.App;
import app.Controller;
import app.ControllerFactory;
import dice.Dice6;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.singleton.LogicConfig;
import ship.CrewMembers;
import ship.cargo.ICargo;
import ship.weapons.BasicWeaponSystem;
import space.SpaceSector;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;
import space.planet.planets.BluePlanet;
import space.planet.planets.GreenPlanet;
import space.planet.planets.RedPlanet;
import space.spaceObject.RouteOut;
import space.spaceObject.Wormhole;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static ship.CrewMembers.*;

public class WaitInSpace extends Controller {

    private Thread eventProcess = new Thread();

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
    public Label shieldsActive;

    //ammunition
    public Label ammunition;
    public Label maxAmmunition;
    public Label weaponType;
    public Label weaponActive;

    //mining drone
    public Label droneShields;
    public Label maxDroneShields;
    public Label droneActive;

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

    public Circle planetCircle;
    public Circle stationCircle;

    public Text planetText;
    public Text stationText;
    //endregion

    public WaitInSpace() {
        ControllerFactory.addController(this);
        LogicConfig.getInstance().addObserver(this);
        LogicConfig.getInstance().getShip().addObserver(this);
        LogicConfig.getInstance().getShip().getCargo().addObserver(this);
        LogicConfig.getInstance().getShip().getWeapon().addObserver(this);
        LogicConfig.getInstance().getShip().getShield().addObserver(this);

        updates = new HashMap<>();

        updates.put("state", () -> {
            updateState();
            return null;
        });
        updates.put("shipPosition", () -> {
            updatePosition();
            return null;
        });
        updates.put("spaceSector", () -> {
            updateSpaceSector();
            return null;
        });
        updates.put("crew", () -> {
            setCrewMembers();
            return null;
        });
        updates.put("fuel", () -> {
            setFuel();
            return null;
        });
        updates.put("shield", () -> {
            setShield();
            return null;
        });
        updates.put("weapon", () -> {
            setAmmunition();
            return null;
        });
        updates.put("drone", () -> {
            setMiningDrone();
            return null;
        });
        updates.put("cargo", () -> {
            updateCargo();
            return null;
        });
    }

    @FXML
    void initialize() {
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
        if (LogicConfig.getInstance().isRunOutOfFuel()) {
            convertView.setVisible(true);
        } else {
            if (!eventProcess.isAlive()) {
                Game.travel();
                if (LogicConfig.getInstance().getPosition() == 1) {
                    LogicConfig.getInstance().setEventId(Dice6.roll());
                    eventProcess = new Thread(() -> {
                        try {
                            Thread.sleep(100);
                            App.setRoot("eventView");
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    });
                    eventProcess.start();
                }
            }
        }
    }

    @FXML
    void goConvert() {
        alert.setText("");
        if (Game.startConvert()) {
            convertView.setVisible(true);
        } else {
            alert.setText("You need to have a Cargo Officer!");
        }
    }

    @FXML
    void goUpgrade() {
        alert.setText("");
        if (Game.startUpgrade()) {
            upgradeView.setVisible(true);
        } else {
            alert.setText("You must be on planet with space station!");
        }
    }

    @FXML
    void goSurface() throws IOException {
        alert.setText("");
        if (Game.dropOnSurface()) {
            App.setRoot("exploreView");
        } else {
            alert.setText("Need to have landing party officer and be on planet!");
        }
    }

    @FXML
    void looseMember() throws CaptainDeletedException {
        LogicConfig.getInstance().getShip().looseCrewMember();
    }

    @FXML
    void looseFuel() throws OutOfFuelException {
        LogicConfig.getInstance().getShip().consumeFuel(1);
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
        state.setText(Game.getState().toString());
    }

    private void updatePosition() {
        position.setText(String.valueOf(LogicConfig.getInstance().getPosition()));

        ship0.setVisible(false);
        ship1.setVisible(false);
        ship2.setVisible(false);
        ship3.setVisible(false);

        switch (LogicConfig.getInstance().getPosition()) {
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
        SpaceSector sector = LogicConfig.getInstance().getSpaceSector();
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

        stationCircle.setVisible(false);
        stationText.setVisible(false);

        Planet planet = (Planet) sector.getObjects()[2];
        if (planet.withSpaceStation()) {
            stationCircle.setVisible(true);
            stationText.setVisible(true);
        }

        planetText.setText(planet.getClass().getSimpleName());

        if (planet.getClass() == BluePlanet.class) {
            planetCircle.setFill(Color.rgb(141, 184, 252));
        } else if (planet.getClass() == BlackPlanet.class) {
            planetCircle.setFill(Color.rgb(55, 56, 59));
        } else if (planet.getClass() == GreenPlanet.class) {
            planetCircle.setFill(Color.rgb(107, 181, 47));
        } else if (planet.getClass() == RedPlanet.class) {
            planetCircle.setFill(Color.rgb(191, 63, 46));
        }
    }

    private void setType() {
        type.setText(LogicConfig.getInstance().getShip().getClass().getSimpleName());
    }

    private void setCrewMembers() {
        List<CrewMembers> crew = LogicConfig.getInstance().getShip().getCrew();

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
        fuel.setText(String.valueOf(LogicConfig.getInstance().getShip().getFuel()));
        maxFuel.setText(String.valueOf(LogicConfig.getInstance().getShip().getMaxFuel()));
    }

    private void setShield() {
        shields.setText(String.valueOf(LogicConfig.getInstance().getShip().getShield().getCells()));
        maxShields.setText(String.valueOf(LogicConfig.getInstance().getShip().getShield().getCapacity()));
        shieldsActive.setText(String.valueOf(LogicConfig.getInstance().getShip().getShield().isActive()));
    }

    private void setAmmunition() {
        ammunition.setText(String.valueOf(LogicConfig.getInstance().getShip().getWeapon().getAmmunition()));
        maxAmmunition.setText(String.valueOf(LogicConfig.getInstance().getShip().getWeapon().getCapacity()));
        weaponActive.setText(String.valueOf(LogicConfig.getInstance().getShip().getWeapon().isActive()));

        if (LogicConfig.getInstance().getShip().getWeapon().getClass() == BasicWeaponSystem.class) {
            weaponType.setText("basic W/S");
        } else {
            weaponType.setText("advan W/S");
        }
    }

    private void setMiningDrone() {
        droneShields.setText(String.valueOf(LogicConfig.getInstance().getShip().getDrone().getShields()));
        maxDroneShields.setText(String.valueOf(LogicConfig.getInstance().getShip().getDrone().getShieldsCapacity()));
        droneActive.setText(String.valueOf(LogicConfig.getInstance().getShip().getDrone().isActive()));
    }

    private void updateCargo() {

        level.setText(String.valueOf(LogicConfig.getInstance().getShip().getCargo().getLevel()));

        setBlacks();
        setBlues();
        setGreens();
        setReds();
        setArtifacts();
    }

    private void setBlacks() {
        ICargo cargo = LogicConfig.getInstance().getShip().getCargo();
        blacks.setText(String.valueOf(cargo.getCapacity(cargo.getBlacks())));
        maxBlacks.setText(String.valueOf(cargo.getBlacks().length));
    }

    private void setBlues() {
        ICargo cargo = LogicConfig.getInstance().getShip().getCargo();
        blues.setText(String.valueOf(cargo.getCapacity(cargo.getBlues())));
        maxBlues.setText(String.valueOf(cargo.getBlues().length));
    }

    private void setGreens() {
        ICargo cargo = LogicConfig.getInstance().getShip().getCargo();
        greens.setText(String.valueOf(cargo.getCapacity(cargo.getGreens())));
        maxGreens.setText(String.valueOf(cargo.getGreens().length));
    }

    private void setReds() {
        ICargo cargo = LogicConfig.getInstance().getShip().getCargo();
        reds.setText(String.valueOf(cargo.getCapacity(cargo.getReds())));
        maxReds.setText(String.valueOf(cargo.getReds().length));
    }

    private void setArtifacts() {
        ICargo cargo = LogicConfig.getInstance().getShip().getCargo();
        artifacts.setText(String.valueOf(cargo.getCapacity(cargo.getArtifacts())));
        maxArtifacts.setText(String.valueOf(cargo.getArtifacts().length));
    }
}
