package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Rol;

public interface IRolDao {

	public void addRol(Rol Rol);

	public void updateRol(Rol Rol);

	public void deleteRol(Rol Rol);

	public Rol getRolById(int id);

	public List<Rol> getRols();

    public List<Rol> getRolesUserbyId(int id);

    public List<String> getRolesMenuByRuta(String ruta);
}
