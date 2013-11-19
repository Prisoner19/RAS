package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Compra;

public interface ICompraService {

	public void addCompra(Compra Compra);

	public void updateCompra(Compra Compra);
	
	public void deleteCompra(Compra Compra);
	
	public void deleteCompraLogico(Compra Compra);
	
	public Compra getCompraById(int id);

	public List<Compra> getCompras();
}
