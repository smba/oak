<?php

function tolobal() {
	global $a;
	$a = 0;
}
echo "a";
tolobal();
echo $a;
?>
