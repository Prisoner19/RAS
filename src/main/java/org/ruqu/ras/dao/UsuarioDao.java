package org.ruqu.ras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Usuario;

public class UsuarioDao implements IUsuarioDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUsuario(Usuario Usuario) {
		getSessionFactory().getCurrentSession().save(Usuario);
	}

	@Override
	public void updateUsuario(Usuario Usuario) {
		getSessionFactory().getCurrentSession().update(Usuario);
	}

	@Override
	public void deleteUsuario(Usuario Usuario) {
		getSessionFactory().getCurrentSession().delete(Usuario);
	}

	@Override
	public Usuario getUsuarioById(int id) {
		@SuppressWarnings("unchecked")
		List<Usuario> list=getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where id=? and Vigencia=1")
				.setParameter(0, id).list();
		
		//Hibernate.initialize(list.get(0).getLogconsultas());
		return (Usuario) list.get(0);
	}

	@Override
	public List<Usuario> getUsuarios() {
		@SuppressWarnings("unchecked")
		List<Usuario> list=getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where Vigencia=1").list();
		
		return list;
	}

    @Override
    public Usuario getByUsuario(String username) {
        @SuppressWarnings("unchecked")
        List<Usuario> list = sessionFactory.getCurrentSession()
                .createQuery("from Usuario where login=?").setParameter(0, username).list();
        Usuario u = list.get(0);


        return u ;
    }
}
