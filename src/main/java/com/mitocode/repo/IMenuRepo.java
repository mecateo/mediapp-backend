package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Menu;

public interface IMenuRepo extends IGenericRepo<Menu, Integer>{

	@Query(value="select m.* from menu_rol mr inner join usuario_rol ur on ur.id_rol = mr.id_rol inner join menu m on m.id_menu = mr.id_menu inner join usuario u on u.id_usuario = ur.id_usuario where u.nombre = :nombre", nativeQuery = true)
	List<Menu> listarMenuPorUsuario(@Param("nombre") String nombre);

	@Modifying
	@Query(value = "DELETE FROM menu_rol WHERE id_menu=:idMenu AND id_rol=:idRol", nativeQuery = true)
	Integer quitarMenuRol(@Param("idMenu") Integer idMenu, @Param("idRol") Integer idRol);
	
	@Modifying
	@Query(value = "INSERT INTO menu_rol(id_menu, id_rol) VALUES (:idMenu, :idRol)", nativeQuery = true)
	Integer registrarMenuRol(@Param("idMenu") Integer idMenu, @Param("idRol") Integer idRol);
}
