package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Locacao;
import utils.ConexaoDB;

public class LocacaoDao {
	private static Connection con;

	public static void setLocacao(Locacao locacao) throws SQLException {
		try {
			con = ConexaoDB.getConexao();
			PreparedStatement stm = con.prepareStatement("call sp_insert_locacoes (?,?,?,?,?,?)");
			stm.setInt(1, locacao.getQtdDias());
			stm.setInt(2, locacao.getVeiculoId());
	        stm.setInt(3, locacao.getClienteId());
            stm.setObject(4, locacao.getDataLocacao());
            stm.setObject(5, locacao.getDataExpiracao());
            stm.setDate(6, null);
	        stm.execute();
		}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}	
	}
	
	public static List<Locacao> getAll() throws Exception {
		List<Locacao> locacoes  = new ArrayList<Locacao>();	
		try {
			con = ConexaoDB.getConexao();
			
			PreparedStatement stm = con.prepareStatement("select * from tb_locacoes");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {	
				LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;
				
				locacoes.add( new Locacao(rs.getInt("id"), rs.getInt("qtd_dias"), rs.getInt("veiculo_id"), rs.getInt("cliente_id"), 
						rs.getDate("data_locacao").toLocalDate(), dataDevolucao, rs.getDate("data_expiracao").toLocalDate()));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return locacoes;
	}
	
	public static void registrarDevolucao(Locacao locacao) throws SQLException {
		try {
			con = ConexaoDB.getConexao();
			PreparedStatement stm = con.prepareStatement("call sp_registrar_devolucao (?,?,?)");
			stm.setInt(1, locacao.getId());
			stm.setObject(2, locacao.getDataDevolucao());
			stm.setInt(3, locacao.getQtdDias());
	        stm.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public static Locacao getById(int id) throws Exception {
	    Locacao locacao = null;	
	    try {
	        con = ConexaoDB.getConexao();		
	        PreparedStatement stm = con.prepareStatement("select * from tb_locacoes where id = ?");
	        stm.setInt(1, id); 
	        ResultSet rs = stm.executeQuery();
	        
	        if (rs.next()) {
	        	LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;
	        	
	            locacao = new Locacao(
	                rs.getInt("id"),
	                rs.getInt("qtd_dias"),
	                rs.getInt("veiculo_id"),
	                rs.getInt("cliente_id"),
	                rs.getDate("data_locacao").toLocalDate(),
	                dataDevolucao,
	                rs.getDate("data_expiracao").toLocalDate()
	            );
	        }
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	    return locacao;
	}
}
