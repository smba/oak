<?php

function abc() {
	global $i;
	$i = 1;
}
abc();
echo $i;
?>
