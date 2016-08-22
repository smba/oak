<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Select>
      <Condition text="def($o == 1)">
        <Concat>
          <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="5"/>
        </Concat>
      </Condition>
      <Condition text="!def($o == 1)">
        <Select>
          <Condition text="def($o == 2)">
            <Concat>
              <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="9"/>
            </Concat>
          </Condition>
          <Condition text="!def($o == 2)">
            <Select>
              <Condition text="def($o == 3)">
                <Concat>
                  <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="13"/>
                </Concat>
              </Condition>
              <Condition text="!def($o == 3)">
                <Concat>
                  <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="16"/>
                </Concat>
              </Condition>
            </Select>
          </Condition>
        </Select>
      </Condition>
    </Select>
    <Select>
      <Condition text="def($o == 1)">
        <Literal Text="5" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
      </Condition>
      <Condition text="(!def($o == 1)&amp;def($o == 2))">
        <Literal Text="6" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
      </Condition>
      <Condition text="(!def($o == 1)&amp;!def($o == 2))">
        <Literal Text="4" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>