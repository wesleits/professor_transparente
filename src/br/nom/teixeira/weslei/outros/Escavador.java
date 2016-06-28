package br.nom.teixeira.weslei.outros;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

public class Escavador 
{
	public static class Formacao
	{
		public String inicio = null;
		public String fim = null;
		public String tipo = null;
		public String local = null;
		public String TCC = null;
		public String anoTCC = null;
		public String orientador = null;
		public String bolsistaDe = null;
		public String palavrasChave = null;
		public String grandeArea = null;
		public String area = null;
		public String subArea = null;
		public String especialidade = null;
		public String setoresDeAtividade = null;
		
		public Formacao()
		{
			
		}
		
		
		public Formacao(String codigo)
		{
			String[] periodo = Utilidades.obterPrimeiraOcorrencia(codigo, "<p.*>(.*?)</p>").split(" - ");
			inicio = periodo[0];
			fim = periodo[1];

			tipo = Utilidades.obterPrimeiraOcorrencia(codigo, "<h4>(.*?)</h4>");
			local = Utilidades.obterPrimeiraOcorrencia(codigo, "</span> (.*?) </a>");
				
			
			if (codigo.contains("Ano de Obtenção: "))
			{
				TCC = Utilidades.obterPrimeiraOcorrencia(codigo, "Título: (.*?),");
				
				anoTCC = Utilidades.obterPrimeiraOcorrencia(codigo, "Ano de Obtenção: (.*?)<br/>");;	
			}
			else
				TCC = Utilidades.obterPrimeiraOcorrencia(codigo, "Título: (.*?)<br/>");
			
			if (codigo.contains("Orientador: "))
				orientador = Utilidades.obterPrimeiraOcorrencia(codigo, "class=\"nomeOrientador\">(.*?)</a>");
			else
				orientador = Utilidades.obterPrimeiraOcorrencia(codigo, "Título:.*<br/>\n(.*?)\\.Bolsista");
			
			
			bolsistaDe = Utilidades.obterPrimeiraOcorrencia(codigo, "Bolsista do\\(a\\): (.*?)\\.");
			palavrasChave = Utilidades.obterPrimeiraOcorrencia(codigo, "Palavras-chave: (.*?)\\.");
			grandeArea = Utilidades.obterPrimeiraOcorrencia(codigo, "Grande área: (.*?)Grande Área: ");
			area = Utilidades.obterPrimeiraOcorrencia(codigo, "/ Área: (.*?) / ");
			subArea = Utilidades.obterPrimeiraOcorrencia(codigo, "Subárea: (.*?) /");
			especialidade = Utilidades.obterPrimeiraOcorrencia(codigo, "Especialidade: (.*?)\\.");
			setoresDeAtividade = Utilidades.obterPrimeiraOcorrencia(codigo, "Setores de atividade: (.*?)\\.");
			
			/*
			System.out.println(inicio);
			System.out.println(fim);
			System.out.println(tipo);
			System.out.println(local);
			System.out.println(TCC);
			System.out.println(anoTCC);
			System.out.println(orientador);
			System.out.println(bolsistaDe);
			System.out.println(grandeArea);
			System.out.println(area);
			System.out.println(subArea);
			System.out.println(especialidade);
			System.out.println(setoresDeAtividade);
			System.out.println("\r\n\r\n");
			*/
		}
	}
	
	
	public static class PosDoutorado extends Formacao
	{
		public PosDoutorado() 
		{
			
		}
		
		public PosDoutorado(String codigo) 
		{
			String[] periodo = Utilidades.obterPrimeiraOcorrencia(codigo, "<h4>(.*?)</h4>").split(" - ");
			inicio = periodo[0];
			fim = periodo[1];
			
			local = Utilidades.obterPrimeiraOcorrencia(codigo, "Pós-Doutorado. , (.*?)\\.");
			bolsistaDe = Utilidades.obterPrimeiraOcorrencia(codigo, "Bolsista do\\(a\\): (.*?)\\.");
			
			/*
			System.out.println(inicio);
			System.out.println(fim);
			System.out.println(local);
			System.out.println(bolsistaDe);
			*/
		}
	}
	
	public static class Complementar extends Formacao
	{
		public String cargaHoraria = null;
		
		public Complementar()
		{
			
			
		}
		
