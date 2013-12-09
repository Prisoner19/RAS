package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Equipoasignado;
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
		Proyecto proyecto=(Proyecto)getSessionFactory().getCurrentSession()
				.createQuery("from Proyecto where id=? and Vigencia=1")
				.setParameter(0, id).uniqueResult();
		if(proyecto!=null){
			Hibernate.initialize(proyecto.getEquipoasignados());
			for(Equipoasignado ea : proyecto.getEquipoasignados()){
				Hibernate.initialize(ea.getEquipo());
			}
		}
		return proyecto;
	}

	@Override
	public List<Proyecto> getProyectos() {
		@SuppressWarnings("unchecked")
		List<Proyecto> list=getSessionFactory().getCurrentSession()
				.createQuery("from Proyecto where Vigencia=1").list();
		
		return list;
	}
}
