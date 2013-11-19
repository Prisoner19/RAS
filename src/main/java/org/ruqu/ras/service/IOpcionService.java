package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Opcion;

public interface IOpcionService {

	public void addOpcion(Opcion Opcion);

	public void updateOpcion(Opcion Opcion);
	
	public void deleteOpcion(Opcion Opcion);
	
	public void deleteOpcionLogico(Opcion Opcion);
	
	public Opcion getOpcionById(int id);

	public List<Opcion> getOpcions();
}
