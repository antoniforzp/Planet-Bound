package game;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.singletons.Data;
import game.states.ChooseShip;

import java.io.Serializable;

public class Game implements Serializable {

    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Game() {
        Data.getInstance().currentStateProperty().addListener((oldVal, newVal) -> {
            System.out.println(oldVal + " ----> " + newVal);
        });
    }

    //STATE OBJECT PROPERTY (initialized with choose ship state)
    private State state = new ChooseShip();

    //STATE OPERATIONS
    public void setState(State state) {
        this.state = state;
        Data.getInstance().currentStateProperty().setValue(state);
    }

    public State getState() {
        return this.state;
    }

    //STATE MACHINE OPERATIONS
    public void chooseShip(int choice) {
        try {
            state = state.chooseShip(choice);
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
    }

    public void startConvert() {
        try {
            state = state.startConvert();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void convert(int choice) {
        try {
            state = state.convert(choice);
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
    }

    public void startUpgrade() {
        try {
            state = state.startUpgrade();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void upgrade(int choice) {
        try {
            state = state.upgrade(choice);
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        try {
            state = state.finish();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void dropOnSurface() {
        try {
            state = state.dropOnSurface();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void fight() {
        try {
            state = state.fight();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void move(int x, int y) {
        try {
            state = state.move(x, y);
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void travel() {
        try {
            state = state.travel();
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException e) {
            e.printStackTrace();
        }
    }

    public void processEvent(int choice) {
        try {
            state = state.processEvent(choice);
            Data.getInstance().currentStateProperty().setValue(state);

        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
    }
}
