package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Categoria;

public interface ICategoriaService {

	public void addCategoria(Categoria Categoria);

	public void updateCategoria(Categoria Categoria);
	
	public void deleteCategoria(Categoria Categoria);
	
	public void deleteCategoriaLogico(Categoria Categoria);
	
	public Categoria getCategoriaById(int id);

	public List<Categoria> getCategorias();
}
