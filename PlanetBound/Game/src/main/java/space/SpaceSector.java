package space;

import space.planet.IPlanet;
import space.planet.PlanetFactory;
import space.spaceObject.*;


public class SpaceSector {

    //   0         1        2         3
    //   >> ----   <> ----  ()     ------ or ~~~~~~
    //entrance    event  space.planet    route    wormhole

    final ISpaceObject[] objects;

    //CONSTRUCTOR
    public SpaceSector() {
        objects = regenerate();
    }

    public SpaceSector(boolean withWormhole) {
        objects = regenerate();
        objects[3] = new Wormhole();
    }

    //GETTERS
    public ISpaceObject[] getObjects() {
        return objects;
    }

    //REGENERATING
    private ISpaceObject[] regenerate() {

        ISpaceObject[] temp = new ISpaceObject[4];

        //entrance always exists
        temp[0] = new Entrance();

        //event always exists
        temp[1] = new Event();

        //get random planet from PlanetFactory
        temp[2] = PlanetFactory.getRandomPlanet();

        //generate route 77.5% or wormhole 12.5% out the space sector
        double rChance = Math.random() * 99 + 1;
        if (rChance > 12.5) {
            temp[3] = new RouteOut();
        } else {
            temp[3] = new Wormhole();
        }

        return temp;
    }

    public String getStructure() {
        StringBuilder strB = new StringBuilder();

        strB.append("  >> ");
        strB.append("-----");
        strB.append(" <event> ");
        strB.append("-----");

        IPlanet planet = (IPlanet) objects[2];
        if (planet.withSpaceStation()) {
            strB.append("(space.pla)");
        } else {
            strB.append(" (planet)) ");
        }

        if (objects[3].getClass() == RouteOut.class) {
            strB.append("----route---");
        } else {
            strB.append("~~wormhole~~");
        }

        return strB.toString();
    }
}
