package com.krakedev.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Cliente;
import com.krakedev.exepciones.KrakedevExeption;
import com.krakedev.persistencia.clietesBDD;

@Path("Clientes")

public class ServiciosClientes {
	@Path("metodo1")
	@GET
	public String saludar() {
		return "hola mundo web services";
	}

	@Path("Buscar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente buscar() {
		Cliente cliente = new Cliente("1754247722", "Romel");

		return cliente;
	}

	@Path("inser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Cliente cliente) {
		System.out.println("<<<<<<" + cliente);
		clietesBDD cli = new clietesBDD();
		try {
			cli.insertar(cliente);
			return Response.ok().build();
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("act")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) {
		System.err.println(" ACTULAIZAR" + cliente);
		clietesBDD cli = new clietesBDD();

		try {
			cli.actualizar(cliente);
			return Response.ok().build();
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Path("All")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerCliente() {
		clietesBDD cli = new clietesBDD();
		ArrayList<Cliente> clientes = null;
		try {
			clientes = cli.recuperarTodos();
			return Response.ok(clientes).build();
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			return Response.serverError().build();

		}

	}
	
	@Path("buscarPorCedula/{cedulaParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorCedula(@ PathParam("cedulaParam")String cedula) {
		clietesBDD cli = new clietesBDD();
		Cliente cliente=null;
		
		try {
			cliente = cli.buscarPorPK(cedula);
			return Response.ok(cliente).build();
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			return Response.serverError().build();

		}
		
		@Path("buscarConMasHijos/{numeroHijosParam}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response buscarConMasHijos(@ PathParam("numeroHijosParam")int numeroHijos) {
			clietesBDD cli = new clietesBDD();
		
			ArrayList<Cliente> clientes = null;
			
			try {
				clientes = cli.buscaConMasHijos(numeroHijos);
				return Response.ok(clientes).build();
			} catch (KrakedevExeption e) {
				e.printStackTrace();
				return Response.serverError().build();

			}

	}

	
	

}
