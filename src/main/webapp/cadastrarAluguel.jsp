<%@page import="modelos.*"%>
<%@page import="controles.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edição de clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<link rel="stylesheet" href="estilos/estilos.css">
</head>
<body class="bg-light">
	<% 
       VeiculoControler vControler = new VeiculoControler();
	
       String id = request.getParameter("id"); 
       Veiculo v = VeiculoControler.getById(Integer.parseInt(id)); 
    %>
    <div class="container container-cadastro d-flex justify-content-center align-items-center min-vh-100">
    	
    	<a href="./gerenciamento.html" class="btn-voltar btn btn-danger position-absolute"><i class="bi bi-arrow-left"></i> Voltar</a>
	    <div class="card card-cadastro-locacao shadow p-4 d-flex flex-wrap justify-content-center align-items-center">
	        <h3 class="text-center text-primary mb-4">Registrar Locação</h3>
	        <section>
	            <form id="cad-locacao" action="cadastro.jsp?veiculoId=<%=id%>" method="POST" class="row mt-3 g-3">
				    <input type="hidden" name="formulario" value="cad-locacao">
				
				    <div class="col-6">
				        <label for="marca" class="form-label">Marca:</label>
				        <input disabled type="text" class="form-control" name="marca" id="marca" value="<%= v.getMarca() %>" />
				
				        <label for="modelo" class="form-label mt-2">Modelo:</label>
				        <input disabled type="text" class="form-control" name="modelo" id="modelo" value="<%= v.getModelo() %>" />
				    </div>
				
				    <div class="col-6">
				        <label for="placa" class="form-label">Placa:</label>
				        <input disabled type="text" class="form-control" name="placa" id="placa" value="<%= v.getPlaca() %>" />
				
				        <label for="email" class="form-label mt-2">Email do Cliente:</label>
				        <input onblur="filtrarPorEmail()" type="email" class="form-control" name="email" id="email" placeholder="Ex: exemplo@email.com" required />				   
				    </div>
				 
				    <div class="col-6">
				        <label for="qtdDias" class="form-label">Qtd Dias:</label>
				        <input type="number" class="form-control" name="qtdDias" id="qtdDias" placeholder="Ex: 12" required />
				    </div>
				
				    <div class="col-6 d-flex align-items-end">
				        <button type="submit" class="btn btn-primary btn-cadastro-locacao">Alugar</button>
				    </div>
				</form>

	        </section>        
		</div>
		<div class="table-container ms-5 d-flex justify-content-center align-items-center">
			<div class=" table-responsive d-flex w-100 justify-content-center align-items-center">
				<table id="tab-consultas" class="table-aluguel table table-striped table-hover align-middle">
					<tbody>
						<tr>
							<td colspan="7" class="text-center fst-italic text-muted">
							Pesquise pelo email e confira
							</td>
						</tr>
					</tbody>
				</table>
			</div>
     	</div>
    </div>

	<script src="/LocadoraVeiculos/JavaScript/funcionamento.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
