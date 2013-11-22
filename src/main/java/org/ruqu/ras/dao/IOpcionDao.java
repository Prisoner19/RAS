package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.domain.Rol;

public interface IOpcionDao {

	public void addOpcion(Opcion Opcion);

	public void updateOpcion(Opcion Opcion);

	public void deleteOpcion(Opcion Opcion);

	public Opcion getOpcionById(int id);

	public List<Opcion> getOpcions();

    public List<Opcion> getSubOpcionsByPadre(List<Rol> roles, int id_padre);

    public List<Opcion> getAllSubOpcionsByPadre(int id_padre);

    public List<Opcion> getSubOpcions();
}
