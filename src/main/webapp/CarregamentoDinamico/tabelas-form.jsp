<%@page import="controles.*, modelos.*, java.util.List"%>
<% 
  try {
      String tabela = request.getParameter("tabela"); 

      if (tabela.equals("cliente")) {
%>
		<div class="p-3 bg-light rounded shadow-sm mb-3">
		    <div class="row g-3 align-items-end">
		        <div class="col-md-6">
		            <label for="email" class="form-label">Email:</label>
		            <input type="email" id="email" placeholder="Ex: exemplo@gmail.com" class="form-control form-control-lg w-100" />
		        </div>
		        <div class="col-md-6 d-flex flex-wrap gap-2">
		            <button type="button" onclick="filtrarPorEmail()" class="btn btn-outline-primary btn-custom">
		                <i class="bi bi-funnel me-1"></i>Filtrar Consulta
		            </button>
		            <button type="button" onclick="carregarTabela('tabelas-clientes.jsp')" class="btn btn-outline-primary btn-custom">
		                <i class="bi bi-list-ul me-1"></i>Consultar Tudo
		            </button>
		            <a href="./cadastrarCliente.html" class="btn btn-outline-primary btn-custom">
		                <i class="bi bi-plus me-1"></i>Cadastrar Novo
		            </a>
		        </div>
		    </div>
		</div>
<%
      } else if (tabela.equals("veiculo")) {
%>
			<div class="p-3 bg-light rounded shadow-sm mb-3">
			    <div class="row g-3 align-items-end">
			        <div class="col-md-6">
			            <label for="placa" class="form-label">Placa:</label>
			            <input type="text" id="placa" placeholder="Ex: 452-BGKS" class="form-control form-control-lg w-100" />
			        </div>
			        <div class="col-md-6 d-flex flex-wrap gap-2">
			            <button type="button" onclick="filtrarPorPlaca()" class="btn btn-outline-secondary btn-custom">
			                <i class="bi bi-funnel me-1"></i>Filtrar Consulta
			            </button>
			            <button type="button" onclick="carregarTabela('tabelas-veiculos.jsp')" class="btn btn-outline-secondary btn-custom">
			                <i class="bi bi-list-ul me-1"></i>Consultar Tudo
			            </button>
			            <a href="./cadastrarVeiculo.html" class="btn btn-outline-secondary btn-custom">
			                <i class="bi bi-plus me-1"></i>Cadastrar Novo
			            </a>
			        </div>
			    </div>
			</div>
<%			
      } else {
%>
		<div class="p-3 bg-light rounded shadow-sm mb-3 d-flex flex-wrap gap-2">
		    <button type="button" onclick="carregarTabela('tabelas-locacoes.jsp')" class="btn btn-outline-success btn-custom">
		        <i class="bi bi-list-ul me-1"></i>Consultar Tudo
		    </button>
		    <button type="button" onclick="carregarTabela('tabelas-veiculos.jsp')" class="btn btn-outline-success btn-custom">
		        <i class="bi bi-plus me-1"></i>Registrar Locação
		    </button>
		</div>
<%
      }
  } catch (Exception e) {
%>
	<div class="alert alert-danger mt-3" role="alert">
	    <i class="bi bi-exclamation-triangle me-1"></i>Erro: <%= e.getMessage() %>
	</div>
<%
  }
%>
