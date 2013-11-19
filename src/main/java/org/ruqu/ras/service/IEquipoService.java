package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Equipo;

public interface IEquipoService {

	public void addEquipo(Equipo Equipo);

	public void updateEquipo(Equipo Equipo);
	
	public void deleteEquipo(Equipo Equipo);
	
	public void deleteEquipoLogico(Equipo Equipo);
	
	public Equipo getEquipoById(int id);

	public List<Equipo> getEquipos();
}
