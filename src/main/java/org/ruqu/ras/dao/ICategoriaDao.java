package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Categoria;

public interface ICategoriaDao {

	public void addCategoria(Categoria Categoria);

	public void updateCategoria(Categoria Categoria);

	public void deleteCategoria(Categoria Categoria);

	public Categoria getCategoriaById(int id);

	public List<Categoria> getCategorias();
}
