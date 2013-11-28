package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IProveedorDao;
import org.ruqu.ras.domain.Proveedor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ProveedorService implements IProveedorService{

	IProveedorDao ProveedorDAO;

	@Transactional(readOnly = false)
	public void addProveedor(Proveedor Proveedor) {
		getProveedorDAO().addProveedor(Proveedor);
	}

	@Transactional(readOnly = false)
	public void deleteProveedor(Proveedor Proveedor) {
		getProveedorDAO().deleteProveedor(Proveedor);
	}
	@Transactional(readOnly = false)
	public void deleteProveedorLogico(Proveedor Proveedor){
		Proveedor.setVigencia(false);
		getProveedorDAO().updateProveedor(Proveedor);
	}

	@Transactional(readOnly = false)
	public void updateProveedor(Proveedor Proveedor) {
		getProveedorDAO().updateProveedor(Proveedor);
	}

	public Proveedor getProveedorById(int id) {
		return getProveedorDAO().getProveedorById(id);
	}

	public List<Proveedor> getProveedors() {
		return getProveedorDAO().getProveedors();
	}

	public IProveedorDao getProveedorDAO() {
		return ProveedorDAO;
	}

	public void setProveedorDAO(IProveedorDao ProveedorDAO) {
		this.ProveedorDAO = ProveedorDAO;
	}
}
