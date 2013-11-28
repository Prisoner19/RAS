package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IDistritoDao;
import org.ruqu.ras.domain.Distrito;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class DistritoService implements IDistritoService{

	IDistritoDao DistritoDAO;

	@Transactional(readOnly = false)
	public void addDistrito(Distrito Distrito) {
		getDistritoDAO().addDistrito(Distrito);
	}

	@Transactional(readOnly = false)
	public void deleteDistrito(Distrito Distrito) {
		getDistritoDAO().deleteDistrito(Distrito);
	}
	@Transactional(readOnly = false)
	public void deleteDistritoLogico(Distrito Distrito){
		Distrito.setVigencia(false);
		getDistritoDAO().updateDistrito(Distrito);
	}

	@Transactional(readOnly = false)
	public void updateDistrito(Distrito Distrito) {
		getDistritoDAO().updateDistrito(Distrito);
	}

	public Distrito getDistritoById(int id) {
		return getDistritoDAO().getDistritoById(id);
	}

	public List<Distrito> getDistritos() {
		return getDistritoDAO().getDistritos();
	}

	public IDistritoDao getDistritoDAO() {
		return DistritoDAO;
	}

	public void setDistritoDAO(IDistritoDao DistritoDAO) {
		this.DistritoDAO = DistritoDAO;
	}
}
