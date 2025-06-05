<%@page import="controles.*, modelos.*, java.util.List"%>
<head>
	<link rel="stylesheet" href="estilos/estilos.css">
</head>
<%
	try {   
	    LocacaoControler lControler = new LocacaoControler();
	
	    if ("devolver".equals(request.getParameter("acao"))) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        lControler.registrarDevolucao(id);
	    	response.sendRedirect("/LocadoraVeiculos/gerenciamento.html");	    	
	    }
	
	    String placa = request.getParameter("placa"); 
	    List<Locacao> locacoes = lControler.getAll();
%>


<thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>Placa</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Data Locação</th>
        <th>Data Devolução</th>
        <th>Data Expiração</th>
        <th>Valor</th>
        <th>Ações</th>
    </tr>
</thead>
<tbody>
<%
    for (Locacao l : locacoes) {
        Veiculo v = VeiculoControler.getById(l.getVeiculoId());
        Cliente c = ClienteControler.getById(l.getClienteId());
%>
	    <tr>
	        <td><%= l.getId() %></td>
	        <td><%= v.getTipo() %></td>
	        <td><%= v.getPlaca() %></td>
	        <td class="text-truncate max-truncate-150"><%= c.getNome() %></td>
	        <td class="text-truncate max-truncate-150"><%= c.getEmail() %></td>
	        <td><%= l.getDataLocacao().toString() %></td>
			<td>
				<%= (l.getDataDevolucao() != null ? l.getDataDevolucao().toString() : l.verificaSituacao()) %>
			</td>
			<td><%= l.getDataExpiracao().toString() %></td>
	        <td>R$ 
				<%= l.verificaSituacao().equals("atrasado") 
					? String.format("%.2f", v.getValorAluguelAtrasado(l.getQtdDias())) 
					: String.format("%.2f", v.getValorAluguel(l.getQtdDias())) %>
			</td>
	        <td>
	        <% if (l.getDataDevolucao() == null) { %>
	            <a href="./CarregamentoDinamico/tabelas-locacoes.jsp?acao=devolver&id=<%= l.getId() %>" class="btn btn-outline-primary">devolver</a>
	        <% } else { %> devolvido <%}%>
	        </td>
	    </tr>
<% } %>
</tbody>

<%
    } catch (Exception e) {
%>
    <tr><td colspan="11">Erro: <%= e.getMessage() %></td></tr>
<%
    }
%>
