package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Personal;

public interface IPersonalDao {
	
	public void addPersonal(Personal Personal);

	public void updatePersonal(Personal Personal);

	public void deletePersonal(Personal Personal);

	public Personal getPersonalById(int id);

	public List<Personal> getPersonals();
}
