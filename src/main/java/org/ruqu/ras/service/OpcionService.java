package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IOpcionDao;
import org.ruqu.ras.domain.Opcion;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class OpcionService implements IOpcionService{

	IOpcionDao OpcionDAO;

	@Transactional(readOnly = false)
	public void addOpcion(Opcion Opcion) {
		getOpcionDAO().addOpcion(Opcion);
	}

	@Transactional(readOnly = false)
	public void deleteOpcion(Opcion Opcion) {
		getOpcionDAO().deleteOpcion(Opcion);
	}
	@Transactional(readOnly = false)
	public void deleteOpcionLogico(Opcion Opcion){
		Opcion.setVigencia(false);
		getOpcionDAO().updateOpcion(Opcion);
	}

	@Transactional(readOnly = false)
	public void updateOpcion(Opcion Opcion) {
		getOpcionDAO().updateOpcion(Opcion);
	}

	public Opcion getOpcionById(int id) {
		return getOpcionDAO().getOpcionById(id);
	}

	public List<Opcion> getOpcions() {
		return getOpcionDAO().getOpcions();
	}

	public IOpcionDao getOpcionDAO() {
		return OpcionDAO;
	}

	public void setOpcionDAO(IOpcionDao OpcionDAO) {
		this.OpcionDAO = OpcionDAO;
	}
}
