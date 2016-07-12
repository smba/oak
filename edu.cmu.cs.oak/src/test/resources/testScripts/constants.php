<?php



function eee() {
	if ("3" == 2) {
		define("KONSTANTE", "A");
	} else {
		define("KONSTANTE", "B");
	}

}
eee();
	echo KONSTANTE; // gibt "Hallo Welt." aus

?>