package models;

import java.util.Objects;

public class Hero {
    private String name;
    private int id;
    private int age;
    private String power;
    private String weakness;
    private int squadId;

    public Hero(String name, int age, String power, String weakness, int squadId) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        this.squadId = squadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return getId() == hero.getId() &&
                getAge() == hero.getAge() &&
                getSquadId() == hero.getSquadId() &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getPower(), hero.getPower()) &&
                Objects.equals(getWeakness(), hero.getWeakness());
    }
}
