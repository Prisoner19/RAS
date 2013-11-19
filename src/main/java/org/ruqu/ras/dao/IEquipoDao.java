package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Equipo;

public interface IEquipoDao {

	public void addEquipo(Equipo Equipo);

	public void updateEquipo(Equipo Equipo);

	public void deleteEquipo(Equipo Equipo);

	public Equipo getEquipoById(int id);

	public List<Equipo> getEquipos();
	
}
