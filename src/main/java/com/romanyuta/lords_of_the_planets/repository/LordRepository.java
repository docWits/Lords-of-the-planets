package com.romanyuta.lords_of_the_planets.repository;

import com.romanyuta.lords_of_the_planets.model.Lord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LordRepository extends JpaRepository<Lord,Long> {

    @Query(value = "select * from lord left join planet on lord.id=planet.lord_id where planet.id is null", nativeQuery=true)
    List<Lord> getLouderLords();

    @Query(value = "select lord.id, lord.name, lord.age from lord order by lord.age asc limit 10;", nativeQuery=true)
    List<Lord> getTopYoungLords();
}
