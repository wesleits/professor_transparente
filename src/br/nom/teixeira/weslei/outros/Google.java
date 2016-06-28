package br.nom.teixeira.weslei.outros;

import java.net.URLEncoder;

public class Google 
{
	public static String pesquisarLinkLattes(String nomeCompleto)
	{
		try 
		{
			System.out.println("https://www.google.com.br/search?q=" + URLEncoder.encode("\"" + nomeCompleto + "\" site:lattes.cnpq.br", "UTF-8"));
			return Utilidades.obterPagina("https://www.google.com.br/search?q=" + URLEncoder.encode("\"" + nomeCompleto + "\" site:lattes.cnpq.br", "UTF-8"));
		} 
		catch (Exception e){}
		
		return null;
	}
}
