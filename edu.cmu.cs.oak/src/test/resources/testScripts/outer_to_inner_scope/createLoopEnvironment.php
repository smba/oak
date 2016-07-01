<?php 

$a = "abc";
$c = array(0, 1, 2);

while ($b = 3) {
	echo $a . $b . $c[0];
	$a = $b;
	$c[0] = "a";
}

echo $a;
echo $c[0];

?>