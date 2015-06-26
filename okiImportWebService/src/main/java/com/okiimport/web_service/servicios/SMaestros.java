package com.okiimport.web_service.servicios;

import java.util.List;
import java.util.Map;

import com.okiimport.web_service.modelo.Analista;
import com.okiimport.web_service.modelo.Cliente;
import com.okiimport.web_service.modelo.Persona;
import com.okiimport.web_service.modelo.Proveedor;

public interface SMaestros {
	//Marcas
	public Map<String,Object> ConsultarMarca(Integer page,Integer limit);
	
	//Personas
	public <T extends Persona> T acutalizarPersona(T persona);
	
	//Cliente
	public Cliente registrarOActualizarCliente(Cliente cliente);
	public Cliente consultarCliente(Cliente cliente);

	//Analistas
	public Map<String, Object> consultarAnalistasSinUsuarios(Persona personaF,  String fieldSort, Boolean sortDirection, 
			int pagina, int limit);
	public Map<String, Object> consultarAdministradoresSinUsuarios(Persona personaF,  String fieldSort, Boolean sortDirection, 
			int pagina, int limit);
	public List<Analista> consultarCantRequerimientos(List<String> estatus, int page, int limit);
	public Map<String, Object> consultarAnalistas(Analista analista, int page, int limit);
	/*public Map<String, Object> consultarAnalistas(Analista analistaF, String fieldSort, Boolean sortDirection, 
			int pagina, int limit);*/
	public Analista registrarAnalista(Analista analista);
	
	
	//Proveedores
	public Map<String, Object> consultarProveedoresSinUsuarios(Persona personaF, String fieldSort, Boolean sortDirection,
			int pagina, int limit);
	
	public Map<String,Object> ConsultarClasificacionRepuesto(Integer page,Integer limit);
	
	public Proveedor registrarProveedor(Proveedor proveedor);
	
	public Map<String,Object> ConsultarMotor(Integer page,Integer limit);
	
	public Map<String, Object> ConsultarProveedoresListaClasificacionRepuesto(Persona persona, String fieldSort, Boolean sortDirection,
			List<Integer> idsClasificacionRepuesto, int start, int limit);
	
	//Estados
		public Map<String,Object> ConsultarEstado(Integer page,Integer limit);
		
    //Ciudades
	  public Map<String,Object> ConsultarCiudad(Integer idEstado,Integer page,Integer limit);
}
