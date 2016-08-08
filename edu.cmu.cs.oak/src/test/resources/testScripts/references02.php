<?php 

function foo(&$var) {
    $var = $var + 1;
}

$a=5;
foo($a);
echo $a;
?>