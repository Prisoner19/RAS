package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DetallecompraId generated by hbm2java
 */
@Embeddable
public class DetallecompraId implements java.io.Serializable {

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DetallecompraId))
			return false;
		DetallecompraId castOther = (DetallecompraId) other;

		return (this.getCompraIdCompra() == castOther.getCompraIdCompra())
				&& (this.getEquipoIdEquipo() == castOther.getEquipoIdEquipo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCompraIdCompra();
		result = 37 * result + this.getEquipoIdEquipo();
		return result;
	}

}