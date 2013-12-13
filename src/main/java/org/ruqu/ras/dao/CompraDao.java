package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Compra;

public class CompraDao implements ICompraDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCompra(Compra Compra) {
		getSessionFactory().getCurrentSession().save(Compra);
	}

	@Override
	public void updateCompra(Compra Compra) {
		getSessionFactory().getCurrentSession().update(Compra);
	}

	@Override
	public void deleteCompra(Compra Compra) {
		getSessionFactory().getCurrentSession().delete(Compra);
	}

	@Override
	public Compra getCompraById(int id) {
		@SuppressWarnings("unchecked")
		List<Compra> list=getSessionFactory().getCurrentSession()
				.createQuery("from Compra where id=? and Vigencia=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getDetallecompras());
		//Hibernate.initialize(list.get(0).getLogconsultas());
		return (Compra) list.get(0);
	}

	@Override
	public List<Compra> getCompras() {
		@SuppressWarnings("unchecked")
		List<Compra> list=getSessionFactory().getCurrentSession()
				.createQuery("from Compra where Vigencia=1").list();
		for(Compra l:list){
			Hibernate.initialize(l.getDetallecompras());
			//Hibernate.initialize(l.getLogconsultas());
			Hibernate.initialize(l.getProveedor());
		}
		return list;
	}
}
