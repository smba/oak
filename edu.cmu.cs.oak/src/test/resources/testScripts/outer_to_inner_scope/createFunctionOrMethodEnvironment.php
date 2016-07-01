<?php 

$a = 4;
function abc($arg1, $arg2, $arg3 = "text") {
	$a = 5;
	echo $arg1 . $arg2 . $arg3;
}
function abc2($arg1, $arg2, $arg3 = "text") {
	echo $arg1 . $arg2 . $arg3;
	return 1;
}

abc("This ", " is ");
echo $a;
print abc2("Winter ", " is ", " coming");


?>