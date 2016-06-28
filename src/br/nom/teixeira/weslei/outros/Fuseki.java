package br.nom.teixeira.weslei.outros;


import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import br.nom.teixeira.weslei.ontologia.ESCAVADOR;
import br.nom.teixeira.weslei.ontologia.LATTES;
import br.nom.teixeira.weslei.ontologia.PORTALDATRANSPARENCIA;
import br.nom.teixeira.weslei.outros.Escavador.AreaDeAtuacao;
import br.nom.teixeira.weslei.outros.Escavador.AtuacaoProfissional;
import br.nom.teixeira.weslei.outros.Escavador.Complementar;
import br.nom.teixeira.weslei.outros.Escavador.EnderecoProfissional;
import br.nom.teixeira.weslei.outros.Escavador.Formacao;
import br.nom.teixeira.weslei.outros.Escavador.Idioma;
import br.nom.teixeira.weslei.outros.Escavador.PosDoutorado;
import br.nom.teixeira.weslei.outros.Escavador.Premio;
import br.nom.teixeira.weslei.outros.Escavador.Projeto;


public class Fuseki 
{
	static public class Resultado
	{
		public Lattes lattes = null;
		public Escavador escavador = null;
		public PortalDaTransparencia portalDaTransparencia = null;
				
		public Resultado()
		{
	
		}
		
		public Resultado(Lattes lattes, Escavador escavador, PortalDaTransparencia portalDaTransparencia)
		{
			this.lattes = lattes;
			this.escavador = escavador;
			this.portalDaTransparencia = portalDaTransparencia;
		}
		
	}
	
	
	static public class Objeto
	{
		public Statement s = null;
		public Resource objeto = null;
		
		public Objeto(Statement s)
		{
			this.s = s;
		}
		
		public Objeto(Resource objeto)
		{
			this.objeto = objeto;
		}
		
		public Resource obter()
		{
			return (s == null) ? objeto : s.getResource();
		}
		
		public String lerPropriedade(Property tipo)
		{
			try
			{
				return (s != null) ? s.getObject().toString() : objeto.getProperty(tipo).getObject().toString();
			}
			catch(Exception e){}
			
			return null;
		}
		
		public String lerPropriedadeFilha(Property tipo)
		{
			try
			{
				return (s != null) ? s.getProperty(tipo).getObject().toString() : objeto.getProperty(tipo).getObject().toString();
			}
			catch(Exception e){}
			
			return null;
		}
		
		public void inserirPropriedade(Property propriedade, String valor)
		{
			if (valor != null && objeto != null)
				objeto.addProperty( propriedade, valor);
		}
	}
	

	static public class Leitor
	{
		Resource sujeito;
		public Leitor(Resource sujeito)
		{
			this.sujeito = sujeito;
		}
		
		public String obterNome()
		{
			return new Objeto(sujeito).lerPropriedadeFilha(ESCAVADOR.nome);
		}
		
		public String obterLink()
		{
			return new Objeto(sujeito).lerPropriedadeFilha(ESCAVADOR.link);
		}
		
		public String obterDescricao()
		{
			return new Objeto(sujeito).lerPropriedadeFilha(ESCAVADOR.descricao);
		}
		
		public List<Idioma> obterIdiomas()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.idioma);
			
			if (iter.hasNext())
			{
				List<Idioma> idiomas = new ArrayList<Idioma>();
			
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					Idioma idioma = new Idioma();
					idioma.nome = 				objeto.lerPropriedadeFilha(ESCAVADOR.Idioma.nome);
					idioma.habilidades = 		objeto.lerPropriedadeFilha(ESCAVADOR.Idioma.habilidades);
	
					idiomas.add(idioma);
				}
				while (iter.hasNext());
				
