package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Personal;

public class PersonalDao implements IPersonalDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPersonal(Personal Personal) {
		getSessionFactory().getCurrentSession().save(Personal);
	}

	@Override
	public void updatePersonal(Personal Personal) {
		getSessionFactory().getCurrentSession().update(Personal);
	}

	@Override
	public void deletePersonal(Personal Personal) {
		getSessionFactory().getCurrentSession().delete(Personal);
	}

	@Override
	public Personal getPersonalById(int id) {
		@SuppressWarnings("unchecked")
		List<Personal> list=getSessionFactory().getCurrentSession()
				.createQuery("from Personal where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Personal) list.get(0);
	}

	@Override
	public List<Personal> getPersonals() {
		@SuppressWarnings("unchecked")
		List<Personal> list=getSessionFactory().getCurrentSession()
				.createQuery("from Personal where Vigencia=1").list();
		return list;
	}
}
