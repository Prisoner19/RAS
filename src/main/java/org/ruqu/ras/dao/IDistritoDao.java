package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Distrito;

public interface IDistritoDao {

	public void addDistrito(Distrito Distrito);

	public void updateDistrito(Distrito Distrito);

	public void deleteDistrito(Distrito Distrito);

	public Distrito getDistritoById(int id);

	public List<Distrito> getDistritos();
}
