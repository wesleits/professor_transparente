<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/custom/custom.css">
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<title>Busca Lattes</title>	
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="index.jsp"><img class="img-responsive img-navbar2" alt="Voltar" src="http://cdn2.hubspot.net/hub/49125/file-14366016-png/images/log-out-icon.png"></a></li>
				</ul>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><img class="img-responsive img-navbar" alt="UFF" src="http://www.tectur.uff.br/sites/default/files/2000px-Logo_UFF_%28blue%29.png"></a></li>
			</ul>
		</div>
	</nav>
	
	<section class="container-fluid">
		<div class = "jumbotron">
			<h1 class="title text-center">Nome do professor</h1>
			<div class="row">
				<div class="col-lg-10 col-lg-offset-1 col-md-8 col-md-offset-2">
				    <!-- Nav tabs -->
					  <ul class="nav nav-pills nav-justify" role="tablist">
						<li role="presentation" class="active col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Dados pessoais</a></li>
						<li role="presentation" class="col-lg-3 col-md-2"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Dados acadÃªmicos</a></li>
						<li role="presentation" class="col-lg-3 col-md-2"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Dados financeiros</a></li>						
					  </ul>

					  <!-- Tab panes -->
					  <div class="tab-content">
<div role="tabpanel" class="tab-pane active col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2" id="home">
						<br />Dados pessoais
						</div>
						<div role="tabpanel" class="tab-pane col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2" id="profile">
						<br />Dados acadêmicos
						</div>
						<div role="tabpanel" class="tab-pane col-lg-3 col-lg-offset-1 col-md-2 col-md-offset-2" id="messages">
						<br />Dados financeiros
						</div>						
					  </div>
				</div>
			</div><!-- /.row -->
		</div>
	</section>
</body>
</html>