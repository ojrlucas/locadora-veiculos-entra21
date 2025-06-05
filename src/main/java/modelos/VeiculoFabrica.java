package modelos;

import enums.SituacaoVeiculo;

public class VeiculoFabrica {
	 public static Veiculo criarVeiculo(String tipoVeiculo, String marca, String modelo, String placa, String ano, String precoDiaria) {
		switch (tipoVeiculo.toLowerCase()) {
		    case "carro":
		        return new Carro(marca, modelo, placa, ano, precoDiaria);
		    case "caminhao":
		        return new Caminhao(marca, modelo, placa, ano, precoDiaria);
		    case "moto":
		        return new Moto(marca, modelo, placa, ano, precoDiaria);
		}
		return null;
	 }
	 
	 public static Veiculo criarVeiculo(int id, String tipoVeiculo, String marca, String modelo, String placa, String ano, String precoDiaria, String situacao) {
			switch (tipoVeiculo.toLowerCase()) {
			    case "carro":
			        return new Carro(id, marca, modelo, placa, ano, precoDiaria, situacao);
			    case "caminhao":
			        return new Caminhao(id, marca, modelo, placa, ano, precoDiaria, situacao);
			    case "moto":
			        return new Moto(id, marca, modelo, placa, ano, precoDiaria, situacao);
			}
			return null;
	}
}
