package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Personal;

public interface IPersonalService {

	public void addPersonal(Personal Personal);

	public void updatePersonal(Personal Personal);
	
	public void deletePersonal(Personal Personal);
	
	public void deletePersonalLogico(Personal Personal);
	
	public Personal getPersonalById(int id);

	public List<Personal> getPersonals();
}
