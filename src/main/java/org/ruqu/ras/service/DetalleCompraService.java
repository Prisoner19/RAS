package org.ruqu.ras.service;

import java.util.List;

import org.ruqu.ras.dao.IDetalleCompraDao;
import org.ruqu.ras.domain.Detallecompra;
import org.ruqu.ras.domain.DetallecompraId;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class DetalleCompraService implements IDetalleCompraService{

	IDetalleCompraDao DetallecompraDAO;

	@Transactional(readOnly = false)
	public void addDetallecompra(Detallecompra Detallecompra) {
		getDetallecompraDAO().addDetallecompra(Detallecompra);
	}

	@Transactional(readOnly = false)
	public void deleteDetallecompra(Detallecompra Detallecompra) {
		getDetallecompraDAO().deleteDetallecompra(Detallecompra);
	}

	@Transactional(readOnly = false)
	public void updateDetallecompra(Detallecompra Detallecompra) {
		getDetallecompraDAO().updateDetallecompra(Detallecompra);
	}

	public Detallecompra getDetallecompraById(DetallecompraId id) {
		return getDetallecompraDAO().getDetallecompraById(id);
	}

	public List<Detallecompra> getDetallecompras() {
		return getDetallecompraDAO().getDetallecompras();
	}

	public IDetalleCompraDao getDetallecompraDAO() {
		return DetallecompraDAO;
	}

	public void setDetallecompraDAO(IDetalleCompraDao DetallecompraDAO) {
		this.DetallecompraDAO = DetallecompraDAO;
	}
}
