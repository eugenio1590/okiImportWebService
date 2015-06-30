package com.okiimport.web_service.componentes;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.okiimport.web_service.mail.MailService;
import com.okiimport.web_service.modelo.Cliente;
import com.okiimport.web_service.modelo.Requerimiento;
import com.okiimport.web_service.servicios.SMaestros;
import com.okiimport.web_service.servicios.STransaccion;

@Component
@Path("gestionTransacciones")
@Produces({ "application/json; charset=UTF-8" })
public class GestionTransacciones {
	
	@Autowired
	protected MailService mailService;
	
	@Autowired
	private SMaestros sMaestros;

	@Autowired
	private STransaccion sTransaccion;
	
	//Requerimientos
	@POST
	@Path("/requerimientos")
	public Map<String, Object> registrarRequerimiento(
			@RequestBody Requerimiento requerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		requerimiento=sTransaccion.registrarRequerimiento(requerimiento, sMaestros);
		parametros.put("requerimiento", requerimiento);
		if(requerimiento!=null){
			Cliente cliente = requerimiento.getCliente();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("nroSolicitud", requerimiento.getIdRequerimiento());
			model.put("cliente", cliente.getNombre());
			model.put("cedula", cliente.getCedula());
			mailService.send(cliente.getCorreo(), "Registro de Requerimiento",
					"registrarRequerimiento.html", model);
		}
		return parametros;
	}

}
