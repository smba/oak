<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Select>
      <Constraint Text="&quot;A&quot;"/>
      <Select>
        <Constraint Text="&quot;B&quot;"/>
        <Select>
          <Constraint Text="&quot;B&quot;"/>
          <Concat>
            <Literal Text="AB" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="17"/>
            <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
          </Concat>
          <Undef/>
        </Select>
        <Select>
          <Constraint Text="&quot;C&quot;"/>
          <Select>
            <Constraint Text="&quot;C&quot;"/>
            <Concat>
              <Literal Text="AC" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="19"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
            <Undef/>
          </Select>
          <Select>
            <Constraint Text="&quot;D&quot;"/>
            <Select>
              <Constraint Text="&quot;D&quot;"/>
              <Concat>
                <Literal Text="AD" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="22"/>
                <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
              </Concat>
              <Undef/>
            </Select>
            <Select>
              <Constraint Text="!(&quot;D&quot;)"/>
              <Concat>
                <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="24"/>
                <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
              </Concat>
              <Undef/>
            </Select>
          </Select>
        </Select>
      </Select>
      <Select>
        <Constraint Text="&quot;E&quot;"/>
        <Select>
          <Constraint Text="&quot;F&quot;"/>
          <Select>
            <Constraint Text="&quot;F&quot;"/>
            <Concat>
              <Literal Text="E" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="30"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
            <Undef/>
          </Select>
          <Undef/>
        </Select>
        <Undef/>
      </Select>
    </Select>
  </Concat>
</DataModel>