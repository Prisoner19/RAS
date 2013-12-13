package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Compra;
import org.ruqu.ras.domain.Detallecompra;

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
		
		Compra compra=  (Compra)getSessionFactory().getCurrentSession()
				.createQuery("from Compra where id=? and Vigencia=1")
				.setParameter(0, id).uniqueResult();
		if(compra!=null){
			Hibernate.initialize(compra.getDetallecompras());
			for(Detallecompra dc :compra.getDetallecompras()){
				Hibernate.initialize(dc.getEquipo());
			}
		}	
		
		//Hibernate.initialize(list.get(0).getLogconsultas());
		return (compra);
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
