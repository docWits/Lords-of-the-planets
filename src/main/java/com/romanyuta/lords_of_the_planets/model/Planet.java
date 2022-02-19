package com.romanyuta.lords_of_the_planets.model;

import javax.persistence.*;

@Entity
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "lord_id")
    private Lord lord;

    public Planet() {
    }

    public Planet(String name, Lord lord) {
        this.name = name;
        this.lord = lord;
    }

    public Planet(String name) {
        this.name = name;
    }

    public long getId() {
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

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
    }
}
