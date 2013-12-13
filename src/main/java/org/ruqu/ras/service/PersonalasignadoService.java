package org.ruqu.ras.service;

import java.math.BigDecimal;
import java.util.List;

import org.ruqu.ras.dao.IPersonalasignadoDao;
import org.ruqu.ras.domain.Personalasignado;
import org.ruqu.ras.domain.PersonalasignadoId;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PersonalasignadoService implements IPersonalasignadoService{

	IPersonalasignadoDao PersonalasignadoDAO;

	@Transactional(readOnly = false)
	public void addPersonalasignado(Personalasignado Personalasignado) {
		getPersonalasignadoDAO().addPersonalasignado(Personalasignado);
	}

	@Transactional(readOnly = false)
	public void deletePersonalasignado(Personalasignado Personalasignado) {
		getPersonalasignadoDAO().deletePersonalasignado(Personalasignado);
	}

	@Transactional(readOnly = false)
	public void updatePersonalasignado(Personalasignado Personalasignado) {
		getPersonalasignadoDAO().updatePersonalasignado(Personalasignado);
	}

	public Personalasignado getPersonalasignadoById(PersonalasignadoId id) {
		return getPersonalasignadoDAO().getPersonalasignadoById(id);
	}

	public List<Personalasignado> getPersonalasignados() {
		return getPersonalasignadoDAO().getPersonalasignados();
	}

	public IPersonalasignadoDao getPersonalasignadoDAO() {
		return PersonalasignadoDAO;
	}

	public void setPersonalasignadoDAO(IPersonalasignadoDao PersonalasignadoDAO) {
		this.PersonalasignadoDAO = PersonalasignadoDAO;
	}
	
	public BigDecimal getCostoRealPersonalasignados(int idProyecto){
		return getPersonalasignadoDAO().getCostoRealPersonalasignados(idProyecto);
	}
}
