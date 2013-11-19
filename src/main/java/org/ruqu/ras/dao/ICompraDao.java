package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Compra;

public interface ICompraDao {

	public void addCompra(Compra Compra);

	public void updateCompra(Compra Compra);

	public void deleteCompra(Compra Compra);

	public Compra getCompraById(int id);

	public List<Compra> getCompras();
	
}
