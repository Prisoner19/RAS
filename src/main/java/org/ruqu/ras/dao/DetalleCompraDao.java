package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Detallecompra;
import org.ruqu.ras.domain.DetallecompraId;

public class DetalleCompraDao implements IDetalleCompraDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addDetallecompra(Detallecompra Detallecompra) {
		getSessionFactory().getCurrentSession().save(Detallecompra);
	}

	@Override
	public void updateDetallecompra(Detallecompra Detallecompra) {
		getSessionFactory().getCurrentSession().update(Detallecompra);
	}

	@Override
	public void deleteDetallecompra(Detallecompra Detallecompra) {
		getSessionFactory().getCurrentSession().delete(Detallecompra);
	}

	@Override
	public Detallecompra getDetallecompraById(DetallecompraId id) {
		@SuppressWarnings("unchecked")
		List<Detallecompra> list=getSessionFactory().getCurrentSession()
				.createQuery("from Detallecompra where id=? and Vigencia=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getCompra());
		Hibernate.initialize(list.get(0).getEquipo());
		return (Detallecompra) list.get(0);
	}

	@Override
	public List<Detallecompra> getDetallecompras() {
		@SuppressWarnings("unchecked")
		List<Detallecompra> list=getSessionFactory().getCurrentSession()
				.createQuery("from Detallecompra where Vigencia=1").list();
		for(Detallecompra l:list){
			Hibernate.initialize(l.getCompra());
			Hibernate.initialize(l.getEquipo());
		}
		return list;
	}
}
