<%@page import="controles.ClienteControler, modelos.Cliente, java.util.List"%>
<%
    ClienteControler cControler = new ClienteControler();
    String email = request.getParameter("email");
    List<Cliente> clientes = (email != null && !email.isEmpty())
        ? (cControler.getByEmail(email) != null ? java.util.Arrays.asList(cControler.getByEmail(email)) : null)
        : cControler.getAll();
%>

<thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Fone</th>
        <th>Ações</th>
    </tr>
</thead>
<tbody>
<%
    if (clientes == null || clientes.isEmpty()) {
%>
	    <tr>
	        <td colspan="5">Cliente não encontrado.</td>
	    </tr>
<%
    } else {
        for (Cliente c : clientes) {
%>
	    <tr>
	        <td><%= c.getId() %></td>
	        <td class="text-truncate max-truncate-200"><%= c.getNome() %></td>
	        <td class="text-truncate max-truncate-200"><%= c.getEmail() %></td>
	        <td><%= c.getFone() %></td>
	        <td>
	            <a href="./editarCliente.jsp?id=<%= c.getId() %>" class="btn btn-outline-secondary">editar</a>
	        </td>
	    </tr>
<%
        }
    }
%>
</tbody>