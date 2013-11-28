package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Distrito;

public interface IDistritoService {

	public void addDistrito(Distrito Distrito);

	public void updateDistrito(Distrito Distrito);
	
	public void deleteDistrito(Distrito Distrito);
	
	public void deleteDistritoLogico(Distrito Distrito);
	
	public Distrito getDistritoById(int id);

	public List<Distrito> getDistritos();
}
