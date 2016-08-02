<?php 

$array1 = array('a', 'b', 'c');
$array2 = array('d', 'e', 'f');
$array3 = array('g', 'h', 'i');

// array_merge
$merged = array_merge($array1, $array2);
echo $merged[2] . $merged[3];


// array_shift
$shifted = array_shift($array3);
echo $shifted . $array3[0];

// array_keys
$keys = array_keys($array1);
$keys2 = array_keys($array1, 'c');
echo $keys[1];// . $keys2[0];

// array_flip
$flipped = array_flip($array1);
echo $flipped[0] . $flipped[1];

echo array_push($array1, 'Apfelsaft') . $array1[3];


$values = array_values($array1);
echo $values[3];
?>