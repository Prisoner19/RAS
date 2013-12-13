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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Categoria generated by hbm2java
 */
@Entity
@Table(name = "Categoria", catalog = "ras")
public class Categoria implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	private String nombre;
	private String descripcion;
	private boolean vigencia=true;
	private Set<Equipo> equipos = new HashSet<Equipo>(0);

	public Categoria() {
	}

	public Categoria(boolean vigencia) {
		this.vigencia = vigencia;
	}

	public Categoria(String nombre, String descripcion, boolean vigencia,
			Set<Equipo> equipos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.vigencia = vigencia;
		this.equipos = equipos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCategoria", unique = true, nullable = false)
	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	@Column(name = "Vigencia", nullable = false)
	public boolean isVigencia() {
		return this.vigencia;
	}

	public void setVigencia(boolean vigencia) {
		this.vigencia = vigencia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idCategoria + "";
	}
	
	

}
