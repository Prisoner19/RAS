package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.ICompraDao;
import org.ruqu.ras.domain.Compra;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CompraService implements ICompraService{

	ICompraDao CompraDAO;

	@Transactional(readOnly = false)
	public void addCompra(Compra Compra) {
		getCompraDAO().addCompra(Compra);
	}

	@Transactional(readOnly = false)
	public void deleteCompra(Compra Compra) {
		getCompraDAO().deleteCompra(Compra);
	}
	@Transactional(readOnly = false)
	public void deleteCompraLogico(Compra Compra){
		Compra.setVigencia(false);
		getCompraDAO().updateCompra(Compra);
	}

	@Transactional(readOnly = false)
	public void updateCompra(Compra Compra) {
		getCompraDAO().updateCompra(Compra);
	}

	public Compra getCompraById(int id) {
		return getCompraDAO().getCompraById(id);
	}

	public List<Compra> getCompras() {
		return getCompraDAO().getCompras();
	}

	public ICompraDao getCompraDAO() {
		return CompraDAO;
	}

	public void setCompraDAO(ICompraDao CompraDAO) {
		this.CompraDAO = CompraDAO;
	}
}
