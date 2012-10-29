<?php

// echo "<pre>";
// print_r($_REQUEST);
// echo "</pre>";die();

error_reporting(0);
	//$html = file_get_html('http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B293/293_925_1003/A');

	$link = "http://www.airparif.fr/etat-air/air-et-climat-commune/ninsee/94081" ;
	
   
	
	//die($link);
	$file = file_get_contents($link);
	$doc = new DOMDocument();
	$doc->loadHTML($file);
	//$dom->preserveWhiteSpace = false; 
	// $body = $doc->getElementsBytagName("body");
	
	// $body = $doc->getElementsByTagName('body')->item(0);

	// print htmlspecialchars(innerHTML($body));
	// die();
	
	$var_input ;
	$table_data =  $doc->getElementsByTagName("table")->item(0);
	$today = $table_data->getElementsByTagName("tr")->item(2);
	$tomorrow = $table_data->getElementsByTagName("tr")->item(3);
	
	$var_input['today']['indice'] = $today->getElementsByTagName("td")->item(1)->nodeValue;
	$var_input['today']['polluant'] = $today->getElementsByTagName("td")->item(2)->nodeValue;
	$var_input['today']['niveau'] = $today->getElementsByTagName("td")->item(3)->nodeValue;
	
	$var_input['tomorrow']['indice'] = $tomorrow->getElementsByTagName("td")->item(1)->nodeValue;
	$var_input['tomorrow']['polluant'] = $tomorrow->getElementsByTagName("td")->item(2)->nodeValue;
	$var_input['tomorrow']['niveau'] = $tomorrow->getElementsByTagName("td")->item(3)->nodeValue;
	
	
	
	echo "{\"response\":".json_encode($var_input)."}";
	

?>