				return idiomas;
			}
			
			return null;
		}
		
		
		public List<Formacao> obterFormacoes()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.formacao);
			
			if (iter.hasNext())
			{
				List<Formacao> formacoes = new ArrayList<Formacao>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					Formacao formacao = new Formacao();
					formacao.inicio =				objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.inicio);
	    			formacao.fim =					objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.fim);
		    		formacao.tipo =					objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.tipo);
	    			formacao.local =				objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.local);
		    		formacao.TCC =					objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.TCC);
		    		formacao.anoTCC =				objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.anoTCC);
		    		formacao.orientador =			objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.orientador);
		    		formacao.bolsistaDe =			objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.bolsistaDe);
		    		formacao.palavrasChave =		objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.palavrasChave);
		    		formacao.grandeArea =			objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.grandeArea);
		    		formacao.area =					objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.area);
		    		formacao.subArea =				objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.subArea);
		    		formacao.especialidade =		objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.especialidade);
		    		formacao.setoresDeAtividade =	objeto.lerPropriedadeFilha(ESCAVADOR.Formacao.setoresDeAtividade);

					formacoes.add(formacao);
				}
				while (iter.hasNext());
				
				return formacoes;
			}
			
			return null;
		}
		
		
		public PosDoutorado obterPosDoutorado()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.posDoutorado);
			
			if (iter.hasNext())
			{
				PosDoutorado posDoutorado = new PosDoutorado();
				
				Objeto objeto = new Objeto(iter.nextStatement());

				posDoutorado.inicio =				objeto.lerPropriedadeFilha(ESCAVADOR.PosDoutorado.inicio);
				posDoutorado.fim =					objeto.lerPropriedadeFilha(ESCAVADOR.PosDoutorado.fim);
				posDoutorado.local =				objeto.lerPropriedadeFilha(ESCAVADOR.PosDoutorado.local);
				posDoutorado.bolsistaDe =			objeto.lerPropriedadeFilha(ESCAVADOR.PosDoutorado.bolsistaDe);
				
				return posDoutorado;
			}
			
			return null;
		}
		
		public List<Complementar> obterFormacoesComplementares()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.formacaoComplementar);
			
			if (iter.hasNext())
			{
				List<Complementar> formacoesComplementares = new ArrayList<Complementar>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					Complementar formacaoComplementar = new Complementar();
					
	
					formacaoComplementar.inicio =			objeto.lerPropriedadeFilha(ESCAVADOR.FormacaoComplementar.inicio);
					formacaoComplementar.fim =				objeto.lerPropriedadeFilha(ESCAVADOR.FormacaoComplementar.fim);
					formacaoComplementar.tipo =				objeto.lerPropriedadeFilha(ESCAVADOR.FormacaoComplementar.tipo);
					formacaoComplementar.cargaHoraria =		objeto.lerPropriedadeFilha(ESCAVADOR.FormacaoComplementar.cargaHoraria);
					formacaoComplementar.local =		 	objeto.lerPropriedadeFilha(ESCAVADOR.FormacaoComplementar.local);

					formacoesComplementares.add(formacaoComplementar);
				}
				while (iter.hasNext());
				
				return formacoesComplementares;
			}
			
			return null;
		}
		
		public List<AreaDeAtuacao> obterAreasDeAtuacao()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.areaDeAtuacao);
			
			if (iter.hasNext())
			{
				List<AreaDeAtuacao> areasDeAtuacao = new ArrayList<AreaDeAtuacao>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					AreaDeAtuacao areaDeAtuacao = new AreaDeAtuacao();

					areaDeAtuacao.grandeArea =			objeto.lerPropriedadeFilha(ESCAVADOR.descricao);;
					areaDeAtuacao.area =				objeto.lerPropriedadeFilha(ESCAVADOR.AreaDeAtuacao.area);
					areaDeAtuacao.subArea =				objeto.lerPropriedadeFilha(ESCAVADOR.AreaDeAtuacao.subArea);
					areaDeAtuacao.especialidade =		objeto.lerPropriedadeFilha(ESCAVADOR.AreaDeAtuacao.especialidade);
		

					areasDeAtuacao.add(areaDeAtuacao);
				}
				while (iter.hasNext());
				
				return areasDeAtuacao;
			}
			
			return null;
		}
		
		public List<String> obterProducoesBibliograficas()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.producaoBibliografica);
			
			if (iter.hasNext())
			{
				List<String> producoesBibliograficas = new ArrayList<String>();
			
				do
				{
					producoesBibliograficas.add(iter.nextStatement().getObject().toString());
				}
				while (iter.hasNext());
				
				return producoesBibliograficas;
			}
			
			return null;
		}
		
		public List<Projeto> obterProjetos()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.projeto);
			
			if (iter.hasNext())
			{
				List<Projeto> projetos = new ArrayList<Projeto>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					Projeto projeto = new Projeto();
					projeto.inicio =			objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.inicio);;
					projeto.fim =				objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.fim);
					projeto.nome =				objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.nome);
					projeto.descricao =			objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.descricao);
					projeto.situacao = 			objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.situacao);
					projeto.integrantes =		objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.integrantes);
					projeto.financiadores = 	objeto.lerPropriedadeFilha(ESCAVADOR.Projeto.financiadores);
		
					projetos.add(projeto);
				}
				while (iter.hasNext());
				
				return projetos;
			}
			
			return null;
		}
		
		public List<Premio> obterPremios()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.premio);
			
			if (iter.hasNext())
			{
				List<Premio> premios = new ArrayList<Premio>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					Premio premio = new Premio();
					premio.data =		objeto.lerPropriedadeFilha(ESCAVADOR.Premio.data);
					premio.nome =		objeto.lerPropriedadeFilha(ESCAVADOR.Premio.nome);

					premios.add(premio);
				}
				while (iter.hasNext());
				
				return premios;
			}
			
			return null;
		}
		
		public EnderecoProfissional obterEnderecoProfissional()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.enderecoProfissional);
			
			if (iter.hasNext())
			{
				EnderecoProfissional enderecoProfissional = new EnderecoProfissional();
				
				Objeto objeto = new Objeto(iter.nextStatement());

				enderecoProfissional.nome =			objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.nome);
				enderecoProfissional.logradouro =	objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.logradouro);
				enderecoProfissional.bairro =		objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.bairro);
				enderecoProfissional.CEP =			objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.CEP);
				enderecoProfissional.cidade =		objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.cidade);
				enderecoProfissional.estado =		objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.estado);
				enderecoProfissional.telefone =		objeto.lerPropriedadeFilha(ESCAVADOR.EnderecoProfissional.telefone);
				
				
				return enderecoProfissional;
			}
			
			return null;
		}
		
		public List<AtuacaoProfissional> obterAtuacoesProfissionais()
		{
			StmtIterator iter = sujeito.listProperties(ESCAVADOR.atuacaoProfissional);
			
			if (iter.hasNext())
			{
				List<AtuacaoProfissional> atuacoesProfissionais = new ArrayList<AtuacaoProfissional>();
				
				do
				{
					Objeto objeto = new Objeto(iter.nextStatement());
					
					AtuacaoProfissional atuacaoProfissional = new AtuacaoProfissional();
					atuacaoProfissional.inicio =			objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.inicio);
					atuacaoProfissional.fim =				objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.fim);
					atuacaoProfissional.instituicao =		objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.instituicao);
					atuacaoProfissional.vinculo =			objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.vinculo);
					atuacaoProfissional.enquadramento =		objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.enquadramento);
					atuacaoProfissional.cargaHoraria =		objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.cargaHoraria);
					atuacaoProfissional.regime =			objeto.lerPropriedadeFilha(ESCAVADOR.AtuacaoProfissional.regime);

					atuacoesProfissionais.add(atuacaoProfissional);
				}
				while (iter.hasNext());
				
				return atuacoesProfissionais;
			}
			
			return null;
		}
		
		public Lattes obterDadosDoLattes()
		{
			Objeto objeto = new Objeto(sujeito);
			Lattes lattes = new Lattes();
			lattes.link = objeto.lerPropriedade(LATTES.link);
			lattes.nome = objeto.lerPropriedade(LATTES.nome);
			lattes.nomeCompleto = objeto.lerPropriedade(LATTES.nomeCompleto);
			
			lattes.resultados = Integer.parseInt(objeto.lerPropriedade(LATTES.resultados));
			
			return lattes;
		}
		
		public PortalDaTransparencia obterDadosDoPortalDaTransparencia()
		{
			PortalDaTransparencia portalDaTransparencia = new PortalDaTransparencia();
			
			Objeto objeto = new Objeto(sujeito);
			
			portalDaTransparencia.CPF = objeto.lerPropriedade(PORTALDATRANSPARENCIA.CPF);
			portalDaTransparencia.linkGeral = objeto.lerPropriedade(PORTALDATRANSPARENCIA.linkGeral);
			portalDaTransparencia.linkRenda = objeto.lerPropriedade(PORTALDATRANSPARENCIA.linkRenda);
			portalDaTransparencia.rendaBruta = objeto.lerPropriedade(PORTALDATRANSPARENCIA.rendaBruta);
			portalDaTransparencia.ultimaAlteracaoNoCargo = objeto.lerPropriedade(PORTALDATRANSPARENCIA.ultimaAlteracaoNoCargo);
			
			return portalDaTransparencia;
		}
	}
	
	
	static public class Gravador
	{
		Resource sujeito;
		Model modelo;
		
		public Gravador(String sujeito)
		{
			this.modelo = ModelFactory.createDefaultModel();
			this.sujeito = this.modelo.createResource(sujeito); 
		}
		
		public void definirNome (String nome)
		{
			new Objeto(sujeito).inserirPropriedade(ESCAVADOR.nome, nome);
		}
		
		public void definirLink (String link)
		{
			new Objeto(sujeito).inserirPropriedade(ESCAVADOR.link, link);
		}

		public void definirDescricao (String descricao)
		{
			new Objeto(sujeito).inserirPropriedade(ESCAVADOR.descricao, descricao);
		}
		
		public void definirIdiomas (List<Idioma> idiomas)
		{
	        for (Idioma idioma : idiomas)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());
	        	
	        	objeto.inserirPropriedade(ESCAVADOR.Idioma.nome, idioma.nome);
	        	objeto.inserirPropriedade(ESCAVADOR.Idioma.habilidades, idioma.habilidades);
	        		
	        	sujeito.addProperty(ESCAVADOR.idioma, objeto.obter());
	        }
		}
		
		public void definirFormacoes (List<Formacao> formacoes)
		{
	        for (Formacao formacao : formacoes)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());

	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.inicio, formacao.inicio);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.fim, formacao.fim);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.local, formacao.tipo);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.fim, formacao.local);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.TCC, formacao.TCC);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.orientador, formacao.orientador);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.bolsistaDe, formacao.bolsistaDe);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.palavrasChave, formacao.palavrasChave);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.grandeArea, formacao.grandeArea);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.area, formacao.area);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.subArea, formacao.subArea);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.especialidade, formacao.especialidade);
	        	objeto.inserirPropriedade(ESCAVADOR.Formacao.setoresDeAtividade, formacao.setoresDeAtividade);

	        	sujeito.addProperty(ESCAVADOR.formacao, objeto.obter());
	        }
		}
		
		public void definirPosDoutorado (PosDoutorado posDoutorado)
		{
        	Objeto objeto = new Objeto(modelo.createResource());

        	objeto.inserirPropriedade(ESCAVADOR.PosDoutorado.inicio, posDoutorado.inicio);
        	objeto.inserirPropriedade(ESCAVADOR.PosDoutorado.fim, posDoutorado.fim);
        	objeto.inserirPropriedade(ESCAVADOR.PosDoutorado.bolsistaDe, posDoutorado.bolsistaDe);
        	objeto.inserirPropriedade(ESCAVADOR.PosDoutorado.local, posDoutorado.local);

        	sujeito.addProperty(ESCAVADOR.posDoutorado, objeto.obter());
		}
		
		public void definirFormacoesComplementares (List<Complementar> formacoesComplementares)
		{
			for (Complementar formacaoComplementar : formacoesComplementares)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());

	        	objeto.inserirPropriedade(ESCAVADOR.FormacaoComplementar.inicio, formacaoComplementar.inicio);
	        	objeto.inserirPropriedade(ESCAVADOR.FormacaoComplementar.fim, formacaoComplementar.fim);
	        	objeto.inserirPropriedade(ESCAVADOR.FormacaoComplementar.tipo, formacaoComplementar.tipo);
	        	objeto.inserirPropriedade(ESCAVADOR.FormacaoComplementar.cargaHoraria, formacaoComplementar.cargaHoraria);
	        	objeto.inserirPropriedade(ESCAVADOR.FormacaoComplementar.local, formacaoComplementar.local);

	        	sujeito.addProperty(ESCAVADOR.formacaoComplementar, objeto.obter());
	        }
		}
		
		public void definirAreasDeAtuacao (List<AreaDeAtuacao> areasDeAtuacao)
		{
			for (AreaDeAtuacao areaDeAtuacao : areasDeAtuacao)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());

	        	objeto.inserirPropriedade(ESCAVADOR.AreaDeAtuacao.grandeArea, areaDeAtuacao.grandeArea);
	        	objeto.inserirPropriedade(ESCAVADOR.AreaDeAtuacao.area, areaDeAtuacao.area);
	        	objeto.inserirPropriedade(ESCAVADOR.AreaDeAtuacao.subArea, areaDeAtuacao.subArea);
	        	objeto.inserirPropriedade(ESCAVADOR.AreaDeAtuacao.especialidade, areaDeAtuacao.especialidade);

	        	sujeito.addProperty(ESCAVADOR.areaDeAtuacao, objeto.obter());
	        }
		}
		
		public void definirProducoesBibliograficas (List<String> producoesBibliograficas)
		{
			Objeto objeto = new Objeto(sujeito);
			for (String producaoBibliografica : producoesBibliograficas)
				objeto.inserirPropriedade(ESCAVADOR.producaoBibliografica, producaoBibliografica);
		}
		
		public void definirProjetos (List<Projeto> projetos)
		{
			for (Projeto projeto : projetos)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());

	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.inicio, projeto.inicio);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.fim, projeto.fim);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.nome, projeto.nome);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.descricao, projeto.descricao);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.situacao, projeto.situacao);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.integrantes, projeto.integrantes);
	        	objeto.inserirPropriedade(ESCAVADOR.Projeto.financiadores, projeto.financiadores);

	        	sujeito.addProperty(ESCAVADOR.projeto, objeto.obter());
	        }
		}
		
		public void definirPremios(List<Premio> premios)
		{
			for (Premio premio : premios)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());

	        	objeto.inserirPropriedade(ESCAVADOR.Premio.data, premio.data);
	        	objeto.inserirPropriedade(ESCAVADOR.Premio.nome, premio.nome);

	        	sujeito.addProperty(ESCAVADOR.premio, objeto.obter());
	        }
		}
		
		public void definirEnderecoProfissional (EnderecoProfissional enderecoProfissional)
		{
        	Objeto objeto = new Objeto(modelo.createResource());

        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.nome, enderecoProfissional.nome);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.logradouro, enderecoProfissional.logradouro);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.bairro, enderecoProfissional.bairro);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.CEP, enderecoProfissional.CEP);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.cidade, enderecoProfissional.cidade);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.estado, enderecoProfissional.estado);
        	objeto.inserirPropriedade(ESCAVADOR.EnderecoProfissional.telefone, enderecoProfissional.telefone);

        	sujeito.addProperty(ESCAVADOR.enderecoProfissional, objeto.obter());
		}
		
		public void definirAtuacoesProfissionais (List<AtuacaoProfissional> atuacoesProfissionais)
		{
			for (AtuacaoProfissional atuacaoProfissional: atuacoesProfissionais)
	        {
	        	Objeto objeto = new Objeto(modelo.createResource());
		
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.inicio, atuacaoProfissional.inicio);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.fim, atuacaoProfissional.fim);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.instituicao, atuacaoProfissional.instituicao);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.vinculo, atuacaoProfissional.vinculo);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.enquadramento, atuacaoProfissional.enquadramento);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.cargaHoraria, atuacaoProfissional.cargaHoraria);
	        	objeto.inserirPropriedade(ESCAVADOR.AtuacaoProfissional.regime, atuacaoProfissional.regime);

	        	sujeito.addProperty(ESCAVADOR.atuacaoProfissional, objeto.obter());
	        }
		}
		
		
		public void definirDadosDoLattes (Lattes lattes)
		{
        	Objeto objeto = new Objeto(sujeito);
        	objeto.inserirPropriedade(LATTES.link, lattes.link);
        	objeto.inserirPropriedade(LATTES.nome, lattes.nome);
        	objeto.inserirPropriedade(LATTES.nomeCompleto, lattes.nomeCompleto);
        	objeto.inserirPropriedade(LATTES.resultados, String.valueOf(lattes.resultados));
		}
		
		public void definirDadosDoPortalDaTransparencia (PortalDaTransparencia portalDaTransparencia)
		{
        	Objeto objeto = new Objeto(sujeito);
        	objeto.inserirPropriedade(PORTALDATRANSPARENCIA.CPF, portalDaTransparencia.CPF);
        	objeto.inserirPropriedade(PORTALDATRANSPARENCIA.linkGeral, portalDaTransparencia.linkGeral);
        	objeto.inserirPropriedade(PORTALDATRANSPARENCIA.linkRenda, portalDaTransparencia.linkRenda);
        	objeto.inserirPropriedade(PORTALDATRANSPARENCIA.rendaBruta, portalDaTransparencia.rendaBruta);
        	objeto.inserirPropriedade(PORTALDATRANSPARENCIA.ultimaAlteracaoNoCargo, portalDaTransparencia.ultimaAlteracaoNoCargo);
		}
	}
	
	
	public static final String DB = "http://localhost:3030/professor";
	public static final String URL = "http://swlab.ic.uff.br/professor/"; 
	
	public String sujeito = null;
	
	public Fuseki()
	{

	}
	
	public Fuseki(String nome)
	{
		definirSujeito(nome);
	}
	
	public void definirSujeito(String nome)
	{
		sujeito = URL + Utilidades.MD5(nome);;
	}
	
	public boolean sujeitoExiste()
	{
		String consulta = "SELECT (COUNT(*) AS ?total) WHERE {?suj ?pred ?obj FILTER (str(?suj) = \"" + sujeito + "\")}";
		
		ResultSet resultado = QueryExecutionFactory.sparqlService(DB, consulta).execSelect();
		QuerySolution linha = resultado.next();
        int total = ((Literal) linha.get("total")).getInt();
        
		return (total > 0);
	}
	
	public Resultado ler()
	{
		Model modelo = obterModeloDoSujeito();
		Resource sujeito = modelo.getResource(this.sujeito);
		
		Leitor leitor = new Leitor(sujeito);
		
		Escavador escavador = new Escavador();
		escavador.nome = leitor.obterNome();
		escavador.link = leitor.obterLink();
		escavador.descricao = leitor.obterDescricao();
		escavador.idiomas = leitor.obterIdiomas();
		escavador.formacoes = leitor.obterFormacoes();
		escavador.posDoutorado = leitor.obterPosDoutorado();
		escavador.formacoesComplementares = leitor.obterFormacoesComplementares();
		escavador.areasDeAtuacao = leitor.obterAreasDeAtuacao();
		escavador.producoesBibliograficas = leitor.obterProducoesBibliograficas();
		escavador.projetos = leitor.obterProjetos();
		escavador.premios = leitor.obterPremios();
		escavador.enderecoProfissional = leitor.obterEnderecoProfissional();
		escavador.atuacoesProfissionais = leitor.obterAtuacoesProfissionais();
		
		
		Lattes lattes = leitor.obterDadosDoLattes();	
		PortalDaTransparencia portalDaTransparencia = leitor.obterDadosDoPortalDaTransparencia();
		
		return new Resultado(lattes, escavador, portalDaTransparencia);
	}
	


	public Model obterModeloCompleto()
	{
		Model modelo = ModelFactory.createDefaultModel();
		
		String consulta = "construct {?s ?p ?o} WHERE {?s ?p ?o}";

		QueryExecution resultado = QueryExecutionFactory.sparqlService(DB, consulta);
		
		resultado.execConstruct(modelo);

		return modelo;
	}
	
	public Model obterModeloDoSujeito()
	{
		Model modelo = ModelFactory.createDefaultModel();
		
		String consulta = 
		"construct {?s1 ?p1 ?o1} " +
		"{ " +
			"bind(\"" + sujeito + "\" as ?url) " + 
			"?s1 ?p1 ?o1 FILTER ( (?o2 = ?s1 && str(?s2) = ?url) || (str(?s2) = ?url  && ?s2 = ?s1) ) " +
			"{ " + 
				"select ?s2 ?p2 ?o2 {?s2 ?p2 ?o2}" + 	
  			"} " +
		"}";
		
		QueryExecution resultado = QueryExecutionFactory.sparqlService(DB, consulta);
		
		resultado.execConstruct(modelo);
		
		return modelo;
	}
	
	public void inserir(Lattes lattes, Escavador escavador, PortalDaTransparencia portalDaTransparencia)
	{
		DatasetAccessor db = DatasetAccessorFactory.createHTTP(DB);
        Gravador professor = new Gravador(sujeito); 
        
        if (lattes != null)
        	professor.definirDadosDoLattes(lattes);
        
        if (portalDaTransparencia != null)
        	professor.definirDadosDoPortalDaTransparencia(portalDaTransparencia);
        
        if (escavador.nome != null)
        	professor.definirNome(escavador.nome);
        
        if (escavador.link != null)
        	professor.definirLink(escavador.link);
        
        if (escavador.descricao != null)
        	professor.definirDescricao(escavador.descricao);
        
        if (escavador.idiomas != null)
        	professor.definirIdiomas(escavador.idiomas);
        
        if (escavador.formacoes != null)
        	professor.definirFormacoes(escavador.formacoes);
        
        if (escavador.posDoutorado != null)
        	professor.definirPosDoutorado(escavador.posDoutorado);
        
        if (escavador.formacoesComplementares != null)
        	professor.definirFormacoesComplementares(escavador.formacoesComplementares);
        
        if (escavador.areasDeAtuacao != null)
        	professor.definirAreasDeAtuacao(escavador.areasDeAtuacao);
        
        if (escavador.producoesBibliograficas != null)
        	professor.definirProducoesBibliograficas(escavador.producoesBibliograficas);
        
        if (escavador.projetos != null)
        	professor.definirProjetos(escavador.projetos);
        
        if (escavador.premios != null)
        	professor.definirPremios(escavador.premios);
        
        if (escavador.enderecoProfissional != null)
        	professor.definirEnderecoProfissional(escavador.enderecoProfissional);
        
        if (escavador.atuacoesProfissionais != null)
        	professor.definirAtuacoesProfissionais(escavador.atuacoesProfissionais);

        db.add(professor.modelo);
	}
}