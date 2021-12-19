package com.mitocode.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.dto.MenuDTO;
import com.mitocode.dto.PacienteDTO;
import com.mitocode.dto.RolDTO;
import com.mitocode.dto.UsuarioDTO;
import com.mitocode.dto.UsuarioRolDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Menu;
import com.mitocode.model.Paciente;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private IRolService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<RolDTO>> listar() throws Exception{				
		List<RolDTO> lista = service.listar().stream().map(p -> mapper.map(p, RolDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<RolDTO>> listarPageable(Pageable page) throws Exception{
		Page<RolDTO> pacientes = service.listarPageable(page).map(p -> mapper.map(p, RolDTO.class));
		
		return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<RolDTO> modificar(@RequestBody RolDTO dtoRequest) throws Exception {
		Rol rol = service.listarPorId(dtoRequest.getIdRol());
		
		if(rol == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdRol());	
		}
		
		Rol p = mapper.map(dtoRequest, Rol.class);
		 
		Rol obj = service.modificar(p);
		
		RolDTO dtoResponse = mapper.map(obj, RolDTO.class);
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Rol rol = service.listarPorId(id);
		
		if(rol == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody RolDTO dtoRequest) throws Exception{
		Rol r = mapper.map(dtoRequest, Rol.class);
		Rol obj = service.registrar(r);
		RolDTO dtoResponse = mapper.map(obj, RolDTO.class);
		//localhost:8080/pacientes/1
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RolDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		RolDTO dtoResponse;
		Rol obj = service.listarPorId(id); 		

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			dtoResponse = mapper.map(obj, RolDTO.class);
		}
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK); 		
	}
	
	@PostMapping("/agregarUsuarioRol")
	public ResponseEntity<Integer> agregarUsuarioRol(@RequestBody UsuarioRolDTO usuarioRol) throws Exception {
		Integer rpta = service.grabarUsuarioRol(usuarioRol.getIdUsuario(), usuarioRol.getIdRol());
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PostMapping("/quitarUsuarioRol")
	public ResponseEntity<Integer> aquitarUsuarioRol(@RequestBody UsuarioRolDTO usuarioRol) throws Exception {
		Integer rpta = service.quitarUsuarioRol(usuarioRol.getIdUsuario(), usuarioRol.getIdRol());
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping("/obtenerMenuPorIdRol/{idRol}")
	public ResponseEntity<List<MenuDTO>> obtenerMenuPorIdRol(@PathVariable("idRol") Integer idRol) throws Exception{
		List<MenuDTO> menu = service.listarMenuPorIdRol(idRol); 		
		return new ResponseEntity<>(menu, HttpStatus.OK); 		
	}
	
}
