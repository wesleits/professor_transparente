package tk.dados.paginas;

import tk.dados.paginas.outros.Base;

public class Dados extends Base
{
	public static String obterCaptcha()
	{
		return "";
	}
	
	
	public static String obterHTML()
	{
		return 
"<!DOCTYPE html>" + NL +
"<html>" + NL +
"	<head>" + NL +
"		<meta charset=\"utf-8\">" + NL +
"		<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">" + NL +
"		<meta name=\"description\" content=\"\">" + NL +
"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + NL +
"		<link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">" + NL +
"		<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">" + NL +
"		<link rel=\"stylesheet\" href=\"css/custom/custom.css\">" + NL +
"		<script src=\"js/jquery.min.js\"></script>" + NL +
"		<script src=\"js/bootstrap.min.js\"></script>" + NL +
"		<title>Busca Lattes</title>" + NL +
"	</head>" + NL +
"	<body>" + NL +
"		<nav class=\"navbar navbar-default navbar-fixed-top\">" + NL +
"			<div class=\"container-fluid\">" + NL +
"				<div class=\"navbar-header\">" + NL +
"					<ul class=\"nav navbar-nav navbar-left\">" + NL +
"						<li>" + NL +
"							<a href=\"..\\\">" + NL +
"								<img class=\"img-responsive img-navbar2\" alt=\"Voltar\" src=\"http://cdn2.hubspot.net/hub/49125/file-14366016-png/images/log-out-icon.png\">" + NL +
"							</a>" + NL +
"						</li>" + NL +
"					</ul>" + NL +
"				</div>" + NL +
"				<ul class=\"nav navbar-nav navbar-right\">" + NL +
"					<li>" + NL +
"						<a href=\"#\">" + NL +
"							<img class=\"img-responsive img-navbar\" alt=\"UFF\" src=\"http://www.tectur.uff.br/sites/default/files/2000px-Logo_UFF_%28blue%29.png\">" + NL +
"						</a>" + NL +
"					</li>" + NL +
"				</ul>" + NL +
"			</div>" + NL +
"		</nav>" + NL +
"		<section class=\"container-fluid\">" + NL +
"			<div class = \"jumbotron\">" + NL +
"				<h1 class=\"title text-center\">Nome do professor</h1>" + NL +
"					<div class=\"row\">" + NL +
"					<div class=\"col-lg-10 col-lg-offset-1 col-md-8 col-md-offset-2\">" + NL +
"						<!-- Nav tabs -->" + NL +
"						<ul class=\"nav nav-pills nav-justify\" role=\"tablist\">" + NL +
"							<li role=\"presentation\" class=\"active col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2\">" + NL +
"								<a href=\"#home\" aria-controls=\"home\" role=\"tab\" data-toggle=\"tab\">Dados pessoais</a>" +
"							</li>" + NL +
"							<li role=\"presentation\" class=\"col-lg-3 col-md-2\">" + NL +
"								<a href=\"#profile\" aria-controls=\"profile\" role=\"tab\" data-toggle=\"tab\">Dados acadêmicos</a>" + NL +
"							</li>" + NL +
"							<li role=\"presentation\" class=\"col-lg-3 col-md-2\">" +
"								<a href=\"#messages\" aria-controls=\"messages\" role=\"tab\" data-toggle=\"tab\">Dados financeiros</a>" +
"							</li>" + NL +					
"						 </ul>" + NL +
"						<!-- Tab panes -->" + NL +
"						<div class=\"tab-content\">" + NL +
"							<div role=\"tabpanel\" class=\"tab-pane active col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2\" id=\"home\">" + NL +
"								<br />" + NL +
"								Dados pessoais" + NL +
"							</div>" + NL +
"							<div role=\"tabpanel\" class=\"tab-pane col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2\" id=\"profile\">" + NL +
"							<br />" + NL +
"								Dados acadêmicos" + NL +
"							</div>" + NL +
"							<div role=\"tabpanel\" class=\"tab-pane col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2\" id=\"messages\">" + NL +
"								<br />" + NL +
"								Dados financeiros" + NL +
"							</div>" + NL +					
"						</div>" + NL +
"					</div>" + NL +
"				</div> <!-- /.row -->" + NL +
"			</div>" + NL +
"		</section>" + NL +
"	</body>" + NL +
"</html>";
	}
}
