<?php

// should return Choice(a == b, a, b)
function abc() {
	if ($d + 6 < 1) {
		return "a";
	}
	return "b";
}

function jkl() {
	if ($d + 6 < 1) {
		return "a";
	} else {
		return "b";
	}
}

//should return a
function def() {
	if (true) {
		return "a";
	}
	return "b";
}
function ghi() {
	return 'c';
	if (1 < 2) {
		return "a";
	} else {
		return 'b';
	}
}


echo "ABC";
echo abc();
echo "DEF";
echo def();
echo "GHI";
echo ghi();
echo "JKL";
echo jkl();
 ?>