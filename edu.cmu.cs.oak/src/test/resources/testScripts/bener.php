<?php

$a = 'hello';

// 1) reference to a simple variable
$b = &$a;
$b = $b . ' world';

echo $a;
?>
