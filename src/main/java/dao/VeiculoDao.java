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

public class VeiculoDao {
	private static Connection con;

	public static void setVeiculo(Veiculo veiculo) throws SQLException {
		try {
			con = ConexaoDB.getConexao();
			PreparedStatement stm = con.prepareStatement("INSERT INTO tb_veiculos (tipo, marca, modelo, placa, ano, precoDiaria,situacao) VALUES (?,?,?,?,?,?,?)");
			
			stm.setString(1, veiculo.getTipo().name());
			stm.setString(2, veiculo.getMarca());
	        stm.setString(3, veiculo.getModelo());
	        stm.setString(4, veiculo.getPlaca());
	        stm.setInt(5, veiculo.getAno());
	        stm.setDouble(6, veiculo.getPrecoDiaria());
	        stm.setString(7, veiculo.getSituacao().name());
	        stm.execute();
		}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}	
	}
	
	public static List<Veiculo> getAll() throws Exception {
		List<Veiculo> veiculos  = new ArrayList<Veiculo>();	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_veiculos");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {	
				veiculos.add(VeiculoFabrica.criarVeiculo(rs.getInt("id"), rs.getString("tipo"), rs.getString("marca"), 
						rs.getString("modelo"), rs.getString("placa"), rs.getString("ano"), rs.getString("precoDiaria"), rs.getString("situacao")));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return veiculos;
	}
	
	public static Veiculo getByPlaca(String placa) throws Exception {
		Veiculo veiculo = null;	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_veiculos where placa = '" + placa + "'");
			ResultSet rs = stm.executeQuery();
			
	        if (rs.next()) {
	        	veiculo = VeiculoFabrica.criarVeiculo(rs.getInt("id"), rs.getString("tipo"), rs.getString("marca"), 
						rs.getString("modelo"), rs.getString("placa"), rs.getString("ano"), rs.getString("precoDiaria"), rs.getString("situacao"));
	        }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return veiculo;
	}
	
	public static Veiculo getById(int id) throws Exception {
		Veiculo veiculo = null;	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_veiculos where id = " + id);
			ResultSet rs = stm.executeQuery();
			
	        if (rs.next()) {
	        	veiculo = VeiculoFabrica.criarVeiculo(rs.getInt("id"), rs.getString("tipo"), rs.getString("marca"), 
						rs.getString("modelo"), rs.getString("placa"), rs.getString("ano"), rs.getString("precoDiaria"), rs.getString("situacao"));
	        }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return veiculo;
	}
	
	public static Veiculo editarVeiculo(Veiculo veiculo) throws Exception {
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("UPDATE tb_veiculos SET marca = ?, modelo = ?, placa = ?, ano = ?, precoDiaria = ?, tipo = ?, situacao = ? WHERE id = ?");
			stm.setString(1, veiculo.getMarca());
			stm.setString(2, veiculo.getModelo());
			stm.setString(3, veiculo.getPlaca());
			stm.setInt(4, veiculo.getAno());
			stm.setDouble(5, veiculo.getPrecoDiaria());
			stm.setString(6, veiculo.getTipo().name());
			stm.setString(7, veiculo.getSituacao().name());
			stm.setInt(8, veiculo.getId());
	        stm.execute();
	        
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return veiculo;
	}
}
