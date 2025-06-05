package controles;

import java.sql.SQLException;
import java.util.List;

import dao.VeiculoDao;
import modelos.Veiculo;

public class VeiculoControler {	
	
	public Veiculo salvar(Veiculo veiculo) throws SQLException {
		try {
			VeiculoDao.setVeiculo(veiculo);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return veiculo;
	}
	
	public List<Veiculo> getAll() throws Exception {
		return VeiculoDao.getAll();
	}
	
	public Veiculo getByPlaca(String placa) throws Exception {
		return VeiculoDao.getByPlaca(placa);
	}
	
	public static Veiculo getById(int id) throws Exception {
		return VeiculoDao.getById(id);
	}
	
	public static Veiculo editarVeiculo(Veiculo veiculo) throws Exception {
		try {
			VeiculoDao.editarVeiculo(veiculo);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return veiculo;
	}
}
