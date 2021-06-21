package dao;

import models.Hero;
import models.Squad;
import java.util.List;

public interface SquadDao {

    List<Squad> getAll();
    List<Hero> getAllHeroesBySquad(int squadId);

    void add (Squad squad);

    Squad findById(int id);

    void update(int id, String name);
    void deleteById(int id);

    void clearAllSquads();
}

