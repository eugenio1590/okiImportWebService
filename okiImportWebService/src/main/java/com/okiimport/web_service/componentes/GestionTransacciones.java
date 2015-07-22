package com.okiimport.web_service.componentes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	@Path("/requerimientos/{idRequerimiento}")
	public Map<String, Object> consultarRequerimiento(@PathParam("idRequerimiento") Integer idRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		Requerimiento requerimiento = consultarRequrimiento(idRequerimiento);
		if(requerimiento!=null)
			parametros.put("requerimiento", requerimiento);
		else
			parametros.put("requerimiento", null);
		return parametros;
	}
	
	@GET
	@Path("/requerimientos/{idRequerimiento}/detallesRequerimientos")
	public Map<String, Object> consultarDetallesRequerimientos(@PathParam("idRequerimiento") Integer idRequerimiento){
		Map<String, Object> parametros = new HashMap<String, Object>();
		Requerimiento requerimiento = consultarRequrimiento(idRequerimiento);
		if(requerimiento!=null)
			parametros.put("detallesRequerimientos", requerimiento.getDetalleRequerimientos());
		else
			parametros.put("detallesRequerimientos", new ArrayList<DetalleRequerimiento>());
		return parametros;
	}
	
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
	
	/**METODOS PROPIOS DE LA CLASE*/
	public Requerimiento consultarRequrimiento(int idRequerimiento){
		Map<String, Object> mapRequermientos = sTransaccion.consultarRequerimientosGeneral(new Requerimiento(idRequerimiento), null, null, 0, 1);
		List<Requerimiento> requerimientos = (List<Requerimiento>) mapRequermientos.get("requerimientos");
		if(requerimientos!=null && requerimientos.size()>0)
			return requerimientos.get(0);
		else
			return null;
	}

}
