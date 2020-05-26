package game;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.states.IState;
import observer.IObservable;
import observer.IObserver;

import java.util.ArrayList;

public class Game implements IObservable {

    private final ArrayList<IObserver> observers;
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Game() {
        observers = new ArrayList<>();
    }

    private static IState state;

    //STATE OPERATIONS
    public static void setState(IState state) {
        if (state != null) {
            if (Game.state == null) {
                System.out.println(" --> " + state.getClass().getName());
            } else {
                System.out.println(Game.state.getClass().getName() + " --> " + state.getClass().getName());

            }
            Game.state = state;
            Game.getInstance().notifyChange("state");
        }
    }

    public static IState getState() {
        return state;
    }

    //STATE MACHINE OPERATIONS
    public static boolean chooseShip(int choice) {
        try {
            return state.chooseShip(choice);
        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean startConvert() {
        try {
            return state.startConvert();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean convert(int choice) {
        try {
            return state.convert(choice);
        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean startUpgrade() {
        try {
            return state.startUpgrade();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean upgrade(int choice) {
        try {
            return state.upgrade(choice);
        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean finish() {
        try {
            return state.finish();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean dropOnSurface() {
        try {
            return state.dropOnSurface();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean fight() {
        try {
            return state.fight();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean move(int x, int y) {
        try {
            return state.move(x, y);
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean travel() {
        try {
            return state.travel();
        } catch (UnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean processEvent(int choice) {
        try {
            return state.processEvent(choice);
        } catch (UnavailableException | WrongArgumentException e) {
            e.printStackTrace();
        }
        return false;
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
