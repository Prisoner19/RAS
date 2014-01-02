package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DetallecompraId generated by hbm2java
 */
@Embeddable
public class DetallecompraId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int compraIdCompra;
	private int equipoIdEquipo;

	public DetallecompraId() {
	}

	public DetallecompraId(int compraIdCompra, int equipoIdEquipo) {
		this.compraIdCompra = compraIdCompra;
		this.equipoIdEquipo = equipoIdEquipo;
	}

	@Column(name = "Compra_idCompra", nullable = false)
	public int getCompraIdCompra() {
		return this.compraIdCompra;
	}

	public void setCompraIdCompra(int compraIdCompra) {
		this.compraIdCompra = compraIdCompra;
	}

	@Column(name = "Equipo_idEquipo", nullable = false)
	public int getEquipoIdEquipo() {
		return this.equipoIdEquipo;
	}

	public void setEquipoIdEquipo(int equipoIdEquipo) {
		this.equipoIdEquipo = equipoIdEquipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + compraIdCompra;
		result = prime * result + equipoIdEquipo;
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
		DetallecompraId other = (DetallecompraId) obj;
		if (compraIdCompra != other.compraIdCompra)
			return false;
		if (equipoIdEquipo != other.equipoIdEquipo)
			return false;
		return true;
	}

	public String obtenerKey() {
		return Integer.toString(compraIdCompra) + "-" + Integer.toString(equipoIdEquipo);
	}

}
