<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Repeat>
      <Constraint Text="True"/>
      <Concat>
        <Select>
          <Condition text="def(($a == 1))">
            <Concat> </Concat>
          </Condition>
          <Condition text="!def(($a == 1))">
            <Concat>
              <Select>
                <Condition text="def(($b == 2))">
                  <Concat> </Concat>
                </Condition>
                <Condition text="!def(($b == 2))">
                  <Concat>
                    <Literal Text="default" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="10"/>
                  </Concat>
                </Condition>
              </Select>
            </Concat>
          </Condition>
        </Select>
        <Select>
          <Condition text="def(($a == 1))">
            <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="6"/>
          </Condition>
          <Condition text="(!def(($a == 1))&amp;def(($b == 2)))">
            <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="8"/>
          </Condition>
          <Condition text="(!def(($a == 1))&amp;!def(($b == 2)))">
            <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="11"/>
          </Condition>
        </Select>
      </Concat>
    </Repeat>
    <Select>
      <Condition text="def(($a == 1))">
        <Literal Text="a" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="6"/>
      </Condition>
      <Condition text="(!def(($a == 1))&amp;def(($b == 2)))">
        <Literal Text="b" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="8"/>
      </Condition>
      <Condition text="(!def(($a == 1))&amp;!def(($b == 2)))">
        <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/inner_to_outer_scope/join01.php" Line="11"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>