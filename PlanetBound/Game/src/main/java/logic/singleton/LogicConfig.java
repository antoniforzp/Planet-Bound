package logic.singleton;

import game.Game;
import observer.IObservable;
import observer.IObserver;
import resources.IResource;
import ship.Ship;
import space.SpaceSector;
import space.planet.Planet;
import walker.Coordinate;
import walker.alien.Alien;
import walker.alien.AlienFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogicConfig implements IObservable {

    private final ArrayList<IObserver> observers;
    private static LogicConfig instance;

    public static LogicConfig getInstance() {
        if (instance == null) {
            instance = new LogicConfig();
        }
        return instance;
    }

    private LogicConfig() {
        this.observers = new ArrayList<>();
        this.spaceSector = new SpaceSector();
        this.planet = (Planet) spaceSector.getObjects()[2];
        this.alien = AlienFactory.getRandomAlien();

        messages = new HashMap<>();
        messages.put(1, "A crew member is injured due to a system malfunction, move the ship crew die to the right one space");
        messages.put(2, "You ship comes across an abandoned ship and you find a random resource. Place all four of the resource cubes in a bag and draw one. Roll the d6 for that resource and add it to your cargo");
        messages.put(3, "A cargo mishap causes you to lose some of your resources. Place the colored cubes of the resources you have in the cargo hold and draw one. Roll a d3 [1-3] to see how much of that resource you lose”");
        messages.put(4, "You accidentally use too much fuel in a test run. Remove [1] fuel cell");
        messages.put(5, "Nothing happens");
        messages.put(6, "You find a ship in distress with a lone crew member. This crew member joins your crew and you move your white crew die to the left one space if you have less than six crew");
    }

    //SHIP
    private Ship ship;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    //FIGHT LOG
    private String fightLog;

    public String getFightLog() {
        return this.fightLog;
    }

    public void setFightLog(String fightLog) {
        this.fightLog = fightLog;
        notifyChange("fightLog");
    }

    //ALIEN
    private Alien alien;

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
        notifyChange("alien");
    }

    //EVENT MESSAGES

    private final Map<Integer, String> messages;

    public Map<Integer, String> getMessages() {
        return messages;
    }

    //EVENT ID

    private int eventId;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
        notifyChange("eventId");
    }

    //SPACE POSITION

    private int position = 0;

    public static void setInstance(LogicConfig instance) {
        LogicConfig.instance = instance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        notifyChange("shipPosition");
    }

    //PLANET

    private Planet planet;

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    //RESOURCE

    private IResource resource;

    public IResource getResource() {
        return resource;
    }

    public void setResource(IResource resource) {
        this.resource = resource;
        notifyChange("currResource");
    }

    //EXTRACTION POINT COORDINATE

    private Coordinate extractionPointCoordinate;

    public Coordinate getExtractionPoint() {
        return extractionPointCoordinate;
    }

    public void setExtractionPoint(Coordinate extractionPointCoordinate) {
        this.extractionPointCoordinate = extractionPointCoordinate;
        notifyChange("extPointCoordinate");
    }

    //RESOURCE COORDINATE

    private Coordinate resourceCoordinate;

    public Coordinate getResourceCoordinate() {
        return resourceCoordinate;
    }

    public void setResourceCoordinate(Coordinate resourceCoordinate) {
        this.resourceCoordinate = resourceCoordinate;
        notifyChange("resourceCoordinate");
    }

    //SPACE SECTOR

    private SpaceSector spaceSector;

    public SpaceSector getSpaceSector() {
        return spaceSector;
    }

    public void setSpaceSector(SpaceSector spaceSector) {
        this.spaceSector = spaceSector;
        this.planet = (Planet) spaceSector.getObjects()[2];
        notifyChange("spaceSector");
    }

    //RESOURCE TAKEN

    private boolean resourceTaken = false;

    public boolean isResourceTaken() {
        return resourceTaken;
    }

    public void setResourceTaken(boolean resourceTaken) {
        this.resourceTaken = resourceTaken;
    }


    //AMOUNT COLLECTED

    private int amount = 0;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        notifyChange("amount");
    }

    //RUN OUT OF FUEL

    private boolean runOutOfFuel = false;

    public boolean isRunOutOfFuel() {
        return runOutOfFuel;
    }

    public void setRunOutOfFuel(boolean runOutOfFuel) {
        this.runOutOfFuel = runOutOfFuel;
        notifyChange("noFuel");
    }

    //ALIENS KILLED

    private int aliensKilled = 0;

    public int getAliensKilled() {
        return aliensKilled;
    }

    public void setAliensKilled(int aliensKilled) {
        this.aliensKilled = aliensKilled;
        notifyChange("aliensKilled");
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyChange(String property) {
        for (IObserver o : observers) {
            o.update(property);
        }
    }
}
