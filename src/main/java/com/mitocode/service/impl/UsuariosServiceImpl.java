package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IUsuarioRepo;
import com.mitocode.service.IUsuarioService;

@Service
public class UsuariosServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	public List<Usuario> listarUsuariosActivos() {
		return repo.listarUsuariosActivos();
	}

	@Override
	protected IGenericRepo<Usuario, Integer> getRepo() {
		return repo;
	}

	@Override
	public Usuario listarUsuarioById(Integer idUsuario) {
		return repo.findById(idUsuario).orElse(null);
	}

}
