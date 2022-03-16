package com.espirit.encuesta.backend.response;

import java.util.List;

import com.espirit.encuesta.backend.model.Cliente;

public class ClienteResponse {
	
	private List<Cliente> cliente;

	public List<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

}
