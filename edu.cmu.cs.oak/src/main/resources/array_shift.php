<?php

function array_shift(&$array) {
	$a = $array[0];
	$array = array_slice($array, 1);
	return $a;
}

function array_push(&$array, $elem) {
	$size = count($array);
	$array[$size] = $elem;
	return 1;
}

?>