		public Complementar(String codigo) 
		{
			String[] periodo = Utilidades.obterPrimeiraOcorrencia(codigo, "<h4>(.*?)</h4>").split(" - ");
			inicio = periodo[0];
			fim = periodo[1];
			
			tipo = Utilidades.obterPrimeiraOcorrencia(codigo, "<p>\n(.*?)\\. \\(");
			cargaHoraria = Utilidades.obterPrimeiraOcorrencia(codigo, "\\(Carga horária: (.*?)\\)");
			local = Utilidades.obterPrimeiraOcorrencia(codigo, "\\. , (.*?)\\.");
			
			/*
			System.out.println(inicio);
			System.out.println(fim);
			System.out.println(tipo);
			System.out.println(cargaHoraria);
			System.out.println("\r\n\r\n");	
			*/
		}
	}
	
	
	public static class AreaDeAtuacao
	{
		public String grandeArea = null;
		public String area = null;
		public String subArea = null;
		public String especialidade = null;
		
		public AreaDeAtuacao() 
		{
			
		}
		
		public AreaDeAtuacao(String codigo) 
		{
			grandeArea = Utilidades.obterPrimeiraOcorrencia(codigo, "Grande área: (.*?) / ");
			area = Utilidades.obterPrimeiraOcorrencia(codigo, "Área: (.*?) / ");
			subArea = Utilidades.obterPrimeiraOcorrencia(codigo, "Subárea: (.*?)/");
			especialidade = Utilidades.obterPrimeiraOcorrencia(codigo, "Especialidade: (.*?)\\.");
			
			/*
			System.out.println(grandeArea);
			System.out.println(area);
			System.out.println(subArea);
			System.out.println(especialidade);
			System.out.println("\r\n\r\n");	
			*/
		}
	}

	public static class AtuacaoProfissional
	{
		public String inicio = null;
		public String fim = null;
		public String instituicao = null;
		public String vinculo = null;
		public String enquadramento = null;
		public String cargaHoraria = null;
		public String regime = null;
		
		public AtuacaoProfissional()
		{

		}
		
		public AtuacaoProfissional(String codigo)
		{
			String[] periodo = Utilidades.obterPrimeiraOcorrencia(codigo, "<p.*>(.*?)</p>").split(" - ");
			inicio = periodo[0];
			fim = periodo[1];
			
			instituicao = Utilidades.obterPrimeiraOcorrencia(codigo, "</span> (.*?)</a>");
			vinculo = Utilidades.obterPrimeiraOcorrencia(codigo, "Vínculo: (.*?), ");
			enquadramento = Utilidades.obterPrimeiraOcorrencia(codigo, "Enquadramento Funcional: (.*?), ");
			cargaHoraria = Utilidades.obterPrimeiraOcorrencia(codigo, "Carga horária: (\\d+)");
			regime = Utilidades.obterPrimeiraOcorrencia(codigo, "Regime: (.*?)\\. ");
			
			/*
			System.out.println(inicio);
			System.out.println(fim);
			System.out.println(instituicao);
			System.out.println(viculo);
			System.out.println(enquadramento);
			System.out.println(cargaHoraria);
			System.out.println(regime);
			System.out.println("\n\n\n\n");
			*/
		}
	}
	
	
	
	public static class LinhaDePesquisa
	{
		public String titulo = null;
		public String objetivo = null;
		public String palavrasChave = null;
	}
	
	
	public static class Projeto
	{
		public String inicio = null;
		public String fim = null;
		public String nome = null;
		public String descricao = null;
		public String situacao = null;
		public String integrantes = null;
		public String financiadores = null;
		
		
		public Projeto()
		{
			
		}
		
		public Projeto(String codigo)
		{
			String[] periodo = Utilidades.obterPrimeiraOcorrencia(codigo, "<h4>(.*?)</h4>").split(" - ");
			inicio = periodo[0];
			fim = periodo[1];
			
			nome = Utilidades.obterPrimeiraOcorrencia(codigo, "<p> (.*?), Descrição:");
			descricao = Utilidades.obterPrimeiraOcorrencia(codigo, "Descrição: (.*?), Situação:");
			situacao = Utilidades.obterPrimeiraOcorrencia(codigo, "Situação: (.*?);");
			integrantes = Utilidades.obterPrimeiraOcorrencia(codigo, "Integrantes: (.*?), ");
			financiadores = Utilidades.obterPrimeiraOcorrencia(codigo, "Financiador\\(es\\): (.*?)\\.");
			
			/*
			System.out.println(inicio);
			System.out.println(fim);
			System.out.println(nome);
			System.out.println(descricao);
			System.out.println(integrantes);
			System.out.println(financiadores);
			
			System.out.println("\n\n\n\n");
			*/
		}
	}

	public static class Idioma
	{
		public String nome = null;
		public String habilidades = null;
		
		
		public Idioma()
		{

		}
		
