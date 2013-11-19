package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name = "proyecto", catalog = "ras")
public class Proyecto implements java.io.Serializable {

	private Integer idProyecto;
	private String nombre;
	private String descripcion;
	private Date inicio;
	private Date fin;
	private BigDecimal costoPersonal;
	private BigDecimal costoEquipo;
	private BigDecimal costoOtros;
	private Boolean cierre;
	private BigDecimal costoMaterialReal;
	private BigDecimal costoPersonalReal;
	private BigDecimal costoOtrosReal;
	private boolean vigencia;
	private Set<Personalasignado> personalasignados = new HashSet<Personalasignado>(
			0);
	private Set<Otrogasto> otrogastos = new HashSet<Otrogasto>(0);
	private Set<Equipo> equipos = new HashSet<Equipo>(0);
	private Set<Logconsulta> logconsultas = new HashSet<Logconsulta>(0);

	public Proyecto() {
	}

	public Proyecto(boolean vigencia) {
		this.vigencia = vigencia;
	}

	public Proyecto(String nombre, String descripcion, Date inicio, Date fin,
			BigDecimal costoPersonal, BigDecimal costoEquipo,
			BigDecimal costoOtros, Boolean cierre,
			BigDecimal costoMaterialReal, BigDecimal costoPersonalReal,
			BigDecimal costoOtrosReal, boolean vigencia,
			Set<Personalasignado> personalasignados, Set<Otrogasto> otrogastos,
			Set<Equipo> equipos, Set<Logconsulta> logconsultas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.inicio = inicio;
		this.fin = fin;
		this.costoPersonal = costoPersonal;
		this.costoEquipo = costoEquipo;
		this.costoOtros = costoOtros;
		this.cierre = cierre;
		this.costoMaterialReal = costoMaterialReal;
		this.costoPersonalReal = costoPersonalReal;
		this.costoOtrosReal = costoOtrosReal;
		this.vigencia = vigencia;
		this.personalasignados = personalasignados;
		this.otrogastos = otrogastos;
		this.equipos = equipos;
		this.logconsultas = logconsultas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idProyecto", unique = true, nullable = false)
	public Integer getIdProyecto() {
		return this.idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	@Column(name = "Nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion", length = 145)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Inicio", length = 10)
	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Fin", length = 10)
	public Date getFin() {
		return this.fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	@Column(name = "CostoPersonal", precision = 10)
	public BigDecimal getCostoPersonal() {
		return this.costoPersonal;
	}

	public void setCostoPersonal(BigDecimal costoPersonal) {
		this.costoPersonal = costoPersonal;
	}

	@Column(name = "CostoEquipo", precision = 10)
	public BigDecimal getCostoEquipo() {
		return this.costoEquipo;
	}

	public void setCostoEquipo(BigDecimal costoEquipo) {
		this.costoEquipo = costoEquipo;
	}

	@Column(name = "CostoOtros", precision = 10)
	public BigDecimal getCostoOtros() {
		return this.costoOtros;
	}

	public void setCostoOtros(BigDecimal costoOtros) {
		this.costoOtros = costoOtros;
	}

	@Column(name = "Cierre")
	public Boolean getCierre() {
		return this.cierre;
	}

	public void setCierre(Boolean cierre) {
		this.cierre = cierre;
	}

	@Column(name = "CostoMaterialReal", precision = 10)
	public BigDecimal getCostoMaterialReal() {
		return this.costoMaterialReal;
	}

	public void setCostoMaterialReal(BigDecimal costoMaterialReal) {
		this.costoMaterialReal = costoMaterialReal;
	}

	@Column(name = "CostoPersonalReal", precision = 10)
	public BigDecimal getCostoPersonalReal() {
		return this.costoPersonalReal;
	}

	public void setCostoPersonalReal(BigDecimal costoPersonalReal) {
		this.costoPersonalReal = costoPersonalReal;
	}

	@Column(name = "CostoOtrosReal", precision = 10)
	public BigDecimal getCostoOtrosReal() {
		return this.costoOtrosReal;
	}

	public void setCostoOtrosReal(BigDecimal costoOtrosReal) {
		this.costoOtrosReal = costoOtrosReal;
	}

	@Column(name = "Vigencia", nullable = false)
	public boolean isVigencia() {
		return this.vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Personalasignado> getPersonalasignados() {
		return this.personalasignados;
	}

	public void setPersonalasignados(Set<Personalasignado> personalasignados) {
		this.personalasignados = personalasignados;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Otrogasto> getOtrogastos() {
		return this.otrogastos;
	}

	public void setOtrogastos(Set<Otrogasto> otrogastos) {
		this.otrogastos = otrogastos;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipoasignado", catalog = "ras", joinColumns = { @JoinColumn(name = "Proyecto_idProyecto", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "Equipo_idEquipo", nullable = false, updatable = false) })
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Logconsulta> getLogconsultas() {
		return this.logconsultas;
	}

	public void setLogconsultas(Set<Logconsulta> logconsultas) {
		this.logconsultas = logconsultas;
	}

}