package modelos;

import enums.SituacaoVeiculo;
import enums.TipoVeiculo;

public class Moto extends Veiculo {
	private final String tipo = "MOTO";
	
	public Moto(String marca, String modelo, String placa, String ano, String precoDiaria) {
		super(marca, modelo, placa, ano, precoDiaria, TipoVeiculo.MOTO, SituacaoVeiculo.DISPONIVEL);
	}
	
	public Moto(int id, String marca, String modelo, String placa, String ano, String precoDiaria, String situacao) {
		super(id, marca, modelo, placa, ano, precoDiaria, TipoVeiculo.MOTO, SituacaoVeiculo.valueOf(situacao));
	}

	@Override
	public double getValorAluguel(int quantidadeDias) {
		if(quantidadeDias >= 7) {
			return (getPrecoDiaria() * quantidadeDias) * 0.85;
		} else {
			return super.getValorAluguel(quantidadeDias);
		}
	}
	
	public double getValorAluguelAtrasado(int quantidadeDias) {
		return getValorAluguel(quantidadeDias) * 1.1;
	}
}
