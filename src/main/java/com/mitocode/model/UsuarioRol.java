package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUsuarioRol;
	
	@Column(name = "id_usuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name = "id_rol", nullable = false)
	private Integer idRol;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
}
