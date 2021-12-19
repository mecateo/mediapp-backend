package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Usuario;

public interface IUsuarioService  extends ICRUD<Usuario, Integer>{
	
	List<Usuario> listarUsuariosActivos();
	
	Usuario listarUsuarioById(Integer idUsuario);

}
