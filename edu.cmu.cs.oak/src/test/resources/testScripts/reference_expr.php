<?php

$a = 'hello'; // $a -> 'hello';
$$a = 'world'; // $hello -> 'world';
echo $hello; //'world'

$b = &$a;
echo $b;// 'hello'

$c = &$$a;
echo $c; //'world'

?>
