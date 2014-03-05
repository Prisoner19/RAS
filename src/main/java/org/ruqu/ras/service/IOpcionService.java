package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Opcion;
import org.ruqu.ras.domain.Rol;

public interface IOpcionService {

	public void addOpcion(Opcion Opcion);

	public void updateOpcion(Opcion Opcion);
	
	public void deleteOpcion(Opcion Opcion);
	
	public void deleteOpcionLogico(Opcion Opcion);
	
	public Opcion getOpcionById(int id);

	public List<Opcion> getOpcions();

    public List<Opcion> getSubOpcions();
    
    public List<Opcion> getSubOpcionesPrimarias();
    
    public List<Opcion> getSubOpcionesSecundarias();

    public List<Opcion> getAllSubOpcionsByPadre(int id_padre);

    public List<Opcion> getSubOpcionsByPadre(List<Rol> roles, int id_padre);
}
