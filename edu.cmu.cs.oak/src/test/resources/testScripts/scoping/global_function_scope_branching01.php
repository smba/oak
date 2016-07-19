<?php 

// Variable $a is left undefined before the funtion call foo.

function foo() {
	global $a;
	if (symbolic_function()) {
		$a[0][2] = 'C';
	} else {
		$a[0][2] = 'D';
	}
}

foo();

echo $a[0][2];

/*
 * Expected behaviour: Variable $a is defined inside the fucntion and will
 * then be included during the environment's merge sinc it is denoted as global.
 */
?>
