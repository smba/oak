<?php 
function foo(&$x) {
	$x *= 2;
}
$a = 2;
foo($a);
echo $a; // assert($a == 4)
?>