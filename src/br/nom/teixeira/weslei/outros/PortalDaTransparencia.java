package br.nom.teixeira.weslei.outros;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

public class PortalDaTransparencia 
{
	public String nomeCompleto = null;
	public String CPF = null;
	public String htmlPesquisa = null;
	public String linkGeral = null;
	public String htmlGeral = null;
	public String linkRenda = null;
	public String htmlRenda = null;
	public String rendaBruta = null;
	public String ultimaAlteracaoNoCargo = null;
	public int resultados = 0;
	
	public PortalDaTransparencia()
	{
		
	}
	
	public PortalDaTransparencia(String nomeCompleto)
	{
		this.nomeCompleto = nomeCompleto;
	}
	
	public void extrairDados()
	{
		
		this.htmlPesquisa = obterHTMLdaPesquisa(this.nomeCompleto);
		this.resultados = obterQuantidadeDeResultados(this.htmlPesquisa);
		
		if (this.resultados == 1)
		{
			this.linkGeral = obterLinkGeral(this.htmlPesquisa);
			this.htmlGeral = obterHTMLGeral(this.linkGeral);
			
			this.CPF = obterCPF(this.htmlGeral);
			this.ultimaAlteracaoNoCargo = obterUltimaAlteracaoNoCargo(this.htmlGeral);
			
			this.linkRenda = obterLinkRenda(this.htmlGeral);
			this.htmlRenda = obterHTMLRenda(this.linkRenda);
			
			this.rendaBruta = obterRendaBruta(this.htmlRenda);
		}
	}
	
	public static int obterQuantidadeDeResultados(String html)
	{
		String bloco = Utilidades.obterPrimeiraOcorrencia(html, "(?s)<table summary=\"Lista de Servidores\">.*?</tr>(.*?)</table>");
		List<String> codigos = Utilidades.obterTodasAsOcorrencias(bloco, "(?s)<tr.*?<td><a href=\"(.*?)</tr>");

		return codigos.size();
	}
	
	public static String obterHTMLdaPesquisa(String nomeCompleto)
	{
		try 
		{
			return Utilidades.obterPagina("http://transparencia.gov.br/servidores/Servidor-ListaServidores.asp?bogus=1&Pagina=1&TextoPesquisa=" + URLEncoder.encode(nomeCompleto, Charset.defaultCharset().toString()));
		} 
		catch (Exception e){};
		
		return null;
	}
	
	public static String obterLinkGeral(String html)
	{
		return "http://transparencia.gov.br/servidores/" + 
			Utilidades.obterPrimeiraOcorrencia(html, "(?s)<td class=\"firstChild\">.*?<td><a href=\"(.*?)\"");
	}
	
	public String obterHTMLGeral(String link)
	{
		try 
		{
			return Utilidades.obterPagina(link);
		} 
		catch (Exception e){};
		
		return null;
	}

	public static String obterLinkRenda(String html)
	{
		return "http://transparencia.gov.br" + 
			Utilidades.obterPrimeiraOcorrencia(html, "(?s)</div>.*?<div id=\"resumo\">.*?<a href=\"(.*?)\"");
	}
	
	public String obterHTMLRenda(String link)
	{
		try 
		{
			return Utilidades.obterPagina(link);
		} 
		catch (Exception e){};
		
		return null;
	}
	
	
	public String obterCPF(String html)
	{
		return Utilidades.obterPrimeiraOcorrencia(html, "(?s)<nobr> CPF:</nobr> </td>.*?<td class=\"colunaValor\" style=\"text-align: left;\">(.*?)</td>").replaceAll("\\r\\n|\\r|\\n", "").replaceAll(" ", "");
	}
	
	public String obterRendaBruta(String html)
	{
		return Utilidades.obterPrimeiraOcorrencia(html, "(?s)<td colspan=\"2\" style=\"width: 45%;\" class=\"\">Remuneração básica bruta</td>.*?<td class=\"colunaValor\">(.*?)</td>");
	}
	
	public String obterUltimaAlteracaoNoCargo(String html)
	{
		return Utilidades.obterPrimeiraOcorrencia(html, "(?s)<td class=\"tituloDetalhe\"  >Data da Última Alteração no Cargo:</td>.*?<td><strong>(.*?)</strong></td>").replaceAll(" ", "");
	}

}
