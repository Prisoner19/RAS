package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Rol;

public class RolDao implements IRolDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addRol(Rol Rol) {
		getSessionFactory().getCurrentSession().save(Rol);
	}

	@Override
	public void updateRol(Rol Rol) {
		getSessionFactory().getCurrentSession().update(Rol);
	}

	@Override
	public void deleteRol(Rol Rol) {
		getSessionFactory().getCurrentSession().delete(Rol);
	}

	@Override
	public Rol getRolById(int id) {
		@SuppressWarnings("unchecked")
		List<Rol> list=getSessionFactory().getCurrentSession()
				.createQuery("from Rol where id=? and eliminacion=1")
				.setParameter(0, id).list();
		Hibernate.initialize(list.get(0).getOpcions());
		return (Rol) list.get(0);
	}

	@Override
	public List<Rol> getRols() {
		@SuppressWarnings("unchecked")
		List<Rol> list=getSessionFactory().getCurrentSession()
				.createQuery("from Rol where eliminacion=1").list();
		for(Rol l:list){
			Hibernate.initialize(l.getOpcions());
		}
		return list;
	}
}
