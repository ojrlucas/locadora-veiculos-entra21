package controles;

import java.sql.SQLException;
import java.util.List;

import dao.ClienteDao;
import dao.VeiculoDao;
import modelos.Cliente;
import modelos.Veiculo;

public class ClienteControler {
	private ClienteDao clienteDao;
	
	public void salvar(Cliente cliente) throws SQLException {
		try {
			ClienteDao.setCliente(cliente);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	public List<Cliente> getAll() throws Exception {
		return ClienteDao.getAll();
	}
	
	public Cliente getByEmail(String email) throws Exception {
		return ClienteDao.getByEmail(email);
	}
	
	public static Cliente getById(int id) throws Exception {
		return ClienteDao.getById(id);
	}
	
	public void editarCliente(Cliente cliente) throws Exception {
		try {
			ClienteDao.editarCliente(cliente);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}
}
