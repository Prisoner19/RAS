package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Opcion;

public class OpcionDao implements IOpcionDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addOpcion(Opcion Opcion) {
		getSessionFactory().getCurrentSession().save(Opcion);
	}

	@Override
	public void updateOpcion(Opcion Opcion) {
		getSessionFactory().getCurrentSession().update(Opcion);
	}

	@Override
	public void deleteOpcion(Opcion Opcion) {
		getSessionFactory().getCurrentSession().delete(Opcion);
	}

	@Override
	public Opcion getOpcionById(int id) {
		@SuppressWarnings("unchecked")
		List<Opcion> list=getSessionFactory().getCurrentSession()
				.createQuery("from Opcion where id=? and Vigencia=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getRols());
		return (Opcion) list.get(0);
	}

	@Override
	public List<Opcion> getOpcions() {
		@SuppressWarnings("unchecked")
		List<Opcion> list=getSessionFactory().getCurrentSession()
				.createQuery("from Opcion where Vigencia=1").list();
		for(Opcion l:list){
			Hibernate.initialize(l.getRols());
		}
		return list;
	}
}