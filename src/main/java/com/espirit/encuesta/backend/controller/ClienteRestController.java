package com.espirit.encuesta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.espirit.encuesta.backend.model.Cliente;
import com.espirit.encuesta.backend.response.ClienteResponseRest;
import com.espirit.encuesta.backend.service.IClienteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class ClienteRestController {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping("/clientes")
	public ResponseEntity<ClienteResponseRest>  consultaClientes() {
		ResponseEntity<ClienteResponseRest>  response = service.getAllClientes();
		return response;
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest>  consultaClienteById(@PathVariable Long id) {
		ResponseEntity<ClienteResponseRest>  response = service.getByIdCliente(id);
		return response;
	}
	
	@PostMapping(path = "/clientes", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteResponseRest> nuevoCliente(@RequestBody Cliente request){
		ResponseEntity<ClienteResponseRest> response = service.crearCliente(request);
		return response;
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest> actualizarCliente(@PathVariable Long id, @RequestBody Cliente request){
		ResponseEntity<ClienteResponseRest> response = service.actualizarCliente(id, request);
		return response;
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<ClienteResponseRest> eliminarCliente(@PathVariable Long id){
		ResponseEntity<ClienteResponseRest> response = service.eliminarCliente(id);
		return response;		
	}

}
