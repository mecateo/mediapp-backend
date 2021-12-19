package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Menu;
import com.mitocode.model.Rol;

public interface IRolRepo extends IGenericRepo<Rol, Integer>{

	@Modifying
	@Query(value = "INSERT INTO usuario_rol(id_usuario, id_rol) VALUES (:idUsuario, :idRol)", nativeQuery = true)
	Integer registrar(@Param("idUsuario") Integer idUsuario, @Param("idRol") Integer idRol);
	
	@Modifying
	@Query(value = "DELETE FROM usuario_rol WHERE id_usuario=:idUsuario AND id_rol=:idRol", nativeQuery = true)
	Integer quitarUsuarioRol(@Param("idUsuario") Integer idUsuario, @Param("idRol") Integer idRol);
	
	@Query("SELECT m FROM Menu m INNER JOIN m.roles r WHERE r.idRol=:idRol")
	List<Menu> listarMenuPorIdRol(@Param("idRol") Integer idRol);
	
}