		public Idioma(String codigo)
		{
			//System.out.println(codigo);
			
			nome = Utilidades.obterPrimeiraOcorrencia(codigo, 
					"(?s)<h4><img .*?> (.*?)</h4>");
			
			habilidades = Utilidades.obterPrimeiraOcorrencia(codigo, 
					"<p>\n(.*?)\n</p>");
			
			//System.out.println(nome);
			//System.out.println(habilidades);
		}
	}
	
	public static class Premio
	{
		public String data = null;
		public String nome = null;
		
		public Premio()
		{
				
		}
		
		public Premio(String codigo)
		{
			data = Utilidades.obterPrimeiraOcorrencia(codigo, "<h4>(.*?)</h4>");
			nome = Utilidades.obterPrimeiraOcorrencia(codigo, "<p> (.*?)</p>");
			
			/*
			System.out.println(data);
			System.out.println(nome);
			*/
		}
	}
	
	public static class EnderecoProfissional
	{
		public String nome = null;
		public String logradouro = null;
		public String bairro = null;
		public String CEP = null;
		public String cidade = null;
		public String estado = null;
		public String telefone = null;
		
		public EnderecoProfissional()
		{
			
		}
		
		public EnderecoProfissional(String codigo)
		{
			nome = Utilidades.obterPrimeiraOcorrencia(codigo, "(.*?)\\.");
			logradouro = Utilidades.obterPrimeiraOcorrencia(codigo, " , (.*?),");
			bairro = Utilidades.obterPrimeiraOcorrencia(codigo, " , .*?, (.*?),");
			CEP = Utilidades.obterPrimeiraOcorrencia(codigo, " , .*?, .*?, (.*?) - ");
			cidade = Utilidades.obterPrimeiraOcorrencia(codigo, " , .*?, .*?, .*? - (.*?),");
			estado = Utilidades.obterPrimeiraOcorrencia(codigo, " - .*, (.*?) -");
			telefone = Utilidades.obterPrimeiraOcorrencia(codigo, "Telefone: (.*?),");

			/*
			System.out.println(nome);
			System.out.println(logradouro);
			System.out.println(bairro);
			System.out.println(CEP);
			System.out.println(cidade);
			System.out.println(estado);
			System.out.println(telefone);
			*/
		}
	}
	
	public String html = null;
	public String nome = null;
	public String link = null;
	public String descricao = null;
	public List<Idioma> idiomas = null;
	public List<Formacao> formacoes = null;
	public PosDoutorado posDoutorado = null;
	public List<Complementar> formacoesComplementares = null;
	public List<AreaDeAtuacao> areasDeAtuacao = null;
	public List<String> producoesBibliograficas = null;
	public List<Projeto> projetos = null;
	public List<Premio> premios = null;
	public EnderecoProfissional enderecoProfissional = null;
	public List<AtuacaoProfissional> atuacoesProfissionais = null;
	
	public Escavador()
	{
	}
	
	
	public Escavador(String nome)
	{
		link = obterLinkDoPrimeiroResultado(nome);
		html = Utilidades.obterPagina(link, "UTF-8");
		html = StringEscapeUtils.unescapeHtml4(html);
	}
	
	public static String obterLinkDoPrimeiroResultado(String nome)
	{
		String html = null;
		try 
		{
			html = Utilidades.obterPagina("http://www.escavador.com/busca?qo=p&q=" + URLEncoder.encode("\"" + nome + "\"", "UTF-8"));
		} 
		catch (Exception e){}
		
		return Utilidades.obterSubstringEntre(html, 
				"<li class=\"buscaBox\">\n<a href=\"", 
				"\" class=\"buscaTitulo\">");
	}
	
