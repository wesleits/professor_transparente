package br.nom.teixeira.weslei;
import java.io.*;
import java.net.URLDecoder;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import br.nom.teixeira.weslei.outros.Escavador;
import br.nom.teixeira.weslei.outros.Fuseki;
import br.nom.teixeira.weslei.outros.Lattes;
import br.nom.teixeira.weslei.outros.PortalDaTransparencia;
import br.nom.teixeira.weslei.outros.Utilidades;


public class Motor extends HttpServlet implements Filter 
{
	FilterConfig filterConfig = null;
	private ServletContext ctx = null;
	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		this.filterConfig = filterConfig;
		this.ctx = filterConfig.getServletContext();
	}
    
	@Override
	public void destroy()
	{
		
	}
	
	public Motor()
	{
		super();
	}
	
    public InputStream obterRecurso(String resourcePath) 
    {
    	  return ctx.getResourceAsStream(resourcePath);
    }
    
    public void escreverSaida(HttpServletResponse res, String URI) throws IOException
    {
    	InputStream entrada = obterRecurso(URI);

		OutputStream saida = res.getOutputStream();
	    byte[] buffer = new byte[4096];

	    for (int length = 0; (length = entrada.read(buffer)) > 0;) 
	        saida.write(buffer, 0, length);
    }
    
    
    public void escreverSaida(HttpServletResponse res, Lang linguagem, Model modelo) throws IOException
    {
		OutputStream saida = res.getOutputStream();
		
		res.setContentType(linguagem.getContentType().getContentType());
		RDFDataMgr.write(saida, modelo, linguagem);
		
		saida.flush();
		saida.close();
    }
    
    public void escreverSaida(HttpServletResponse res, String nome, Lang linguagem) throws IOException
    {
    	Fuseki db = new Fuseki(nome);
    	
    	if (db.sujeitoExiste())
    		escreverSaida(res, linguagem, db.obterModeloDoSujeito());
    	else
    	{
    		Lattes lattes = new Lattes(nome);
    		if (lattes.resultados == 1)
    		{
    			db.definirSujeito(lattes.nomeCompleto);
    			if (db.sujeitoExiste())
    				escreverSaida(res, linguagem, db.obterModeloDoSujeito());
    			else
    			{
    				Escavador escavador = new Escavador(lattes.nomeCompleto);
    				escavador.extrairDados();
    				
    				PortalDaTransparencia portalDaTransparencia = new PortalDaTransparencia(lattes.nomeCompleto);
    				portalDaTransparencia.extrairDados();
    				
    				db.inserir(lattes, escavador, portalDaTransparencia);
    				escreverSaida(res, linguagem, db.obterModeloDoSujeito());
    			}
    		}
    		else
    			res.setStatus(404);
    	}
    }
    
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException 
	{
		filtrar((HttpServletRequest)arg0, (HttpServletResponse)arg1, arg2);
	}
	
	public void filtrar(HttpServletRequest req, HttpServletResponse res, FilterChain filtro)  throws IOException, ServletException 
	{
		String URI = URLDecoder.decode(req.getRequestURI(), "UTF-8");
		Lang linguagem = Utilidades.obterLinguagem(req.getHeader("Accept"));
	
		switch(URI)
		{
			case "/":
				req.getRequestDispatcher("/index.jsp").forward(req, res);
				break;
				
			case "/dump":
				if (linguagem != null)
				{
					escreverSaida(res, linguagem, new Fuseki().obterModeloCompleto());
					break;
				}

			default:
				File arquivo = new File(ctx.getRealPath(URI));
				
				if(arquivo.exists() && !arquivo.isDirectory())
				{
					if (URI.endsWith(".jsp")) 
						filtro.doFilter(req, res);
					else 
						escreverSaida(res, URI);
				}
				else
				{
					String[] parteURI = URI.split("/");
					
					if (parteURI.length == 2 && !URI.endsWith("/"))
					{
						if (linguagem == null)
						{
							res.setContentType("text/html");
							req.getRequestDispatcher("/dados.jsp?nome=" + parteURI[1]).forward(req, res);
						}
						else
							escreverSaida(res, parteURI[1], linguagem);
					}
					else
						res.setStatus(404);
				}
		}

	}
}
