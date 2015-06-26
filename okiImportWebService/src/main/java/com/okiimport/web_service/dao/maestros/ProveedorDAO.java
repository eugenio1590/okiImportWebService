package com.okiimport.web_service.dao.maestros;

import java.util.List;

import com.okiimport.web_service.modelo.Persona;
import com.okiimport.web_service.modelo.Proveedor;

public interface ProveedorDAO extends PersonaDAO<Proveedor> {
	public List<Proveedor> consultarProveedoresSinUsuarios(Persona persona,  String fieldSort, Boolean sortDirection, 
			int start, int limit);
	public List<Proveedor> consultarProveedoresListaClasificacionRepuesto(Persona persona, String fieldSort, Boolean sortDirection,
			List<Integer> idsClasificacionRepuesto, int start, int limit);
}
