<?php 

function abc($x) {
	echo $x;
	xyz("e");
}

function xyz($x) {
	echo $x;
}

function ifelse($x) {
	if ($x < 0) {
		echo "A";
	} else {
		xyz("B");
	}
	echo "C";
}

ifelse("o");
echo ("a" < 9);
abc("b");
$v = abc("c");
xyz("Z");

?>