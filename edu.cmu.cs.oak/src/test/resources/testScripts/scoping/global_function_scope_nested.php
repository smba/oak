<?php 

$a = 'a';
$b = array('a', 'b');
$c = array( array('A', 'B') );

function foo() {
	global $a, $b, $c;
	$a = $a . 'b';
	$b[2] = 'c';
	$c[0][2] = 'C';
	bar();
}

function bar() {
	global $a, $b, $c;
	$a = $a . $a;
	$b[2] = $b[1] . $b[2];
	$c[0][2] = $c[0][1] . $c[0][2];
}

foo();

echo $a;
echo $b[2];
echo $c[0][2];
?>