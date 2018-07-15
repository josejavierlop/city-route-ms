package es.jjlop.cityserver.dao;


import es.jjlop.cityserver.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface CitiesRepository extends JpaRepository<City, String> {
    public City getCityByName(final String name);
}
