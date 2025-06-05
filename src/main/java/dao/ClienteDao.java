package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Cliente;
import modelos.Veiculo;
import modelos.VeiculoFabrica;
import utils.ConexaoDB;

public class ClienteDao {
	private static Connection con;
	
	public static void setCliente(Cliente cliente) throws SQLException {
		try {
			con = ConexaoDB.getConexao();
			PreparedStatement stm = con.prepareStatement("INSERT INTO tb_clientes (nome, email, fone) VALUES (?,?,?)");
			
	        stm.setString(1, cliente.getNome());
	        stm.setString(2, cliente.getEmail());
	        stm.setString(3, cliente.getFone());
	        stm.execute();
		}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}	
	}
	
	public static List<Cliente> getAll() throws Exception {
		List<Cliente> clientes  = new ArrayList<Cliente>();
		try {
			Connection con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_clientes");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				clientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("fone")));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return clientes;
	}
	
	public static Cliente getByEmail(String email) throws Exception {
		Cliente cliente = null;	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_clientes where email = '" + email + "'");
			ResultSet rs = stm.executeQuery();
			
	        if (rs.next()) {
	        	cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("fone"));
	        }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return cliente;
	}
	
	public static Cliente getById(int id) throws Exception {
		Cliente cliente = null;	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_clientes where id = " + id);
			ResultSet rs = stm.executeQuery();
			
	        if (rs.next()) {
	        	cliente = new Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("fone"));
	        }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return cliente;
	}
	
	public static Cliente editarCliente(Cliente cliente) throws Exception {
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("UPDATE tb_clientes SET nome = ?, email = ?, fone = ? WHERE id = ?");
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getFone());
			stm.setInt(4, cliente.getId());
	        stm.execute();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return cliente;
	}
}
