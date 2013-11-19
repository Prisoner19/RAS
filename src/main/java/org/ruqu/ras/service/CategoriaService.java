package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.ICategoriaDao;
import org.ruqu.ras.domain.Categoria;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CategoriaService implements ICategoriaService{

	ICategoriaDao CategoriaDAO;

	@Transactional(readOnly = false)
	public void addCategoria(Categoria Categoria) {
		getCategoriaDAO().addCategoria(Categoria);
	}

	@Transactional(readOnly = false)
	public void deleteCategoria(Categoria Categoria) {
		getCategoriaDAO().deleteCategoria(Categoria);
	}
	@Transactional(readOnly = false)
	public void deleteCategoriaLogico(Categoria Categoria){
		Categoria.setVigencia(false);
		getCategoriaDAO().updateCategoria(Categoria);
	}

	@Transactional(readOnly = false)
	public void updateCategoria(Categoria Categoria) {
		getCategoriaDAO().updateCategoria(Categoria);
	}

	public Categoria getCategoriaById(int id) {
		return getCategoriaDAO().getCategoriaById(id);
	}

	public List<Categoria> getCategorias() {
		return getCategoriaDAO().getCategorias();
	}

	public ICategoriaDao getCategoriaDAO() {
		return CategoriaDAO;
	}

	public void setCategoriaDAO(ICategoriaDao CategoriaDAO) {
		this.CategoriaDAO = CategoriaDAO;
	}
}
