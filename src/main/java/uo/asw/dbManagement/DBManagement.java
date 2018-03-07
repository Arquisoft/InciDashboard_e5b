package uo.asw.dbManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbManagement.model.Incidence;

public class DBManagement {

	//TODO - revisar
	
	@Autowired
	private OperatorsRepository operatorsRepository;
	
	@Autowired
	private FilterRepository filterRepository;
	
	@Autowired
	private IncidencesRepository incidencesRepository;
	
	public List<Incidence> getOperatorIncidences(Long idOperator) {
		return incidencesRepository.getOperatorIncidences(idOperator);
	}

	public List<Incidence> getIncidencesOfCategory(List<String> categories) {
		return incidencesRepository.getIncidencesOfCategory(categories);
	}

	public Incidence getIncidence(Long idIncidence) {
		return incidencesRepository.findOne(idIncidence);
	}

	public void updateIncidence(Incidence incidence) {
		incidencesRepository.save(incidence);
	}

}
