package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Personalasignado;
import org.ruqu.ras.domain.PersonalasignadoId;

public class PersonalasignadoDao implements IPersonalasignadoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPersonalasignado(Personalasignado Personalasignado) {
		getSessionFactory().getCurrentSession().save(Personalasignado);
	}

	@Override
	public void updatePersonalasignado(Personalasignado Personalasignado) {
		getSessionFactory().getCurrentSession().update(Personalasignado);
	}

	@Override
	public void deletePersonalasignado(Personalasignado Personalasignado) {
		getSessionFactory().getCurrentSession().delete(Personalasignado);
	}

	@Override
	public Personalasignado getPersonalasignadoById(PersonalasignadoId id) {
		@SuppressWarnings("unchecked")
		List<Personalasignado> list=getSessionFactory().getCurrentSession()
				.createQuery("from Personalasignado where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Personalasignado) list.get(0);
	}

	@Override
	public List<Personalasignado> getPersonalasignados() {
		@SuppressWarnings("unchecked")
		List<Personalasignado> list=getSessionFactory().getCurrentSession()
				.createQuery("from Personalasignado where Vigencia=1").list();
		return list;
	}
}
