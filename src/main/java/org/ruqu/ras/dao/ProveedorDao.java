package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Proveedor;

public class ProveedorDao implements IProveedorDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProveedor(Proveedor Proveedor) {
		getSessionFactory().getCurrentSession().save(Proveedor);
	}

	@Override
	public void updateProveedor(Proveedor Proveedor) {
		getSessionFactory().getCurrentSession().update(Proveedor);
	}

	@Override
	public void deleteProveedor(Proveedor Proveedor) {
		getSessionFactory().getCurrentSession().delete(Proveedor);
	}

	@Override
	public Proveedor getProveedorById(int id) {
		@SuppressWarnings("unchecked")
		List<Proveedor> list=getSessionFactory().getCurrentSession()
				.createQuery("from Proveedor where id=? and Vigencia=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getCompras());
		return (Proveedor) list.get(0);
	}

	@Override
	public List<Proveedor> getProveedors() {
		@SuppressWarnings("unchecked")
		List<Proveedor> list=getSessionFactory().getCurrentSession()
				.createQuery("from Proveedor where Vigencia=1").list();
		for(Proveedor l:list){
			Hibernate.initialize(l.getCompras());
		}
		return list;
	}
}
