<?php 

	/* Assignments */
	$i = array(1,2,4,8);
	$i[5] = 16;
	
	$x = 8;
	
	/* Access array variables */
	$j["key"] = $i[1] * 2;
	$j["i"] = $i;
	
	/* Print */
	echo $j;

	$k = "a" . "b" . ($x < 9);
	echo "a" . "b" . ($x < 9);
?>