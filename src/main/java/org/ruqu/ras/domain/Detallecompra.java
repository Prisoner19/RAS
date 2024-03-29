package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Detallecompra generated by hbm2java
 */
@Entity
@Table(name = "DetalleCompra", catalog = "ras")
public class Detallecompra implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DetallecompraId id;
	private Compra compra;
	private Equipo equipo;
	private Integer cantidad;
	private BigDecimal totalDetalle;

	public Detallecompra() {
	}

	public Detallecompra(DetallecompraId id, Compra compra, Equipo equipo) {
		this.id = id;
		this.compra = compra;
		this.equipo = equipo;
	}

	public Detallecompra(DetallecompraId id, Compra compra, Equipo equipo,
			Integer cantidad, BigDecimal totalDetalle) {
		this.id = id;
		this.compra = compra;
		this.equipo = equipo;
		this.cantidad = cantidad;
		this.totalDetalle = totalDetalle;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "compraIdCompra", column = @Column(name = "Compra_idCompra", nullable = false)),
			@AttributeOverride(name = "equipoIdEquipo", column = @Column(name = "Equipo_idEquipo", nullable = false)) })
	public DetallecompraId getId() {
		return this.id;
	}

	public void setId(DetallecompraId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Compra_idCompra", nullable = false, insertable = false, updatable = false)
	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Equipo_idEquipo", nullable = false, insertable = false, updatable = false)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Column(name = "Cantidad")
	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "TotalDetalle", precision = 10)
	public BigDecimal getTotalDetalle() {
		return this.totalDetalle;
	}

	public void setTotalDetalle(BigDecimal totalDetalle) {
		this.totalDetalle = totalDetalle;
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
		Detallecompra other = (Detallecompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
