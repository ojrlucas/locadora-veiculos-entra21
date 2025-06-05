function gravarCliente() {
	var nome = document.getElementById("nome")
	if (nome.value === "") {
		alert("O nome deve ser informado")
		nome.focus()
		return
	}
	
	var email = document.getElementById("email")
	if (email.value === "") {
		alert("O email deve ser informado")
		email.focus()
		return
	}
	
	var fone = document.getElementById("fone")
	if (fone.value === "") {
		alert("O fone deve ser informado")
		fone.focus()
		return
	}
	
	document.getElementById("cad-cliente").submit()
}	

async function carregarTabela(url) {
    try {
		const resp = await fetch('/LocadoraVeiculos/CarregamentoDinamico/' + url)
        const html = await resp.text()
        document.getElementById('tab-consultas').innerHTML = html
    } catch(e) {
        document.getElementById('tab-consultas').innerHTML = '<tr><td colspan="7">Erro ao carregar dados.</td></tr>'
    }
}

function filtrarPorPlaca() {
    const placa = document.getElementById('placa')	
    carregarTabela('tabelas-veiculos.jsp' + '?placa=' + placa.value)	
	placa.value = null;
}

function filtrarPorEmail() {
    const email = document.getElementById('email')

    carregarTabela('tabelas-clientes.jsp' + '?email=' + email.value)	
}

async function carregarTabelasForm(tabela) {
    try {
		const resp = await fetch('/LocadoraVeiculos/CarregamentoDinamico/tabelas-form.jsp?tabela=' + tabela)
        const html = await resp.text()
        document.getElementById('ftabela').innerHTML = html
    } catch(e) {
        alert("Erro ao carregar dados.")
    }
}