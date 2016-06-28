$(document).ready(function()
{
	$('#ir').click(function() 
	{
		$(location).attr('href', '/' + $('#nome').val());
	}); 
});