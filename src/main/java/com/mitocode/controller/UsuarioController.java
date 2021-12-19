package com.mitocode.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.PacienteDTO;
import com.mitocode.dto.UsuarioDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosActivos() throws Exception{				
		List<UsuarioDTO> lista = service.listarUsuariosActivos().stream().map(p -> mapper.map(p, UsuarioDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> listarUsuariosById(@PathVariable("id") Integer id) throws Exception{				
		UsuarioDTO dtoResponse;
		Usuario obj = service.listarPorId(id);		

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			dtoResponse = mapper.map(obj, UsuarioDTO.class);
		}
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK); 	
	}
}
