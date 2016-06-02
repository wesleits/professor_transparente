package tk.dados.paginas;

import tk.dados.paginas.outros.Base;

public class Index extends Base
{
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
"		<title>Busca Lattes</title>	" + NL +
"	</head>" + NL +
"	<body>" + NL +
"		<nav class=\"navbar navbar-default navbar-fixed-top\">" + NL +
"			<div class=\"container-fluid\">" + NL +
"				<div class=\"navbar-header\">" + NL +
"					<a class=\"navbar-brand\" href=\"#\">" + NL +
"						<img alt=\"\" src=\"...\">" + NL +
"					</a>" + NL +
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
"			<div class=\"jumbotron\">" + NL +
"				<h1 class=\"title text-center\">Busca Lattes</h1>" + NL +
"					<div class=\"row\">" + NL +
"						<div class=\"col-lg-6 col-lg-offset-3\">" + NL +
"						<div class=\"input-group input-group-lg\">" + NL +
"							<input type=\"text\" class=\"form-control\" placeholder=\"Pesquisar por...\">" + NL +
"							<span class=\"input-group-btn\">" + NL +
"								<a href=\"dados.jsp\">" + NL +
"									Go!" + NL +
"									<button class=\"btn btn-default btn-lg\" type=\"button\">Go!</button>" + NL +
"								</a>" + NL +
"						  	</span>" + NL +
"						</div> <!-- /input-group -->" + NL +
"					</div> <!-- /.col-lg-6 -->" + NL +
"				</div> <!-- /.row -->" + NL +
"			</div>" + NL +
"		</section>" + NL +
"	</body>" + NL +
"</html>";
	}
}
