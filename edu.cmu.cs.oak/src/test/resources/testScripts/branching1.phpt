<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Literal Text="ABC" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="36"/>
    <Select>
      <Condition text="True">
        <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="8"/>
      </Condition>
      <Condition text="False">
        <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="6"/>
      </Condition>
      <Condition text="False">
        <Undef/>
      </Condition>
    </Select>
    <Literal Text="DEF" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="38"/>
    <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="14"/>
    <Literal Text="GHI" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="40"/>
    <Select>
      <Condition text="True">
        <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="19"/>
      </Condition>
      <Condition text="False">
        <Undef/>
      </Condition>
    </Select>
    <Literal Text="JKL" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="42"/>
    <Concat>
      <Select>
        <Condition text="!def((($d + 6) &lt; 1))">
          <Concat> </Concat>
        </Condition>
      </Select>
    </Concat>
    <Select>
      <Condition text="def((($d + 6) &lt; 1))">
        <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="29"/>
      </Condition>
      <Condition text="False">
        <Undef/>
      </Condition>
      <Condition text="!def((($d + 6) &lt; 1))">
        <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/branching1.php" Line="31"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>