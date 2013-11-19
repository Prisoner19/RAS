package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IProyectoDao;
import org.ruqu.ras.domain.Proyecto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProyectoService implements IProyectoService{

	IProyectoDao ProyectoDAO;

	@Transactional(readOnly = false)
	public void addProyecto(Proyecto Proyecto) {
		getProyectoDAO().addProyecto(Proyecto);
	}

	@Transactional(readOnly = false)
	public void deleteProyecto(Proyecto Proyecto) {
		getProyectoDAO().deleteProyecto(Proyecto);
	}
	@Transactional(readOnly = false)
	public void deleteProyectoLogico(Proyecto Proyecto){
		Proyecto.setVigencia(false);
		getProyectoDAO().updateProyecto(Proyecto);
	}

	@Transactional(readOnly = false)
	public void updateProyecto(Proyecto Proyecto) {
		getProyectoDAO().updateProyecto(Proyecto);
	}

	public Proyecto getProyectoById(int id) {
		return getProyectoDAO().getProyectoById(id);
	}

	public List<Proyecto> getProyectos() {
		return getProyectoDAO().getProyectos();
	}

	public IProyectoDao getProyectoDAO() {
		return ProyectoDAO;
	}

	public void setProyectoDAO(IProyectoDao ProyectoDAO) {
		this.ProyectoDAO = ProyectoDAO;
	}
}
