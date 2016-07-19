<?php 

// Variable $a is left defined before the funtion call foo.
$a = array( array('a', 'b') );

function foo() {
	global $a;
	$a[0][2] = 'c';
}

foo();

echo $a[0][2];

?>
