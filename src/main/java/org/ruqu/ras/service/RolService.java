package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IRolDao;
import org.ruqu.ras.domain.Rol;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class RolService implements IRolService{

	IRolDao RolDAO;

	@Transactional(readOnly = false)
	public void addRol(Rol Rol) {
		getRolDAO().addRol(Rol);
	}

	@Transactional(readOnly = false)
	public void deleteRol(Rol Rol) {
		getRolDAO().deleteRol(Rol);
	}
	@Transactional(readOnly = false)
	public void deleteRolLogico(Rol Rol){
		Rol.setVigencia(false);
		getRolDAO().updateRol(Rol);
	}

	@Transactional(readOnly = false)
	public void updateRol(Rol Rol) {
		getRolDAO().updateRol(Rol);
	}

	public Rol getRolById(int id) {
		return getRolDAO().getRolById(id);
	}

	public List<Rol> getRols() {
		return getRolDAO().getRols();
	}

	public IRolDao getRolDAO() {
		return RolDAO;
	}

	public void setRolDAO(IRolDao RolDAO) {
		this.RolDAO = RolDAO;
	}
}
