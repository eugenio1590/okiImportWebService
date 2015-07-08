package com.okiimport.web_service.componentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.okiimport.web_service.modelo.Cliente;
import com.okiimport.web_service.modelo.MarcaVehiculo;
import com.okiimport.web_service.servicios.SMaestros;

@Component
@Path("gestionMaestros")
@Produces({ "application/json; charset=UTF-8" })
public class GestionMaestros {
	
	@Autowired
	private SMaestros sMaestros;

	/**ESTADOS**/
	@GET
	@Path("/estados")
	public Map<String, Object> consultarEstados(
			@DefaultValue("0") @QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarEstado(pagina, limite);
	}
	
	@GET
	@Path("/estados/{idEstado}/ciudades")
	public Map<String, Object> consultarCiudades(
			@PathParam("idEstado") int idEstado,
			@DefaultValue("0") @QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarCiudad(idEstado, pagina, limite);
	}
			
	/**MARCAS*/
	@GET
	@Path("/marcas/vehiculos")
	public Map<String, Object> consultarMarcas(
			@DefaultValue("0") @QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarMarcas(pagina, limite);
	}
	
	/**CLIENTES*/
	@GET
	@Path("/clientes/{cedula}")
	public Map<String, Object> consultarCliente(@PathParam("cedula") String cedula){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cliente", sMaestros.consultarCliente(new Cliente(cedula)));
		return parametros;
	}
	
	@POST
	@Path("/clientes")
	public Map<String, Object> registrarCliente(@RequestBody Cliente cliente){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cliente", sMaestros.registrarOActualizarCliente(cliente));
		return parametros;
	}
}
