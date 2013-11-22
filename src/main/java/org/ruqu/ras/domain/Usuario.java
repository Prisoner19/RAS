package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

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
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "Usuario", catalog = "ras")
public class Usuario implements java.io.Serializable {

	private Integer idUsuario;
	private Rol rol;
	private String login;
	private String password;
	private boolean vigencia;
	private Set<Logconsulta> logconsultas = new HashSet<Logconsulta>(0);

	public Usuario() {
	}

	public Usuario(Rol rol, String login, String password, boolean vigencia) {
		this.rol = rol;
		this.login = login;
		this.password = password;
		this.vigencia = vigencia;
	}

	public Usuario(Rol rol, String login, String password, boolean vigencia,
			Set<Logconsulta> logconsultas) {
		this.rol = rol;
		this.login = login;
		this.password = password;
		this.vigencia = vigencia;
		this.logconsultas = logconsultas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUsuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Rol_idRol", nullable = false)
	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Column(name = "Login", nullable = false, length = 45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "Password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Vigencia", nullable = false)
	public boolean isVigencia() {
		return this.vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Logconsulta> getLogconsultas() {
		return this.logconsultas;
	}

	public void setLogconsultas(Set<Logconsulta> logconsultas) {
		this.logconsultas = logconsultas;
	}

}
