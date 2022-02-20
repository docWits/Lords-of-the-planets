package com.romanyuta.lords_of_the_planets.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int age;

    @OneToMany(mappedBy = "lord")
    private List<Planet> planets;

    public Lord() {
    }

    public Lord(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
