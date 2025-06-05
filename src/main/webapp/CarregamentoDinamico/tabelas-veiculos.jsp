<%@page import="controles.VeiculoControler, modelos.Veiculo, enums.SituacaoVeiculo, java.util.List"%>
<%
    VeiculoControler vControler = new VeiculoControler();
    String placa = request.getParameter("placa");
    
    List<Veiculo> veiculos = (placa != null && !placa.isEmpty()) 
        ? (vControler.getByPlaca(placa) != null ? java.util.Arrays.asList(vControler.getByPlaca(placa)) : null) 
        : vControler.getAll();
%>

<thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>Marca</th>
        <th>Modelo</th>
        <th>Placa</th>
        <th>Ano</th>
        <th>Preço da Diária</th>
        <th>Situação</th>
        <th>Ações</th>
    </tr>
</thead>
<tbody>
<%
    if (veiculos == null || veiculos.isEmpty()) {
%>
	    <tr>
	        <td colspan="9">Veículo não encontrado.</td>
	    </tr>
<%
    } else {
        for (Veiculo v : veiculos) {
%>
	    <tr>
	        <td><%= v.getId() %></td>
	        <td><%= v.getTipo() %></td>
	        <td><%= v.getMarca() %></td>
	        <td><%= v.getModelo() %></td>
	        <td><%= v.getPlaca() %></td>
	        <td><%= v.getAno() %></td>
	        <td>R$ <%= v.getPrecoDiaria() %></td>
	        <td><%= v.getSituacao() %></td>
	        <td>
	            <a href="./editarVeiculo.jsp?id=<%= v.getId() %>" class="btn btn-outline-secondary">editar</a>
	            <% if (v.getSituacao() == SituacaoVeiculo.DISPONIVEL) { %>
	                <a href="./cadastrarAluguel.jsp?id=<%= v.getId() %>" class="ms-3 btn btn-outline-primary">alugar</a>
	            <% } %>
	        </td>
	    </tr>
<%
        }
    }
%>
</tbody> 