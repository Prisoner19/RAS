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
				.createQuery("from Rol where vigencia=1").list();
		for(Rol l:list){
			Hibernate.initialize(l.getOpcions());
		}
		return list;
	}

    @Override
    public List<Rol> getRolesUserbyId(int id){
        @SuppressWarnings("unchecked")
        List<Rol> list = sessionFactory.getCurrentSession()
                .createQuery("select new Rol(r.id,concat('ROLE_',r.descripcion)) from Usuario as ur "
                        + "inner join ur.rol as r "
                        + "where ur.id=? ").
                        setParameter(0, id).list();

        return list;
    }

    @Override
    public List<String> getRolesMenuByRuta(String ruta){
        @SuppressWarnings("unchecked")
        List<String> list = sessionFactory.getCurrentSession()
                .createQuery("select concat('ROLE_',r.descripcion) "
                        + "from Opcion as op "
                        + "inner join op.rols as r "
                        + "where op.descripcion=? "
                        ).
                        setParameter(0, ruta).list();

        return list;
    }
}
