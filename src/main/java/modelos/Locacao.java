package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Locacao {
    private int id, qtdDias, veiculoId, clienteId;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private LocalDate dataExpiracao;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Locacao(String qtdDias, String veiculoId, String clienteId) {
        this.qtdDias = Integer.parseInt(qtdDias);
        this.veiculoId = Integer.parseInt(veiculoId);
        this.clienteId = Integer.parseInt(clienteId);
        this.dataLocacao = LocalDate.now();
        this.dataExpiracao = dataLocacao.plusDays(this.qtdDias);
    }

    public Locacao(int id, int qts_dias, int veiculoId, int clienteId, LocalDate dataLocacao, LocalDate dataDevolucao, LocalDate dataExpiracao) {
        this.id = id;
        this.qtdDias = qts_dias;
        this.veiculoId = veiculoId;
        this.clienteId = clienteId;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.dataExpiracao = dataExpiracao;
    }

    public int getId() {
        return id;
    }

    public int getQtdDias() {
        return qtdDias;
    }

    public void setQtdDias(int qts_dias) {
        this.qtdDias = qts_dias;
    }

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = LocalDate.parse(dataLocacao, FORMATTER);
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao() {
        this.dataDevolucao = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(dataLocacao, dataDevolucao);
        if (dias == 0) dias = 1;
        setQtdDias((int) dias);
    } 

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = LocalDate.parse(dataExpiracao, FORMATTER);
    }

    public String verificaSituacao() {
    	LocalDate hoje = (dataDevolucao == null) ? LocalDate.now() : dataDevolucao;
    	
        return (hoje.isAfter(dataExpiracao)) ?  "atrasado" : "aguardando";
    }
}
