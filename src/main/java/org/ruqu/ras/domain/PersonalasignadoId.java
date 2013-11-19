package org.ruqu.ras.domain;

// Generated 14-nov-2013 0:10:23 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PersonalasignadoId generated by hbm2java
 */
@Embeddable
public class PersonalasignadoId implements java.io.Serializable {

	private int proyectoIdProyecto;
	private int personalIdPersonal;

	public PersonalasignadoId() {
	}

	public PersonalasignadoId(int proyectoIdProyecto, int personalIdPersonal) {
		this.proyectoIdProyecto = proyectoIdProyecto;
		this.personalIdPersonal = personalIdPersonal;
	}

	@Column(name = "Proyecto_idProyecto", nullable = false)
	public int getProyectoIdProyecto() {
		return this.proyectoIdProyecto;
	}

	public void setProyectoIdProyecto(int proyectoIdProyecto) {
		this.proyectoIdProyecto = proyectoIdProyecto;
	}

	@Column(name = "Personal_idPersonal", nullable = false)
	public int getPersonalIdPersonal() {
		return this.personalIdPersonal;
	}

	public void setPersonalIdPersonal(int personalIdPersonal) {
		this.personalIdPersonal = personalIdPersonal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PersonalasignadoId))
			return false;
		PersonalasignadoId castOther = (PersonalasignadoId) other;

		return (this.getProyectoIdProyecto() == castOther
				.getProyectoIdProyecto())
				&& (this.getPersonalIdPersonal() == castOther
						.getPersonalIdPersonal());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProyectoIdProyecto();
		result = 37 * result + this.getPersonalIdPersonal();
		return result;
	}

}
