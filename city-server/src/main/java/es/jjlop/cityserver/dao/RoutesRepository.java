package es.jjlop.cityserver.dao;

import es.jjlop.cityserver.entity.City;
import es.jjlop.cityserver.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoutesRepository extends JpaRepository<Route, Long> {
    public List<Route> getRoutesByOrigin(final City city);
}
