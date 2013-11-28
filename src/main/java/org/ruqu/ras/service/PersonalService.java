package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IPersonalDao;
import org.ruqu.ras.domain.Personal;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class PersonalService implements IPersonalService{

	IPersonalDao PersonalDAO;

	@Transactional(readOnly = false)
	public void addPersonal(Personal Personal) {
		getPersonalDAO().addPersonal(Personal);
	}

	@Transactional(readOnly = false)
	public void deletePersonal(Personal Personal) {
		getPersonalDAO().deletePersonal(Personal);
	}
	@Transactional(readOnly = false)
	public void deletePersonalLogico(Personal Personal){
		Personal.setVigencia(false);
		getPersonalDAO().updatePersonal(Personal);
	}

	@Transactional(readOnly = false)
	public void updatePersonal(Personal Personal) {
		getPersonalDAO().updatePersonal(Personal);
	}

	public Personal getPersonalById(int id) {
		return getPersonalDAO().getPersonalById(id);
	}

	public List<Personal> getPersonals() {
		return getPersonalDAO().getPersonals();
	}

	public IPersonalDao getPersonalDAO() {
		return PersonalDAO;
	}

	public void setPersonalDAO(IPersonalDao PersonalDAO) {
		this.PersonalDAO = PersonalDAO;
	}
}
