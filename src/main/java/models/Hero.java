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
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getAge(), getPower(), getWeakness(), getSquadId());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getPower() {
        return power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }
}


