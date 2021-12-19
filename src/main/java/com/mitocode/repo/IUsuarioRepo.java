package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.mitocode.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer>  {

	//from usuario where username = ?
	//Derived Query
	Usuario findOneByUsername(String username);	
	
	@Query("SELECT u FROM Usuario u WHERE u.enabled=true")
	List<Usuario> listarUsuariosActivos();
	
}
