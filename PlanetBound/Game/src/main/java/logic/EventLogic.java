package logic;

import dice.Dice3;
import dice.Dice6;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import exceptions.WrongArgumentException;
import game.singletons.Data;
import resources.*;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;
import ship.Ship;

import java.io.Serializable;
import java.util.*;

public class EventLogic implements Serializable {

    public void setEventId(int eventId) {
        Data.getInstance().setEventId(eventId);
    }

    public void processEvent() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {
        Ship ship = Data.getInstance().getShip();

        switch (Data.getInstance().getEventId()) {
            case 1: {
                ship.looseCrewMember();
            }
            break;
            case 2: {
                IResource resource = null;
                Random random = new Random();
                switch (random.nextInt(4) + 1) {
                    case 1: {
                        resource = new BlackResource();
                    }
                    break;
                    case 2: {
                        resource = new RedResource();
                    }
                    break;
                    case 3: {
                        resource = new BlueResource();
                    }
                    break;
                    case 4: {
                        resource = new GreenResource();
                    }
                    break;
                }

                IResource[] resources = new IResource[Dice6.roll()];
                Arrays.fill(resources, resource);

                ship.getCargo().loadResources(resources);
            }
            break;
            case 3: {
                ArrayList<ResourceType> types = new ArrayList<>();
                Random random = new Random();

                //get available resource types to eliminate (if certain array in cargo is empty it is omitted)
                if (!ship.getCargo().isBlacksEmpty()) {
                    types.add(ResourceType.Black);
                }
                if (!ship.getCargo().isBluesEmpty()) {
                    types.add(ResourceType.Blue);
                }
                if (!ship.getCargo().isRedsEmpty()) {
                    types.add(ResourceType.Red);
                }
                if (!ship.getCargo().isGreensEmpty()) {
                    types.add(ResourceType.Green);
                }

                //decide how much resources to delete
                IResource[] resources = new IResource[Dice3.roll()];

                //randomly decide which resource type is to delete
                if (types.size() > 0) {
                    switch (types.get(random.nextInt((types.size())))) {
                        case Black: {
                            Arrays.fill(resources, new BlackResource());
                        }
                        break;
                        case Blue: {
                            Arrays.fill(resources, new BlueResource());
                        }
                        break;
                        case Red: {
                            Arrays.fill(resources, new RedResource());
                        }
                        break;
                        case Green: {
                            Arrays.fill(resources, new GreenResource());
                        }
                        break;
                    }

                    //apply removal to ship
                    ship.getCargo().removeResources(resources);
                }

            }
            break;
            case 4: {
                ship.consumeFuel(1);
            }
            break;
            case 5: {
            }
            break;
            case 6: {
                ship.addNewCrewMember();
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }
    }
}
