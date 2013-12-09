package org.ruqu.ras.domain;

// Generated 08-dic-2013 17:26:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Equipoasignado generated by hbm2java
 */
@Entity
@Table(name = "equipoasignado", catalog = "ras")
public class Equipoasignado implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EquipoasignadoId id;
	private Equipo equipo;
	private Proyecto proyecto;
	private Date fecha;
	private int cantidad;
	private BigDecimal precioUnit;

	public Equipoasignado() {
	}

	public Equipoasignado(EquipoasignadoId id, Equipo equipo,
			Proyecto proyecto, int cantidad, BigDecimal precioUnit) {
		this.id = id;
		this.equipo = equipo;
		this.proyecto = proyecto;
		this.cantidad = cantidad;
		this.precioUnit = precioUnit;
	}

	public Equipoasignado(EquipoasignadoId id, Equipo equipo,
			Proyecto proyecto, Date fecha, int cantidad, BigDecimal precioUnit) {
		this.id = id;
		this.equipo = equipo;
		this.proyecto = proyecto;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precioUnit = precioUnit;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "equipoIdEquipo", column = @Column(name = "Equipo_idEquipo", nullable = false)),
			@AttributeOverride(name = "proyectoIdProyecto", column = @Column(name = "Proyecto_idProyecto", nullable = false)) })
	public EquipoasignadoId getId() {
		return this.id;
	}

	public void setId(EquipoasignadoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Equipo_idEquipo", nullable = false, insertable = false, updatable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Proyecto_idProyecto", nullable = false, insertable = false, updatable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Fecha", length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "Cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "PrecioUnit", nullable = false, precision = 10)
	public BigDecimal getPrecioUnit() {
		return this.precioUnit;
	}

	public void setPrecioUnit(BigDecimal precioUnit) {
		this.precioUnit = precioUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipoasignado other = (Equipoasignado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
