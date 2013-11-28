package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Distrito;

public class DistritoDao implements IDistritoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addDistrito(Distrito Distrito) {
		getSessionFactory().getCurrentSession().save(Distrito);
	}

	@Override
	public void updateDistrito(Distrito Distrito) {
		getSessionFactory().getCurrentSession().update(Distrito);
	}

	@Override
	public void deleteDistrito(Distrito Distrito) {
		getSessionFactory().getCurrentSession().delete(Distrito);
	}

	@Override
	public Distrito getDistritoById(int id) {
		@SuppressWarnings("unchecked")
		List<Distrito> list=getSessionFactory().getCurrentSession()
				.createQuery("from Distrito where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Distrito) list.get(0);
	}

	@Override
	public List<Distrito> getDistritos() {
		@SuppressWarnings("unchecked")
		List<Distrito> list=getSessionFactory().getCurrentSession()
				.createQuery("from Distrito where Vigencia=1").list();
		return list;
	}
}
