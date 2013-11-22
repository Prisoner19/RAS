package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Personal generated by hbm2java
 */
@Entity
@Table(name = "Personal", catalog = "ras")
public class Personal implements java.io.Serializable {

	private Integer idPersonal;
	private String nombre;
	private String profesion;
	private Date registro;
	private String celular;
	private String email;
	private boolean vigencia;
	private Set<Personalasignado> personalasignados = new HashSet<Personalasignado>(
			0);

	public Personal() {
	}

	public Personal(boolean vigencia) {
		this.vigencia = vigencia;
	}

	public Personal(String nombre, String profesion, Date registro,
			String celular, String email, boolean vigencia,
			Set<Personalasignado> personalasignados) {
		this.nombre = nombre;
		this.profesion = profesion;
		this.registro = registro;
		this.celular = celular;
		this.email = email;
		this.vigencia = vigencia;
		this.personalasignados = personalasignados;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPersonal", unique = true, nullable = false)
	public Integer getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	@Column(name = "Nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Profesion", length = 45)
	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Registro", length = 10)
	public Date getRegistro() {
		return this.registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Column(name = "Celular", length = 45)
	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "Email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Vigencia", nullable = false)
	public boolean isVigencia() {
		return this.vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personal")
	public Set<Personalasignado> getPersonalasignados() {
		return this.personalasignados;
	}

	public void setPersonalasignados(Set<Personalasignado> personalasignados) {
		this.personalasignados = personalasignados;
	}

}
