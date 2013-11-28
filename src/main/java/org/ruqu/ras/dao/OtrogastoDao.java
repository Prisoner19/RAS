package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Otrogasto;

public class OtrogastoDao implements IOtrogastoDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addOtrogasto(Otrogasto Otrogasto) {
		getSessionFactory().getCurrentSession().save(Otrogasto);
	}

	@Override
	public void updateOtrogasto(Otrogasto Otrogasto) {
		getSessionFactory().getCurrentSession().update(Otrogasto);
	}

	@Override
	public void deleteOtrogasto(Otrogasto Otrogasto) {
		getSessionFactory().getCurrentSession().delete(Otrogasto);
	}

	@Override
	public Otrogasto getOtrogastoById(int id) {
		@SuppressWarnings("unchecked")
		List<Otrogasto> list=getSessionFactory().getCurrentSession()
				.createQuery("from Otrogasto where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Otrogasto) list.get(0);
	}

	@Override
	public List<Otrogasto> getOtrogastos() {
		@SuppressWarnings("unchecked")
		List<Otrogasto> list=getSessionFactory().getCurrentSession()
				.createQuery("from Otrogasto where Vigencia=1").list();
		return list;
	}
}
