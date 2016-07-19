<?php 

function foo() {
    global $a;
    $a[0][2] = 'c';
}
$a = array(array('a', 'b'));

foo();
echo "k";
echo $a[0][2];

?>