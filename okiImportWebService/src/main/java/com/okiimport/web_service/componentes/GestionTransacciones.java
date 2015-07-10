package com.okiimport.web_service.componentes;

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

import com.okiimport.web_service.mail.MailService;
import com.okiimport.web_service.modelo.Cliente;
import com.okiimport.web_service.modelo.DetalleRequerimiento;
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
	@GET
	@Path("/requerimientos/clientes/{cedula}")
	public Map<String, Object> consultarRequerimientos(
			@PathParam("cedula") String cedula,
			@DefaultValue("0") @QueryParam("pagina") Integer pagina,
			@QueryParam("limite") Integer limite){
		return sTransaccion.ConsultarRequerimientosCliente(null, null, null, cedula, pagina, limite);
	}
	
	@POST
	@Path("/requerimientos")
	public Map<String, Object> registrarRequerimiento(
			@RequestBody Requerimiento requerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		requerimiento=sTransaccion.registrarRequerimiento(requerimiento, sMaestros);
		parametros.put("requerimiento", requerimiento);
		if(requerimiento!=null){
			try {
				Cliente cliente = requerimiento.getCliente();
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("nroSolicitud", requerimiento.getIdRequerimiento());
				model.put("cliente", cliente.getNombre());
				model.put("cedula", cliente.getCedula());
				mailService.send(cliente.getCorreo(), "Registro de Requerimiento",
						"registrarRequerimiento.html", model);
			}
			catch(Exception e){
				return parametros;
			}
		}
		return parametros;
	}
	
	@POST
	@Path("/requerimientos/{idRequerimiento}/detalleRequerimiento")
	public Map<String, Object> registrarDetalleRequerimiento(
			@PathParam("idRequerimiento") int idRequerimiento,
			@RequestBody DetalleRequerimiento detalleRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		detalleRequerimiento=sTransaccion.registrarDetalleRequerimiento(idRequerimiento, detalleRequerimiento);
		parametros.put("detalleRequerimiento", detalleRequerimiento);
		return parametros;
	}

}
