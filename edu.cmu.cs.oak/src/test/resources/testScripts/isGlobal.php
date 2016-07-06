<?php
$a = "1";
$b = "2";

echo $a;

function sum()
{
    global $a, $b;
    echo $a;
    echo $b;
    $b = $a . $b;
} 

sum();
echo $b;
?>
