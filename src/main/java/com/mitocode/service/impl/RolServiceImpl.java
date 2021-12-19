package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.MenuDTO;
import com.mitocode.model.Rol;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRolRepo;
import com.mitocode.service.IRolService;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService{

	@Autowired
	private IRolRepo repo;

	@Override
	public Page<Rol> listarPageable(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	protected IGenericRepo<Rol, Integer> getRepo() {
		return repo;
	}

	@Transactional
	@Override
	public Integer grabarUsuarioRol(Integer idUsuario, Integer idRol) {
		return repo.registrar(idUsuario, idRol);
	}

	@Transactional
	@Override
	public Integer quitarUsuarioRol(Integer idUsuario, Integer idRol) {
		return repo.quitarUsuarioRol(idUsuario, idRol);
	}

	@Override
	public List<MenuDTO> listarMenuPorIdRol(Integer idRol) {
		List<MenuDTO> menus = new ArrayList<>();
		repo.listarMenuPorIdRol(idRol).forEach(x -> {
			MenuDTO m = new MenuDTO();
			m.setIdMenu(x.getIdMenu());
			m.setNombre(x.getNombre());
			menus.add(m);
		});
		return menus;
	}

}
