package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewSquadObjectGetsCorrectlyCreated_True() throws Exception {
        Squad squad = new Squad("Avengers");
        assertTrue(squad instanceof Squad);
    }
}