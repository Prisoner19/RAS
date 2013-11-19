package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Detallecompra;
import org.ruqu.ras.domain.DetallecompraId;

public interface IDetalleCompraService {

	public void addDetallecompra(Detallecompra Detallecompra);

	public void updateDetallecompra(Detallecompra Detallecompra);
	
	public void deleteDetallecompra(Detallecompra Detallecompra);
	
	public Detallecompra getDetallecompraById(DetallecompraId id);

	public List<Detallecompra> getDetallecompras();
}
