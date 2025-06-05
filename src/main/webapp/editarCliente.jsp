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
       String id = request.getParameter("id");
       Cliente cliente = ClienteControler.getById(Integer.parseInt(id));
    %>
    <div class="container container-cadastro d-flex justify-content-center align-items-center min-vh-100">
        <a href="./gerenciamento.html" class="btn-voltar btn btn-danger position-absolute"><i class="bi bi-arrow-left"></i> Voltar</a>
        <div class="card shadow p-4 d-flex justify-content-center align-items-center" style="width: 600px;">
		    <h3 class="text-center text-primary mb-4">Editar Cliente: <% out.print(id); %></h3>
		     <section>
                <form id="fEdicao" action="editarCliente.jsp" method="POST" class="row g-3 mt-3">
                    <input type="hidden" name="id" value="<%= id %>" />
 
                    <div class="col-md-6">
                        <label for="nome" class="form-label">Nome:</label>
                        <input type="text" class="form-control" id="nome" name="nome"
                            placeholder="Ex: Maria"
                            value="<%= cliente.getNome() %>" required />
                    </div>

                    <div class="col-md-6">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control" id="email" name="email"
                            placeholder="Ex: maria@gmail.com"
                            value="<%= cliente.getEmail() %>" required />
                    </div>

                    <div class="col-md-6">
                        <label for="fone" class="form-label">Fone:</label>
                        <input type="tel" class="form-control" id="fone" name="fone"
                            placeholder="Ex: (99)9999-9999"
                            value="<%= cliente.getFone() %>" required />
                    </div>

                    <div class="col-md-6">
                        <button type="submit" class="btn btn-primary btn-cadastro-cliente">Salvar</button>
                    </div>
                </form>
            </section>		
		    <%
		        if ("POST".equalsIgnoreCase(request.getMethod())) {
		            ClienteControler cControler = new ClienteControler();
		            Cliente c = new Cliente(Integer.parseInt(request.getParameter("id")), request.getParameter("nome"), 
		            		request.getParameter("email"), request.getParameter("fone"));
		            cControler.editarCliente(c);
		            response.sendRedirect("gerenciamento.html");
		        }
		    %>
		</div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
