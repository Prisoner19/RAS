package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Proyecto;

public interface IProyectoService {

	public void addProyecto(Proyecto Proyecto);

	public void updateProyecto(Proyecto Proyecto);
	
	public void deleteProyecto(Proyecto Proyecto);
	
	public void deleteProyectoLogico(Proyecto Proyecto);
	
	public Proyecto getProyectoById(int id);

	public List<Proyecto> getProyectos();
}
