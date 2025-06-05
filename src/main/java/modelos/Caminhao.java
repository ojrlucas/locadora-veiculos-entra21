package modelos;

import enums.SituacaoVeiculo;
import enums.TipoVeiculo;

public class Caminhao extends Veiculo {
	private final String tipo = "CAMINHAO";

	public Caminhao(String marca, String modelo, String placa, String ano, String precoDiaria) {
		super(marca, modelo, placa, ano, precoDiaria, TipoVeiculo.CAMINHAO, SituacaoVeiculo.DISPONIVEL);
	}
	
	public Caminhao(int id, String marca, String modelo, String placa, String ano, String precoDiaria, String situacao) {
		super(id, marca, modelo, placa, ano, precoDiaria, TipoVeiculo.CAMINHAO, SituacaoVeiculo.valueOf(situacao));
	}

	@Override
	public double getValorAluguel(int quantidadeDias) {
		if(quantidadeDias >= 7) {
			return (getPrecoDiaria() * quantidadeDias) * 1.2;
		} else {
			return super.getValorAluguel(quantidadeDias);
		}
	}
	
	public double getValorAluguelAtrasado(int quantidadeDias) {
		return getValorAluguel(quantidadeDias) * 1.5;
	}
}