	public void extrairNome()
	{
		nome = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<span class=\"icon iconPessoa\">\n</span>\n(.*?)\n</h1>");
	}

	public void extrairDescricao()
	{
		descricao = Utilidades.obterSubstringEntre(html, 
				"//]]>--></script>\n</div>\n<p>", 
				"<br/></p>\n</div>\n");
	} 
	
	public void extrairIdiomas()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"idiomas\" class=\"linkScroll\">.*?<ul class=\"lista\">(.*?)</ul>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li>(.*?)</li>");
		
		if (codigos.size() > 0)
		{
			idiomas = new ArrayList<Idioma>();
		
			for (String codigo : codigos)
				idiomas.add(new Idioma(codigo));
		}
	}
	
	public void extrairFormacoes()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)(<div id=\"formacao\" class=\"linkScroll\">.*?</div>)");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)(<li>.*?</li>)");
		
		if (codigos.size() > 0)
		{
			formacoes = new ArrayList<Formacao>();
		
			for (String codigo : codigos)
				formacoes.add(new Formacao(codigo));
		}
	}
	
	public void extrairPosDoutorado()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"pos-doutorado\" class=\"linkScroll\">(.*?)</div>");

		if (bloco != null)
			posDoutorado = new PosDoutorado(bloco);
	}
	
	public void extrairFormacoesComplementares()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"formacao-complementar\" class=\"linkScroll\">(.*?)</div>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li>(.*?)</li>");
		
		if (codigos.size() > 0)
		{
			formacoesComplementares = new ArrayList<Complementar>();
		
			for (String codigo : codigos)
				formacoesComplementares.add(new Complementar(codigo));
		}	
	}
	
	public void extrairAreasDeAtuacao()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"areas_atuacao\" class=\"linkScroll\">(.*?)</div>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li>(.*?)</li>");
		
		if (codigos.size() > 0)
		{
			areasDeAtuacao = new ArrayList<AreaDeAtuacao>();
		
			for (String codigo : codigos)
				areasDeAtuacao.add(new AreaDeAtuacao(codigo));
		}	
	}
	
	public void extrairProducoesBibliograficas()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"producoes\" class=\"linkScroll\">(.*?)</div>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<p>\n(.*?)\n</p>");
		
		if (codigos.size() > 0)
		{
			producoesBibliograficas = new ArrayList<String>();
		
			for (String codigo : codigos)
			{
				codigo = codigo.replaceAll("\\r\\n|\\r|\\n", "");
				producoesBibliograficas.add(codigo);
				//System.out.println(codigo);
				//System.out.println("\n\n\n\n");
			}
		}	
	}
	
	public void extrairProjetos()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"projetos\" class=\"linkScroll\">(.*?)</div>");
		
		
		//System.out.println(bloco);
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li.*?>(.*?)</li>");
		 
		if (codigos.size() > 0)
		{
			projetos = new ArrayList<Projeto>();
		
			for (String codigo : codigos)
			{
				codigo = codigo.replaceAll(" \\r\\n| \\r| \\n", "").replaceAll("\\r\\n|\\r|\\n", " ");
				//System.out.println(codigo);
				projetos.add(new Projeto(codigo));
				
				//System.out.println("\n\n\n\n");
			}
		}	 
	}
	
	public void extrairPremios()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div id=\"premios\" class=\"linkScroll\">(.*?)</div>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li>(.*?)</li>");
		 
		if (codigos.size() > 0)
		{
			premios = new ArrayList<Premio>();
		
			for (String codigo : codigos)
			{
				codigo = codigo.replaceAll(" \\r\\n| \\r| \\n", "").replaceAll("\\r\\n|\\r|\\n", " ");
				//System.out.println(codigo);
				//System.out.println("\n\n\n\n");
				premios.add(new Premio(codigo));
			}
		}	 
	}
	
	public void extrairEnderecoProfissional()
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, 
				"(?s)<h3 class=\"subTitulo\">Endereço profissional</h3>.*?<p class=\"descricaoSection\">(.*?)</p>");

		if (bloco != null)
		{
			//System.out.println(bloco);
			//System.out.println("\n\n\n\n");
			enderecoProfissional = new EnderecoProfissional(bloco);
		}	 
	}
	
	
	public void extrairAtuacaoProfissional()
	{
		String bloco = Utilidades.remover("(?s)(<div class=\"atividades\">.*?</div>)", html);
		
		
		//System.out.println(bloco);
		
		bloco = Utilidades.obterPrimeiraOcorrencia(bloco, 
				"(?s)<div.*?<h3 class=\"subTitulo\">Atuação profissional</h3>(.*?)</div>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<li class=\"atuacaoProfissional\">(.*?)</li>");
		 
		//System.out.println(bloco);
		
		///*
		if (codigos.size() > 0)
		{
			atuacoesProfissionais = new ArrayList<AtuacaoProfissional>();
		
			for (String codigo : codigos)
			{
				atuacoesProfissionais.add(new AtuacaoProfissional(codigo));
				//System.out.println(codigo);
				//System.out.println("\n\n\n\n");
			}
		}
		//*/
	}
	
	public void extrairDados()
	{
		extrairNome();
		extrairDescricao();
		extrairIdiomas();
		extrairFormacoes();
		extrairPosDoutorado();
		extrairFormacoesComplementares();
		extrairAreasDeAtuacao();
		extrairProducoesBibliograficas();
		extrairProjetos();
		extrairPremios();
		extrairEnderecoProfissional();
		extrairAtuacaoProfissional();
	}
}