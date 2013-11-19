package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IEquipoDao;
import org.ruqu.ras.domain.Equipo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class EquipoService implements IEquipoService{

	IEquipoDao EquipoDAO;

	@Transactional(readOnly = false)
	public void addEquipo(Equipo Equipo) {
		getEquipoDAO().addEquipo(Equipo);
	}

	@Transactional(readOnly = false)
	public void deleteEquipo(Equipo Equipo) {
		getEquipoDAO().deleteEquipo(Equipo);
	}
	@Transactional(readOnly = false)
	public void deleteEquipoLogico(Equipo Equipo){
		Equipo.setVigencia(false);
		getEquipoDAO().updateEquipo(Equipo);
	}

	@Transactional(readOnly = false)
	public void updateEquipo(Equipo Equipo) {
		getEquipoDAO().updateEquipo(Equipo);
	}

	public Equipo getEquipoById(int id) {
		return getEquipoDAO().getEquipoById(id);
	}

	public List<Equipo> getEquipos() {
		return getEquipoDAO().getEquipos();
	}

	public IEquipoDao getEquipoDAO() {
		return EquipoDAO;
	}

	public void setEquipoDAO(IEquipoDao EquipoDAO) {
		this.EquipoDAO = EquipoDAO;
	}
}
