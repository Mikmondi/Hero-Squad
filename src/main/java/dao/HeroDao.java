package dao;

import models.Hero;
import java.util.List;

public interface HeroDao {

    List<Hero> getAll();

    void add(Hero hero);

    Hero findById(int id);

    void update(int id, String name,int age, String power, String weakness, int squadId);

    void deleteById(int id);
    void clearAllHeroes();

}
