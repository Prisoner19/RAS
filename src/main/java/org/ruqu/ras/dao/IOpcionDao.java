package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Opcion;

public interface IOpcionDao {

	public void addOpcion(Opcion Opcion);

	public void updateOpcion(Opcion Opcion);

	public void deleteOpcion(Opcion Opcion);

	public Opcion getOpcionById(int id);

	public List<Opcion> getOpcions();
	
}
