package com.okiimport.web_service.componentes;

import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okiimport.web_service.servicios.SMaestros;

@Component
@Path("gestionMaestros")
public class GestionMaestros {
	
	@Autowired
	private SMaestros sMaestros;

	/**ESTADOS**/
	@GET
	@Path("/estados")
	@Produces({ "application/json; charset=UTF-8" })
	public Map<String, Object> consultarEstados(
			@DefaultValue("0") @QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarEstado(pagina, limite);
	}
	
	@GET
	@Path("/estados/{idEstado}/ciudades")
	@Produces({ "application/json; charset=UTF-8" })
	public Map<String, Object> consultarCiudades(
			@PathParam("idEstado") int idEstado,
			@DefaultValue("0") @QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarCiudad(idEstado, pagina, limite);
	}
			

}
