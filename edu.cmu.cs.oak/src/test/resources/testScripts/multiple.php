<?php
	class K {
		var $name;

		function __construct($n) {
			$this->name = $n;	
		}

		function full_name($last) {
			//echo $this->name;
			return	$this->name . $last;
		}
	}

	if ("A") {
		if ("B") {
			$obj = new K("AB");
		} else if ("C") {
			$obj = new K("AC");
		} else {
			if ("D") {
				$obj = new K("AD");
			} else {
				$obj = new K("A");
			}
		}
	} else {
		if ("E") {
			if ("F") {
			$obj = new K("E");
			} else { }
		} else {
		}
	}
	$a = new K("A");
	echo $obj->full_name("N");
?>
