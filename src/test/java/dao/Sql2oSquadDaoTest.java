package dao;

import models.Hero;
import models.Squad;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {
    private static Sql2oSquadDao squadDao;
    private static Sql2oHeroDao heroDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/hero_squad";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "gre8tness");
        squadDao = new Sql2oSquadDao(sql2o);
        heroDao = new Sql2oHeroDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        squadDao.clearAllSquads();
        heroDao.clearAllHeroes();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();
        System.out.println("connection closed");
    }


    @Test
    public void addingHeroSetsId() throws Exception {
        Squad squad = createNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }

    @Test
    public void addedSquadsAreReturnedFromGetAll() throws Exception {
        Squad squad = createNewSquad();
        squadDao.add(squad);
        assertEquals(1, squadDao.getAll().size());
    }

    @Test
    public void existingSquadCanBeFoundById() throws Exception {
        Squad squad = createNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad, foundSquad);
    }


    @Test
    public void updateChangesSquadName() throws Exception {
        Squad squad = createNewSquad();
        String initialName = squad.getName();
        squadDao.add(squad);

        squadDao.update(squad.getId(), "Avengers");
        Squad updatedName = squadDao.findById(squad.getId());
        assertNotEquals(initialName, updatedName.getName());
    }

    @Test
    public void deleteByIdDeletesCorrectSquad() throws Exception {
        Squad squad = createNewSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        assertEquals(0, squadDao.getAll().size());
    }

    @Test
    public void clearAllSquads() throws Exception {
        Squad squad = createNewSquad();
        Squad otherSquad = new Squad("Captain America");
        squadDao.add(squad);
        squadDao.add(otherSquad);
        int daoSize = squadDao.getAll().size();
        squadDao.clearAllSquads();
        assertTrue(daoSize > 0 && daoSize > squadDao.getAll().size());
    }

    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly() throws Exception {
        Squad squad = createNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero newHero = new Hero("Batman", 45, "Weapons", "Joker", squadId);
        Hero otherHero = new Hero("Superman", 40, "Speed", "Kryptonite", squadId);
        Hero thirdHero = new Hero("Thing", 25, "Botanic", "Sun", squadId);
        heroDao.add(newHero);
        heroDao.add(otherHero);
        assertEquals(2, squadDao.getAllHeroesBySquad(squadId).size());
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(newHero));
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(otherHero));
        assertFalse(squadDao.getAllHeroesBySquad(squadId).contains(thirdHero));
    }


    public Squad createNewSquad() {
        return new Squad("Justice League");
    }
}