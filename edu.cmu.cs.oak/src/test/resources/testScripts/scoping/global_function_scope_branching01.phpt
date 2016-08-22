<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Concat>
      <Select>
        <Condition text="!def(symbolic_function())">
          <Concat> </Concat>
        </Condition>
      </Select>
    </Concat>
    <Select>
      <Condition text="def(symbolic_function())">
        <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching01.php" Line="8"/>
      </Condition>
      <Condition text="!def(symbolic_function())">
        <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching01.php" Line="10"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>