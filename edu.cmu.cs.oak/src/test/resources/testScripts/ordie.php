<?php

function boo() {
	return 42;
}

die("bener");
$a = boo() or die("f");
echo $a;
?>
