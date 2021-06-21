package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = createNewHero();
        assertTrue(true);
    }
    @Test
    public void HeroInstantiatesWithName_true() throws Exception {
        Hero hero = createNewHero();
        assertEquals("Tony Stark", hero.getName());
    }
    @Test
    public void HeroInstantiatesWithPower_true() throws  Exception {
        Hero hero = createNewHero();
        hero.setPower("Fly");

        assertEquals("Fly",hero.getPower());
    }
    @Test
    public void HeroInstantiatesWithWeakness_true() throws  Exception {
        Hero hero = createNewHero();
        hero.setWeakness("Kryptonite");

        assertEquals("Kryptonite",hero.getWeakness());
    }

    @Test
    public void getAge_heroesInstantiateWithAge_true() throws Exception{
        Hero myHero = createNewHero();
        myHero.setAge(50);
        assertEquals(50, myHero.getAge());
    }

    private Hero createNewHero(){
        return new Hero("Tony Stark", 50 , "Flying" , "Thanos",1);
    }

}
