package game.singletons;

import binding.properties.*;
import game.State;
import resources.IResource;
import ship.Ship;
import space.SpaceSector;
import space.planet.Planet;
import walker.Coordinate;
import walker.alien.Alien;
import walker.alien.AlienFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {

    private static Data instance;

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public static void setInstance(Data instance) {
        System.out.println("new instance: " + instance);
        Data.instance = instance;
    }

    private Data() {
        messages = new HashMap<>();
        messages.put(1, "A crew member is injured due to a system malfunction, move the ship crew die to the right one space");
        messages.put(2, "You ship comes across an abandoned ship and you find a random resource. Place all four of the resource cubes in a bag and draw one. Roll the d6 for that resource and add it to your cargo");
        messages.put(3, "A cargo mishap causes you to lose some of your resources. Place the colored cubes of the resources you have in the cargo hold and draw one. Roll a d3 [1-3] to see how much of that resource you lose”");
        messages.put(4, "You accidentally use too much fuel in a test run. Remove [1] fuel cell");
        messages.put(5, "Nothing happens");
        messages.put(6, "You find a ship in distress with a lone crew member. This crew member joins your crew and you move your white crew die to the left one space if you have less than six crew");
    }

    //CURRENT STATE property
    private final ObjectProperty<State> currentStateProperty = new ObjectProperty<>();

    public ObjectProperty<State> currentStateProperty() {
        return currentStateProperty;
    }

    public void setState(State state) {
        currentStateProperty.setValue(state);
    }

    public State getState() {
        return currentStateProperty.getValue();
    }


    // TODO: 12/06/2020 revisiting the planet does not work well - now it is blocked
    //IS PLANET VISITED property
    private final BooleanProperty planetVisited = new BooleanProperty(false);

    public BooleanProperty planetVisitedProperty() {
        return planetVisited;
    }

    public boolean isPlanetVisited() {
        return planetVisited.getValue();
    }

    public void setPlanetVisited(boolean value) {
        planetVisited.setValue(value);
    }


    //SHIP (has own properties )
    private Ship ship;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }


    //FIGHT LOG property
    private final StringProperty fightLog = new StringProperty();

    public StringProperty fightLogStringProperty() {
        return fightLog;
    }

    public String getFightLog() {
        return this.fightLog.getValue();
    }

    public void setFightLog(String fightLog) {
        this.fightLog.setValue(fightLog);
    }


    //ALIEN property
    private final ObjectProperty<Alien> alien = new ObjectProperty<>(AlienFactory.getRandomAlien());

    public ObjectProperty<Alien> alienObjectProperty() {
        return alien;
    }

    public Alien getAlien() {
        return alien.getValue();
    }

    public void setAlien(Alien alien) {
        this.alien.setValue(alien);
        //set alien's new coordinates when new alien is set
        this.alienCoordinate.setValue(alien.getPosition());
    }


    //ALIEN COORDINATES property
    private final CoordinateProperty alienCoordinate = new CoordinateProperty();

    public CoordinateProperty alienPosCoordinateProperty() {
        return alienCoordinate;
    }

    public Coordinate getAlienCoordinate() {
        return alienCoordinate.getValue();
    }

    public void setAlienCoordinate(Coordinate alienCoordinate) {
        this.alienCoordinate.setValue(alienCoordinate);
    }


    //EVENT MESSAGES
    private final Map<Integer, String> messages;

    public Map<Integer, String> getMessages() {
        return messages;
    }


    //EVENT ID property
    private final IntegerProperty eventId = new IntegerProperty();

    public IntegerProperty eventIdIntegerProperty() {
        return eventId;
    }

    public int getEventId() {
        return eventId.getValue();
    }

    public void setEventId(int eventId) {
        this.eventId.setValue(eventId);
    }


    //SPACE POSITION property
    private final IntegerProperty position = new IntegerProperty(0);

    public IntegerProperty positionProperty() {
        return position;
    }

    public int getPosition() {
        return position.getValue();
    }

    public void setPosition(int position) {
        this.position.setValue(position);
    }


    //RESOURCE property
    private final ObjectProperty<IResource> resource = new ObjectProperty<>(null);

    public ObjectProperty<IResource> resourceObjectProperty() {
        return resource;
    }

    public IResource getResource() {
        return resource.getValue();
    }

    public void setResource(IResource resource) {
        this.resource.setValue(resource);
    }


    //EXTRACTION POINT COORDINATE property
    private final CoordinateProperty extractionPointCoordinate = new CoordinateProperty();

    public CoordinateProperty extPointCoordinateProperty() {
        return extractionPointCoordinate;
    }

    public Coordinate getExtractionPoint() {
        return extractionPointCoordinate.getValue();
    }

    public void setExtractionPoint(Coordinate extractionPointCoordinate) {
        this.extractionPointCoordinate.setValue(extractionPointCoordinate);
    }


    //RESOURCE COORDINATE property
    private final CoordinateProperty resourceCoordinate = new CoordinateProperty();

    public CoordinateProperty resCoordinateProperty() {
        return resourceCoordinate;
    }

    public Coordinate getResourceCoordinate() {
        return resourceCoordinate.getValue();
    }

    public void setResourceCoordinate(Coordinate resourceCoordinate) {
        this.resourceCoordinate.setValue(resourceCoordinate);
    }


    //SPACE SECTOR property
    private final ObjectProperty<SpaceSector> spaceSector = new ObjectProperty<>(new SpaceSector());

    public ObjectProperty<SpaceSector> spaceSectorObjectProperty() {
        return spaceSector;
    }

    public SpaceSector getSpaceSector() {
        return spaceSector.getValue();
    }

    public void setSpaceSector(SpaceSector spaceSector) {
        this.spaceSector.setValue(spaceSector);
        this.planet = (Planet) spaceSector.getObjects()[2];
        this.planetVisited.setValue(false);
    }


    //PLANET
    private Planet planet = (Planet) spaceSector.getValue().getObjects()[2];

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }


    //RESOURCE TAKEN
    private boolean resourceTaken = false;

    public boolean isResourceTaken() {
        return resourceTaken;
    }

    public void setResourceTaken(boolean resourceTaken) {
        this.resourceTaken = resourceTaken;
    }


    //ALIEN MET property
    private transient BooleanProperty alienMet = new BooleanProperty(false);

    public boolean isAlienMet() {
        return alienMet.getValue();
    }

    public void setAlienMet(boolean alienMet) {
        this.alienMet.setValue(alienMet);
    }

    public BooleanProperty alienMetProperty() {
        return alienMet;
    }

    public void setAlienMetProperty(BooleanProperty property) {
        this.alienMet = property;
    }


    //AMOUNT COLLECTED property
    private final IntegerProperty amount = new IntegerProperty(0);

    public IntegerProperty amountIntegerProperty() {
        return amount;
    }

    public int getAmount() {
        return amount.getValue();
    }

    public void setAmount(int amount) {
        this.amount.setValue(amount);
    }


    //ALIENS KILLED property
    private final IntegerProperty aliensKilled = new IntegerProperty(0);

    public IntegerProperty aliensKilledIntegerProperty() {
        return aliensKilled;
    }

    public int getAliensKilled() {
        return aliensKilled.getValue();
    }

    public void setAliensKilled(int aliensKilled) {
        this.aliensKilled.setValue(aliensKilled);
    }


    //ALERT property
    private final StringProperty alert = new StringProperty();

    public String getAlert() {
        return alert.getValue();
    }

    public StringProperty alertProperty() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert.setValue(alert);
    }

    //FLAGS
    //CONVERT STATE flag
    private boolean canConvert;

    public boolean isCanConvert() {
        return canConvert;
    }

    public void setCanConvert(boolean canConvert) {
        this.canConvert = canConvert;
        if (!canConvert) {
            alert.setValue("You cannot convert that");
        } else {
            alert.setValue("");
        }
    }


    //EXPLORE PLANET
    //if exploration is finished
    private boolean finishedExploring;

    public boolean isFinishedExploring() {
        return finishedExploring;
    }

    public void setFinishedExploring(boolean finishedExploring) {
        this.finishedExploring = finishedExploring;
    }


    //FIGHT
    //if fight is won
    private boolean fightWon;

    public boolean isFightWon() {
        return fightWon;
    }

    public void setFightWon(boolean fightWon) {
        this.fightWon = fightWon;
    }


    //MULTIPLY
    //if win condition is met
    private boolean winCondition;

    public boolean isWinCondition() {
        return winCondition;
    }

    public void setWinCondition(boolean winCondition) {
        this.winCondition = winCondition;
    }


    //UPGRADE
    //if user can upgrade
    private boolean canUpgrade;

    public boolean isCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(boolean canUpgrade) {
        this.canUpgrade = canUpgrade;

        if (!canConvert) {
            alert.setValue("You cannot upgrade that");
        } else {
            alert.setValue("");
        }
    }


    //WAIT IN SPACE
    //if user can start converting
    private final BooleanProperty canStartConvert = new BooleanProperty(false);

    public boolean isCanStartConvert() {
        return canStartConvert.getValue();
    }

    public void setCanStartConvert(boolean canStartConvert) {
        this.canStartConvert.setValue(canStartConvert);
        if (!canConvert) {
            alert.setValue("You need cargo officer");
        } else {
            alert.setValue("");
        }
    }

    public BooleanProperty canStartConvertProperty() {
        return canStartConvert;
    }


    //if user can start converting
    private final BooleanProperty canStartUpgrade = new BooleanProperty(false);

    public BooleanProperty canStartUpgradeBooleanProperty() {
        return canStartUpgrade;
    }

    public boolean isCanStartUpgrade() {
        return canStartUpgrade.getValue();
    }

    public void setCanStartUpgrade(boolean canStartUpgrade) {
        this.canStartUpgrade.setValue(canStartUpgrade);
        if (!canStartUpgrade) {
            alert.setValue("You need land on space station");
        } else {
            alert.setValue("");
        }
    }


    //if user can drop on planet
    private final BooleanProperty canDropOnSurface = new BooleanProperty(false);

    public BooleanProperty canDropOnSurfaceBooleanProperty() {
        return canDropOnSurface;
    }

    public boolean isCanDropOnSurface() {
        return canDropOnSurface.getValue();
    }

    public void setCanDropOnSurface(boolean canDropOnSurface) {
        this.canDropOnSurface.setValue(canDropOnSurface);
        if (!canDropOnSurface) {
            alert.setValue("You cannot drop on surface");
        } else {
            alert.setValue("");
        }
    }


    //if user met event
    private boolean eventMet;

    public boolean isEventMet() {
        return eventMet;
    }

    public void setEventMet(boolean eventMet) {
        this.eventMet = eventMet;
    }
}
