package org.ruqu.ras.dao;

import java.math.BigDecimal;
import java.util.List;

import org.ruqu.ras.domain.Equipoasignado;
import org.ruqu.ras.domain.EquipoasignadoId;

public interface IEquipoasignadoDao {

	public void addEquipoasignado(Equipoasignado Equipoasignado);

	public void updateEquipoasignado(Equipoasignado Equipoasignado);

	public void deleteEquipoasignado(Equipoasignado Equipoasignado);

	public Equipoasignado getEquipoasignadoById(EquipoasignadoId id);

	public List<Equipoasignado> getEquipoasignados();
	
	public BigDecimal getCostoRealEquipoasignados(int idProyecto);
}
