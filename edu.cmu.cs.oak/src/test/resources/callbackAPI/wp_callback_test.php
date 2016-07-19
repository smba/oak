<?php
// // Load the WordPress callback API
require_once 'plugin.php';
function example_callback( $string, $arg1, $arg2 ) {
	// (maybe) modify $string
	return $string;
}
add_filter( 'example_filter', 'example_callback', 10, 3 );
/*
 * Apply the filters by calling the 'example_callback' function we
 * "hooked" to 'example_filter' using the add_filter() function above.
 * - 'example_filter' is the filter hook $tag
 * - 'filter me' is the value being filtered
 * - $arg1 and $arg2 are the additional arguments passed to the callback.*/
$value = apply_filters( 'example_filter', 'filter me', $arg1, $arg2 );
echo $value;
?>