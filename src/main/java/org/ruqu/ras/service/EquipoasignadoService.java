package org.ruqu.ras.service;

import java.math.BigDecimal;
import java.util.List;

import org.ruqu.ras.dao.IEquipoasignadoDao;
import org.ruqu.ras.domain.Equipoasignado;
import org.ruqu.ras.domain.EquipoasignadoId;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class EquipoasignadoService implements IEquipoasignadoService{

	IEquipoasignadoDao EquipoasignadoDAO;

	@Transactional(readOnly = false)
	public void addEquipoasignado(Equipoasignado Equipoasignado) {
		getEquipoasignadoDAO().addEquipoasignado(Equipoasignado);
	}

	@Transactional(readOnly = false)
	public void deleteEquipoasignado(Equipoasignado Equipoasignado) {
		getEquipoasignadoDAO().deleteEquipoasignado(Equipoasignado);
	}

	@Transactional(readOnly = false)
	public void updateEquipoasignado(Equipoasignado Equipoasignado) {
		getEquipoasignadoDAO().updateEquipoasignado(Equipoasignado);
	}

	public Equipoasignado getEquipoasignadoById(EquipoasignadoId id) {
		return getEquipoasignadoDAO().getEquipoasignadoById(id);
	}

	public List<Equipoasignado> getEquipoasignados() {
		return getEquipoasignadoDAO().getEquipoasignados();
	}

	public IEquipoasignadoDao getEquipoasignadoDAO() {
		return EquipoasignadoDAO;
	}

	public void setEquipoasignadoDAO(IEquipoasignadoDao EquipoasignadoDAO) {
		this.EquipoasignadoDAO = EquipoasignadoDAO;
	}
	
	public BigDecimal getCostoRealEquipoasignados(int idProyecto){
		return getEquipoasignadoDAO().getCostoRealEquipoasignados(idProyecto);
	}
}
