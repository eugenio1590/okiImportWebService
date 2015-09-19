package com.okiimport.web_service.componentes;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okiimport.app.model.Cliente;
import com.okiimport.app.service.maestros.SMaestros;

@Controller
@RequestMapping("/rest/gestionMaestros")
public class GestionMaestros extends AbstractController{
	
	@Autowired
	private SMaestros sMaestros;

	/**ESTADOS**/
	@RequestMapping(value = "/estados", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarEstados(
			@QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarEstado(defaultPage(pagina), defaultLimit(limite));
	}
	
	@RequestMapping(value = "/estados/{idEstado}/ciudades", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarCiudades(
			@PathVariable("idEstado") int idEstado,
			@QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.ConsultarCiudad(idEstado, defaultPage(pagina), defaultLimit(limite));
	}
			
	/**MARCAS*/
	@RequestMapping(value = "/marcas/vehiculos", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarMarcas(
			@QueryParam("pagina") Integer pagina, 
			@QueryParam("limite") Integer limite){
		return sMaestros.consultarMarcas(defaultPage(pagina), defaultLimit(limite));
	}
	
	/**CLIENTES*/
	@RequestMapping(value = "/clientes/{cedula}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> consultarCliente(@PathVariable("cedula") String cedula){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cliente", sMaestros.consultarCliente(new Cliente(cedula)));
		return parametros;
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> registrarCliente(@RequestBody Cliente cliente){
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cliente", sMaestros.registrarOActualizarCliente(cliente));
		return parametros;
	}
}
