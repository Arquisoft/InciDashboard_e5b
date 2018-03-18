package uo.asw.inciDashboard.currentIncidences;

import org.springframework.data.jpa.repository.JpaRepository;

import uo.asw.dbManagement.model.Incidence;

public interface IncidenceRepository  extends JpaRepository<Incidence, Long>{

	Incidence findByName(String name);
}
