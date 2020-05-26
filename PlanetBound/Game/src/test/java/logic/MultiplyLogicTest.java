package logic;

import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import resources.IResource;
import resources.types.Artefact;
import resources.types.BlackResource;
import ship.MiningShip;
import ship.cargo.Cargo;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyLogicTest {

    @Test
    void multiplyTest1() {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new BlackResource());

        Cargo cargo = LogicConfig.getInstance().getShip().getCargo();

        MultiplyLogic logic = new MultiplyLogic();

        logic.multiply(3);

        //actual capacities are not equal with the previous ones
        assertFalse(cargo.getCapacity(cargo.getBlacks()) == 0 &&
                cargo.getCapacity(cargo.getBlues()) == 0 &&
                cargo.getCapacity(cargo.getGreens()) == 0 &&
                cargo.getCapacity(cargo.getReds()) == 0);

    }

    @Test
    void multiplyTest2() {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new Artefact());

        Cargo cargo = LogicConfig.getInstance().getShip().getCargo();

        MultiplyLogic logic = new MultiplyLogic();

        assertFalse(logic.multiply(1));

        assertTrue(cargo.getCapacity(cargo.getBlacks()) == 0 &&
                cargo.getCapacity(cargo.getBlues()) == 0 &&
                cargo.getCapacity(cargo.getGreens()) == 0 &&
                cargo.getCapacity(cargo.getReds()) == 0);

        assertEquals(1, cargo.getCapacity(cargo.getArtifacts()));
    }

    @Test
    void multiplyTest3() {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new Artefact());

        IResource[] artifact = new IResource[4];
        Arrays.fill(artifact, new Artefact());

        Cargo cargo = LogicConfig.getInstance().getShip().getCargo();
        cargo.loadResources(artifact);

        MultiplyLogic logic = new MultiplyLogic();
        assertTrue(logic.multiply(1));

        assertTrue(cargo.getCapacity(cargo.getBlacks()) == 0 &&
                cargo.getCapacity(cargo.getBlues()) == 0 &&
                cargo.getCapacity(cargo.getGreens()) == 0 &&
                cargo.getCapacity(cargo.getReds()) == 0);

        assertEquals(5, cargo.getCapacity(cargo.getArtifacts()));
    }
}