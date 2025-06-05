package modelos;

import java.time.LocalDate;

import enums.SituacaoVeiculo;
import enums.TipoVeiculo;

public class Veiculo {
	private int id, ano;
	private String marca, modelo, placa;
	private double precoDiaria;
	private TipoVeiculo tipo;
	private SituacaoVeiculo situacao;
	
	public Veiculo(String marca, String modelo, String placa, String ano, String precoDiaria, TipoVeiculo tipo, SituacaoVeiculo situacao) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.ano = Integer.parseInt(ano);
		this.precoDiaria = Double.parseDouble(precoDiaria);
		this.tipo = tipo;
		this.situacao = situacao;
	}
	
	public Veiculo(int id, String marca, String modelo, String placa, String ano, String precoDiaria, TipoVeiculo tipo, SituacaoVeiculo situacao) {
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.ano = Integer.parseInt(ano);
		this.precoDiaria = Double.parseDouble(precoDiaria);
		this.tipo = tipo;
		this.situacao = situacao;
	}
	
	public int getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getPrecoDiaria() {
		return precoDiaria;
	}

	public void setPrecoDiaria(double precoDiaria) {
		this.precoDiaria = precoDiaria;
	}
	
	public double getValorAluguel(int quantidadeDias) {
		return precoDiaria * quantidadeDias;
	}
	
	public double getValorAluguelAtrasado(int quantidadeDias) {
		return getValorAluguel(quantidadeDias) * 1.1;
	}
	
	public TipoVeiculo getTipo() {
		return tipo;
	}

	public SituacaoVeiculo getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoVeiculo situacao) {
		this.situacao = situacao;
	}
}
