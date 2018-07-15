package es.jjlop.cityserver.dao;

import es.jjlop.cityserver.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface StepsRepository extends JpaRepository<Step, Long> {
}
