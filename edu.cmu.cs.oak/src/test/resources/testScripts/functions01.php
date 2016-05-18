<?php 
echo 'A'; 

function f($x) {
	echo 'pre'; 
	if ($x > 0) {
		echo 'B2D'; 
	} else {
		echo 'B3'; 
	} 
	echo 'post';
} 
f(1); 
f('text'); 
echo 'Z';
?>