package org.ruqu.ras.domain;

// Generated 08-dic-2013 17:26:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Equipo generated by hbm2java
 */
@Entity
@Table(name = "equipo", catalog = "ras")
public class Equipo implements java.io.Serializable {

	private Integer idEquipo;
	private Categoria categoria;
	private String codigo;
	private String nombre;
	private String descripcion;
	private BigDecimal costo;
	private Integer stock;
	private boolean vigencia;
	private Set<Logconsulta> logconsultas = new HashSet<Logconsulta>(0);
	private Set<Detallecompra> detallecompras = new HashSet<Detallecompra>(0);
	private Set<Equipoasignado> equipoasignados = new HashSet<Equipoasignado>(0);

	public Equipo() {
	}

	public Equipo(Categoria categoria, boolean vigencia) {
		this.categoria = categoria;
		this.vigencia = vigencia;
	}

	public Equipo(Categoria categoria, String codigo, String nombre,
			String descripcion, BigDecimal costo, Integer stock,
			boolean vigencia, Set<Logconsulta> logconsultas,
			Set<Detallecompra> detallecompras,
			Set<Equipoasignado> equipoasignados) {
		this.categoria = categoria;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.stock = stock;
		this.vigencia = vigencia;
		this.logconsultas = logconsultas;
		this.detallecompras = detallecompras;
		this.equipoasignados = equipoasignados;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idEquipo", unique = true, nullable = false)
	public Integer getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Categoria_idCategoria", nullable = false)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "Codigo", length = 45)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion", length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Costo", precision = 10)
	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	@Column(name = "Stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "Vigencia", nullable = false)
	public boolean isVigencia() {
		return this.vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
	public Set<Logconsulta> getLogconsultas() {
		return this.logconsultas;
	}

	public void setLogconsultas(Set<Logconsulta> logconsultas) {
		this.logconsultas = logconsultas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
	public Set<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(Set<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo")
	public Set<Equipoasignado> getEquipoasignados() {
		return this.equipoasignados;
	}

	public void setEquipoasignados(Set<Equipoasignado> equipoasignados) {
		this.equipoasignados = equipoasignados;
	}

}

