<?php 
class Klasse {
	static $staticField = "Bener";
	var $field;
	
	function __construct($firstName) {
		$this->field = $firstName;
	}
	
	function change() {
		if ($a == 8) {
			self::$staticField = 4;
		} else {
			//self::$staticField = 5;	
		}
	}
}

$a = new Klasse("Klaas");
echo Klasse::$staticField; 
$a->change();
echo Klasse::$staticField;
?>
