package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.domain.Proveedor;

public interface IProveedorService {

	public void addProveedor(Proveedor Proveedor);

	public void updateProveedor(Proveedor Proveedor);
	
	public void deleteProveedor(Proveedor Proveedor);
	
	public void deleteProveedorLogico(Proveedor Proveedor);
	
	public Proveedor getProveedorById(int id);

	public List<Proveedor> getProveedors();
}
