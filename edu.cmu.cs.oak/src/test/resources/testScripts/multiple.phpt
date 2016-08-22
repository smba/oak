<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Select>
      <Condition text="def(&quot;A&quot;)">
        <Concat>
          <Select>
            <Condition text="def(&quot;B&quot;)">
              <Concat> </Concat>
            </Condition>
            <Condition text="!def(&quot;B&quot;)">
              <Concat>
                <Select>
                  <Condition text="def(&quot;C&quot;)">
                    <Concat> </Concat>
                  </Condition>
                  <Condition text="!def(&quot;C&quot;)">
                    <Concat>
                      <Select>
                        <Condition text="!def(&quot;D&quot;)">
                          <Concat> </Concat>
                        </Condition>
                      </Select>
                    </Concat>
                  </Condition>
                </Select>
              </Concat>
            </Condition>
          </Select>
        </Concat>
      </Condition>
      <Condition text="!def(&quot;A&quot;)">
        <Concat>
          <Select>
            <Condition text="def(&quot;E&quot;)">
              <Concat>
                <Select>
                  <Condition text="!def(&quot;F&quot;)">
                    <Concat> </Concat>
                  </Condition>
                </Select>
              </Concat>
            </Condition>
            <Condition text="!def(&quot;E&quot;)">
              <Concat> </Concat>
            </Condition>
          </Select>
        </Concat>
      </Condition>
    </Select>
    <Select>
      <Condition text="(!def(&quot;C&quot;)&amp;def(&quot;D&quot;)&amp;!def(&quot;B&quot;)&amp;def(&quot;A&quot;))">
        <Select>
          <Condition text="(!def(&quot;C&quot;)&amp;def(&quot;D&quot;)&amp;!def(&quot;B&quot;)&amp;def(&quot;A&quot;))">
            <Concat>
              <Literal Text="AD" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="22"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
          </Condition>
          <Condition text="(def(&quot;C&quot;)|!def(&quot;D&quot;)|def(&quot;B&quot;)|!def(&quot;A&quot;))">
            <Undef/>
          </Condition>
        </Select>
      </Condition>
      <Condition text="(!def(&quot;C&quot;)&amp;!def(&quot;D&quot;)&amp;!def(&quot;B&quot;)&amp;def(&quot;A&quot;))">
        <Select>
          <Condition text="(!def(&quot;C&quot;)&amp;!def(&quot;D&quot;)&amp;!def(&quot;B&quot;)&amp;def(&quot;A&quot;))">
            <Concat>
              <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="24"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
          </Condition>
          <Condition text="(def(&quot;C&quot;)|def(&quot;D&quot;)|def(&quot;B&quot;)|!def(&quot;A&quot;))">
            <Undef/>
          </Condition>
        </Select>
      </Condition>
      <Condition text="(def(&quot;A&quot;)&amp;def(&quot;B&quot;))">
        <Select>
          <Condition text="(def(&quot;A&quot;)&amp;def(&quot;B&quot;))">
            <Concat>
              <Literal Text="AB" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="17"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
          </Condition>
          <Condition text="(!def(&quot;A&quot;)|!def(&quot;B&quot;))">
            <Undef/>
          </Condition>
        </Select>
      </Condition>
      <Condition text="(!def(&quot;B&quot;)&amp;def(&quot;C&quot;)&amp;def(&quot;A&quot;))">
        <Select>
          <Condition text="(!def(&quot;B&quot;)&amp;def(&quot;C&quot;)&amp;def(&quot;A&quot;))">
            <Concat>
              <Literal Text="AC" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="19"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
          </Condition>
          <Condition text="(def(&quot;B&quot;)|!def(&quot;C&quot;)|!def(&quot;A&quot;))">
            <Undef/>
          </Condition>
        </Select>
      </Condition>
      <Condition text="(!def(&quot;A&quot;)&amp;(!def(&quot;E&quot;)|!def(&quot;F&quot;)))">
        <Undef/>
      </Condition>
      <Condition text="(def(&quot;E&quot;)&amp;def(&quot;F&quot;)&amp;!def(&quot;A&quot;))">
        <Select>
          <Condition text="(def(&quot;E&quot;)&amp;def(&quot;F&quot;)&amp;!def(&quot;A&quot;))">
            <Concat>
              <Literal Text="E" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="30"/>
              <Literal Text="N" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/testScripts/multiple.php" Line="36"/>
            </Concat>
          </Condition>
          <Condition text="(!def(&quot;E&quot;)|!def(&quot;F&quot;)|def(&quot;A&quot;))">
            <Undef/>
          </Condition>
        </Select>
      </Condition>
    </Select>
  </Concat>
</DataModel>