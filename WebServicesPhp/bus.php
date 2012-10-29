<?php

// echo "<pre>";
// print_r($_REQUEST);
// echo "</pre>";die();
if(isset($_REQUEST["station_id"]) && isset($_REQUEST["direction"]) ) {

	$station_id = $_REQUEST["station_id"] ; // on récupère l'id de la station
	$direction =  $_REQUEST["direction"] ; // on récupère la direction A pour Allé et R est Retour
}
else {
die("");
}
function getTabBus($link){
	$file = file_get_contents($link);
	$doc = new DOMDocument();
	$doc->loadHTML($file);
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
	

	return $var_input;
}




error_reporting(0);
	//$html = file_get_html('http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B293/293_925_1003/A');

	$station_list = explode(",",$station_id);
	foreach ($station_list as $station) // boucle pour parcourir la liste des bus
	{
		if(strlen($station)>0) 
		{	
			$bus = explode("_",$station);
			$numBus=$bus[0];
			$link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B".$numBus."/".$station."/".$direction ;
			$var_output[$numBus][$direction] = getTabBus($link);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* $link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B293/293_925_1003/A" ;
	$var_output["293"]["A"] = getTabBus($link); 
	
	$link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B293/293_925_1003/R" ;
	$var_output["293"]["R"] = getTabBus($link);
	
	$link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B132/132_581_591/A" ;
	$var_output["132"]["A"] = getTabBus($link);
	
	$link = "http://www.ratp.fr/horaires/fr/ratp/bus/prochains_passages/PP/B132/132_581_591/R" ;
	$var_output["132"]["R"] = getTabBus($link);
	 */
	// echo "<pre>";
	// print_r($var_output);
	// echo "</pre>";die();
	
	echo "{\"response\":".json_encode($var_output)."}";
	

?>
