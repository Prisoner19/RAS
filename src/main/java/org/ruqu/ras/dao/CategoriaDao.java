package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Categoria;

public class CategoriaDao implements ICategoriaDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCategoria(Categoria Categoria) {
		getSessionFactory().getCurrentSession().save(Categoria);
	}

	@Override
	public void updateCategoria(Categoria Categoria) {
		getSessionFactory().getCurrentSession().update(Categoria);
	}

	@Override
	public void deleteCategoria(Categoria Categoria) {
		getSessionFactory().getCurrentSession().delete(Categoria);
	}

	@Override
	public Categoria getCategoriaById(int id) {
		@SuppressWarnings("unchecked")
		List<Categoria> list=getSessionFactory().getCurrentSession()
				.createQuery("from Categoria where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Categoria) list.get(0);
	}

	@Override
	public List<Categoria> getCategorias() {
		@SuppressWarnings("unchecked")
		List<Categoria> list=getSessionFactory().getCurrentSession()
				.createQuery("from Categoria where Vigencia=1").list();
		return list;
	}
}
