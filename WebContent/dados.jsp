<%@ page import="br.nom.teixeira.weslei.outros.*"%> 
<%@ page import="br.nom.teixeira.weslei.outros.Escavador.*"%> 
<%@ page import="br.nom.teixeira.weslei.outros.Fuseki.*"%> 

<%
String espacador = "    ==========================\r\n";
String nome = null;
Escavador escavador = null;
Lattes lattes = new Lattes();
PortalDaTransparencia portalDaTransparencia = null;


try
{
	nome = request.getParameter("nome");
	
	Fuseki db = new Fuseki(nome);
	if (db.sujeitoExiste())
	{
		Resultado resultado = db.ler();
		lattes = resultado.lattes;
		escavador = resultado.escavador;
		portalDaTransparencia = resultado.portalDaTransparencia;
		lattes.resultados = 1;
	}
	else
	{
		lattes.extrair(nome);
		if (lattes.resultados == 1)
		{
			db.definirSujeito(lattes.nomeCompleto);
			if (db.sujeitoExiste())
			{
				Resultado resultado = db.ler();
				lattes = resultado.lattes;
				escavador = resultado.escavador;
				portalDaTransparencia = resultado.portalDaTransparencia;;
			}
			else
			{
				escavador = new Escavador(lattes.nomeCompleto);
				escavador.extrairDados();
				
				portalDaTransparencia = new PortalDaTransparencia(lattes.nomeCompleto);
				portalDaTransparencia.extrairDados();
				
				db.inserir(lattes, escavador, portalDaTransparencia); 
				
				Resultado resultado = db.ler();
				lattes = resultado.lattes;
				escavador = resultado.escavador;
				portalDaTransparencia = resultado.portalDaTransparencia;
				
				//System.out.println("aqui");
			}
		}
	}
}
catch(Exception e)
{
	System.out.println(e);
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/principal.css">
		<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<script src="js/principal.js"></script>
		<title>Resultados</title>
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<ul class="nav navbar-nav navbar-left">
						<li>
							<a href="../">
								<img class="img-responsive img-navbar2" alt="Voltar" src="http://cdn2.hubspot.net/hub/49125/file-14366016-png/images/log-out-icon.png">
							</a>
						</li>
					</ul>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a>
							<img class="img-responsive img-navbar" alt="UFF" src="http://www.tectur.uff.br/sites/default/files/2000px-Logo_UFF_%28blue%29.png">
						</a>
					</li>
				</ul>
			</div>
		</nav>
		<section class="container-fluid">
			<div class = "jumbotron">
				<h1 class="title text-center"><%=((lattes.resultados == 0) ? "N�o foi encontrado nenhum professor!" : ((lattes.resultados > 1) ? "O nome que voc� digitou � muito curto!" : "\"" + nome + "\""))%></h1>
				<%
if (lattes.resultados == 1)
{
	String saida = "<pre style=\"overflow: scroll;\">";
	
	if (escavador.nome != null)
		saida += "Nome: " + escavador.nome + "\r\n\r\n";
	
	if (lattes.link != null)
		saida += "Link Lattes: " + lattes.link + "\r\n\r\n";  
	
	if (escavador.link != null)
		saida += "Link Escavador: " + escavador.link + "\r\n\r\n";  
	
	if (escavador.descricao != null)
		saida += "Descri��o: " + escavador.descricao + "\r\n\r\n";
	
	if (escavador.idiomas != null)
	{
		saida += "Idiomas: \r\n";
		
		for (Idioma idioma : escavador.idiomas)
		{
			saida += "    (Nome) -> " + idioma.nome + "\r\n";
			saida += "    (Habilidades) -> " + idioma.habilidades + "\r\n";
			saida += espacador;
		}
		
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";	
	}
	
	if (escavador.formacoes != null)
	{
		saida += "Forma��es: \r\n";
		
		for (Formacao formacao : escavador.formacoes)
		{
			if (formacao.inicio != null)
				saida += "    (In�cio) -> " + formacao.inicio + "\r\n";
				
			if (formacao.fim != null)
				saida += "    (Fim) -> " + formacao.fim + "\r\n";
			
			if (formacao.tipo != null)
				saida += "    (Tipo) -> " + formacao.tipo + "\r\n";
			
			if (formacao.local != null)
				saida += "    (Local) -> " + formacao.local + "\r\n";
				
			if (formacao.TCC != null)
				saida += "    (TCC) -> " + formacao.TCC + "\r\n";
			
			if (formacao.anoTCC != null)
				saida += "    (Ano do TCC) -> " + formacao.anoTCC + "\r\n";
			
			if (formacao.orientador != null)
				saida += "    (Orientador) -> " + formacao.orientador + "\r\n";
			
			if (formacao.bolsistaDe != null)
				saida += "    (Bolsita de) -> " + formacao.bolsistaDe + "\r\n";
			
			if (formacao.palavrasChave != null)
				saida += "    (Palavras Chave) -> " + formacao.palavrasChave + "\r\n";
			
			if (formacao.grandeArea != null)
				saida += "    (Grande �rea) -> " + formacao.grandeArea + "\r\n";
			
			if (formacao.area != null)
				saida += "    (�rea) -> " + formacao.area + "\r\n";
			
			if (formacao.subArea != null)
				saida += "    (Sub-�rea) -> " + formacao.subArea + "\r\n";
			
			if (formacao.especialidade != null)
				saida += "    (Especialidade) -> " + formacao.especialidade + "\r\n";
			
			if (formacao.setoresDeAtividade != null)
				saida += "    (Setores de Atividade) -> " + formacao.setoresDeAtividade + "\r\n";
				
			saida += espacador;
		}
		
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	if (escavador.posDoutorado != null)
	{
		saida += "P�s Doutorado: \r\n";
		
		if (escavador.posDoutorado.inicio != null)
			saida += "    (In�cio) -> " + escavador.posDoutorado.inicio + "\r\n";
			
		if (escavador.posDoutorado.fim != null)
			saida += "    (Fim) -> " + escavador.posDoutorado.fim + "\r\n";
			
		if (escavador.posDoutorado.local != null)
			saida += "    (Local) -> " + escavador.posDoutorado.local + "\r\n";
			
		if (escavador.posDoutorado.bolsistaDe != null)
			saida += "    (Bolsita De) -> " + escavador.posDoutorado.bolsistaDe + "\r\n";
		
		saida += "\r\n";
	}
	
	if (escavador.formacoesComplementares != null)
	{
		saida += "Forma��es Complementares: \r\n";
		
		for (Complementar formacaoComplementar : escavador.formacoesComplementares)
		{
			if (formacaoComplementar.inicio != null)
				saida += "    (In�cio) -> " + formacaoComplementar.inicio + "\r\n";
				
			if (formacaoComplementar.fim != null)
				saida += "    (Fim) -> " + formacaoComplementar.fim + "\r\n";
			
			if (formacaoComplementar.subArea != null)
				saida += "    (Tipo) -> " + formacaoComplementar.tipo + "\r\n";
			
			if (formacaoComplementar.cargaHoraria != null)
				saida += "    (Carga Hor�ria) -> " + formacaoComplementar.cargaHoraria + "\r\n";
				
			if (formacaoComplementar.local!= null)
				saida += "    (Local) -> " + formacaoComplementar.local + "\r\n";
				
			saida += espacador;
		}
	
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	if (escavador.areasDeAtuacao != null)
	{
		saida += "�reas de Atua��o: \r\n";
		
		for (AreaDeAtuacao areaDeAtuacao : escavador.areasDeAtuacao)
		{
			if (areaDeAtuacao.grandeArea != null)
				saida += "    (Grande �rea) -> " + areaDeAtuacao.grandeArea + "\r\n";
				
			if (areaDeAtuacao.area != null)
				saida += "    (�rea) -> " + areaDeAtuacao.area + "\r\n";
			
			if (areaDeAtuacao.subArea != null)
				saida += "    (Sub-�rea) -> " + areaDeAtuacao.subArea + "\r\n";
			
			if (areaDeAtuacao.especialidade != null)
				saida += "    (Especialidade) -> " + areaDeAtuacao.especialidade + "\r\n";
				
			saida += espacador;
		}
	
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	if (escavador.producoesBibliograficas != null)
	{
		saida += "Produ��es Bibliogr�ficas: \r\n";
		
		int i = 0;
		for (String producaoBibliografica : escavador.producoesBibliograficas)
			saida += "    (" + i++ + ") -> " + producaoBibliografica + "\r\n";
			
		saida += "\r\n";
	}
	
	if (escavador.projetos != null)
	{
		saida += "Projetos: \r\n";
		
		for (Projeto projeto : escavador.projetos)
		{
			if (projeto.inicio != null)
				saida += "    (In�cio) -> " + projeto.inicio + "\r\n";
				
			if (projeto.fim != null)
				saida += "    (Fim) -> " + projeto.fim + "\r\n";
			
			if (projeto.nome != null)
				saida += "    (Nome) -> " + projeto.nome + "\r\n";
			
			if (projeto.descricao != null)
				saida += "    (Descri��o) -> " + projeto.descricao + "\r\n";
				
			if (projeto.situacao != null)
				saida += "    (Situa��o) -> " + projeto.situacao + "\r\n";
				
			if (projeto.integrantes != null)
				saida += "    (Integrantes) -> " + projeto.integrantes + "\r\n";
				
			if (projeto.financiadores != null)
				saida += "    (Financiadores) -> " + projeto.financiadores + "\r\n";
				
			saida += espacador;
		}
	
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	if (escavador.premios != null)
	{
		saida += "Pr�mios: \r\n";
		
		for (Premio premio : escavador.premios)
		{
			if (premio.data != null)
				saida += "    (Data) -> " + premio.data + "\r\n";
				
			if (premio.nome != null)
				saida += "    (Nome) -> " + premio.nome + "\r\n";
				
			saida += espacador;
		}
	
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	if (escavador.enderecoProfissional != null)
	{
		saida += "Endere�o Profissional: \r\n";

		if (escavador.enderecoProfissional.nome != null)
			saida += "    (Nome) -> " + escavador.enderecoProfissional.nome + "\r\n";
			
		if (escavador.enderecoProfissional.logradouro != null)
			saida += "    (Logradouro) -> " + escavador.enderecoProfissional.logradouro + "\r\n";
			
		if (escavador.enderecoProfissional.bairro != null)
			saida += "    (Bairro) -> " + escavador.enderecoProfissional.bairro + "\r\n";	
			
		if (escavador.enderecoProfissional.CEP != null)
			saida += "    (CEP) -> " + escavador.enderecoProfissional.CEP + "\r\n";	
				
		if (escavador.enderecoProfissional.cidade != null)
			saida += "    (Cidade) -> " + escavador.enderecoProfissional.cidade + "\r\n";	
					
		if (escavador.enderecoProfissional.estado != null)
			saida += "    (Estado) -> " + escavador.enderecoProfissional.estado + "\r\n";	
						
		if (escavador.enderecoProfissional.telefone != null)
			saida += "    (Telefone) -> " + escavador.enderecoProfissional.telefone + "\r\n";	
				
		saida += "\r\n";
	}
	
	if (escavador.atuacoesProfissionais != null)
	{
		saida += "Atua��es Profissionais: \r\n";
		
		for (AtuacaoProfissional atuacaoProfissional : escavador.atuacoesProfissionais)
		{
			if (atuacaoProfissional.inicio != null)
				saida += "    (In�cio) -> " + atuacaoProfissional.inicio + "\r\n";
				
			if (atuacaoProfissional.fim != null)
				saida += "    (Fim) -> " + atuacaoProfissional.fim + "\r\n";
				
			if (atuacaoProfissional.instituicao != null)
				saida += "    (Institui��o) -> " + atuacaoProfissional.instituicao + "\r\n";
					
			if (atuacaoProfissional.vinculo != null)
				saida += "    (V�nculo) -> " + atuacaoProfissional.vinculo + "\r\n";
						
			if (atuacaoProfissional.enquadramento != null)
				saida += "    (Enquadramento) -> " + atuacaoProfissional.enquadramento + "\r\n";
							
			if (atuacaoProfissional.cargaHoraria != null)
				saida += "    (Carga Hor�ria) -> " + atuacaoProfissional.cargaHoraria + "\r\n";
				
			if (atuacaoProfissional.regime != null)
				saida += "    (Regime) -> " + atuacaoProfissional.regime + "\r\n";
				
			saida += espacador;
		}
	
		saida = saida.substring(0, saida.length() - espacador.length()) + "\r\n";
	}
	
	
	
	
	
	
	
	
	if (portalDaTransparencia!= null)
	{
		saida += "Dados do Portal da Transpar�ncia: \r\n";
		
		if (portalDaTransparencia.linkGeral != null)
			saida += "    (Link Geral) -> " + portalDaTransparencia.linkGeral + "\r\n";

		if (portalDaTransparencia.linkRenda != null)
			saida += "    (Link da Renda) -> " + portalDaTransparencia.linkRenda + "\r\n";
				
		if (portalDaTransparencia.CPF != null)
			saida += "    (CPF) -> " + portalDaTransparencia.CPF + "\r\n";
				
		if (portalDaTransparencia.rendaBruta != null)
			saida += "    (Renda Bruta) -> R$ " + portalDaTransparencia.rendaBruta + "\r\n";
			
		if (portalDaTransparencia.ultimaAlteracaoNoCargo != null)
			saida += "    (Ultima Altera��o no Cargo) -> " + portalDaTransparencia.ultimaAlteracaoNoCargo + "\r\n";
	}

	
	saida += "</pre>";
	
	out.print(saida);
}
%>
			
			</div>
		</section>
	</body>
</html>