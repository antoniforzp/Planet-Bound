package logic;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitInSpaceLogicTest {

    @Test
    void setPositionTest() {

        WaitInSpaceLogic logic = new WaitInSpaceLogic();
        assertFalse(logic.setPosition(0));
        assertEquals(0, Data.getInstance().getPosition());

        //true when event is encountered
        assertTrue(logic.setPosition(1));
        assertEquals(1, Data.getInstance().getPosition());

        assertFalse(logic.setPosition(2));
        assertEquals(2, Data.getInstance().getPosition());

        assertFalse(logic.setPosition(3));
        assertEquals(3, Data.getInstance().getPosition());

    }

    @Test
    void travelTest() throws OutOfFuelException, CaptainDeletedException {

        WaitInSpaceLogic logic = new WaitInSpaceLogic();
        Data.getInstance().setPosition(0);

        assertEquals(0, Data.getInstance().getPosition());
        assertTrue(logic.travel());
        assertEquals(1, Data.getInstance().getPosition());

        //true when event is encountered
        assertFalse(logic.travel());
        assertEquals(2, Data.getInstance().getPosition());

        assertFalse(logic.travel());
        assertEquals(3, Data.getInstance().getPosition());

        assertFalse(logic.travel());
        assertEquals(0, Data.getInstance().getPosition());

    }
}