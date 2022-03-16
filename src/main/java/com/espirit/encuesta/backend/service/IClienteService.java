package com.espirit.encuesta.backend.service;

import org.springframework.http.ResponseEntity;

import com.espirit.encuesta.backend.model.Cliente;
import com.espirit.encuesta.backend.response.ClienteResponseRest;

public interface IClienteService {
	
	public ResponseEntity<ClienteResponseRest>  getAllClientes();
	
	public ResponseEntity<ClienteResponseRest> getByIdCliente(Long id);
	
	public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente);
	
	public ResponseEntity<ClienteResponseRest> actualizarCliente(Long id, Cliente cliente);
	
	public ResponseEntity<ClienteResponseRest> eliminarCliente(Long id);

}
