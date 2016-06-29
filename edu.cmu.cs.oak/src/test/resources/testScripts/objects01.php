<?php 
class foo {
    public $value;

    function __construct() {
    	$this->value = 42;
    }

    public function getValue() {
    	return $this->value;
    }
    
    public function &getValueRef() {
        return $this->value;
    }
}

$obj = new foo;
$myValue = &$obj->getValueRef();
echo $myValue;
$obj->value = 2;
echo $obj->getValue();
echo $myValue;
?>