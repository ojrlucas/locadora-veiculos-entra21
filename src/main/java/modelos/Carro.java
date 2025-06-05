package modelos;

import enums.SituacaoVeiculo;
import enums.TipoVeiculo;

public class Carro extends Veiculo {

	public Carro(String marca, String modelo, String placa, String ano, String precoDiaria) {
		super(marca, modelo, placa, ano, precoDiaria, TipoVeiculo.CARRO, SituacaoVeiculo.DISPONIVEL);
	}
	
	public Carro(int id, String marca, String modelo, String placa, String ano, String precoDiaria, String situacao) {
		super(id, marca, modelo, placa, ano, precoDiaria, TipoVeiculo.CARRO, SituacaoVeiculo.valueOf(situacao));
	}

	@Override
	public double getValorAluguel(int quantidadeDias) {
		if(quantidadeDias >= 7) {
			return (getPrecoDiaria() * quantidadeDias) * 0.95;
		} else {
			return super.getValorAluguel(quantidadeDias);
		}
	}
	
	public double getValorAluguelAtrasado(int quantidadeDias) {
		return getValorAluguel(quantidadeDias) * 1.30;
	}
}
