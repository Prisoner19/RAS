package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IUsuarioDao;
import org.ruqu.ras.domain.Usuario;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService{

	IUsuarioDao UsuarioDAO;

	@Transactional(readOnly = false)
	public void addUsuario(Usuario Usuario) {
		getUsuarioDAO().addUsuario(Usuario);
	}

	@Transactional(readOnly = false)
	public void deleteUsuario(Usuario Usuario) {
		getUsuarioDAO().deleteUsuario(Usuario);
	}
	@Transactional(readOnly = false)
	public void deleteUsuarioLogico(Usuario Usuario){
		Usuario.setVigencia(false);
		getUsuarioDAO().updateUsuario(Usuario);
	}

	@Transactional(readOnly = false)
	public void updateUsuario(Usuario Usuario) {
		getUsuarioDAO().updateUsuario(Usuario);
	}

	public Usuario getUsuarioById(int id) {
		return getUsuarioDAO().getUsuarioById(id);
	}

	public List<Usuario> getUsuarios() {
		return getUsuarioDAO().getUsuarios();
	}

	public IUsuarioDao getUsuarioDAO() {
		return UsuarioDAO;
	}

	public void setUsuarioDAO(IUsuarioDao UsuarioDAO) {
		this.UsuarioDAO = UsuarioDAO;
	}
}
