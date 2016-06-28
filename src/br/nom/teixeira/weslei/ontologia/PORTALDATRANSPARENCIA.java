package br.nom.teixeira.weslei.ontologia;

import org.apache.jena.rdf.model.*;

public class PORTALDATRANSPARENCIA 
{
	public static final String uri ="http://swlab.ic.uff.br/2016/portaldatransparencia-rdf/1.0#";
    private static Model m = ModelFactory.createDefaultModel();
    
    public static final Property CPF = m.createProperty(uri + "CPF");
    public static final Property linkGeral = m.createProperty(uri + "linkGeral");
    public static final Property linkRenda = m.createProperty(uri + "linkRenda");
    public static final Property rendaBruta = m.createProperty(uri + "rendaBruta");
    public static final Property ultimaAlteracaoNoCargo = m.createProperty(uri + "ultimaAlteracaoNoCargo");
}