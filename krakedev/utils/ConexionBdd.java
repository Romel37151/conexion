package com.krakedev.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.krakedev.exepciones.KrakedevExeption;

public class ConexionBdd {

	public static Connection obtenerConexion() throws KrakedevExeption {
		Context ctx = null;
		DataSource ds = null;
		Connection con = null;
		try {
			ctx = new InitialContext();
			// JNDI
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/PgDB");
			con = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			throw new KrakedevExeption("Error de conexion");
			
		}
		
		return con;
	}

}
