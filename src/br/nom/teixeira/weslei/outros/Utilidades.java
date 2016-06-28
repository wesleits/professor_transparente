package br.nom.teixeira.weslei.outros;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.apache.jena.riot.Lang;

public class Utilidades 
{
	
    public static String converterInputStreamParaString(InputStream is) 
    {
    	return converterInputStreamParaString(is, Charset.defaultCharset().toString());
    }
	
    public static String converterInputStreamParaString(InputStream is, String codificacao) 
    {
        Scanner s = new Scanner(is, codificacao);
        s.useDelimiter("\\A");
        String resultado = s.hasNext() ? s.next() : "";
        s.close();
        return resultado;
    }
  
    public static String obterSubstringEntre(String str, String delimitador1, String delimitador2) 
    {
        if (str == null || delimitador1 == null || delimitador2 == null)
            return null;

        int origem = str.indexOf(delimitador1);
        if (origem != -1)
        {
            int fim = str.indexOf(delimitador2, origem + delimitador1.length());
            if (fim != -1) 
                return str.substring(origem + delimitador1.length(), fim);
        }
        
        return null;
    }
    
    
	public static String obterPrimeiraOcorrencia(String texto, String regex) 
	{
		try
		{
	        Matcher m = Pattern.compile(regex).matcher(texto);
	        m.find();
	        return m.group(1);
		}
		catch (Exception e){};
		
		return null;
    }
	
	public static String remover(String regex, String content) 
	{
	    Pattern p = Pattern.compile(regex);
	    return p.matcher(content).replaceAll("");
	}
    
	public static List<String> obterTodasAsOcorrencias(String texto, String regex) 
	{
		List<String> correspondente = new ArrayList<String>();
		
		try
		{
	        Matcher m = Pattern.compile(regex).matcher(texto);
	        while(m.find())
	        	correspondente.add(m.group(1));
		}
		catch (Exception e){};
        
        return correspondente;
    }
    
    public static String obterPagina(String URL)
    {
		return obterPagina(URL, Charset.defaultCharset().toString());
    }
    
    public static String obterPagina(String URL, String codificacao)
    {
        try 
        {
        	URL obj = new URL(URL);
        	
        	if (URL.startsWith("https://"))
        	{
        		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
            	conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
    			return converterInputStreamParaString(conn.getInputStream(), codificacao);
        	}
        	else
        	{
        		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            	conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
    			return converterInputStreamParaString(conn.getInputStream(), codificacao);
        	}		
		} 
        catch (Exception e){}
		
		return null;
    }
    
	public static String enviarPOST(String requestURL, Map<String, String> params) throws IOException 
	{
        URL url = new URL(requestURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoInput(true); // true indicates the server returns response
 
        StringBuffer requestParams = new StringBuffer();
 
        if (params != null && params.size() > 0) 
        {
            httpConn.setDoOutput(true); // true indicates POST request
 
            // creates the params string, encode them using URLEncoder
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) 
            {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, Charset.defaultCharset().toString()));
                requestParams.append("=").append(
                        URLEncoder.encode(value, Charset.defaultCharset().toString()));
                requestParams.append("&");
            } 
 
            // sends POST data
            OutputStreamWriter writer = new OutputStreamWriter(
                    httpConn.getOutputStream());
            writer.write(requestParams.toString());
            writer.flush();
        }
 
        return converterInputStreamParaString(httpConn.getInputStream());
    }
	
	
	public static Lang obterLinguagem(String accept) 
	{
		Lang[] langs = {Lang.TURTLE, Lang.TRIG, Lang.TRIX, Lang.JSONLD, Lang.RDFJSON, Lang.RDFXML,
				Lang.RDFTHRIFT, Lang.NQUADS, Lang.NTRIPLES};
		
		for (Lang lang : langs)
			if (accept.toLowerCase().contains(lang.getHeaderString().toLowerCase()))
				return lang;
		return null;
	}
	
    public static String MD5(String texto) 
    {
    	MessageDigest md = null;
		try 
		{
			md = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException e){}
		
	    byte[] messageDigest = md.digest(texto.getBytes());

	    BigInteger number = new BigInteger(1, messageDigest);

	    return String.format("%032x", number);
    }
}
