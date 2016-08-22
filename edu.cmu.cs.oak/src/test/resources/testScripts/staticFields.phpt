<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Literal Text="Bener" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/staticFields.php" Line="3"/>
    <Concat>
      <Select>
        <Condition text="!def(($a == 8))">
          <Concat> </Concat>
        </Condition>
      </Select>
    </Concat>
    <Select>
      <Condition text="def(($a == 8))">
        <Literal Text="4" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/staticFields.php" Line="22"/>
      </Condition>
      <Condition text="!def(($a == 8))">
        <Literal Text="Bener" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/staticFields.php" Line="3"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>