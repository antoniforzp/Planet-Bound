package config;

import binding.Property;
import game.Game;
import game.singletons.Data;

import java.io.*;

public class Loader {

    static String filepath = "save.txt";

    public static void saveGame() {
        try {
            try (FileOutputStream fout = new FileOutputStream(filepath);
                 ObjectOutputStream oos = new ObjectOutputStream(fout)) {

                oos.writeObject(Data.getInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Data loadGame() {

        try {
            try (FileInputStream fileIn = new FileInputStream(filepath);
                 ObjectInputStream objectIn = new ObjectInputStream(fileIn);) {

                Object obj = objectIn.readObject();
                objectIn.close();
                fileIn.close();

                Data data = (Data) obj;
                Data.setInstance(data);
                Game.getInstance().setState(data.getState());
                System.out.println("current state: " + Game.getInstance().getState());

                return data;
            }
        } catch (EOFException e) {
            System.out.println("save file is empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
