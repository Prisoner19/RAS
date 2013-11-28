package org.ruqu.ras.dao;

import java.util.List;

import org.ruqu.ras.domain.Proveedor;

public interface IProveedorDao {

	public void addProveedor(Proveedor Proveedor);

	public void updateProveedor(Proveedor Proveedor);

	public void deleteProveedor(Proveedor Proveedor);

	public Proveedor getProveedorById(int id);

	public List<Proveedor> getProveedors();
}
