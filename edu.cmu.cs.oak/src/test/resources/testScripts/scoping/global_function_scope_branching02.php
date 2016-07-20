<?php 

// Variable $a is left undefined before the funtion call foo.
$a = array( array('a', 'b') );

function foo() {
	global $a;
	if (symbolic_function()) {
		$a[0][2] = 'c';
		echo $a[0][2];
	} else {
		$a[0][2] = 'd';
		echo $a[0][2];
	}
	echo $a[0][2];

}

foo();

echo $a[0][2];

?>