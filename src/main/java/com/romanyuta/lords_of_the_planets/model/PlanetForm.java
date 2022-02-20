package com.romanyuta.lords_of_the_planets.model;

public class PlanetForm {
    private String name;
    private Long lord_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLord_id() {
        return lord_id;
    }

    public void setLord_id(Long lord_id) {
        this.lord_id = lord_id;
    }

    public PlanetForm() {
    }

    public PlanetForm(String name, Long lord_id) {
        this.name = name;
        this.lord_id = lord_id;
    }
}
