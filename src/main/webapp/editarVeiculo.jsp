<%@page import="modelos.*"%>
<%@page import="controles.*"%>
<%@page import="dao.*"%>
<%@page import="enums.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Veículos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">   
    <link rel="stylesheet" href="estilos/estilos.css">
</head>
<body class="bg-light">
	<%
       String id = request.getParameter("id");
       Veiculo veiculo = VeiculoControler.getById(Integer.parseInt(id));
    %>
    <div class="container container-cadastro d-flex justify-content-center align-items-center min-vh-100">
    	<a href="./gerenciamento.html" class="btn-voltar btn btn-danger position-absolute"><i class="bi bi-arrow-left"></i> Voltar</a>        
        <div class="card shadow p-4 d-flex justify-content-center align-items-center" style="width: 700px;">
            <h3 class="text-center text-primary mb-4">Editar Veículo: <% out.print(id); %></h3>
            <section>
                <form id="cad-veiculo" action="./editarVeiculo.jsp?id=<%= id %>" method="POST" class="row g-3 mt-3">
                    <input type="hidden" name="tipoVeiculo" value="<%= veiculo.getTipo() %>" />
                								
				    <div class="col-md-6">
				        <label for="marca" class="form-label">Marca:</label>
				        <input value="<%= veiculo.getMarca() %>" type="text" class="form-control" name="marca" id="marca" placeholder="Ex: Honda" required />
				    </div>
				
				    <div class="col-md-6">
				        <label for="modelo" class="form-label">Modelo:</label>
				        <input value="<%= veiculo.getModelo() %>" type="text" class="form-control" name="modelo" id="modelo" placeholder="Ex: Civic" required />
				    </div>
				
				    <div class="col-md-6">
				        <label for="placa" class="form-label">Placa:</label>
				        <input value="<%= veiculo.getPlaca() %>" type="text" class="form-control" name="placa" id="placa" placeholder="Ex: 456-ABD4" required />
				    </div>
				
				    <div class="col-md-6">
				        <label for="ano" class="form-label">Ano:</label>
				        <input value="<%= veiculo.getAno() %>" type="number" class="form-control" name="ano" id="ano" placeholder="Ex: 2005" required />
				    </div>
				
				    <div class="col-md-6">
				        <label for="valor-diaria" class="form-label">Valor da Diária:</label>
				        <input value="<%= veiculo.getPrecoDiaria() %>" type="number" step="0.01" class="form-control" name="precoDiaria" id="valor-diaria" placeholder="Ex: 100.00" required />
				    </div>
				    <div class="col-md-6">
				    	<% String situacaoo = request.getParameter("situacao");

				    	if (!veiculo.getSituacao().equals(SituacaoVeiculo.INDISPONIVEL)) { %>
					        <label for="situacao" class="form-label">Status:</label>
					        <select class="form-select" name="situacao" id="situacao" required>
					            <option value="" disabled selected>Selecione um status</option>
					            <option value="DISPONIVEL">Disponível</option>
					            <option value="INATIVO">Inativo</option>
					        </select>				
					    <% } else { %>
					        <label for="situacao" class="form-label">Status:</label>
					        <input disabled="disabled" placeholder="<%= veiculo.getSituacao() %> - Veículo Alugado" type="text" class="form-control" id="situacao"/>
					        <input type="hidden" name="situacao" value="INDISPONIVEL" />
					    <% } %>

				    </div>
				    <div class="col-12">
				        <button type="submit" class="btn btn-primary mt-3 w-100">Salvar</button>
				    </div>
				</form>
            </section>
            <%
		        if ("POST".equalsIgnoreCase(request.getMethod())) {
		            VeiculoControler vControler = new VeiculoControler();
		            Veiculo v = VeiculoFabrica.criarVeiculo(Integer.parseInt(request.getParameter("id")), request.getParameter("tipoVeiculo"), request.getParameter("marca"), request.getParameter("modelo"),
		            		 request.getParameter("placa"), request.getParameter("ano"),  request.getParameter("precoDiaria"),  request.getParameter("situacao"));
		            
		            vControler.editarVeiculo(v);
		            response.sendRedirect("gerenciamento.html");
		        }
		    %>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
