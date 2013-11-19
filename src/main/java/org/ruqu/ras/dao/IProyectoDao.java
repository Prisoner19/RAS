package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Proyecto;

public interface IProyectoDao {

	public void addProyecto(Proyecto Proyecto);

	public void updateProyecto(Proyecto Proyecto);

	public void deleteProyecto(Proyecto Proyecto);

	public Proyecto getProyectoById(int id);

	public List<Proyecto> getProyectos();
}
