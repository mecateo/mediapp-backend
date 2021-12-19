package com.mitocode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.mitocode.dto.MenuDTO;
import com.mitocode.model.Rol;

public interface IRolService extends ICRUD<Rol, Integer>{

	Page<Rol> listarPageable(Pageable page);
	
	Integer grabarUsuarioRol(Integer idUsuario, Integer idRol);
	
	Integer quitarUsuarioRol(Integer idUsuario, Integer idRol);
	
	List<MenuDTO> listarMenuPorIdRol(Integer idRol);
}
