package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Partida;

public class PartidaDao implements IPartidaDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Partida partida) {
		getSessionFactory().getCurrentSession().save(partida);

	}

	@Override
	public void update(Partida partida) {
		getSessionFactory().getCurrentSession().update(partida);

	}

	@Override
	public void delete(Partida partida) {
		getSessionFactory().getCurrentSession().delete(partida);
		
	}

	@Override
	public Partida getById(int id) {
		Partida p=(Partida) getSessionFactory().getCurrentSession()
				.createQuery("from Partida where id=:idPartida "
						+ "and Vigencia=1")
				.setParameter("idPartida", id).uniqueResult();
		
		return p;
	}

	@Override
	public List<Partida> getAll() {
		@SuppressWarnings("unchecked")
		List<Partida> list = getSessionFactory().getCurrentSession()
				.createQuery("from Partida where Vigencia=1").list();
		
		return list;
	}

}
