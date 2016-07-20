<?php
// Load the WordPress callback API
require_once 'plugin.php';


function example_callback( $example ) {
	// Maybe modify $example in some way.
	return '....::::' . $example . '::::....';
}

function example_callback2( $example2 ) {
	// Maybe modify $example in some way.
	return '_' . $example2 . '_';
}

//add_filter( 'example_filter', 'example_callback' );
add_filter( 'example_filter', 'example_callback2' );

echo apply_filters('example_filter', 'Wubba-lubba-dub-dub!')



?>