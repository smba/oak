<?php

function foobar($arg, $arg2) {
    echo "foobar " . " got $arg and $arg2";
}
class fou {
    function bar($arg, $arg2) {
        echo "bar " . " got $arg and $arg2";
    }
}

// Call the foobar() function with 2 arguments
call_user_func_array("foobar", array("one", "two"));

// Call the $foo->bar() method with 2 arguments
$foo = new fou;
call_user_func_array(array($foo, "bar"), array("three", "four"));

?>