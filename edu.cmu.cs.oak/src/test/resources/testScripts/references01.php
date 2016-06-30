<?php 
function foo($a) {
	$a[0] = 222;
}

$b = array(0, 1);
foo($b);
echo $b[0];

?>