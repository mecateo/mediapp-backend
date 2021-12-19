package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.MenuDTO;
import com.mitocode.dto.MenuRolDTO;
import com.mitocode.dto.PacienteDTO;
import com.mitocode.dto.UsuarioRolDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Menu;
import com.mitocode.model.Paciente;
import com.mitocode.service.IMenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IMenuService service;
	
	@GetMapping
	public ResponseEntity<List<MenuDTO>> listar() throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = service.listar();
		List<MenuDTO> menusDTO = modelMapper.map(menus, new TypeToken<List<MenuDTO>>() {}.getType());
		return new ResponseEntity<>(menusDTO, HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<List<MenuDTO>> listar(@RequestBody String nombre) throws Exception{
		List<Menu> menus = new ArrayList<>();
		//Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();
		//menus = service.listarMenuPorUsuario(usuarioLogueado.getName());
		menus = service.listarMenuPorUsuario(nombre);
		List<MenuDTO> menusDTO = modelMapper.map(menus, new TypeToken<List<MenuDTO>>() {}.getType());
		return new ResponseEntity<>(menusDTO, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<MenuDTO>> listarPageable(Pageable page) throws Exception{
		Page<MenuDTO> menus = service.listarPageable(page).map(p -> modelMapper.map(p, MenuDTO.class));
		
		return new ResponseEntity<>(menus, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MenuDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		MenuDTO dtoResponse;
		Menu obj = service.listarPorId(id);

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			dtoResponse = modelMapper.map(obj, MenuDTO.class);
		}
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK); 		
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody MenuDTO dtoRequest) throws Exception{
		Menu p = modelMapper.map(dtoRequest, Menu.class);
		Menu obj = service.registrar(p);
		MenuDTO dtoResponse = modelMapper.map(obj, MenuDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenu()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<MenuDTO> modificar(@RequestBody MenuDTO dtoRequest) throws Exception {
		Menu menu = service.listarPorId(dtoRequest.getIdMenu());
		
		if(menu == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdMenu());	
		}
		
		Menu m = modelMapper.map(dtoRequest, Menu.class);
		 
		Menu obj = service.modificar(m);
		
		MenuDTO dtoResponse = modelMapper.map(obj, MenuDTO.class);
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Menu menu = service.listarPorId(id);
		
		if(menu == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/agregarMenuRol")
	public ResponseEntity<Integer> agregarMenuRol(@RequestBody MenuRolDTO menuRol) throws Exception {
		Integer rpta = service.grabarMenuRol(menuRol.getIdMenu(), menuRol.getIdRol());
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PostMapping("/quitarMenuRol")
	public ResponseEntity<Integer> aquitarMenuRol(@RequestBody MenuRolDTO menuRol) throws Exception {
		Integer rpta = service.quitarMenuRol(menuRol.getIdMenu(), menuRol.getIdRol());
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

}
