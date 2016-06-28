<?php 

class Peer {
	
	var $lastName;
	
	function __construct($last) {
		$this->lastName = $last;
	}
	
	function sayHi() {
		echo "Peer $this->lastName's last name is " . $this->lastName;
		print "Peer $this->lastName says Hi!";
	}
	
	function greet($whom) {
		return "Peer $this->lastName greets Peer " . $whom;
	}
	
}

$steinbrueck = new Peer("Steinbrück");
$steinbrueck->sayHi();
echo $steinbrueck->greet("Mertesacker");
?>