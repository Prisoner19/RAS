package org.ruqu.ras.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;
import org.ruqu.ras.domain.Equipoasignado;
import org.ruqu.ras.domain.EquipoasignadoId;

public class EquipoasignadoDao implements IEquipoasignadoDao{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEquipoasignado(Equipoasignado Equipoasignado) {
		getSessionFactory().getCurrentSession().save(Equipoasignado);
	}

	@Override
	public void updateEquipoasignado(Equipoasignado Equipoasignado) {
		getSessionFactory().getCurrentSession().update(Equipoasignado);
	}

	@Override
	public void deleteEquipoasignado(Equipoasignado Equipoasignado) {
		getSessionFactory().getCurrentSession().delete(Equipoasignado);
	}

	@Override
	public Equipoasignado getEquipoasignadoById(EquipoasignadoId id) {
		@SuppressWarnings("unchecked")
		List<Equipoasignado> list=getSessionFactory().getCurrentSession()
				.createQuery("from Equipoasignado where id=? and Vigencia=1")
				.setParameter(0, id).list();
		return (Equipoasignado) list.get(0);
	}

	@Override
	public List<Equipoasignado> getEquipoasignados() {
		@SuppressWarnings("unchecked")
		List<Equipoasignado> list=getSessionFactory().getCurrentSession()
				.createQuery("from Equipoasignado where Vigencia=1").list();
		return list;
	}
	
	public BigDecimal getCostoRealEquipoasignados(int idProyecto){
		List<BigDecimal> list=getSessionFactory().getCurrentSession()
				.createQuery("select sum(ea.cantidad*ea.precioUnit) from Equipoasignado ea where Proyecto_idProyecto=?")
				.setParameter(0, idProyecto).list();
		return (BigDecimal)list.get(0);
	}
}
