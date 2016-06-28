package br.nom.teixeira.weslei.ontologia;

import org.apache.jena.rdf.model.*;


public class ESCAVADOR 
{
	static public class Idioma
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/idioma#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property nome = m.createProperty(uri + "nome");
		public static final Property habilidades = m.createProperty(uri + "habilidades" );
	}
	
	static public class Formacao
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/formacao#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property inicio = m.createProperty(uri + "inicio");
		public static final Property fim = m.createProperty(uri + "fim");
		public static final Property tipo = m.createProperty(uri + "tipo");
		public static final Property local = m.createProperty(uri + "local");
		public static final Property TCC = m.createProperty(uri + "TCC");
		public static final Property anoTCC = m.createProperty(uri + "anoTCC");
		public static final Property orientador = m.createProperty(uri + "orientador");
		public static final Property bolsistaDe = m.createProperty(uri + "bolsitaDe");
		public static final Property palavrasChave = m.createProperty(uri + "palavrasChave");
		public static final Property grandeArea = m.createProperty(uri + "grandeArea");
		public static final Property area = m.createProperty(uri + "area");
		public static final Property subArea = m.createProperty(uri + "subArea");
		public static final Property especialidade = m.createProperty(uri + "especialidade");
		public static final Property setoresDeAtividade = m.createProperty(uri + "setoresDeAtividade");
	}
	
	static public class PosDoutorado
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/posdoutorado#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property inicio = m.createProperty(uri + "inicio");
		public static final Property fim = m.createProperty(uri + "fim");
		public static final Property local = m.createProperty(uri + "local");
		public static final Property bolsistaDe = m.createProperty(uri + "bolsistaDe");
		
	}
	
	static public class FormacaoComplementar
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/formacaoComplementar#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property inicio = m.createProperty(uri + "inicio");
		public static final Property fim = m.createProperty(uri + "fim");
		public static final Property tipo = m.createProperty(uri + "tipo");
		public static final Property cargaHoraria = m.createProperty(uri + "cargaHoraria");
		public static final Property local = m.createProperty(uri + "local");
	}
	
	static public class AreaDeAtuacao
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/areadeatuacao#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property grandeArea = m.createProperty(uri + "grandeArea");
		public static final Property area = m.createProperty(uri + "area");
		public static final Property subArea = m.createProperty(uri + "subArea");
		public static final Property especialidade = m.createProperty(uri + "especialidade");	
	}
	
	static public class Projeto
	{
        public static final String uri ="http://dbpedia.org/ontology/ResearchProject#";
        public static final String uri2 ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/projeto#";
        private static Model m = ModelFactory.createDefaultModel();
          
          
        public static final Property inicio = m.createProperty(uri + "projectStartDate");
        public static final Property fim = m.createProperty(uri + "projectEndDate");
        public static final Property nome = m.createProperty(uri2 + "nome");
        public static final Property descricao = m.createProperty(uri2 + "descricao");
        public static final Property situacao = m.createProperty(uri2 + "situacao");
        public static final Property integrantes = m.createProperty(uri + "projectParticipant");
        public static final Property financiadores = m.createProperty(uri2 + "financiadores");
	}
	
	static public class Premio
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/premio#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property data = m.createProperty(uri + "data");
		public static final Property nome = m.createProperty(uri + "nome");
	}
	
	
	static public class EnderecoProfissional
	{
        public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/atuacaoprofissional#";
        public static final String uri2 ="http://dbpedia.org/ontology/City#";
        private static Model m = ModelFactory.createDefaultModel();
          
        public static final Property nome = m.createProperty(uri + "nome");
        public static final Property logradouro = m.createProperty(uri + "logradouro");
        public static final Property bairro = m.createProperty(uri + "bairro");
        public static final Property CEP = m.createProperty(uri + "CEP");
        public static final Property cidade = m.createProperty(uri2 + "city");
        public static final Property estado = m.createProperty(uri + "estado");
        public static final Property telefone = m.createProperty(uri + "telefone");
	}
	
	static public class AtuacaoProfissional
	{
		public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0/atuacaoprofissional#";
		private static Model m = ModelFactory.createDefaultModel();
		
		public static final Property inicio = m.createProperty(uri + "inicio");
		public static final Property fim = m.createProperty(uri + "fim");
		public static final Property instituicao = m.createProperty(uri + "instituicao");
		public static final Property vinculo = m.createProperty(uri + "vinculo");
		public static final Property enquadramento = m.createProperty(uri + "enquadramento");
		public static final Property cargaHoraria = m.createProperty(uri + "cargaHoraria");
		public static final Property regime = m.createProperty(uri + "regime");
	}
	

	public static final String uri ="http://swlab.ic.uff.br/2016/escavador-rdf/1.0#";
    private static Model m = ModelFactory.createDefaultModel();
   
    public static final Property html = m.createProperty(uri + "html");
    public static final Property nome = m.createProperty(uri + "nome" );
    public static final Property link = m.createProperty(uri + "link");
    public static final Property descricao = m.createProperty(uri + "descricao");
    public static final Property idioma = m.createProperty(uri + "idioma");
    public static final Property formacao = m.createProperty(uri + "formacao");
    public static final Property posDoutorado = m.createProperty(uri + "posDoutorado");
    public static final Property formacaoComplementar = m.createProperty(uri, "formacaoComplementar");
    public static final Property areaDeAtuacao = m.createProperty(uri, "areaDeAtuacao");
    public static final Property producaoBibliografica = m.createProperty(uri, "producaoBibliografica");
    public static final Property projeto = m.createProperty(uri, "projetos");
    public static final Property premio = m.createProperty(uri, "premios");
    public static final Property enderecoProfissional = m.createProperty(uri, "enderecoProfissional");
    public static final Property atuacaoProfissional = m.createProperty(uri, "atuacaoProfissional");
}