package br.nom.teixeira.weslei.ontologia;

import org.apache.jena.rdf.model.*;

public class LATTES 
{
	public static final String uri ="http://swlab.ic.uff.br/2016/lattes-rdf/1.0#";
    private static Model m = ModelFactory.createDefaultModel();
    
    public static final Property nome = m.createProperty(uri + "nome");
    public static final Property nomeCompleto = m.createProperty(uri + "nomeCompleto");
    public static final Property link = m.createProperty(uri + "link");
    public static final Property resultados = m.createProperty(uri + "resultados");
}
