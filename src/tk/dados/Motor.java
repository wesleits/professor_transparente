package tk.dados;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tk.dados.paginas.Dados;
import tk.dados.paginas.Index;

@WebServlet("/Principal")
public class Motor extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	String showPage = "assets/index.jsp";
       
    public Motor() 
    {
        super();
    }
    
    public InputStream obterRecurso(String resourcePath) 
    {
    	  ServletContext servletContext = getServletContext();
    	  InputStream openStream = servletContext.getResourceAsStream(resourcePath);
    	  return openStream;
    }
    
    
    public String convertStreamToString(InputStream is) 
    {
        java.util.Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
 
    public void retonarPaginaDoAsset(HttpServletResponse res, String URI) throws IOException
    {
    	InputStream entrada = obterRecurso("assets" + URI);

		OutputStream saida = res.getOutputStream();
	    byte[] buffer = new byte[4096];

	    for (int length = 0; (length = entrada.read(buffer)) > 0;) 
	        saida.write(buffer, 0, length);
    }
    
    
    public void retonarString(HttpServletResponse res, String html) throws IOException
    {
    	OutputStream saida = res.getOutputStream();
    	saida.write(html.getBytes(), 0, html.length());
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		System.out.println(req.getRequestURI());
		
		String URI = req.getRequestURI();
		
		switch(URI)
		{
		
			case "/index.jsp":
			case "/":
			case "/index":
				retonarString(res, Index.obterHTML());
				break;
				
			case "/dados.jsp":
			case "/dados":
				retonarString(res, Dados.obterHTML());
				break;
				
			default:
				retonarPaginaDoAsset(res, URI);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
}
