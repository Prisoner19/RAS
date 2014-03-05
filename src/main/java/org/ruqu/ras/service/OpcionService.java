package org.ruqu.ras.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.ruqu.ras.dao.IOpcionDao;
import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.domain.Rol;
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

    @Override
    public List<Opcion> getSubOpcionsByPadre(List<Rol> roles, int id_padre){
        return this.OpcionDAO.getSubOpcionsByPadre(roles,id_padre);
    }

    @Override
    public List<Opcion> getAllSubOpcionsByPadre(int id_padre){
        return this.OpcionDAO.getAllSubOpcionsByPadre(id_padre);
    }

    @Override
    public List<Opcion> getSubOpcions(){
        return this.OpcionDAO.getSubOpcions();
    }
    
    @Override
    public List<Opcion> getSubOpcionesPrimarias(){
    	return this.OpcionDAO.getSubOpcionesPrimarias();
    }
    
    @Override
    public List<Opcion> getSubOpcionesSecundarias(){
    	return this.OpcionDAO.getSubOpcionesSecundarias();
    }
}
