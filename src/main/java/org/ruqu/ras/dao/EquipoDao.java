package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Equipo;

public class EquipoDao implements IEquipoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEquipo(Equipo Equipo) {
		getSessionFactory().getCurrentSession().save(Equipo);
	}

	@Override
	public void updateEquipo(Equipo Equipo) {
		getSessionFactory().getCurrentSession().update(Equipo);
	}

	@Override
	public void deleteEquipo(Equipo Equipo) {
		getSessionFactory().getCurrentSession().delete(Equipo);
	}

	@Override
	public Equipo getEquipoById(int id) {
		@SuppressWarnings("unchecked")
		List<Equipo> list=getSessionFactory().getCurrentSession()
				.createQuery("from Equipo where id=? and Vigencia=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getCategoria());		
		Hibernate.initialize(list.get(0).getDetallecompras());
		//Hibernate.initialize(list.get(0).getLogconsultas());
		return (Equipo) list.get(0);
	}

	@Override
	public List<Equipo> getEquipos() {
		@SuppressWarnings("unchecked")
		List<Equipo> list=getSessionFactory().getCurrentSession()
				.createQuery("from Equipo where Vigencia=1").list();
		for(Equipo l:list){
			Hibernate.initialize(l.getCategoria());		
			Hibernate.initialize(l.getDetallecompras());
			//Hibernate.initialize(l.getLogconsultas());
		}
		return list;
	}
}
