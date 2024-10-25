package com.krakedev.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Cliente;
import com.krakedev.exepciones.KrakedevExeption;
import com.krakedev.utils.ConexionBdd;

public class clietesBDD {
	public void insertar(Cliente cliente) throws KrakedevExeption  {
		Connection con= null;
		try {
			
				con=ConexionBdd.obtenerConexion();
			
			PreparedStatement ps=
					con.prepareStatement("insert into clientes(cedula,nombre,numeroHijos) values(?,?,?)");
			
			ps.setString(1,cliente.getCedula());
			ps.setString(2,cliente.getNombre());
			ps.setInt(3, cliente.getNumeroHijos());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevExeption("Error al insertar el cliente  detalle"+e.getMessage());
		}catch (KrakedevExeption e) {
				e.printStackTrace();
				throw e;
			}finally {
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
			}
	
	}
	public void actualizar(Cliente cliente) throws KrakedevExeption  {
		Connection con= null;
		try {
			
				con=ConexionBdd.obtenerConexion();
			
			PreparedStatement ps=
					con.prepareStatement("update clientes set nombre=?,numeroHijos=?  where cedula=?");
			
			ps.setString(1, cliente.getNombre());
			ps.setInt(2,cliente.getNumeroHijos());
			ps.setString(3, cliente.getCedula());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevExeption("Error al actulalizar el cliente  detalle"+e.getMessage());
		}catch (KrakedevExeption e) {
				e.printStackTrace();
				throw e;
			}finally {
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
				}
			}
	
	}
	public ArrayList<Cliente> recuperarTodos() throws KrakedevExeption{
		
		ArrayList<Cliente> cliente= new ArrayList<Cliente>();
		
		Connection con= null;
		PreparedStatement ps= null;
		ResultSet rs= null;
		Cliente cl=null;
		try {
			con=ConexionBdd.obtenerConexion();
				ps=con.prepareStatement("select * from clientes");
				rs=ps.executeQuery();
				
				while(rs.next()) {
					String cedula=rs.getString("cedula");
					String nombre=rs.getString("nombre");
					int numeroHijos=rs.getInt("numeroHijos");
					cl= new Cliente(cedula,nombre,numeroHijos);
					cliente.add(cl);
				}
			
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			throw e;
		}catch (SQLException e) {
				e.printStackTrace();
				throw new KrakedevExeption("Error al consultar"+e.getMessage());
			}
		
		return cliente;
	}

public Cliente buscarPorPK(String cedulaB) throws KrakedevExeption{

		Connection con= null;
		PreparedStatement ps= null;
		ResultSet rs= null;
		Cliente cl=null;
		try {
			con=ConexionBdd.obtenerConexion();
				ps=con.prepareStatement("select * from clientes where cedula=?");
				ps.setString(1, cedulaB);
				rs=ps.executeQuery();
				
				if(rs.next()) {
					String cedula=rs.getString("cedula");
					String nombre=rs.getString("nombre");
					int numeroHijos=rs.getInt("numeroHijos");
					cl= new Cliente(cedula,nombre,numeroHijos);
					
				}
			
		} catch (KrakedevExeption e) {
			e.printStackTrace();
			throw e;
		}catch (SQLException e) {
				e.printStackTrace();
				throw new KrakedevExeption("Error al consultar"+e.getMessage());
			}
		
		return cl;
	}

public ArrayList<Cliente> buscaConMasHijos(int numeroHijosPK) throws KrakedevExeption{
	ArrayList<Cliente> cliente= new ArrayList<Cliente>();
	Connection con= null;
	PreparedStatement ps= null;
	ResultSet rs= null;
	Cliente cl=null;
	try {
		con=ConexionBdd.obtenerConexion();
			ps=con.prepareStatement("select * from clientes where numeroHijos>=?");
			ps.setInt(1, numeroHijosPK);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String cedula=rs.getString("cedula");
				String nombre=rs.getString("nombre");
				int numeroHijos=rs.getInt("numeroHijos");
				cl= new Cliente(cedula,nombre,numeroHijos);
				cliente.add(cl);
			}
		
	} catch (KrakedevExeption e) {
		e.printStackTrace();
		throw e;
	}catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevExeption("Error al consultar"+e.getMessage());
		}
	
	return cliente;
}

	

}
