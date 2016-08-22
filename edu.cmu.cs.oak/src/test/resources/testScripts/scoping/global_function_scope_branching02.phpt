<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Concat>
      <Select>
        <Condition text="def(symbolic_function())">
          <Concat>
            <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="9"/>
          </Concat>
        </Condition>
        <Condition text="!def(symbolic_function())">
          <Concat>
            <Literal Text="d" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="12"/>
          </Concat>
        </Condition>
      </Select>
      <Select>
        <Condition text="def(symbolic_function())">
          <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="9"/>
        </Condition>
        <Condition text="!def(symbolic_function())">
          <Literal Text="d" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="12"/>
        </Condition>
      </Select>
    </Concat>
    <Select>
      <Condition text="def(symbolic_function())">
        <Literal Text="c" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="9"/>
      </Condition>
      <Condition text="!def(symbolic_function())">
        <Literal Text="d" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/scoping/global_function_scope_branching02.php" Line="12"/>
      </Condition>
    </Select>
  </Concat>
</DataModel>