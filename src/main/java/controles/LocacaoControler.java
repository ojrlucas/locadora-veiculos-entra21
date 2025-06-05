package controles;

import java.sql.SQLException;
import java.util.List;

import dao.LocacaoDao;
import dao.VeiculoDao;
import modelos.Locacao;
import modelos.Veiculo;

public class LocacaoControler {
	public void salvar(Locacao locacao) throws SQLException {
		try {
			LocacaoDao.setLocacao(locacao);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<Locacao> getAll() throws Exception {
		return LocacaoDao.getAll();
	}
	
	public static Locacao getById(int id) throws Exception {
		return LocacaoDao.getById(id);
	}
	
	public void registrarDevolucao(int id) throws Exception {
		try {
			Locacao locacao = getById(id);
			locacao.setDataDevolucao();
			LocacaoDao.registrarDevolucao(locacao);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
