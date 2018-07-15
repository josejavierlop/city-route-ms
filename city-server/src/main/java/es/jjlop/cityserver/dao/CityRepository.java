package es.jjlop.cityserver.dao;


import es.jjlop.cityserver.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CityRepository extends JpaRepository<City, String> {
    public List<City> getCitiesByName(final String name);
}
