package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Usuario;

public interface IUsuarioService {

	public void addUsuario(Usuario Usuario);

	public void updateUsuario(Usuario Usuario);
	
	public void deleteUsuario(Usuario Usuario);
	
	public void deleteUsuarioLogico(Usuario Usuario);
	
	public Usuario getUsuarioById(int id);

	public List<Usuario> getUsuarios();
}
