package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Usuario;

public interface IUsuarioDao {

	public void addUsuario(Usuario Usuario);

	public void updateUsuario(Usuario Usuario);

	public void deleteUsuario(Usuario Usuario);

	public Usuario getUsuarioById(int id);

	public List<Usuario> getUsuarios();
	
}
