<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Literal Text="ABC" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="36"/>
    <Select>
      <Constraint Text="!((($d + 6) &lt; 1))"/>
      <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="8"/>
      <Select>
        <Constraint Text="(($d + 6) &lt; 1)"/>
        <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="6"/>
        <Undef/>
      </Select>
    </Select>
    <Literal Text="DEF" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="38"/>
    <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="14"/>
    <Literal Text="GHI" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="40"/>
    <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="19"/>
    <Literal Text="JKL" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="42"/>
    <Select>
      <Constraint Text="(($d + 6) &lt; 1)"/>
      <Select>
        <Constraint Text="(($d + 6) &lt; 1)"/>
        <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="29"/>
        <Undef/>
      </Select>
      <Select>
        <Constraint Text="!((($d + 6) &lt; 1))"/>
        <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="31"/>
        <Undef/>
      </Select>
    </Select>
  </Concat>
</DataModel>