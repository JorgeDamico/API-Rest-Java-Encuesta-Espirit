package com.espirit.encuesta.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.espirit.encuesta.backend.model.Cliente;
import com.espirit.encuesta.backend.model.dao.IClienteDao;
import com.espirit.encuesta.backend.response.ClienteResponseRest;

@Service
public class ClienteServiceImpl implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClienteResponseRest>  getAllClientes() {
		
		log.info("Buscando todos los clientes");
		
		ClienteResponseRest response = new ClienteResponseRest();
		
		try {
			
			List<Cliente> cliente = (List<Cliente>) clienteDao.findAll();
			
			response.getClienteResponse().setCliente(cliente);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta correcta");
			
		}catch(Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar clientes");
			log.error(": ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK); // 200
	}	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ClienteResponseRest> getByIdCliente(Long id) {
		
		log.info("Buscando un cliente");
		
		ClienteResponseRest response = new ClienteResponseRest();
		
		List<Cliente> list = new ArrayList<>();
		
		try {
			
			Optional<Cliente> cliente = clienteDao.findById(id);
			
			if(cliente.isPresent()) {
				list.add(cliente.get());
				response.getClienteResponse().setCliente(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error al consultar cliente");
				response.setMetadata("Respuesta Nok", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al consultar cliente");
			e.getStackTrace(); 
			response.setMetadata("Respuesta Nok", "-1", "Error al consultar cliente");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> crearCliente(Cliente cliente) {
		
		log.info("Creando un cliente");
        
        ClienteResponseRest response = new ClienteResponseRest();
        
        List<Cliente> list = new ArrayList<>();
        
        try {
        	
        	Cliente clienteCreado = clienteDao.save(cliente);
        	
        	if(clienteCreado != null) {
        		list.add(clienteCreado);
        		response.getClienteResponse().setCliente(list);
        		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        	} else {
        		log.error("Error al crear cliente");
        		response.setMetadata("Respuesta nok", "-1", "Error al guardar cliente");
        		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
        	}
			
		} catch (Exception e) {
			log.error("Error en grabar cliente");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar cliente");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> actualizarCliente(Long id, Cliente cliente) {
		
		log.info("Actualizando cliente");
		
		ClienteResponseRest response = new ClienteResponseRest();
		
		List<Cliente> list = new ArrayList<>();
		
		try {
			
			Optional<Cliente> clienteBuscado = clienteDao.findById(id);
			
			if (clienteBuscado.isPresent()) {
				clienteBuscado.get().setNombre(cliente.getNombre());
				clienteBuscado.get().setNacimiento(cliente.getNacimiento());
				clienteBuscado.get().setGenero(cliente.getGenero());
				clienteBuscado.get().setProvincia(cliente.getProvincia());
				clienteBuscado.get().setValoracion(cliente.getValoracion());
				clienteBuscado.get().setRecomendacion(cliente.getRecomendacion());
				clienteBuscado.get().setComentario(cliente.getComentario());
				
				Cliente clienteActualizar = clienteDao.save(clienteBuscado.get());
				
				if(clienteActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Cliente actualizado");
				    list.add(clienteActualizar);
					response.getClienteResponse().setCliente(list);
				} else {
					log.error("error en actualizar cliente");
					response.setMetadata("Respuesta nok", "-1", "Cliente no actualizado");
					return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				log.error("error en actualizar cliente");
				response.setMetadata("Respuesta nok", "-1", "Cliente no actualizado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("error en actualizar cliente", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cliente no actualizado");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> eliminarCliente(Long id) {
		
		log.info("Eliminando un cliente");
		
		ClienteResponseRest response = new ClienteResponseRest();
		
		 try {
			 
			 Optional<Cliente> clienteBuscado = clienteDao.findById(id);
			 
			 if(clienteBuscado.isPresent()) {
				 //eliminamos el registro
				 clienteDao.deleteById(id);
				 response.setMetadata("Respuesta ok", "00", "Cliente eliminada");
			 } else {
				 log.error("El cliente no existe");
				 response.setMetadata("Respuesta nok", "-1", "Cliente no existe");
				 return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			 }
			 
		 } catch (Exception e) {
			log.error("error en eliminar cliente", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cliente no eliminado");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
		 
		 return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	
	}

}
