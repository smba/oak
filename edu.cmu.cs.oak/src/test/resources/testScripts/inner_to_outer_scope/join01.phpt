<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Repeat>
      <Concat>
        <Select>
          <Constraint Text="($a == 1)"/>
          <Concat> </Concat>
          <Concat>
            <Select>
              <Constraint Text="($b == 2)"/>
              <Concat> </Concat>
              <Concat>
                <Literal Text="default" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="10"/>
              </Concat>
            </Select>
          </Concat>
        </Select>
        <Select>
          <Constraint Text="($a == 1)"/>
          <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="6"/>
          <Select>
            <Constraint Text="($b == 2)"/>
            <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="8"/>
            <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="11"/>
          </Select>
        </Select>
      </Concat>
    </Repeat>
    <Select>
      <Constraint Text="($a == 1)"/>
      <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="6"/>
      <Select>
        <Constraint Text="($b == 2)"/>
        <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="8"/>
        <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/join01.php" Line="11"/>
      </Select>
    </Select>
  </Concat>
</DataModel>