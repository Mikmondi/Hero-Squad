package models;

import java.util.Objects;

public class Squad {
    private String name;
    private int id;

    public Squad(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squad squad = (Squad) o;
        return id == squad.id &&
                name.equals(squad.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
