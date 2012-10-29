<?php

// echo "<pre>";
// print_r($_REQUEST);
// echo "</pre>";die();

error_reporting(0);
	$html = file_get_html('http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B293/293_925_1003/A');
	$bus = $_REQUEST['bus'];
	$direction = $_REQUEST['direction'];
	
	if($bus == "293")
	{
		$station = "293_925_1003";
	}
	else
	{
		$station = "132_581_591";
	}
	$link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B".$bus."/".$station."/".$direction ;
	
   
	
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
	$prochain_passage =  $doc->getElementById("prochains_passages");
	$span = $doc->getElementsByTagName("span");
	$str_direction = "Hello";
	foreach($span  as  $searchNode ) {
		if( $searchNode->getAttribute("class") == "direction" ){
			$str_direction = $searchNode->nodeValue;
		}
	}
	//echo $str_direction ;
	$var_input["direction"]=$str_direction;
	$tableau = $doc->getElementById("prochains_passages")->getElementsByTagName("td");
	$var_input["now"] = $tableau->item(1)->nodeValue ;   // = intval($tableau->item(1)->nodeValue) ;
	$var_input["next"] = $tableau->item(3)->nodeValue ;
	
	echo "{\"response\":".json_encode($var_input)."}";
	

?>
