<?php 

	/* Assignments */
	$i = array(0,1);
	$i[0] = 42;
	
// 	/* Access array variables */
// 	$j["key"] = $i[1] * 2;
 	$j["i"] = $i;
	
	/* Print */
	echo $j["i"][0];
?>