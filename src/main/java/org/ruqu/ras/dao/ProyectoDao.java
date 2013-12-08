package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Proyecto;

public class ProyectoDao implements IProyectoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProyecto(Proyecto Proyecto) {
		getSessionFactory().getCurrentSession().save(Proyecto);
	}

	@Override
	public void updateProyecto(Proyecto Proyecto) {
		getSessionFactory().getCurrentSession().update(Proyecto);
	}

	@Override
	public void deleteProyecto(Proyecto Proyecto) {
		getSessionFactory().getCurrentSession().delete(Proyecto);
	}

	@Override
	public Proyecto getProyectoById(int id) {
		@SuppressWarnings("unchecked")
		List<Proyecto> list=getSessionFactory().getCurrentSession()
				.createQuery("from Proyecto where id=? and Vigencia=1")
				.setParameter(0, id).list();		
		//Hibernate.initialize(list.get(0).getLogconsultas());
		return (Proyecto) list.get(0);
	}

	@Override
	public List<Proyecto> getProyectos() {
		@SuppressWarnings("unchecked")
		List<Proyecto> list=getSessionFactory().getCurrentSession()
				.createQuery("from Proyecto where Vigencia=1").list();
		for(Proyecto l:list){			
			//Hibernate.initialize(l.getLogconsultas());
		}
		return list;
	}
}
