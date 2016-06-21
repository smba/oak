<?php 
$o = 4;
switch ($o) {
	case 1:
		echo "A";
		$o = 5;
		break;
	case 2:
		echo "B";
		$o = 6;
		break;
	case 3:
		echo "C";
		break;
	default:
		echo "D";
		break;
}
echo $o;

?>