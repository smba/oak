<?php
// Load the WordPress callback API
require_once 'plugin.php';


function paragraph( $example ) {
	// Maybe modify $example in some way.
	return '<p>' . $example . '</p>';
}

function greeting( $text ) {
	return 'Ciao! Como estai? ' . $text;
}

function bold( $example2 ) {
	// Maybe modify $example in some way.
	return '<b>' . $example2 . '</b>';
}

add_filter( 'html', 'greeting' );
add_filter( 'html', 'paragraph' );
add_filter( 'html', 'bold' );

echo apply_filters('html', 'Mi chiamo Stefano.')



?>