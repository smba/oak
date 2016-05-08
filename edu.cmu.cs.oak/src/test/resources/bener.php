<?php
$j = 9;
echo "Hallo Christian!";
$i = 1;
function foo($x) {
	echo "foo";
	return $x * 2;
}
function bar($x) {
	echo "bar";
	return $x * 3;
}

if ($i == "Bener") {
	foo($i);
} else {
	bar($i);
}
echo foo(1)-30;
?>