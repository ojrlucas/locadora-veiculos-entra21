<%@page import="modelos.*"%>
<%@page import="controles.*"%>
<%
	String nomeFormulario = request.getParameter("formulario");
	
	if (nomeFormulario.equals("cad-cliente")) {
		try {
			Cliente cliente = new Cliente(request.getParameter("nome"), request.getParameter("email"), request.getParameter("fone"));
			
			ClienteControler cliControler = new ClienteControler();
			cliControler.salvar(cliente);
		}
		catch (Exception e) {
		    response.setStatus(500);
		    out.print("Erro ao processar formulário: " + e.getMessage());
		}
	}
	
	if (nomeFormulario.equals("cad-veiculo")) {
		try {
			Veiculo veiculo  = VeiculoFabrica.criarVeiculo(request.getParameter("tipo"), request.getParameter("marca"), request.getParameter("modelo"), 
			           request.getParameter("placa"), request.getParameter("ano"), request.getParameter("valor-diaria"));
	
			VeiculoControler vControler = new VeiculoControler();
			vControler.salvar(veiculo);
		}
		catch (Exception e) {
		    response.setStatus(500);
		    out.print("Erro ao processar formulário: " + e.getMessage());
		}
	}
	
	if (nomeFormulario.equals("cad-locacao")) {
		try {	
			ClienteControler cliControler = new ClienteControler();
			Cliente c = cliControler.getByEmail(request.getParameter("email"));
			
			Locacao locacao = new Locacao(request.getParameter("qtdDias"), request.getParameter("veiculoId"), String.valueOf(c.getId()));
			out.print(locacao);
			LocacaoControler lControler = new LocacaoControler();
			lControler.salvar(locacao);
		}
		catch (Exception e) {
		    response.setStatus(500);
		    out.print("Erro ao processar formulário: " + e.getMessage());
		}

	}
	
	response.sendRedirect("gerenciamento.html");
%>

