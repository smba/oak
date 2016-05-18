<?php
$a = array(3,2,1);
$b = &$a[0];
$b *= $b;
echo $a[0]; // assert($a[0] == 9)
?>