package com.romanyuta.lords_of_the_planets.repository;

import com.romanyuta.lords_of_the_planets.model.Planet;
import com.romanyuta.lords_of_the_planets.model.PlanetForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet,Long> {

    @Query("update Planet p set p.name = ?1, p.lord.id = ?2 where p.id=?3")
    void setPlanetInfoById(String name, Long long_id, Long id);


}
