<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Select>
      <Condition text="def(! is_null())">
        <Concat>
          <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="17"/>
          <Select>
            <Condition text="def(! is_null())">
              <Concat>
                <Literal Text="&lt;p&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="8"/>
                <Select>
                  <Condition text="def(! is_null())">
                    <Concat>
                      <Literal Text="Ciao! Como estai? " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="12"/>
                      <Literal Text="Mi chiamo Stefano." Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="24"/>
                    </Concat>
                  </Condition>
                  <Condition text="!def(! is_null())">
                    <Undef/>
                  </Condition>
                </Select>
                <Literal Text="&lt;/p&gt;" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="8"/>
              </Concat>
            </Condition>
            <Condition text="!def(! is_null())">
              <Undef/>
            </Condition>
          </Select>
          <Literal Text="&lt;/b&gt;" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/callbackAPI/wp_callback_test.php" Line="17"/>
        </Concat>
      </Condition>
      <Condition text="!def(! is_null())">
        <Undef/>
      </Condition>
    </Select>
  </Concat>
</DataModel>