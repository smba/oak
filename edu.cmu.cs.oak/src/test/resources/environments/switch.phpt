<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Select>
      <Constraint Text="$o == 1"/>
      <Concat>
        <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="5"/>
      </Concat>
      <Select>
        <Constraint Text="$o == 2"/>
        <Concat>
          <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="9"/>
        </Concat>
        <Select>
          <Constraint Text="$o == 3"/>
          <Concat>
            <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="13"/>
          </Concat>
          <Concat>
            <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="16"/>
          </Concat>
        </Select>
      </Select>
    </Select>
    <Select>
      <Constraint Text="$o == 1"/>
      <Literal Text="5" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
      <Select>
        <Constraint Text="$o == 2"/>
        <Literal Text="6" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
        <Literal Text="4" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/environments/switch.php" Line="19"/>
      </Select>
    </Select>
  </Concat>
</DataModel>