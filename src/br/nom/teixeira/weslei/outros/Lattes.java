package br.nom.teixeira.weslei.outros;

import java.util.HashMap;
import java.util.Map;

public class Lattes 
{
	public String nome = null;
	public String nomeCompleto = null;
	public String html = null;
	public String link = null;
	public int resultados = 0;
	
	public Lattes()
	{
		
	}
	
	public Lattes(String nome)
	{
		extrair(nome);
	}
	
	public void extrair(String nome)
	{
		this.nome = nome;
		this.html = buscar(nome);
		this.resultados = obterQuantidadeDeResultados(this.html);
		this.nomeCompleto = obterNomeCompleto(this.html);
		this.link = obterLink(this.nomeCompleto);
	}
	
	public static String obterLink(String nomeCompleto)
	{
		String html = Google.pesquisarLinkLattes(nomeCompleto);
		//System.out.println(html);
		return Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div class=\"g\"><!--m--><div class=\"rc\" data-hveid=\"29\"><h3 class=\"r\"><a href=\"(.*?)\"");
	}
	
	public static String obterNomeCompleto(String html)
	{
		return Utilidades.obterPrimeiraOcorrencia(html, "(?s)<div class=\"resultado\">.*?<a .*?>(.*?)</a>");
	}
	
	public static String buscar(String nome)
	{
		 Map<String, String> parametros = new HashMap<String, String>();
		 parametros.put("acao", "");
		 parametros.put("buscaAvancada", "0");
		 parametros.put("buscarBrasileiros", "true");
		 parametros.put("buscarBrasileirosAvancada", "true");
		 parametros.put("buscarDoutores", "true");
		 parametros.put("buscarDoutoresAvancada", "true");
		 parametros.put("buscarEstrangeirosAvancada", "true");
		 parametros.put("filtros.areaProducao", "0");
		 parametros.put("filtros.atualizacaoCurriculo", "48");
		 parametros.put("filtros.buscaAtuacao", "false");
		 parametros.put("filtros.buscaNome", "true");
		 parametros.put("filtros.categoriaNivelBolsa", "");
		 parametros.put("filtros.codigoAreaAtuacao", "0");
		 parametros.put("filtros.codigoEspecialidadeAtuacao", "0");
		 parametros.put("filtros.codigoGrandeAreaAtuacao", "0");
		 parametros.put("filtros.codigoSubareaAtuacao", "0");
		 parametros.put("filtros.conceitoCurso", "");
		 parametros.put("filtros.grandeAreaProducao", "0");
		 parametros.put("filtros.idioma", "0");
		 parametros.put("filtros.modalidadeBolsa", "0");
		 parametros.put("filtros.naturezaAtividade", "0");
		 parametros.put("filtros.nivelFormacao", "0");
		 parametros.put("filtros.nomeInstAtividade", "");
		 parametros.put("filtros.nomeInstFormacao", "");
		 parametros.put("filtros.orientadorCNPq", "");
		 parametros.put("filtros.paisAtividade", "0");
		 parametros.put("filtros.paisFormacao", "0");
		 parametros.put("filtros.radioPeriodoProducao", "1");
		 parametros.put("filtros.regiaoAtividade", "0");
		 parametros.put("filtros.regiaoFormacao", "0");
		 parametros.put("filtros.setorProducao", "0");
		 parametros.put("filtros.ufAtividade", "0");
		 parametros.put("filtros.ufFormacao", "0");
		 parametros.put("filtros.visualizaAreasAtuacaoCV", "true");
		 parametros.put("filtros.visualizaArtigosCV", "true");
		 parametros.put("filtros.visualizaAtuacaoProfCV", "true");
		 parametros.put("filtros.visualizaDadosComplementaresCV", "true");
		 parametros.put("filtros.visualizaDemaisTrabalhosCV", "true");
		 parametros.put("filtros.visualizaEnderecoCV", "true");
		 parametros.put("filtros.visualizaFormacaoAcadTitCV", "true");
		 parametros.put("filtros.visualizaIdiomasCV", "true");
		 parametros.put("filtros.visualizaLivrosCapitulosCV", "true");
		 parametros.put("filtros.visualizaOrientacoesAndamentoCV", "true");
		 parametros.put("filtros.visualizaOrientacoesConcluidasCV", "true");
		 parametros.put("filtros.visualizaOutrasInfRelevantesCV", "true");
		 parametros.put("filtros.visualizaOutrasProdBibCV", "true");
		 parametros.put("filtros.visualizaOutrasProdTecCV", "true");
		 parametros.put("filtros.visualizaPeriodoProducaoCV", "");
		 parametros.put("filtros.visualizaPremiosTitulosCV", "true");
		 parametros.put("filtros.visualizaProcessosCV", "true");
		 parametros.put("filtros.visualizaProdArtCultCV", "true");
		 parametros.put("filtros.visualizaProdutosCV", "true");
		 parametros.put("filtros.visualizaSoftwaresCV", "true");
		 parametros.put("filtros.visualizaTrabEventosCV", "true");
		 parametros.put("filtros.visualizaTrabalhosTecnicosCV", "true");
		 parametros.put("filtros.visualizaTxtJornalRevistaCV", "true");
		 parametros.put("metodo", "buscar");
		 parametros.put("modoIndAdhoc", "");
		 parametros.put("paisNascimento", "0");
		 parametros.put("paisNascimentoAvancada", "0");
		 parametros.put("quantidadeRegistros", "10");
		 parametros.put("resumoAtividade", "");
		 parametros.put("resumoAtuacao", "");
		 parametros.put("resumoFormacao", "");
		 parametros.put("resumoIdioma", "");
		 parametros.put("resumoModalidade", "");
		 parametros.put("resumoPesquisador", "");
		 parametros.put("resumoPresencaDGP", "");
		 parametros.put("resumoProducao", "");
		 parametros.put("textoBusca", nome);
		 parametros.put("textoBuscaFrase", "");
		 parametros.put("textoBuscaNenhuma", "");
		 parametros.put("textoBuscaQualquer", "");
		 parametros.put("textoBuscaTodas", "");
		 parametros.put("textoExpressao", "");
	        
	        
		 String resultado = "";
		 try 
		 {
			 resultado = Utilidades.enviarPOST("http://buscatextual.cnpq.br/buscatextual/busca.do", parametros);
		 } 
		 catch (Exception e){}
	     
		 return resultado;
	}

	public static int obterQuantidadeDeResultados(String html)
	{
		if (html != null)
		{
			html = html.replace("\t", "").replace(" ", "");
			
			String resultado = Utilidades.obterSubstringEntre(html, "\r\n<b>", "</b>\r\nencontrado");
			
			if (resultado != null)
				return Integer.parseInt(resultado);
		}
		
		return 0;
	}

	
	public boolean nomeExiste()
	{ 
	     return (resultados > 0);
	}
	
	public static boolean nomeExisteNoLattes(String html)
	{ 
	     return (obterQuantidadeDeResultados(html) > 0);
	}
}
