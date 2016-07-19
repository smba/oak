<?php 

$array_a = array(1, 2, 3);

$array_b[0] = 'A';
$array_b[1] = 'B';
$array_b[2] = 'C';

$array_c[0][0] = 'D';
$array_c[0][1] = 'E';
$array_c[0][2] = 'F';

$array_d[0] = array('G', 'H', 'H');

$array_e[] = "I";

$array_f[0][1][2] = 'J';

$a = 'a';
$b = 'b';
$c = 'c';
$array_g[$a][$b][0] = 'K';

$array_h = array(3 => 'L', 5 => 'M');


// /* ------------------------------- */
echo $array_a[0] . $array_a[2];
echo $array_b[0] . $array_b[2];
echo $array_c[0][1] . $array_c[0][2];
echo $array_d[0][0] . $array_d[0][2];
echo $array_e[0];
echo $array_f[0][1][2];
echo $array_g['a']['b'][0];
echo $array_h[0] . $array_h[5];

// /* ------------------------------- */


?>