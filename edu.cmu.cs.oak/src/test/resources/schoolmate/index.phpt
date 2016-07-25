<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<DataModel>
  <Concat>
    <Concat>
      <Literal Text="Unable to retrieve school name: " Length="32" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/header.php" Line="4"/>
      <Symbolic Text="mysql_error()"/>
    </Concat>
    <Concat>
      <Literal 
      Text="&lt;html&gt;
 &lt;head&gt;
 &lt;title&gt;SchoolMate - " Length="36" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/header.php" Line="17">
</Literal>
      <Symbolic Text="htmlspecialchars()"/>
      <Literal 
      Text="&lt;/title&gt;
 &lt;style type=&quot;text/css&quot;&gt;

 /* BODY */
 body
 {
  background-color: #336699;
 }

 /* LINKS */
  A.footer
  {
    font-family: arial;
    font-size: 10pt;
    font-weight: normal;
    color: silver;
    text-decoration: underline;
  }

  A.footer:hover
  {
    font-family: arial;
    font-size: 10pt;
    font-weight: normal;
    color: black;
	text-decoration: underline;
  }

  A.menu
  {
	font-family: arial;
	font-size: 12pt;
	font-weight: bold;
	color: #e6ca3d;
	text-decoration: none;
  }

  A.menu:hover
  {
	font-family: arial;
    font-size: 12pt;
    font-wight: bold;
	color: #FFFFBB;
    text-decoration: none;
  }

  A.pagenum
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #808080;
   text-decoration: none;
  }

  A.pagenum:hover
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #ACACAC;
   text-decoration: none;
  }

  A.selectedpagenum
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #000000;
   text-decoration: underline;
  }

  A.selectedpagenum:hover
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #ACACAC;
   text-decoration: underline;
  }

  A.items
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #000000;
   text-decoration: underline;
  }

  A.items:hover
  {
   font-family: arial;
   font-size: 10pt;
   font-weight: normal;
   color: #5F5F5F;
   text-decoration: underline;
  }

  /* TABLES */
  table
  {
	background-color: #FFFFFF;
  }

  table.y
  {
	background-color: #FFFFBB;
  }

  table.dynamiclist
  {
   border-color: #585858;
   border-width: .075EM;
   border-style: solid;
   padding-right: 1px;
   padding-bottom: 1px;
   padding-left: 0px;
   padding-top: 0px;
  }

  td.b
  {
   background-color: #336699;
  }

  td.bv
  {
   background-color: #336699;
   background-repeat: repeat-y;
  }

  td.w
  {
   background-color: #FFFFFF;
  }

  td.announcement
  {
   text-align: justify;
   padding-left: 20px;
   padding-right: 20px;
  }

  .odd
  {
   background-color: #ACACAC;
   text-align: center;
  }

  .even
  {
   background-color: #E0E0E0;
   text-align: center;
  }

  .header
  {
   background-color: #FFFFBB;
  }

 /* HEADERS */
  h1
  {
   	font-family: times;
    font-size: 22pt;
    font-weight: bold;
    color: #000000;
    text-decoration: none;
    text-align: center;
  }

  h2.message
  {
	font-family: times;
    font-size: 16pt;
    font-weight: bold;
    text-decoration: none;
    text-align: center;
  }

 /* CUSTOM CLASSES */
  .messagebox
  {
	background-color: #FFFFBB;
	border-style: solid;
	border-width: .075EM;
	border-color: #e6ca3d;
	padding: 10px 10px 10px 10px;
	min-width: 150px;
	min-height: 150px;
	width: 300;
	height: 200;
	font-family: arial;
	font-size: 10pt;
	text-align: left;
	overflow: none;
  }

  .messageboxcenter
  {
	background-color: #FFFFBB;
	border-style: solid;
	border-width: .075EM;
	border-color: #e6ca3d;
	padding: 10px 10px 10px 10px;
	min-width: 150px;
	min-height: 50px;
	width: 300;
	height: 200;
	font-family: arial;
	text-align: center;
	vertical-align: middle;
	overflow: auto;
  }

  .footer
  {
    font-family: arial;
    font-size: 10pt;
    font-weight: normal;
    color: silver;
  }

  .yellowtext
  {
	font-family: times;
	font-size: 25pt;
	font-weight: normal;
	color: #e6ca3d;
	text-decoration: none;
  }


&lt;/style&gt;
&lt;/head&gt;
 " Length="3420" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/header.php" Line="254">
</Literal>
    </Concat>
    <Concat>
      <Concat>
        <Literal 
        Text="Unable to validate login and password with the database: " Length="57" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ValidateLogin.php" Line="4">
</Literal>
        <Symbolic Text="mysql_error()"/>
      </Concat>
      <Select>
        <Constraint Text="(md5() != $result)"/>
        <Concat> </Concat>
        <Concat>
          <Concat>
            <Literal Text="Unable to get user type: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ValidateLogin.php" Line="20"/>
            <Symbolic Text="mysql_error()"/>
          </Concat>
          <Concat>
            <Literal Text="Unable to get userid from users: " Length="33" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ValidateLogin.php" Line="27"/>
            <Symbolic Text="mysql_error()"/>
          </Concat>
          <Select>
            <Constraint Text="$_SESSION[&quot;usertype&quot;] == &quot;Admin&quot;"/>
            <Concat> </Concat>
            <Select>
              <Constraint Text="$_SESSION[&quot;usertype&quot;] == &quot;Teacher&quot;"/>
              <Concat> </Concat>
              <Select>
                <Constraint Text="$_SESSION[&quot;usertype&quot;] == &quot;Substitute&quot;"/>
                <Concat> </Concat>
                <Select>
                  <Constraint Text="$_SESSION[&quot;usertype&quot;] == &quot;Student&quot;"/>
                  <Concat> </Concat>
                  <Select>
                    <Constraint Text="$_SESSION[&quot;usertype&quot;] == &quot;Parent&quot;"/>
                    <Concat> </Concat>
                    <Concat>
                      <Literal 
                      Text="ValidateLogin.php: Unable to determine the type of user.  Please verify." Length="72" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ValidateLogin.php" Line="59">
</Literal>
                    </Concat>
                  </Select>
                </Select>
              </Select>
            </Select>
          </Select>
        </Concat>
      </Select>
    </Concat>
    <Select>
      <Constraint Text="$page == 0"/>
      <Concat>
        <Literal 
        Text="
 &lt;body onLoad='document.login.username.focus();'&gt;
 " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="4">
</Literal>
        <Concat>
          <Literal 
          Text="&lt;table cellpadding=0 cellspacing=0 border=0 width='100%' height='80'&gt;
 &lt;tr&gt;
 &lt;td class='b' width='300'&gt;
  &lt;img src='./images/title.gif' height='75' width='300' /&gt;
 &lt;/td&gt;
 &lt;td class='b'&gt;
  &lt;table cellpadding=0 cellspacing=0 border=0 width='80%'&gt;
  &lt;tr&gt;
  &lt;td class='b'&gt;
   &lt;div class='yellowtext' align='center'&gt;" Length="311" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="11">
</Literal>
          <Symbolic Text="htmlspecialchars()"/>
          <Literal 
          Text="&lt;/div&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;

 &lt;table width='100%' height='88%' border=0 cellspacing=0 cellpadding=0 align='center'&gt;
 &lt;tr&gt;
  &lt;td class='b' width='130' height=10&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height=10 background='./images/top.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;

" Length="423" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="27">
</Literal>
        </Concat>
        <Concat>
          <Literal 
          Text="
  &lt;tr&gt;
  &lt;td class='b' width=130 valign='top'&gt;
   &lt;br&gt;
  &lt;/td&gt;
  &lt;td class='bv' width=10 background='./images/left.gif'&gt;&amp;nbsp;&lt;/td&gt;
  &lt;td class='w' valign='top'&gt;
   &lt;table border=0 cellspacing=0 cellpadding=25 width='100%' height='100%'&gt;
	&lt;tr&gt;
	 &lt;td valign='top'&gt;
	  &lt;br&gt;
	  &lt;table width='150' border=0 align='center' cellspacing=0 cellpadding=5&gt;
	  &lt;tr&gt;

	   &lt;td width='50%' align='left' valign='top'&gt;
		&lt;div class='messagebox'&gt;
		  " Length="435" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="31">
</Literal>
          <Symbolic Text="mysql_result()"/>
          <Literal 
          Text="
		&lt;/div&gt;
	   &lt;/td&gt;

	 &lt;td width='50%' align='right' valign='top'&gt;
		&lt;div class='messagebox'&gt;
		 &lt;br&gt;
		 &lt;form action='./index.php' method='post' name='login'&gt;" Length="159" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="38">
</Literal>
        </Concat>
        <Concat>
          <Literal 
          Text="&lt;font color='red'&gt;&lt;center&gt;Invalid username or password!&lt;/center&gt;&lt;/font&gt;" Length="71" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="42">
</Literal>
        </Concat>
        <Concat>
          <Literal 
          Text=" &lt;table width='100%' height='85%' border=0 cellspacing=0 cellpadding=0 align='center' class='y'&gt;
		  &lt;tr&gt;
		   &lt;td align='right' height=50 valign='middle'&gt;&lt;b&gt;Username:&amp;nbsp;&lt;/b&gt;&lt;/td&gt;
		   &lt;td&gt;&lt;input type=text name='username' maxlength=15 width=18&gt;&lt;/td&gt;
		  &lt;/tr&gt;
		  &lt;tr&gt;
		   &lt;td align='right' height=45 valign='middle'&gt;&lt;b&gt;Password:&amp;nbsp;&lt;/b&gt;&lt;/td&gt;
		   &lt;td&gt;&lt;input type=password name='password' maxlength=15 width=18&gt;&lt;/td&gt;
		  &lt;/tr&gt;
		  &lt;tr&gt;
		   &lt;td&gt;&amp;nbsp;&lt;/td&gt;
		   &lt;td align='center' height=45&gt;&lt;input type=submit value='Login' onClick='document.login.login.value=1;'&gt;&lt;/td&gt;

		  &lt;/tr&gt;
		 &lt;/table&gt;
	   &lt;input type='hidden' name='page' value='" Length="643" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="60">
</Literal>
          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="95"/>
          <Literal 
          Text="'&gt;
	   &lt;input type='hidden' name='login'&gt;
	  &lt;/form&gt;
	 &lt;/div&gt;
	&lt;/td&gt;

   &lt;/tr&gt;
   &lt;tr&gt;

   &lt;td width='50%' align='right' valign='top'&gt;
	&lt;div class='messageboxcenter'&gt;
	 &lt;table border=0 cellpadding=0 cellspacing=0 class='y'&gt;
	 &lt;tr&gt;
	  &lt;td align='center' valign='middle'&gt;
		&lt;br&gt;
		&lt;img src='./images/school.jpg' style='padding-left: 30px; padding-top: 20px;' width='233' height='111' align='center' valign='middle' /&gt;
	  &lt;/td&gt;
	 &lt;/tr&gt;
	 &lt;/table&gt;
	&lt;/div&gt;
   &lt;/td&gt;

	&lt;td width='50%' align='left' valign='top'&gt;
	 &lt;div class='messageboxcenter'&gt;
	  &lt;h2 class='message'&gt;Today's Message&lt;/h2&gt; &lt;br&gt;
	   " Length="592" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="85">
</Literal>
          <Symbolic Text="mysql_result()"/>
          <Literal 
          Text="
	 &lt;/div&gt;
	&lt;/td&gt;

   &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;

  &lt;/tr&gt;
 &lt;/table&gt;
&lt;/td&gt;" Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Login.php" Line="95">
</Literal>
        </Concat>
      </Concat>
      <Select>
        <Constraint Text="$page == 1"/>
        <Concat>
          <Concat>
            <Literal Text="Invalid User!" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="5"/>
          </Concat>
          <Literal 
          Text="&lt;script language='JavaScript'&gt;
  function logoutAdmin()
  {
	document.admin.logout.value=1;
	document.admin.submit();
  }

  function classes()
  {
	document.admin.page2.value=0;
	document.admin.submit();
  }

  function schoolInfo()
  {
	document.admin.page2.value=1;
	document.admin.submit();
  }

  function students()
  {
	document.admin.page2.value=2;
	document.admin.submit();
  }

  function teachers()
  {
	document.admin.page2.value=3;
	document.admin.submit();
  }

  function announcements()
  {
	document.admin.page2.value=4;
	document.admin.submit();
  }

  function semesters()
  {
	document.admin.page2.value=5;
	document.admin.submit();
  }

  function terms()
  {
	document.admin.page2.value=6;
	document.admin.submit();
  }

  function users()
  {
	document.admin.page2.value=10;
	document.admin.submit();
  }

  function parents()
  {
	document.admin.page2.value=22;
	document.admin.submit();
  }

  function register()
  {
	document.admin.page2.value=26;
	document.admin.submit();
  }

  function attendance()
  {
	document.admin.page2.value=30;
	document.admin.submit();
  }
 &lt;/script&gt;

 &lt;body&gt;" Length="1115" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="83">
</Literal>
          <Concat>
            <Literal 
            Text="&lt;table cellpadding=0 cellspacing=0 border=0 width='100%' height='80'&gt;
 &lt;tr&gt;
 &lt;td class='b' width='300'&gt;
  &lt;img src='./images/title.gif' height='75' width='300' /&gt;
 &lt;/td&gt;
 &lt;td class='b'&gt;
  &lt;table cellpadding=0 cellspacing=0 border=0 width='80%'&gt;
  &lt;tr&gt;
  &lt;td class='b'&gt;
   &lt;div class='yellowtext' align='center'&gt;" Length="311" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="11">
</Literal>
            <Symbolic Text="htmlspecialchars()"/>
            <Literal 
            Text="&lt;/div&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;

 &lt;table width='100%' height='88%' border=0 cellspacing=0 cellpadding=0 align='center'&gt;
 &lt;tr&gt;
  &lt;td class='b' width='130' height=10&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height=10 background='./images/top.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;

" Length="423" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="27">
</Literal>
          </Concat>
          <Concat>
            <Literal 
            Text="
 &lt;tr&gt;
  &lt;td class='b' width=130 valign='top'&gt;
   &lt;br&gt;
   &lt;form name='admin' action='./index.php' method='POST'&gt;

   &lt;a class='menu' href='javascript: schoolInfo();' onMouseover=&quot;window.status='Manage School Information'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;School&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: terms();' onMouseover=&quot;window.status='Manage Terms'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Terms&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: semesters();' onMouseover=&quot;window.status='Manage Semesters'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Semesters&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: classes();' onMouseover=&quot;window.status='Manage Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Classes&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: users();' onMouseover=&quot;window.status='Manage Users'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Users&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: teachers();' onMouseover=&quot;window.status='Manage Teachers'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Teachers&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: students();' onMouseover=&quot;window.status='Manage Students'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Students&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: register();' onMouseover=&quot;window.status='Register Students for Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Registration&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: attendance();' onMouseover=&quot;window.status='Keep Attendance'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Attendance&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: parents();' onMouseover=&quot;window.status='Manage Parents'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Parents&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: announcements();' onMouseover=&quot;window.status='Manage Announcements'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Announcements&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;
   &lt;a class='menu' href='javascript: logoutAdmin();' onMouseover=&quot;window.status='Log Out';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;Log Out&lt;/a&gt;

   &lt;input type='hidden' name='page2' value='" Length="2313" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="117">
</Literal>
            <Symbolic Text="$_POST"/>
            <Literal 
            Text="'&gt;
   &lt;input type='hidden' name='logout'&gt;
   &lt;input type='hidden' name='page' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="119">
</Literal>
            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="126"/>
            <Literal 
            Text="'&gt;
 &lt;/form&gt;
  &lt;/td&gt;
  &lt;td class='b' width=10 background='./images/left.gif'&gt;&lt;div style='letter-spacing: 1pt;'&gt;&amp;nbsp;&lt;/div&gt;&lt;/td&gt;
  &lt;td class='w' valign='top'&gt;
   &lt;table border=0 cellspacing=0 cellpadding=10 width='100%' height='100%'&gt;
	&lt;tr&gt;
	 &lt;td valign='top'&gt;" Length="259" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="126">
</Literal>
          </Concat>
          <Select>
            <Constraint Text="$page2 == 0"/>
            <Concat>
              <Concat>
                <Select>
                  <Constraint Text="($_POST[&quot;fullyear&quot;] != 1)"/>
                  <Concat>
                    <Concat>
                      <Literal 
                      Text="ManageClasses.php: Unable to insert new class - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="24">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                  </Concat>
                  <Concat>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="ManageClasses.php: Unable to insert new class - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="40">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="ManageClasses.php: Unable to insert new class - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="47">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                    </Concat>
                  </Concat>
                </Select>
              </Concat>
              <Concat>
                <Concat>
                  <Literal 
                  Text="ManageClasses.php: Unable to update the class information - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="74">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
              </Concat>
              <Literal 
              Text="&lt;script language='JavaScript'&gt;
  // Function to make sure the user wants to delete the class(es) //
  function validate()
  {
   if( document.classes.selectclass.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Deleteing a class will also delete the class bulletins, tardies, attendance, assignments, and registrations that occured during that class. \n \nAre you sure?&quot;);

	if( confirmed == true )
	{
	 document.classes.submit();
	}
   }
   else
   {
	alert('You must select a class to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.classes.selectclass.value == 1 )
   {
	document.classes.submit();
   }
   else
   {
	if( document.classes.selectclass.value &gt; 1 )
	{
	 alert('You can only edit one class at a time.');
	}
	else
	{
	 alert(document.classes.selectclass.value);
	 alert('You must select a class to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.classes.elements[row].checked)
   {
	document.classes.selectclass.value = Math.round(document.classes.selectclass.value) - 1;
   }
   else
   {
	document.classes.selectclass.value = Math.round(document.classes.selectclass.value) + 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Classes&lt;/h1&gt;
 &lt;br&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;

 &lt;table align='center' width='900' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;
" Length="1550" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="160">
</Literal>
              <Concat>
                <Literal 
                Text="ViewCourses.php: Unable to get a list of semesters for drop-down - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="163">
</Literal>
                <Symbolic Text="mysql_error()"/>
              </Concat>
              <Repeat>
                <Constraint Text="$page2 == 0"/>
                <Concat>
                  <Concat>
                    <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                    <Undef/>
                    <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                    <Select>
                      <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                      <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                      <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                    </Select>
                    <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                    <Undef/>
                    <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="182"/>
                  </Concat>
                </Concat>
              </Repeat>
              <Concat>
                <Literal Text=" &lt;option value='-1' " Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="188"/>
                <Select>
                  <Constraint Text="($_POST[&quot;semester&quot;] == -1)"/>
                  <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="188"/>
                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="188"/>
                </Select>
                <Literal Text="&gt;All&lt;/option&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="188"/>
              </Concat>
              <Literal 
              Text="	 &lt;/select&gt;
	 &lt;br&gt;&lt;br&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;input type='button' value='Add' onClick=&quot;document.classes.page2.value='9';document.classes.submit();&quot;&gt;
  &lt;input type='button' value='Edit' onClick='document.classes.page2.value=11;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.classes.deleteclass.value=1;validate();'&gt;
  &lt;input type='button' value='Show in Grid' onClick='document.classes.page2.value=25;document.classes.submit();' /&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='900' cellpadding='5' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
	&lt;th&gt;Teacher&lt;/th&gt;
	&lt;th&gt;Semester&lt;/th&gt;
	&lt;th&gt;Section Number&lt;/th&gt;
	&lt;th&gt;Room Number&lt;/th&gt;
	&lt;th&gt;Period Number&lt;/th&gt;
	&lt;th&gt;Days&lt;/th&gt;
	&lt;th&gt;Substitute&lt;/th&gt;
   &lt;/tr&gt;" Length="763" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="215">
</Literal>
              <Select>
                <Constraint Text="($_POST[&quot;semester&quot;] != &quot;&quot;)"/>
                <Concat>
                  <Concat>
                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="221"/>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text=" - ManageClasses.php: Unable to retrieve total number of classes - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="221">
</Literal>
                    <Symbolic Text="mysql_error()"/>
                  </Concat>
                  <Symbolic Text="mysql_error()"/>
                  <Repeat>
                    <Constraint Text="($_POST[&quot;semester&quot;] != &quot;&quot;)"/>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageClasses.php: Unable to get the semester title for the current course being displayed - " Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="247">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="ManageClasses.php: Unable to get the teacher name for the current course being displayed - " Length="91" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="251">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="ManageClasses.php: Unable to get the substitute name for the current corse being displayed - " Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="255">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="258"/>
                          <Select>
                            <Constraint Text="(($row % 2) == 0)"/>
                            <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="258"/>
                            <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="258"/>
                          </Select>
                          <Literal 
                          Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="259">
</Literal>
                          <Undef/>
                          <Literal 
                          Text="' onClick='updateboxes(1337);' /&gt;&lt;/td&gt;
	  &lt;td&gt;" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="260">
</Literal>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="261"/>
                          <Undef/>
                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="261"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="262"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="263"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="264"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="265"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="266"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="267"/>
                          <Undef/>
                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="267"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="268"/>
                        </Concat>
                      </Concat>
                    </Concat>
                  </Repeat>
                </Concat>
                <Concat> </Concat>
              </Select>
              <Literal 
              Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.classes.page2.value=9;document.classes.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.classes.page2.value=11;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.classes.deleteclass.value=1;validate();'&gt;
  &lt;input type='button' value='Show in Grid' onClick='document.classes.page2.value=25;document.classes.submit();' /&gt;

  &lt;br&gt;&lt;br&gt;
  &lt;font color='red'&gt;* Deleting a class will also delete the information for that class&lt;/font&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="562" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="289">
</Literal>
              <Repeat>
                <Constraint Text="$page2 == 0"/>
                <Concat>
                  <Select>
                    <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="&lt;a href='JavaScript: document.classes.deleteclass.value=0;document.classes.page2.value=0;document.classes.onpage.value=" Length="119" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295"/>
                        <Literal 
                        Text=";document.classes.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="92" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295"/>
                        <Literal 
                        Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295"/>
                        <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="295"/>
                      </Concat>
                    </Concat>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="&lt;a href='JavaScript: document.classes.deleteclass.value=0;document.classes.page2.value=0;document.classes.onpage.value=" Length="119" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299"/>
                        <Literal 
                        Text=";document.classes.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="84" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299"/>
                        <Literal 
                        Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299">
</Literal>
                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299"/>
                        <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="299"/>
                      </Concat>
                    </Concat>
                  </Select>
                </Concat>
              </Repeat>
              <Concat>
                <Literal 
                Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteclass'&gt;
  &lt;input type='hidden' name='selectclass'&gt;
  &lt;input type='hidden' name='page2' value='" Length="140" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="306">
</Literal>
                <Symbolic Text="$_POST"/>
                <Literal 
                Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="307">
</Literal>
                <Symbolic Text="$_POST"/>
                <Literal 
                Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="309">
</Literal>
                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="321"/>
                <Literal 
                Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageClasses.php" Line="321">
</Literal>
              </Concat>
            </Concat>
            <Select>
              <Constraint Text="$page2 == 1"/>
              <Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve School Address " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="5">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve PhoneNumber " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="10">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve NumSemesters " Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="15">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve NumPeriods " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="20">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve Point System " Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="25">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ManageSchoolInfo.php: Unable to retrieve sitetext " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="30">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal 
                  Text="&lt;table width='100%' border=0 cellpadding=10 cellspacing=0&gt;
&lt;tr&gt;
&lt;td&gt;

 &lt;h1&gt;Manage School Information&lt;/h1&gt;
 &lt;br&gt;

 &lt;form name='info' method='POST' action='./index.php'&gt;

 &lt;table border=0 width=500 cellspacing=0 cellpadding='3' align='center' cellpadding=0 class='dynamiclist'&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   School Name:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="389" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="52">
</Literal>
                  <Symbolic Text="htmlspecialchars()"/>
                  <Literal 
                  Text="' maxlength='50' name='schoolname' size=40&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Address:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="168" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="60">
</Literal>
                  <Symbolic Text="mysql_result()"/>
                  <Literal 
                  Text="' name='schooladdress' maxlength='50' size=40&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Phone #:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="68">
</Literal>
                  <Symbolic Text="mysql_result()"/>
                  <Literal 
                  Text="' name='schoolphone' maxlength='14'&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Semesters Per Year:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="76">
</Literal>
                  <Symbolic Text="mysql_result()"/>
                  <Literal 
                  Text="' name='numsemesters' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Periods Per Day:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="176" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="84">
</Literal>
                  <Symbolic Text="mysql_result()"/>
                  <Literal 
                  Text="' name='numperiods' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Points for A:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="92">
</Literal>
                  <Symbolic Text="number_format()"/>
                  <Literal 
                  Text="' name='apoint' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Points for B:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="167" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="100">
</Literal>
                  <Symbolic Text="number_format()"/>
                  <Literal 
                  Text="' name='bpoint' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Points for C:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="167" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="108">
</Literal>
                  <Symbolic Text="number_format()"/>
                  <Literal 
                  Text="' name='cpoint' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Points for D:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="167" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="116">
</Literal>
                  <Symbolic Text="number_format()"/>
                  <Literal 
                  Text="' name='dpoint' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right'&gt;
   Points for F:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;input type='text' value='" Length="167" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="124">
</Literal>
                  <Symbolic Text="number_format()"/>
                  <Literal 
                  Text="' name='fpoint' maxlength='3' size=3&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right' valign='top'&gt;
   Text For Login Page:
  &lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;textarea name='sitetext' cols=40 rows=10&gt;" Length="203" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="132">
</Literal>
                  <Undef/>
                  <Literal 
                  Text="&lt;/textarea&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr class='even'&gt;
  &lt;td align='right' valign='top'&gt;Today's Message:&lt;/td&gt;
  &lt;td align='left'&gt;
   &lt;textarea name='sitemessage' cols=40 rows=10&gt;" Length="169" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="138">
</Literal>
                  <Undef/>
                  <Literal 
                  Text="&lt;/textarea&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
&lt;br&gt;
 &lt;table width='500' align='center' cellpadding='0' cellspacing='0' border='0'&gt;
  &lt;tr&gt;
   &lt;td align='center'&gt;&lt;input type='button' value=' Update ' onClick='document.info.infoupdate.value=1;document.info.submit();'&gt;&lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;

 &lt;input type='hidden' name='infoupdate' value=''&gt;
 &lt;input type='hidden' name='page2' value='" Length="373" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="150">
</Literal>
                  <Symbolic Text="$_POST"/>
                  <Literal 
                  Text="'&gt;
 &lt;input type='hidden' name='logout'&gt;
 &lt;input type='hidden' name='page' value='" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="152">
</Literal>
                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="166"/>
                  <Literal 
                  Text="'&gt;
 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;

&lt;/td&gt;
&lt;/tr&gt;
 &lt;/table&gt;
 " Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSchoolInfo.php" Line="166">
</Literal>
                </Concat>
              </Concat>
              <Select>
                <Constraint Text="$page2 == 2"/>
                <Concat>
                  <Concat>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="ManageStudents.php: Uanable to get list of users - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="9">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint 
                        Text="(((($_POST[&quot;username&quot;] != &quot;&quot;) &amp;&amp; ($_POST[&quot;fname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;mi&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;lname&quot;] != &quot;&quot;))">
</Constraint>
                        <Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="&lt;br&gt;&lt;br&gt;&lt;h1 align='center'&gt;&lt;font color='red'&gt;That user is already assigned to a student!&lt;/font&gt;&lt;/h1&gt;
	 &lt;br&gt;
	 &lt;form name='uhoh' action='./index.php' method='POST'&gt;
	 &lt;center&gt;&lt;input type='button' value='&amp;nbsp;Back&amp;nbsp;' onClick='document.uhoh.page2.value=2;document.uhoh.submit();'&gt;&lt;/center&gt;
	 &lt;input type='hidden' name='page2' value='" Length="335" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="20">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="'&gt;
	 &lt;input type='hidden' name='logout'&gt;
	 &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="22">
</Literal>
                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="23"/>
                              <Literal Text="'&gt; &lt;/form&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="23"/>
                            </Concat>
                          </Concat>
                        </Concat>
                      </Repeat>
                      <Concat>
                        <Literal 
                        Text="ManageStudents.php: Unable to insert new student - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="29">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                    </Concat>
                  </Concat>
                  <Concat>
                    <Concat>
                      <Literal 
                      Text="ManageStudents.php: Unable to update the student information - " Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="37">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                  </Concat>
                  <Concat>
                    <Repeat>
                      <Constraint Text="($_POST[&quot;deletestudent&quot;] == 1)"/>
                      <Concat>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="DeleteFunctions.php: Unable to delete selected Student(s) - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="62">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                        </Concat>
                      </Concat>
                    </Repeat>
                  </Concat>
                  <Literal 
                  Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the student wants to delete the student(s) //
  function validate()
  {
   if( document.students.selectstudent.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this student?&quot;);

	if( confirmed == true )
	{
	 document.students.submit();
	}
   }
   else
   {
	alert('You must select a student to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.students.selectstudent.value == 1 )
   {
	document.students.submit();
   }
   else
   {
	if( document.students.selectstudent.value &gt; 1 )
	{
	 alert('You can only edit one student at a time.');
	}
	else
	{
	 alert('You must select a student to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.students.elements[row].checked)
   {
	document.students.selectstudent.value = Math.round(document.students.selectstudent.value) + 1;
   }
   else
   {
	document.students.selectstudent.value = Math.round(document.students.selectstudent.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Students&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='425' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='students' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.students.page2.value=20;document.students.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.students.page2.value=21;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.students.deletestudent.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='425' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Middle Initial&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;" Length="1882" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="126">
</Literal>
                  <Concat>
                    <Literal 
                    Text="Managestudents.php: Unable to retrieve total number of students - " Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="130">
</Literal>
                    <Symbolic Text="mysql_error()"/>
                  </Concat>
                  <Repeat>
                    <Constraint Text="$page2 == 2"/>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="152"/>
                          <Select>
                            <Constraint Text="(($row % 2) == 0)"/>
                            <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="152"/>
                            <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="152"/>
                          </Select>
                          <Literal 
                          Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="153">
</Literal>
                          <Undef/>
                          <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="153"/>
                          <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="158"/>
                          <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="154"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="155"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="156"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="157"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="158"/>
                        </Concat>
                      </Concat>
                    </Concat>
                  </Repeat>
                  <Literal 
                  Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;b&gt;Reports:&lt;/b&gt;
  &lt;select name='report' onChange='document.students.page2.value=document.students.report.value;document.students.deletestudent.value=0;document.students.submit();'&gt;
   &lt;option&gt;Choose a Report...&lt;/option&gt;
   &lt;option value='27'&gt;Deficiency Report&lt;/option&gt;
   &lt;option value='28'&gt;Grade Report&lt;/option&gt;
   &lt;option value='32'&gt;Points Report&lt;/option&gt;
   &lt;option value='1337'&gt;Report Cards&lt;/option&gt;
  &lt;/select&gt;
 &amp;nbsp;&amp;nbsp;&amp;nbsp;&amp;nbsp;&lt;b&gt;Term: &lt;/b&gt; &lt;select name='term' onChange='document.students.submit();'&gt;" Length="533" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="172">
</Literal>
                  <Concat>
                    <Literal 
                    Text="ManageStudents.php: Unable to get a list of terms for drop-down - " Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="175">
</Literal>
                    <Symbolic Text="mysql_error()"/>
                  </Concat>
                  <Repeat>
                    <Constraint Text="$page2 == 2"/>
                    <Concat>
                      <Concat>
                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                        <Undef/>
                        <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                        <Select>
                          <Constraint Text="(($_POST[&quot;term&quot;] == $term[0]) &amp;&amp; ($_POST[&quot;term&quot;] != null))"/>
                          <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                          <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                        </Select>
                        <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                        <Undef/>
                        <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="186"/>
                      </Concat>
                    </Concat>
                  </Repeat>
                  <Literal 
                  Text="
	 &lt;/select&gt;
  &lt;br /&gt;&lt;br /&gt;
  &lt;input type='button' value='Add' onClick='document.students.page2.value=20;document.students.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.students.page2.value=21;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.students.deletestudent.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="358" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="197">
</Literal>
                  <Repeat>
                    <Constraint Text="$page2 == 2"/>
                    <Concat>
                      <Select>
                        <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="&lt;a href='JavaScript: document.students.deletestudent.value=0;document.students.page2.value=2;document.students.onpage.value=" Length="124" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203"/>
                            <Literal 
                            Text=";document.students.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203"/>
                            <Literal 
                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203"/>
                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="203"/>
                          </Concat>
                        </Concat>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="&lt;a href='JavaScript: document.students.deletestudent.value=0;document.students.page2.value=2;document.students.onpage.value=" Length="124" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207"/>
                            <Literal 
                            Text=";document.students.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207"/>
                            <Literal 
                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207">
</Literal>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207"/>
                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="207"/>
                          </Concat>
                        </Concat>
                      </Select>
                    </Concat>
                  </Repeat>
                  <Concat>
                    <Literal 
                    Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deletestudent'&gt;
  &lt;input type='hidden' name='selectstudent'&gt;
  &lt;input type='hidden' name='page2' value='" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="214">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="215">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="217">
</Literal>
                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="230"/>
                    <Literal 
                    Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageStudents.php" Line="230">
</Literal>
                  </Concat>
                </Concat>
                <Select>
                  <Constraint Text="$page2 == 3"/>
                  <Concat>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageTeachers.php: Uanable to get list of users - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="9">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Repeat>
                          <Constraint 
                          Text="((($_POST[&quot;username&quot;] != &quot;&quot;) &amp;&amp; ($_POST[&quot;fname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;lname&quot;] != &quot;&quot;))">
</Constraint>
                          <Concat>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="&lt;br&gt;&lt;br&gt;&lt;h1 align='center'&gt;&lt;font color='red'&gt;That user is already assigned to a teacher!&lt;/font&gt;&lt;/h1&gt;
	 &lt;br&gt;
	 &lt;form name='uhoh' action='./index.php' method='POST'&gt;
	 &lt;center&gt;&lt;input type='button' value='&amp;nbsp;Back&amp;nbsp;' onClick='document.uhoh.page2.value=3;document.uhoh.submit();'&gt;&lt;/center&gt;
	 &lt;input type='hidden' name='page2' value='" Length="335" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="20">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="'&gt;
	 &lt;input type='hidden' name='logout'&gt;
	 &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="22">
</Literal>
                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="23"/>
                                <Literal Text="'&gt; &lt;/form&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="23"/>
                              </Concat>
                            </Concat>
                          </Concat>
                        </Repeat>
                        <Concat>
                          <Literal 
                          Text="ManageTeachers.php: Unable to insert new teacher - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="29">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                      </Concat>
                    </Concat>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="Manageteachers.php: Unable to update the teacher information - " Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="37">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                    </Concat>
                    <Concat>
                      <Repeat>
                        <Constraint Text="($_POST[&quot;deleteteacher&quot;] == 1)"/>
                        <Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="DeleteFunctions.php: Unable to delete selected Teacher(s) - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="52">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                        </Concat>
                      </Repeat>
                    </Concat>
                    <Literal 
                    Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the teacher wants to delete the teacher(s) //
  function validate()
  {
   if( document.teachers.selectteacher.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this teacher?&quot;);

	if( confirmed == true )
	{
	 document.teachers.submit();
	}
   }
   else
   {
	alert('You must select a teacher to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.teachers.selectteacher.value == 1 )
   {
	document.teachers.submit();
   }
   else
   {
	if( document.teachers.selectteacher.value &gt; 1 )
	{
	 alert('You can only edit one teacher at a time.');
	}
	else
	{
	 alert('You must select a teacher to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.teachers.elements[row].checked)
   {
	document.teachers.selectteacher.value = Math.round(document.teachers.selectteacher.value) + 1;
   }
   else
   {
	document.teachers.selectteacher.value = Math.round(document.teachers.selectteacher.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Teachers&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='400' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='teachers' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.teachers.page2.value=16;document.teachers.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.teachers.page2.value=17;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.teachers.deleteteacher.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='400' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;" Length="1857" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="125">
</Literal>
                    <Concat>
                      <Literal 
                      Text="ManageTeachers.php: Unable to retrieve total number of teachers - " Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="129">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Repeat>
                      <Constraint Text="$page2 == 3"/>
                      <Concat>
                        <Concat>
                          <Concat>
                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="151"/>
                            <Select>
                              <Constraint Text="(($row % 2) == 0)"/>
                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="151"/>
                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="151"/>
                            </Select>
                            <Literal 
                            Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="152">
</Literal>
                            <Undef/>
                            <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="152"/>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="156"/>
                            <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="153"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="154"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="155"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="156"/>
                          </Concat>
                        </Concat>
                      </Concat>
                    </Repeat>
                    <Literal 
                    Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.teachers.page2.value=16;document.teachers.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.teachers.page2.value=17;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.teachers.deleteteacher.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="347" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="167">
</Literal>
                    <Repeat>
                      <Constraint Text="$page2 == 3"/>
                      <Concat>
                        <Select>
                          <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="&lt;a href='JavaScript: document.teachers.deleteteacher.value=0;document.teachers.page2.value=3;document.teachers.onpage.value=" Length="124" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173"/>
                              <Literal 
                              Text=";document.teachers.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173"/>
                              <Literal 
                              Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173"/>
                              <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="173"/>
                            </Concat>
                          </Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="&lt;a href='JavaScript: document.teachers.deleteteacher.value=0;document.teachers.page2.value=3;document.teachers.onpage.value=" Length="124" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177"/>
                              <Literal 
                              Text=";document.teachers.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177"/>
                              <Literal 
                              Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177"/>
                              <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="177"/>
                            </Concat>
                          </Concat>
                        </Select>
                      </Concat>
                    </Repeat>
                    <Concat>
                      <Literal 
                      Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteteacher'&gt;
  &lt;input type='hidden' name='selectteacher'&gt;
  &lt;input type='hidden' name='page2' value='" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="184">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="185">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="187">
</Literal>
                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="200"/>
                      <Literal 
                      Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTeachers.php" Line="200">
</Literal>
                    </Concat>
                  </Concat>
                  <Select>
                    <Constraint Text="$page2 == 4"/>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAnnouncements.php: Unable to insert new announcement - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="9">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                      </Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAnnouncements.php: Unable to update the announcement information - " Length="73" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="16">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                      </Concat>
                      <Concat>
                        <Repeat>
                          <Constraint Text="($_POST[&quot;deleteannouncement&quot;] == 1)"/>
                          <Concat>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="DeleteFunctions.php: Unable to delete selected Announcement(s) - " Length="65" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="42">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                            </Concat>
                          </Concat>
                        </Repeat>
                      </Concat>
                      <Literal 
                      Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the announcement wants to delete the announcement(s) //
  function validate()
  {
   if( document.announcements.selectannouncement.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this announcement?&quot;);

	if( confirmed == true )
	{
	 document.announcements.submit();
	}
   }
   else
   {
	alert('You must select a announcement to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.announcements.selectannouncement.value == 1 )
   {
	document.announcements.submit();
   }
   else
   {
	if( document.announcements.selectannouncement.value &gt; 1 )
	{
	 alert('You can only edit one announcement at a time.');
	}
	else
	{
	 alert('You must select a announcement to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.announcements.elements[row].checked)
   {
	document.announcements.selectannouncement.value = Math.round(document.announcements.selectannouncement.value) + 1;
   }
   else
   {
	document.announcements.selectannouncement.value = Math.round(document.announcements.selectannouncement.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Announcements&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='announcements' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.announcements.page2.value=18;document.announcements.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.announcements.page2.value=19;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.announcements.deleteannouncement.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
	&lt;th&gt;Date&lt;/th&gt;
   &lt;/tr&gt;" Length="1996" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="104">
</Literal>
                      <Concat>
                        <Literal 
                        Text="ManageAnnouncements.php: Unable to retrieve total number of announcements - " Length="76" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="108">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="ManageAnnouncements.php: Unable to retrieve the announcements - " Length="64" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="121">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint Text="$page2 == 4"/>
                        <Concat>
                          <Concat>
                            <Concat>
                              <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="131"/>
                              <Select>
                                <Constraint Text="(($row % 2) == 0)"/>
                                <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="131"/>
                                <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="131"/>
                              </Select>
                              <Literal 
                              Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="132">
</Literal>
                              <Undef/>
                              <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="132"/>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="136"/>
                              <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="133"/>
                              <Undef/>
                              <Literal 
                              Text="&lt;/td&gt;
	  &lt;td class='announcement'&gt;" Length="34" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="134">
</Literal>
                              <Undef/>
                              <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="135"/>
                              <Symbolic Text="convertfromdb()"/>
                              <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="136"/>
                            </Concat>
                          </Concat>
                        </Concat>
                      </Repeat>
                      <Literal 
                      Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.announcements.page2.value=18;document.announcements.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.announcements.page2.value=19;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.announcements.deleteannouncement.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="372" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="147">
</Literal>
                      <Repeat>
                        <Constraint Text="$page2 == 4"/>
                        <Concat>
                          <Select>
                            <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153"/>
                                <Literal 
                                Text=";document.announcements.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="98" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153"/>
                                <Literal 
                                Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153"/>
                                <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="153"/>
                              </Concat>
                            </Concat>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157"/>
                                <Literal 
                                Text=";document.announcements.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157"/>
                                <Literal 
                                Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157">
</Literal>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157"/>
                                <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="157"/>
                              </Concat>
                            </Concat>
                          </Select>
                        </Concat>
                      </Repeat>
                      <Concat>
                        <Literal 
                        Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteannouncement'&gt;
  &lt;input type='hidden' name='selectannouncement'&gt;
  &lt;input type='hidden' name='page2' value='" Length="154" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="164">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="165">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="167">
</Literal>
                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="180"/>
                        <Literal 
                        Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAnnouncements.php" Line="180">
</Literal>
                      </Concat>
                    </Concat>
                    <Select>
                      <Constraint Text="$page2 == 5"/>
                      <Concat>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="ManageSemesters.php: Unable to insert new semester - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="10">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                        </Concat>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="ManageSemesters.php: Unable to update the semester information - " Length="65" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="20">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                        </Concat>
                        <Literal 
                        Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the user wants to delete the semester(s) //
  function validate()
  {
   if( document.semesters.selectsemester.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Deleteing a semester will also delete the classes, bulletins, tardies, attendance, assignments, and registrations that occured during that semester. \n \nAre you sure?&quot;);

	if( confirmed == true )
	{
	 document.semesters.submit();
	}
   }
   else
   {
	alert('You must select a semester to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.semesters.selectsemester.value == 1 )
   {
	document.semesters.submit();
   }
   else
   {
	if( document.semesters.selectsemester.value &gt; 1 )
	{
	 alert('You can only edit one semester at a time.');
	}
	else
	{
	 alert('You must select a semester to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.semesters.elements[row].checked)
   {
	document.semesters.selectsemester.value = Math.round(document.semesters.selectsemester.value) + 1;
   }
   else
   {
	document.semesters.selectsemester.value = Math.round(document.semesters.selectsemester.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Semesters&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='semesters' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.semesters.page2.value=7;document.semesters.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.semesters.page2.value=13;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.semesters.deletesemester.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;Semester Name&lt;/th&gt;
	&lt;th&gt;Term&lt;/th&gt;
	&lt;th&gt;Start Date&lt;/th&gt;
	&lt;th&gt;Midterm Date&lt;/th&gt;
	&lt;th&gt;End Date&lt;/th&gt;
	&lt;th&gt;Half&lt;/th&gt;
   &lt;/tr&gt;" Length="2060" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="111">
</Literal>
                        <Concat>
                          <Literal 
                          Text="ManageSemesters.php: Unable to retrieve total number of semesters - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="115">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Repeat>
                          <Constraint Text="$page2 == 5"/>
                          <Concat>
                            <Concat>
                              <Concat>
                                <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="139"/>
                                <Select>
                                  <Constraint Text="(($row % 2) == 0)"/>
                                  <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="139"/>
                                  <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="139"/>
                                </Select>
                                <Literal 
                                Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="140">
</Literal>
                                <Undef/>
                                <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="140"/>
                                <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="147"/>
                                <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="141"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="142"/>
                                <Symbolic Text="mysql_result()"/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="143"/>
                                <Symbolic Text="convertfromdb()"/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="144"/>
                                <Symbolic Text="convertfromdb()"/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="145"/>
                                <Symbolic Text="convertfromdb()"/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="146"/>
                                <Select>
                                  <Constraint Text="($smstr[6] == 1)"/>
                                  <Literal Text="First" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="146"/>
                                  <Literal Text="Second" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="146"/>
                                </Select>
                                <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="147"/>
                              </Concat>
                            </Concat>
                          </Concat>
                        </Repeat>
                        <Literal 
                        Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.semesters.page2.value=7;document.semesters.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.semesters.page2.value=13;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.semesters.deletesemester.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;font color='red'&gt;* Deleting a semester will also delete the classes in that semester and the information for those classes&lt;/font&gt;
	&lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="494" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="160">
</Literal>
                        <Repeat>
                          <Constraint Text="$page2 == 5"/>
                          <Concat>
                            <Select>
                              <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="&lt;a href='JavaScript: document.semesters.deletesemester.value=0;document.semesters.page2.value=5;document.semesters.onpage.value=" Length="128" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166"/>
                                  <Literal 
                                  Text=";document.semesters.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="94" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166"/>
                                  <Literal 
                                  Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166"/>
                                  <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="166"/>
                                </Concat>
                              </Concat>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="&lt;a href='JavaScript: document.semesters.deletesemester.value=0;document.semesters.page2.value=5;document.semesters.onpage.value=" Length="128" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170"/>
                                  <Literal 
                                  Text=";document.semesters.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="86" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170"/>
                                  <Literal 
                                  Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170"/>
                                  <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="170"/>
                                </Concat>
                              </Concat>
                            </Select>
                          </Concat>
                        </Repeat>
                        <Concat>
                          <Literal 
                          Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deletesemester'&gt;
  &lt;input type='hidden' name='selectsemester'&gt;
  &lt;input type='hidden' name='page2' value='" Length="146" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="178">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="179">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="181">
</Literal>
                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="194"/>
                          <Literal 
                          Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageSemesters.php" Line="194">
</Literal>
                        </Concat>
                      </Concat>
                      <Select>
                        <Constraint Text="$page2 == 6"/>
                        <Concat>
                          <Concat>
                            <Concat>
                              <Literal Text="ManageTerms.php: Unable to insert new term - " Length="45" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="9"/>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="ManageTerms.php: Unable to update the term information - " Length="57" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="18">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                          <Literal 
                          Text="&lt;script language='JavaScript'&gt;
  // Function to make sure the user wants to delete the term(s) //
  function validate()
  {
   if( document.terms.selectterm.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Deleteing a term will also delete the semesters, classes, bulletins, tardies, attendance, assignments, and registrations that occured during that term. \n \nAre you sure?&quot;);

	if( confirmed == true )
	{
	 document.terms.submit();
	}
   }
   else
   {
	alert('You must select a term to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.terms.selectterm.value == 1 )
   {
	document.terms.submit();
   }
   else
   {
	if( document.terms.selectterm.value &gt; 1 )
	{
	 alert('You can only edit one term at a time.');
	}
	else
	{
	 alert('You must select a term to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.terms.elements[row].checked)
   {
	document.terms.selectterm.value = Math.round(document.terms.selectterm.value) + 1;
   }
   else
   {
	document.terms.selectterm.value = Math.round(document.terms.selectterm.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Terms&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='450' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='terms' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.terms.page2.value=8;document.terms.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.terms.page2.value=12;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.terms.deleteterm.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='450' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
   &lt;td&gt;&amp;nbsp;&lt;/td&gt;
   &lt;th&gt;Term Name&lt;/th&gt;
   &lt;th&gt;Start Date&lt;/th&gt;
   &lt;th&gt;End Date&lt;/th&gt;
   &lt;/tr&gt;" Length="1901" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="105">
</Literal>
                          <Concat>
                            <Literal 
                            Text="ManageTerms.php: Unable to retrieve total number of terms - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="109">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Repeat>
                            <Constraint Text="$page2 == 6"/>
                            <Concat>
                              <Concat>
                                <Concat>
                                  <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="130"/>
                                  <Select>
                                    <Constraint Text="(($row % 2) == 0)"/>
                                    <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="130"/>
                                    <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="130"/>
                                  </Select>
                                  <Literal 
                                  Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="131">
</Literal>
                                  <Undef/>
                                  <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="131"/>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="135"/>
                                  <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="132"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="133"/>
                                  <Symbolic Text="convertfromdb()"/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="134"/>
                                  <Symbolic Text="convertfromdb()"/>
                                  <Literal Text="&lt;/td&gt; &lt;tr&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="135"/>
                                </Concat>
                              </Concat>
                            </Concat>
                          </Repeat>
                          <Literal 
                          Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.terms.page2.value=8;document.terms.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.terms.page2.value=12;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.terms.deleteterm.value=1;validate();'&gt;

  &lt;br&gt;&lt;br&gt;
  &lt;font color='red'&gt;* Deleting a term will also delete the semesters, classes in each semester, and the information for those classes&lt;/font&gt;
	&lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="483" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="149">
</Literal>
                          <Repeat>
                            <Constraint Text="$page2 == 6"/>
                            <Concat>
                              <Select>
                                <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                <Concat>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;a href='JavaScript: document.terms.deleteterm.value=0;document.terms.page2.value=6;document.terms.onpage.value=" Length="112" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155"/>
                                    <Literal 
                                    Text=";document.terms.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155"/>
                                    <Literal 
                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155"/>
                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="155"/>
                                  </Concat>
                                </Concat>
                                <Concat>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;a href='JavaScript: document.terms.deleteterm.value=0;document.terms.page2.value=6;document.terms.onpage.value=" Length="112" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159"/>
                                    <Literal 
                                    Text=";document.terms.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="82" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159"/>
                                    <Literal 
                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159"/>
                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="159"/>
                                  </Concat>
                                </Concat>
                              </Select>
                            </Concat>
                          </Repeat>
                          <Concat>
                            <Literal 
                            Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteterm'&gt;
  &lt;input type='hidden' name='selectterm'&gt;
  &lt;input type='hidden' name='page2' value='" Length="138" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="167">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="168">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="170">
</Literal>
                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="183"/>
                            <Literal 
                            Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageTerms.php" Line="183">
</Literal>
                          </Concat>
                        </Concat>
                        <Select>
                          <Constraint Text="$page2 == 7"/>
                          <Concat>
                            <Literal 
                            Text="&lt;h1&gt;Add New Semester&lt;/h1&gt;

  &lt;form name='addsemester' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='600'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Semester Name&lt;/th&gt;
	&lt;th&gt;Term&lt;/th&gt;
	&lt;th&gt;Start Date&lt;/th&gt;
	&lt;th&gt;Midterm Date&lt;/th&gt;
	&lt;th&gt;End Date&lt;/th&gt;
	&lt;th&gt;Half&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
    &lt;td&gt;&lt;input type='text' name='title' maxlength='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='term'&gt;" Length="452" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="20">
</Literal>
                            <Repeat>
                              <Constraint Text="$page2 == 7"/>
                              <Concat>
                                <Concat>
                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="25"/>
                                  <Undef/>
                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="25"/>
                                  <Undef/>
                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="25"/>
                                </Concat>
                              </Concat>
                            </Repeat>
                            <Concat>
                              <Literal 
                              Text="&lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='startdate' maxlength='10' size='10' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='middate' maxlength='10' size='10' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='enddate' maxlength='10' size='10' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='half'&gt;
	  &lt;option value='1'&gt;First&lt;/option&gt;
	  &lt;option value='2'&gt;Second&lt;/option&gt;
	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='600'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Semester' onClick='document.addsemester.addsemester.value=1;document.addsemester.page2.value=5;document.addsemester.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addsemester.page2.value=5;document.addsemester.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addsemester' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="862" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="51">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="53">
</Literal>
                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="64"/>
                              <Literal 
                              Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddSemester.php" Line="64">
</Literal>
                            </Concat>
                          </Concat>
                          <Select>
                            <Constraint Text="$page2 == 8"/>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="&lt;h1&gt;Add New Term&lt;/h1&gt;

  &lt;form name='addterm' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='600'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Term Name&lt;/th&gt;
	&lt;th&gt;Start Date&lt;/th&gt;
	&lt;th&gt;End Date&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='startdate' maxlength='10' size='10' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='enddate' maxlength='10' size='10' /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='600'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Term' onClick='document.addterm.addterm.value=1;document.addterm.page2.value=6;document.addterm.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addterm.page2.value=6;document.addterm.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addterm' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="979" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTerm.php" Line="29">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTerm.php" Line="31">
</Literal>
                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTerm.php" Line="42"/>
                                <Literal 
                                Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTerm.php" Line="42">
</Literal>
                              </Concat>
                            </Concat>
                            <Select>
                              <Constraint Text="$page2 == 9"/>
                              <Concat>
                                <Select>
                                  <Constraint Text="($_POST[&quot;fullyear&quot;] != 1)"/>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;h1&gt;Add New Class&lt;/h1&gt;

  &lt;form name='addclass' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='800'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
	&lt;th&gt;Teacher&lt;/th&gt;
	&lt;th&gt;Semester&lt;/th&gt;
	&lt;th&gt;Section Number&lt;/th&gt;
	&lt;th&gt;Room Number&lt;/th&gt;
	&lt;th&gt;Period Number&lt;/th&gt;
	&lt;th&gt;Substitute&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='20' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='teacher'&gt;" Length="479" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="20">
</Literal>
                                    <Concat>
                                      <Literal Text="AddClass.php: Unable to get list of teachers - " Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="24"/>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="($_POST[&quot;fullyear&quot;] != 1)"/>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="30"/>
                                            <Undef/>
                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="30"/>
                                            <Undef/>
                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="30"/>
                                            <Undef/>
                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="30"/>
                                          </Concat>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Literal 
                                    Text="&lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;&lt;select name='semester'&gt;" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="35">
</Literal>
                                    <Concat>
                                      <Literal Text="AddClass.php: Unable to get list of semesters - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="39"/>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="($_POST[&quot;fullyear&quot;] != 1)"/>
                                      <Concat>
                                        <Concat>
                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="42"/>
                                          <Undef/>
                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="42"/>
                                          <Undef/>
                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="42"/>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Literal 
                                    Text="&lt;/select&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='sectionnum' maxlength='15' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='roomnum' maxlength='4' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='periodnum' maxlength='2' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='substitute'&gt;" Length="265" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="50">
</Literal>
                                    <Concat>
                                      <Literal 
                                      Text="AddClass.php: Unable to get list of substitutes - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="54">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="($_POST[&quot;fullyear&quot;] != 1)"/>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="60"/>
                                            <Undef/>
                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="60"/>
                                            <Undef/>
                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="60"/>
                                            <Undef/>
                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="60"/>
                                          </Concat>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Concat>
                                      <Literal 
                                      Text="	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br /&gt;

   &lt;table cellpadding='0' border='0' align='center' width='800'&gt;
   &lt;tr&gt;
   &lt;td&gt;
   &lt;b&gt;Days of the Week:&lt;/b&gt;
   &lt;br /&gt;
   &lt;input type='checkbox' value='M' name='Days[]' /&gt; Monday&lt;br /&gt;
   &lt;input type='checkbox' value='T' name='Days[]' /&gt; Tuesday&lt;br /&gt;
   &lt;input type='checkbox' value='W' name='Days[]' /&gt; Wednesday&lt;br /&gt;
   &lt;input type='checkbox' value='H' name='Days[]' /&gt; Thursday&lt;br /&gt;
   &lt;input type='checkbox' value='F' name='Days[]' /&gt; Friday
   &lt;br /&gt;&lt;br /&gt;
   &lt;input type='button' value='Add Class' onClick='document.addclass.addclass.value=1;document.addclass.page2.value=0;document.addclass.submit();'&gt;
   &lt;input type='button' value='Full Year' onClick='document.addclass.fullyear.value=1;document.addclass.page2.value=9;document.addclass.submit();'&gt;
   &lt;input type='button' value='Cancel' onClick='document.addclass.page2.value=0;document.addclass.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addclass' value='' /&gt;
  &lt;input type='hidden' name='fullyear' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="1086" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="89">
</Literal>
                                      <Symbolic Text="$_POST"/>
                                      <Literal 
                                      Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="91">
</Literal>
                                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="102"/>
                                      <Literal 
                                      Text="' /&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="147" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="102">
</Literal>
                                    </Concat>
                                  </Concat>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;h1&gt;Add New Class&lt;/h1&gt;

  &lt;form name='addclass' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='800'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
	&lt;th&gt;Teacher&lt;/th&gt;
	&lt;th&gt;First Semester&lt;/th&gt;
	&lt;th&gt;Second Semester&lt;/th&gt;
	&lt;th&gt;Section Number&lt;/th&gt;
	&lt;th&gt;Room Number&lt;/th&gt;
	&lt;th&gt;Period Number&lt;/th&gt;
	&lt;th&gt;Substitute&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='20' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='teacher'&gt;" Length="511" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="123">
</Literal>
                                    <Concat>
                                      <Literal Text="AddClass.php: Unable to get list of teachers - " Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="127"/>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="!(($_POST[&quot;fullyear&quot;] != 1))"/>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="133"/>
                                            <Undef/>
                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="133"/>
                                            <Undef/>
                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="133"/>
                                            <Undef/>
                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="133"/>
                                          </Concat>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Literal 
                                    Text="&lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;&lt;select name='semester'&gt;" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="138">
</Literal>
                                    <Concat>
                                      <Literal Text="AddClass.php: Unable to get list of semesters - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="142">
                                      </Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="!(($_POST[&quot;fullyear&quot;] != 1))"/>
                                      <Concat>
                                        <Concat>
                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="145"/>
                                          <Undef/>
                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="145"/>
                                          <Undef/>
                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="145"/>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Literal 
                                    Text="&lt;/select&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='semester2'&gt;" Length="45" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="149">
</Literal>
                                    <Concat>
                                      <Literal Text="AddClass.php: Unable to get list of semesters - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="153">
                                      </Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="!(($_POST[&quot;fullyear&quot;] != 1))"/>
                                      <Concat>
                                        <Concat>
                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="156"/>
                                          <Undef/>
                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="156"/>
                                          <Undef/>
                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="156"/>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Literal 
                                    Text="&lt;/select&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='sectionnum' maxlength='15' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='roomnum' maxlength='4' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='periodnum' maxlength='2' size='6' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='substitute'&gt;" Length="265" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="164">
</Literal>
                                    <Concat>
                                      <Literal 
                                      Text="AddClass.php: Unable to get list of substitutes - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="168">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint Text="!(($_POST[&quot;fullyear&quot;] != 1))"/>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="174"/>
                                            <Undef/>
                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="174"/>
                                            <Undef/>
                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="174"/>
                                            <Undef/>
                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="174"/>
                                          </Concat>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Concat>
                                      <Literal 
                                      Text="     &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br /&gt;

   &lt;table cellpadding='0' border='0' align='center' width='880'&gt;
   &lt;tr&gt;
   &lt;td&gt;
   &lt;b&gt;Days of the Week:&lt;/b&gt;
   &lt;br /&gt;
   &lt;input type='checkbox' value='M' name='Days[]' /&gt; Monday&lt;br /&gt;
   &lt;input type='checkbox' value='T' name='Days[]' /&gt; Tuesday&lt;br /&gt;
   &lt;input type='checkbox' value='W' name='Days[]' /&gt; Wednesday&lt;br /&gt;
   &lt;input type='checkbox' value='H' name='Days[]' /&gt; Thursday&lt;br /&gt;
   &lt;input type='checkbox' value='F' name='Days[]' /&gt; Friday
   &lt;br /&gt;&lt;br /&gt;
   &lt;input type='button' value='Add Class' onClick='document.addclass.addclass.value=1;document.addclass.page2.value=0;document.addclass.submit();'&gt;
   &lt;input type='button' value='Full Year' onClick='document.addclass.fullyear.value=1;document.addclass.page2.value=9;document.addclass.submit();'&gt;
   &lt;input type='button' value='Cancel' onClick='document.addclass.page2.value=0;document.addclass.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addclass' value='' /&gt;
  &lt;input type='hidden' name='fullyear' value='" Length="1050" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="202">
</Literal>
                                      <Symbolic Text="$_POST"/>
                                      <Literal 
                                      Text="' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="203">
</Literal>
                                      <Symbolic Text="$_POST"/>
                                      <Literal 
                                      Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="205">
</Literal>
                                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="216"/>
                                      <Literal 
                                      Text="' /&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="147" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddClass.php" Line="216">
</Literal>
                                    </Concat>
                                  </Concat>
                                </Select>
                              </Concat>
                              <Select>
                                <Constraint Text="$page2 == 10"/>
                                <Concat>
                                  <Concat>
                                    <Concat>
                                      <Literal 
                                      Text="ManageUsers.php: Uanable to get list of users - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="7">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Repeat>
                                      <Constraint 
                                      Text="((($_POST[&quot;adduser&quot;] == 1) &amp;&amp; ($_POST[&quot;password&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;type&quot;] != &quot;&quot;))">
</Constraint>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;br&gt;&lt;br&gt;&lt;h1 align='center'&gt;&lt;font color='red'&gt;Username already exists!&lt;/font&gt;&lt;/h1&gt;
	&lt;br&gt;
	&lt;form name='uhoh' action='./index.php' method='POST'&gt;
	&lt;center&gt;&lt;input type='button' value='&amp;nbsp;Back&amp;nbsp;' onClick='document.uhoh.page2.value=10;document.uhoh.submit();'&gt;&lt;/center&gt;
	&lt;input type='hidden' name='page2' value='" Length="313" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="18">
</Literal>
                                            <Symbolic Text="$_POST"/>
                                            <Literal 
                                            Text="'&gt;
	&lt;input type='hidden' name='logout'&gt;
	&lt;input type='hidden' name='page' value='" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="20">
</Literal>
                                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="21"/>
                                            <Literal Text="'&gt; &lt;/form&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="21"/>
                                          </Concat>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                    <Concat>
                                      <Literal Text="ManageUsers.php: Unable to insert new user - " Length="45" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="27"/>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                  </Concat>
                                  <Concat>
                                    <Select>
                                      <Constraint Text="($_POST[&quot;password&quot;] != &quot;&quot;)"/>
                                      <Concat>
                                        <Concat>
                                          <Literal 
                                          Text="ManageUsers.php: Unable to update the user information (password) - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="36">
</Literal>
                                          <Symbolic Text="mysql_error()"/>
                                        </Concat>
                                      </Concat>
                                      <Concat>
                                        <Concat>
                                          <Literal 
                                          Text="ManageUsers.php: Unable to update the user information (no password) - " Length="71" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="41">
</Literal>
                                          <Symbolic Text="mysql_error()"/>
                                        </Concat>
                                      </Concat>
                                    </Select>
                                  </Concat>
                                  <Concat>
                                    <Repeat>
                                      <Constraint Text="($_POST[&quot;deleteuser&quot;] == 1)"/>
                                      <Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="DeleteFunction.php: Unable to delete selected User(s) - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="95">
</Literal>
                                            <Symbolic Text="mysql_error()"/>
                                          </Concat>
                                          <Select>
                                            <Constraint Text="$type == &quot;Teacher&quot;"/>
                                            <Concat>
                                              <Concat>
                                                <Literal 
                                                Text="DeleteFunctions.php: Unable to get list of teachers to delete for deleteUser() - " Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="102">
</Literal>
                                                <Symbolic Text="mysql_error()"/>
                                              </Concat>
                                              <Repeat>
                                                <Constraint Text="$type == &quot;Teacher&quot;"/>
                                                <Concat>
                                                  <Concat>
                                                    <Concat>
                                                      <Literal 
                                                      Text="DeleteFunctions.php: Unable to delete selected Teacher(s) - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="52">
</Literal>
                                                      <Symbolic Text="mysql_error()"/>
                                                    </Concat>
                                                  </Concat>
                                                </Concat>
                                              </Repeat>
                                            </Concat>
                                            <Select>
                                              <Constraint Text="$type == &quot;Student&quot;"/>
                                              <Concat>
                                                <Concat>
                                                  <Literal 
                                                  Text="DeleteFunctions.php: Unable to get list of students to delete for deleteUser() - " Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="113">
</Literal>
                                                  <Symbolic Text="mysql_error()"/>
                                                </Concat>
                                                <Repeat>
                                                  <Constraint Text="$type == &quot;Student&quot;"/>
                                                  <Concat>
                                                    <Concat>
                                                      <Concat>
                                                        <Literal 
                                                        Text="DeleteFunctions.php: Unable to delete selected Student(s) - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="62">
</Literal>
                                                        <Symbolic Text="mysql_error()"/>
                                                      </Concat>
                                                    </Concat>
                                                  </Concat>
                                                </Repeat>
                                              </Concat>
                                              <Select>
                                                <Constraint Text="$type == &quot;Parent&quot;"/>
                                                <Concat>
                                                  <Concat>
                                                    <Literal 
                                                    Text="DeleteFunctions.php: Unable to get list of parents to delete for deleteUser() - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="124">
</Literal>
                                                    <Symbolic Text="mysql_error()"/>
                                                  </Concat>
                                                  <Repeat>
                                                    <Constraint Text="$type == &quot;Parent&quot;"/>
                                                    <Concat>
                                                      <Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="DeleteFunctions.php: Unable to delete selected Parent(s) - " Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="29">
</Literal>
                                                          <Symbolic Text="mysql_error()"/>
                                                        </Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="DeleteFunctions.php: Unable to delete the parents in the student/parent match - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="32">
</Literal>
                                                          <Symbolic Text="mysql_error()"/>
                                                        </Concat>
                                                      </Concat>
                                                    </Concat>
                                                  </Repeat>
                                                </Concat>
                                                <Select>
                                                  <Constraint Text="$type == &quot;Admin&quot;"/>
                                                  <Concat>
                                                    <Concat>
                                                      <Literal 
                                                      Text="DeleteFunctions.php: Unable to get list of admins to delete for deleteUser() - " Length="79" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="135">
</Literal>
                                                      <Symbolic Text="mysql_error()"/>
                                                    </Concat>
                                                    <Repeat>
                                                      <Constraint Text="$type == &quot;Admin&quot;"/>
                                                      <Concat>
                                                        <Concat>
                                                          <Concat>
                                                            <Literal 
                                                            Text="DeleteFunctions.php: Unable to delete selected Admin(s) - " Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="81">
</Literal>
                                                            <Symbolic Text="mysql_error()"/>
                                                          </Concat>
                                                        </Concat>
                                                      </Concat>
                                                    </Repeat>
                                                  </Concat>
                                                  <Concat> </Concat>
                                                </Select>
                                              </Select>
                                            </Select>
                                          </Select>
                                        </Concat>
                                      </Concat>
                                    </Repeat>
                                  </Concat>
                                  <Literal 
                                  Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the user wants to delete the user(s) //
  function validate()
  {
   if( document.users.selectuser.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Deleting a user will also delete that student/teacher/parent from the database.\n\nAre you sure you want to delete this user?&quot;);

	if( confirmed == true )
	{
	 document.users.submit();
	}
   }
   else
   {
	alert('You must select a user to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.users.selectuser.value == 1 )
   {
	document.users.submit();
   }
   else
   {
	if( document.users.selectuser.value &gt; 1 )
	{
	 alert('You can only edit one user at a time.');
	}
	else
	{
	 alert('You must select a user to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.users.elements[row].checked)
   {
	document.users.selectuser.value = Math.round(document.users.selectuser.value) + 1;
   }
   else
   {
	document.users.selectuser.value = Math.round(document.users.selectuser.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Users&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='250' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='users' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.users.page2.value=14;document.users.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.users.page2.value=15;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.users.deleteuser.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='250' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;Username&lt;/th&gt;
	&lt;th&gt;Type&lt;/th&gt;
   &lt;/tr&gt;" Length="1824" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="129">
</Literal>
                                  <Concat>
                                    <Literal 
                                    Text="ManageUsers.php: Unable to retrieve total number of users - " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="133">
</Literal>
                                    <Symbolic Text="mysql_error()"/>
                                  </Concat>
                                  <Concat>
                                    <Literal 
                                    Text="ManageUsers.php: Unable to retrieve user information - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="146">
</Literal>
                                    <Symbolic Text="mysql_error()"/>
                                  </Concat>
                                  <Repeat>
                                    <Constraint Text="$page2 == 10"/>
                                    <Concat>
                                      <Concat>
                                        <Concat>
                                          <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="157"/>
                                          <Select>
                                            <Constraint Text="(($row % 2) == 0)"/>
                                            <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="157"/>
                                            <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="157"/>
                                          </Select>
                                          <Literal 
                                          Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="158">
</Literal>
                                          <Undef/>
                                          <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="158"/>
                                          <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="161"/>
                                          <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="159"/>
                                          <Undef/>
                                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="160"/>
                                          <Undef/>
                                          <Literal Text="&lt;/td&gt; &lt;tr&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="161"/>
                                        </Concat>
                                      </Concat>
                                    </Concat>
                                  </Repeat>
                                  <Literal 
                                  Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.users.page2.value=14;document.users.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.users.page2.value=15;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.users.deleteuser.value=1;validate();'&gt;

  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="333" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="173">
</Literal>
                                  <Repeat>
                                    <Constraint Text="$page2 == 10"/>
                                    <Concat>
                                      <Select>
                                        <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;a href='JavaScript: document.users.deleteuser.value=0;document.users.page2.value=10;document.users.onpage.value=" Length="113" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179"/>
                                            <Literal 
                                            Text=";document.users.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179"/>
                                            <Literal 
                                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179"/>
                                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="179"/>
                                          </Concat>
                                        </Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;a href='JavaScript: document.users.deleteuser.value=0;document.users.page2.value=10;document.users.onpage.value=" Length="113" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183"/>
                                            <Literal 
                                            Text=";document.users.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="82" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183"/>
                                            <Literal 
                                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183"/>
                                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="183"/>
                                          </Concat>
                                        </Concat>
                                      </Select>
                                    </Concat>
                                  </Repeat>
                                  <Concat>
                                    <Literal 
                                    Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteuser'&gt;
  &lt;input type='hidden' name='selectuser'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="139" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="191">
</Literal>
                                    <Symbolic Text="$_POST"/>
                                    <Literal 
                                    Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="192">
</Literal>
                                    <Symbolic Text="$_POST"/>
                                    <Literal 
                                    Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="194">
</Literal>
                                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="207"/>
                                    <Literal 
                                    Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageUsers.php" Line="207">
</Literal>
                                  </Concat>
                                </Concat>
                                <Select>
                                  <Constraint Text="$page2 == 11"/>
                                  <Concat>
                                    <Concat>
                                      <Literal 
                                      Text="EditClass.php: Unable to retrieve the information about the class to edit - " Length="76" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="6">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Concat>
                                      <Literal 
                                      Text="&lt;h1&gt;Edit Class&lt;/h1&gt;

  &lt;form name='editclass' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='800'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
	&lt;th&gt;Teacher&lt;/th&gt;
	&lt;th&gt;Semester&lt;/th&gt;
	&lt;th&gt;Section Number&lt;/th&gt;
	&lt;th&gt;Room Number&lt;/th&gt;
	&lt;th&gt;Period Number&lt;/th&gt;
	&lt;th&gt;Substitute&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='20' value='" Length="448" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="25">
</Literal>
                                      <Undef/>
                                      <Literal 
                                      Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='teacher'&gt;" Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="26">
</Literal>
                                    </Concat>
                                    <Concat>
                                      <Literal Text="EditClass.php: Unable to get list of teachers - " Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="30">
                                      </Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Select>
                                      <Constraint Text="($teacher[0] == $class[1])"/>
                                      <Concat>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="37"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="37"/>
                                        <Undef/>
                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="37"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="37"/>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="32"/>
                                      </Concat>
                                      <Concat>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="32"/>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="41"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="41"/>
                                        <Undef/>
                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="41"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="41"/>
                                      </Concat>
                                    </Select>
                                    <Literal 
                                    Text="&lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;&lt;select name='semester'&gt;" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="49">
</Literal>
                                    <Concat>
                                      <Literal 
                                      Text="EditClass.php: Unable to get list of semesters - " Length="49" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="53">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Select>
                                      <Constraint Text="($semester[0] == $class[2])"/>
                                      <Concat>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="60"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="60"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="60"/>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="55"/>
                                      </Concat>
                                      <Concat>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="55"/>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="64"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="64"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="64"/>
                                      </Concat>
                                    </Select>
                                    <Concat>
                                      <Literal 
                                      Text="&lt;/select&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='sectionnum' maxlength='15' size='6' value='" Length="88" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="71">
</Literal>
                                      <Undef/>
                                      <Literal 
                                      Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='roomnum' maxlength='4' size='6' value='" Length="79" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="72">
</Literal>
                                      <Undef/>
                                      <Literal 
                                      Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='periodnum' maxlength='2' size='6' value='" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="73">
</Literal>
                                      <Undef/>
                                      <Literal 
                                      Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='substitute'&gt;" Length="44" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="75">
</Literal>
                                    </Concat>
                                    <Concat>
                                      <Literal 
                                      Text="EditClass.php: Unable to get list of substitutes - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="79">
</Literal>
                                      <Symbolic Text="mysql_error()"/>
                                    </Concat>
                                    <Select>
                                      <Constraint Text="($teacher[0] == $class[7])"/>
                                      <Concat>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="90"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="90"/>
                                        <Undef/>
                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="90"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="90"/>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="81"/>
                                      </Concat>
                                      <Concat>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="81"/>
                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="94"/>
                                        <Undef/>
                                        <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="94"/>
                                        <Undef/>
                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="94"/>
                                        <Undef/>
                                        <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="94"/>
                                      </Concat>
                                    </Select>
                                    <Literal 
                                    Text="	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='800'&gt;
   &lt;tr&gt;
   &lt;td&gt;
   &lt;b&gt;Days of the Week:&lt;/b&gt;
   &lt;br /&gt;
   &lt;input type='checkbox' value='M' name='Days[]'" Length="218" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="113">
</Literal>
                                    <Concat>
                                      <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="116"/>
                                    </Concat>
                                    <Literal 
                                    Text=" /&gt; Monday&lt;br /&gt;
   &lt;input type='checkbox' value='T' name='Days[]'" Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="119">
</Literal>
                                    <Concat>
                                      <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="122"/>
                                    </Concat>
                                    <Literal 
                                    Text=" /&gt; Tuesday&lt;br /&gt;
   &lt;input type='checkbox' value='W' name='Days[]'" Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="125">
</Literal>
                                    <Concat>
                                      <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="128"/>
                                    </Concat>
                                    <Literal 
                                    Text=" /&gt; Wednesday&lt;br /&gt;
   &lt;input type='checkbox' value='H' name='Days[]'" Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="131">
</Literal>
                                    <Concat>
                                      <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="134"/>
                                    </Concat>
                                    <Literal 
                                    Text=" /&gt; Thursday&lt;br /&gt;
   &lt;input type='checkbox' value='F' name='Days[]'" Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="137">
</Literal>
                                    <Concat>
                                      <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="140"/>
                                    </Concat>
                                    <Concat>
                                      <Literal 
                                      Text=" /&gt; Friday
   &lt;br /&gt;&lt;br /&gt;
	&lt;input type='button' value='Edit Class' onClick='document.editclass.editclass.value=1;document.editclass.page2.value=0;document.editclass.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editclass.page2.value=0;document.editclass.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editclass' value=''&gt;
  &lt;input type='hidden' name='courseid' value='" Length="415" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="151">
</Literal>
                                      <Undef/>
                                      <Literal 
                                      Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="152">
</Literal>
                                      <Symbolic Text="$_POST"/>
                                      <Literal 
                                      Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="154">
</Literal>
                                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="165"/>
                                      <Literal 
                                      Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditClass.php" Line="165">
</Literal>
                                    </Concat>
                                  </Concat>
                                  <Select>
                                    <Constraint Text="$page2 == 12"/>
                                    <Concat>
                                      <Concat>
                                        <Literal 
                                        Text="EditTerm.php: Unable to retrieve the information about the term to edit - " Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="6">
</Literal>
                                        <Symbolic Text="mysql_error()"/>
                                      </Concat>
                                      <Concat>
                                        <Literal 
                                        Text="&lt;h1&gt;Edit Term&lt;/h1&gt;

  &lt;form name='editterm' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='450'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Term Name&lt;/th&gt;
	&lt;th&gt;Start Date&lt;/th&gt;
	&lt;th&gt;End Date&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' value='" Length="356" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="21">
</Literal>
                                        <Undef/>
                                        <Literal 
                                        Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='startdate' value='" Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="22">
</Literal>
                                        <Symbolic Text="convertfromdb()"/>
                                        <Literal 
                                        Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='enddate' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="23">
</Literal>
                                        <Symbolic Text="convertfromdb()"/>
                                        <Literal 
                                        Text="' /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='450'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit Term' onClick='document.editterm.editterm.value=1;document.editterm.page2.value=6;document.editterm.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editterm.page2.value=6;document.editterm.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editterm'&gt;
  &lt;input type='hidden' name='termid' value='" Length="491" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="39">
</Literal>
                                        <Undef/>
                                        <Literal 
                                        Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="40">
</Literal>
                                        <Symbolic Text="$_POST"/>
                                        <Literal 
                                        Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="42">
</Literal>
                                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="53"/>
                                        <Literal 
                                        Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTerm.php" Line="53">
</Literal>
                                      </Concat>
                                    </Concat>
                                    <Select>
                                      <Constraint Text="$page2 == 13"/>
                                      <Concat>
                                        <Concat>
                                          <Literal 
                                          Text="EditSemester.php: Unable to retrieve the information about the semester to edit - " Length="82" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="6">
</Literal>
                                          <Symbolic Text="mysql_error()"/>
                                        </Concat>
                                        <Concat>
                                          <Literal 
                                          Text="&lt;h1&gt;Edit Semester&lt;/h1&gt;

  &lt;form name='editsemester' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='600'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Semester Name&lt;/th&gt;
	&lt;th&gt;Term&lt;/th&gt;
	&lt;th&gt;Start Date&lt;/th&gt;
	&lt;th&gt;Midterm Date&lt;/th&gt;
	&lt;th&gt;End Date&lt;/th&gt;
	&lt;th&gt;Half&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' value='" Length="421" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="26">
</Literal>
                                          <Undef/>
                                          <Literal 
                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;select name='term'&gt;" Length="35" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="27">
</Literal>
                                        </Concat>
                                        <Repeat>
                                          <Constraint Text="$page2 == 13"/>
                                          <Concat>
                                            <Concat>
                                              <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="32"/>
                                              <Undef/>
                                              <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="32"/>
                                              <Undef/>
                                              <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="32"/>
                                            </Concat>
                                          </Concat>
                                        </Repeat>
                                        <Concat>
                                          <Literal 
                                          Text="&lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' maxlength='10' name='startdate' size='8' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="37">
</Literal>
                                          <Symbolic Text="convertfromdb()"/>
                                          <Literal 
                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' maxlength='10' name='middate' size='8' value='" Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="38">
</Literal>
                                          <Symbolic Text="convertfromdb()"/>
                                          <Literal 
                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' maxlength='10' name='enddate' size='8' value='" Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="39">
</Literal>
                                          <Symbolic Text="convertfromdb()"/>
                                          <Literal 
                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='half'&gt;
	  &lt;option value='1' " Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="42">
</Literal>
                                          <Select>
                                            <Constraint Text="($semester[4] == 1)"/>
                                            <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="42"/>
                                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="42"/>
                                          </Select>
                                          <Literal 
                                          Text="&gt;First&lt;/option&gt;
	  &lt;option value='2' " Length="37" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="43">
</Literal>
                                          <Select>
                                            <Constraint Text="($semester[4] == 2)"/>
                                            <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="43"/>
                                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="43"/>
                                          </Select>
                                          <Literal 
                                          Text="&gt;Second&lt;/option&gt;
	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='600'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit Semester' onClick='document.editsemester.editsemester.value=1;document.editsemester.page2.value=5;document.editsemester.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editsemester.page2.value=5;document.editsemester.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editsemester'&gt;
  &lt;input type='hidden' name='semesterid' value='" Length="553" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="61">
</Literal>
                                          <Undef/>
                                          <Literal 
                                          Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="62">
</Literal>
                                          <Symbolic Text="$_POST"/>
                                          <Literal 
                                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="64">
</Literal>
                                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="75"/>
                                          <Literal 
                                          Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditSemester.php" Line="75">
</Literal>
                                        </Concat>
                                      </Concat>
                                      <Select>
                                        <Constraint Text="$page2 == 14"/>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;script language='JavaScript'&gt;
 &lt;!--
 function validate()
 {
  if(document.adduser.password.value == document.adduser.password2.value &amp;&amp; document.adduser.password.value != '')
  {
   document.adduser.submit();
  }
  else
  {
   alert('Passwords do not match!');
   document.adduser.password.value = '';
   document.adduser.password2.value = '';
   document.adduser.password.select();
  }
 }
 --&gt;
 &lt;/script&gt;

 &lt;h1&gt;Add New User&lt;/h1&gt;

  &lt;form name='adduser' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='450'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Username&lt;/th&gt;
	&lt;th&gt;Password&lt;/th&gt;
	&lt;th&gt;Confirm Password&lt;/th&gt;
	&lt;th&gt;Type&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='username' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='password' name='password' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='password' name='password2' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='type'&gt;
	  &lt;option value='Admin'&gt;Admin&lt;/option&gt;
	  &lt;option value='Teacher'&gt;Teacher&lt;/option&gt;
      &lt;option value='Substitute'&gt;Substitute&lt;/option&gt;
	  &lt;option value='Student'&gt;Student&lt;/option&gt;
	  &lt;option value='Parent'&gt;Parent&lt;/option&gt;
	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='450'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add User' onClick='document.adduser.adduser.value=1;document.adduser.page2.value=10;validate();'&gt; &lt;input type='button' value='Cancel' onClick='document.adduser.page2.value=10;document.adduser.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='adduser' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="1688" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddUser.php" Line="58">
</Literal>
                                            <Symbolic Text="$_POST"/>
                                            <Literal 
                                            Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddUser.php" Line="60">
</Literal>
                                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddUser.php" Line="71"/>
                                            <Literal 
                                            Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddUser.php" Line="71">
</Literal>
                                          </Concat>
                                        </Concat>
                                        <Select>
                                          <Constraint Text="$page2 == 15"/>
                                          <Concat>
                                            <Concat>
                                              <Literal 
                                              Text="EditUser.php: Unable to retrieve the information about the user to edit - " Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="6">
</Literal>
                                              <Symbolic Text="mysql_error()"/>
                                            </Concat>
                                            <Concat>
                                              <Literal 
                                              Text="&lt;script language='JavaScript'&gt;
 &lt;!--
  function validate()
  {
   if(document.edituser.password.value == '' &amp;&amp; document.edituser.password2.value == '')
   {
	document.edituser.submit();
   }
   else
   {
	if(document.edituser.password.value == document.edituser.password2.value)
	{
	 document.edituser.submit();
	}
	else
	{
	 alert('Passwords do not match!');
	}
   }
  }
 --&gt;
 &lt;/script&gt;

 &lt;h1&gt;Edit User&lt;/h1&gt;

  &lt;form name='edituser' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='550'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Username&lt;/th&gt;
	&lt;th&gt;Password&lt;/th&gt;
	&lt;th&gt;Confirm Password&lt;/th&gt;
	&lt;th&gt;Type&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='username' maxlength='15' value='" Length="769" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="45">
</Literal>
                                              <Undef/>
                                              <Literal 
                                              Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='password' name='password' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='password' name='password2' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='type'&gt;" Length="143" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="49">
</Literal>
                                            </Concat>
                                            <Select>
                                              <Constraint Text="($types[$i] == $user[1])"/>
                                              <Concat>
                                                <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="58"/>
                                                <Literal Text="Admin" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="51"/>
                                                <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="58"/>
                                                <Literal Text="Admin" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="51"/>
                                                <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="58"/>
                                                <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="53"/>
                                              </Concat>
                                              <Concat>
                                                <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="53"/>
                                                <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="62"/>
                                                <Literal Text="Admin" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="51"/>
                                                <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="62"/>
                                                <Literal Text="Admin" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="51"/>
                                                <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="62"/>
                                              </Concat>
                                            </Select>
                                            <Concat>
                                              <Literal 
                                              Text="&lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='550'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit user' onClick='document.edituser.edituser.value=1;document.edituser.page2.value=10;validate();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.edituser.page2.value=10;document.edituser.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='edituser'&gt;
  &lt;input type='hidden' name='userid' value='" Length="484" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="85">
</Literal>
                                              <Undef/>
                                              <Literal 
                                              Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="86">
</Literal>
                                              <Symbolic Text="$_POST"/>
                                              <Literal 
                                              Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="88">
</Literal>
                                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="99"/>
                                              <Literal 
                                              Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditUser.php" Line="99">
</Literal>
                                            </Concat>
                                          </Concat>
                                          <Select>
                                            <Constraint Text="$page2 == 16"/>
                                            <Concat>
                                              <Literal 
                                              Text="&lt;h1&gt;Add New Teacher&lt;/h1&gt;

  &lt;form name='addteacher' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='450'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='lname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="477" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="17">
</Literal>
                                              <Concat>
                                                <Literal 
                                                Text="AddTeacher: Unable to retrieve the list of users - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="20">
</Literal>
                                                <Symbolic Text="mysql_error()"/>
                                              </Concat>
                                              <Repeat>
                                                <Constraint Text="$page2 == 16"/>
                                                <Concat>
                                                  <Concat>
                                                    <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="24"/>
                                                    <Undef/>
                                                    <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="24"/>
                                                    <Undef/>
                                                    <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="24"/>
                                                  </Concat>
                                                </Concat>
                                              </Repeat>
                                              <Concat>
                                                <Literal 
                                                Text="	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='450'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Teacher' onClick='document.addteacher.addteacher.value=1;document.addteacher.page2.value=3;document.addteacher.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addteacher.page2.value=3;document.addteacher.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addteacher' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="517" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="40">
</Literal>
                                                <Symbolic Text="$_POST"/>
                                                <Literal 
                                                Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="42">
</Literal>
                                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="53"/>
                                                <Literal 
                                                Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddTeacher.php" Line="53">
</Literal>
                                              </Concat>
                                            </Concat>
                                            <Select>
                                              <Constraint Text="$page2 == 17"/>
                                              <Concat>
                                                <Concat>
                                                  <Literal 
                                                  Text="EditTeacher.php: Unable to retrieve the information about the teacher to edit - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="6">
</Literal>
                                                  <Symbolic Text="mysql_error()"/>
                                                </Concat>
                                                <Concat>
                                                  <Literal 
                                                  Text="&lt;h1&gt;Edit Teacher&lt;/h1&gt;

  &lt;form name='editteacher' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='550'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' value='" Length="362" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="21">
</Literal>
                                                  <Undef/>
                                                  <Literal 
                                                  Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='test' name='lname' maxlength='15' value='" Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="22">
</Literal>
                                                  <Undef/>
                                                  <Literal 
                                                  Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="42" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="24">
</Literal>
                                                </Concat>
                                                <Concat>
                                                  <Literal 
                                                  Text="AddTeacher: Unable to get the current teacher's userid - " Length="57" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="28">
</Literal>
                                                  <Symbolic Text="mysql_error()"/>
                                                </Concat>
                                                <Concat>
                                                  <Literal 
                                                  Text="EditTeacher: Unable to retrieve the list of users - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="32">
</Literal>
                                                  <Symbolic Text="mysql_error()"/>
                                                </Concat>
                                                <Select>
                                                  <Constraint Text="($teacher[0] == $user[0])"/>
                                                  <Concat>
                                                    <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="39"/>
                                                    <Undef/>
                                                    <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="39"/>
                                                    <Undef/>
                                                    <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="39"/>
                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="34"/>
                                                  </Concat>
                                                  <Concat>
                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="34"/>
                                                    <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="43"/>
                                                    <Undef/>
                                                    <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="43"/>
                                                    <Undef/>
                                                    <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="43"/>
                                                  </Concat>
                                                </Select>
                                                <Concat>
                                                  <Literal 
                                                  Text="     &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='550'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit teacher' onClick='document.editteacher.editteacher.value=1;document.editteacher.page2.value=3;document.editteacher.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editteacher.page2.value=3;document.editteacher.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editteacher'&gt;
  &lt;input type='hidden' name='teacherid' value='" Length="530" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="66">
</Literal>
                                                  <Undef/>
                                                  <Literal 
                                                  Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="67">
</Literal>
                                                  <Symbolic Text="$_POST"/>
                                                  <Literal 
                                                  Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="69">
</Literal>
                                                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="80"/>
                                                  <Literal 
                                                  Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditTeacher.php" Line="80">
</Literal>
                                                </Concat>
                                              </Concat>
                                              <Select>
                                                <Constraint Text="$page2 == 18"/>
                                                <Concat>
                                                  <Concat>
                                                    <Literal 
                                                    Text="&lt;h1&gt;Add New Announcement&lt;/h1&gt;

  &lt;form name='addannouncement' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='600'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;textarea name='message' rows=5 cols=30&gt;&lt;/textarea&gt;&lt;/td&gt;

   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='600'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add announcement' onClick='document.addannouncement.addannouncement.value=1;document.addannouncement.page2.value=4;document.addannouncement.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addannouncement.page2.value=4;document.addannouncement.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addannouncement' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="973" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAnnouncements.php" Line="28">
</Literal>
                                                    <Symbolic Text="$_POST"/>
                                                    <Literal 
                                                    Text="'&gt;
  &lt;input type='hidden' name='date'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="119" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAnnouncements.php" Line="31">
</Literal>
                                                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAnnouncements.php" Line="42"/>
                                                    <Literal 
                                                    Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAnnouncements.php" Line="42">
</Literal>
                                                  </Concat>
                                                </Concat>
                                                <Select>
                                                  <Constraint Text="$page2 == 19"/>
                                                  <Concat>
                                                    <Concat>
                                                      <Literal 
                                                      Text="EditAnnouncement.php: Unable to retrieve the information about the announcement to edit - " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="6">
</Literal>
                                                      <Symbolic Text="mysql_error()"/>
                                                    </Concat>
                                                    <Concat>
                                                      <Literal 
                                                      Text="&lt;h1&gt;Edit Announcement&lt;/h1&gt;

  &lt;form name='editannouncement' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='600'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
	&lt;th&gt;Date&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' value='" Length="374" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="21">
</Literal>
                                                      <Undef/>
                                                      <Literal 
                                                      Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;textarea name='message' rows='5' cols='30'&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="22">
</Literal>
                                                      <Undef/>
                                                      <Literal 
                                                      Text="&lt;/textarea&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='date' value='" Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="23">
</Literal>
                                                      <Symbolic Text="convertfromdb()"/>
                                                      <Literal 
                                                      Text="' /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='600'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit Announcement' onClick='document.editannouncement.editannouncement.value=1;document.editannouncement.page2.value=4;document.editannouncement.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editannouncement.page2.value=4;document.editannouncement.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editannouncement'&gt;
  &lt;input type='hidden' name='announcementid' value='" Length="563" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="39">
</Literal>
                                                      <Undef/>
                                                      <Literal 
                                                      Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="40">
</Literal>
                                                      <Symbolic Text="$_POST"/>
                                                      <Literal 
                                                      Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="42">
</Literal>
                                                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="53"/>
                                                      <Literal 
                                                      Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAnnouncements.php" Line="53">
</Literal>
                                                    </Concat>
                                                  </Concat>
                                                  <Select>
                                                    <Constraint Text="$page2 == 20"/>
                                                    <Concat>
                                                      <Literal 
                                                      Text="&lt;h1&gt;Add New Student&lt;/h1&gt;

  &lt;form name='addstudent' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='500'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Middle Initial&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='mi' maxlength='2' size='2' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='lname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="567" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="19">
</Literal>
                                                      <Concat>
                                                        <Literal 
                                                        Text="AddStudent: Unable to retrieve the list of users - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="22">
</Literal>
                                                        <Symbolic Text="mysql_error()"/>
                                                      </Concat>
                                                      <Repeat>
                                                        <Constraint Text="$page2 == 20"/>
                                                        <Concat>
                                                          <Concat>
                                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="26"/>
                                                            <Undef/>
                                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="26"/>
                                                            <Undef/>
                                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="26"/>
                                                          </Concat>
                                                        </Concat>
                                                      </Repeat>
                                                      <Concat>
                                                        <Literal 
                                                        Text="	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='500'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Student' onClick='document.addstudent.addstudent.value=1;document.addstudent.page2.value=2;document.addstudent.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addstudent.page2.value=2;document.addstudent.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addstudent' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="517" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="42">
</Literal>
                                                        <Symbolic Text="$_POST"/>
                                                        <Literal 
                                                        Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="44">
</Literal>
                                                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="55"/>
                                                        <Literal 
                                                        Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddStudent.php" Line="55">
</Literal>
                                                      </Concat>
                                                    </Concat>
                                                    <Select>
                                                      <Constraint Text="$page2 == 21"/>
                                                      <Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="EditStudent.php: Unable to retrieve the information about the student to edit - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="6">
</Literal>
                                                          <Symbolic Text="mysql_error()"/>
                                                        </Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="&lt;h1&gt;Edit Student&lt;/h1&gt;

  &lt;form name='editstudent' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='525'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Middle Initial&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' value='" Length="387" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="22">
</Literal>
                                                          <Undef/>
                                                          <Literal 
                                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='mi' maxlength='2' size='2' value='" Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="23">
</Literal>
                                                          <Undef/>
                                                          <Literal 
                                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='test' name='lname' maxlength='15' value='" Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="24">
</Literal>
                                                          <Undef/>
                                                          <Literal 
                                                          Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="42" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="26">
</Literal>
                                                        </Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="EditStudent: Unable to get the current student's userid - " Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="30">
</Literal>
                                                          <Symbolic Text="mysql_error()"/>
                                                        </Concat>
                                                        <Concat>
                                                          <Literal 
                                                          Text="EditStudent: Unable to retrieve the list of users - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="34">
</Literal>
                                                          <Symbolic Text="mysql_error()"/>
                                                        </Concat>
                                                        <Select>
                                                          <Constraint Text="($student[0] == $user[0])"/>
                                                          <Concat>
                                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="41"/>
                                                            <Undef/>
                                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="41"/>
                                                            <Undef/>
                                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="41"/>
                                                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="36"/>
                                                          </Concat>
                                                          <Concat>
                                                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="36"/>
                                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="45"/>
                                                            <Undef/>
                                                            <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="45"/>
                                                            <Undef/>
                                                            <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="45"/>
                                                          </Concat>
                                                        </Select>
                                                        <Concat>
                                                          <Literal 
                                                          Text="     &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='525'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit Student' onClick='document.editstudent.editstudent.value=1;document.editstudent.page2.value=2;document.editstudent.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editstudent.page2.value=2;document.editstudent.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editstudent'&gt;
  &lt;input type='hidden' name='studentid' value='" Length="530" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="68">
</Literal>
                                                          <Undef/>
                                                          <Literal 
                                                          Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="69">
</Literal>
                                                          <Symbolic Text="$_POST"/>
                                                          <Literal 
                                                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="71">
</Literal>
                                                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="82"/>
                                                          <Literal 
                                                          Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditStudent.php" Line="82">
</Literal>
                                                        </Concat>
                                                      </Concat>
                                                      <Select>
                                                        <Constraint Text="$page2 == 22"/>
                                                        <Concat>
                                                          <Concat>
                                                            <Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="ManageParents.php: Uanable to get list of users - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="9">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Repeat>
                                                                <Constraint 
                                                                Text="(((($_POST[&quot;username&quot;] != &quot;&quot;) &amp;&amp; ($_POST[&quot;fname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;lname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;student&quot;] != &quot;&quot;))">
</Constraint>
                                                                <Concat>
                                                                  <Repeat>
                                                                    <Constraint 
                                                                    Text="(((($_POST[&quot;username&quot;] != &quot;&quot;) &amp;&amp; ($_POST[&quot;fname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;lname&quot;] != &quot;&quot;)) &amp;&amp; ($_POST[&quot;student&quot;] != &quot;&quot;))">
</Constraint>
                                                                    <Concat>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal 
                                                                          Text="&lt;br&gt;&lt;br&gt;&lt;h1 align='center'&gt;&lt;font color='red'&gt;That user is already assigned to a parent!&lt;/font&gt;&lt;/h1&gt;
	  &lt;br&gt;
	  &lt;form name='uhoh' action='./index.php' method='POST'&gt;
	  &lt;center&gt;&lt;input type='button' value='&amp;nbsp;Back&amp;nbsp;' onClick='document.uhoh.page2.value=22;document.uhoh.submit();'&gt;&lt;/center&gt;
	  &lt;input type='hidden' name='page2' value='" Length="339" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="23">
</Literal>
                                                                          <Symbolic Text="$_POST"/>
                                                                          <Literal 
                                                                          Text="'&gt;
	  &lt;input type='hidden' name='logout'&gt;
	  &lt;input type='hidden' name='page' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="25">
</Literal>
                                                                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="26"/>
                                                                          <Literal Text="'&gt; &lt;/form&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="26"/>
                                                                        </Concat>
                                                                      </Concat>
                                                                    </Concat>
                                                                  </Repeat>
                                                                </Concat>
                                                              </Repeat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="ManageParents.php: Unable to insert new parent - " Length="49" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="33">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Concat>
                                                                <Literal Text="Unable to get the newly entered parentid - " Length="43" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="37"/>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="ManageParents.php: Unable to insert the parent to student match - " Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="43">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                            </Concat>
                                                          </Concat>
                                                          <Concat>
                                                            <Concat>
                                                              <Literal 
                                                              Text="ManageParents.php: Unable to update the parent information - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="51">
</Literal>
                                                              <Symbolic Text="mysql_error()"/>
                                                            </Concat>
                                                          </Concat>
                                                          <Concat>
                                                            <Repeat>
                                                              <Constraint Text="($_POST[&quot;deleteparent&quot;] == 1)"/>
                                                              <Concat>
                                                                <Concat>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="DeleteFunctions.php: Unable to delete selected Parent(s) - " Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="29">
</Literal>
                                                                    <Symbolic Text="mysql_error()"/>
                                                                  </Concat>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="DeleteFunctions.php: Unable to delete the parents in the student/parent match - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="32">
</Literal>
                                                                    <Symbolic Text="mysql_error()"/>
                                                                  </Concat>
                                                                </Concat>
                                                              </Concat>
                                                            </Repeat>
                                                          </Concat>
                                                          <Literal 
                                                          Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the parent wants to delete the parent(s) //
  function validate()
  {
   if( document.parents.selectparent.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this parent?&quot;);

	if( confirmed == true )
	{
	 document.parents.submit();
	}
   }
   else
   {
	alert('You must select a parent to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.parents.selectparent.value == 1 )
   {
	document.parents.submit();
   }
   else
   {
	if( document.parents.selectparent.value &gt; 1 )
	{
	 alert('You can only edit one parent at a time.');
	}
	else
	{
	 alert('You must select a parent to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;

   if(document.parents.elements[row].checked)
   {
	document.parents.selectparent.value = Math.round(document.parents.selectparent.value) + 1;
   }
   else
   {
	document.parents.selectparent.value = Math.round(document.parents.selectparent.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Parents&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='500' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='parents' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.parents.page2.value=23;document.parents.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.parents.page2.value=24;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.parents.deleteparent.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='500' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Student Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;" Length="1851" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="141">
</Literal>
                                                          <Concat>
                                                            <Literal 
                                                            Text="ManageParents.php: Unable to retrieve total number of parents - " Length="64" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="145">
</Literal>
                                                            <Symbolic Text="mysql_error()"/>
                                                          </Concat>
                                                          <Concat>
                                                            <Literal 
                                                            Text="ManageParents.php: Unable to get a list of parents - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="158">
</Literal>
                                                            <Symbolic Text="mysql_error()"/>
                                                          </Concat>
                                                          <Repeat>
                                                            <Constraint Text="$page2 == 22"/>
                                                            <Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="ManageParents.php: Unable to get a list of parents with the matching students - " Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="166">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Concat>
                                                                <Concat>
                                                                  <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="172"/>
                                                                  <Select>
                                                                    <Constraint Text="(($row % 2) == 0)"/>
                                                                    <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="172"/>
                                                                    <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="172"/>
                                                                  </Select>
                                                                  <Literal 
                                                                  Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="173">
</Literal>
                                                                  <Undef/>
                                                                  <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="173"/>
                                                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="178"/>
                                                                  <Literal Text=");document.parents.studentid.value=" Length="35" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="173"/>
                                                                  <Select>
                                                                    <Constraint Text="($student[2] == null)"/>
                                                                    <Literal Text="-1" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="173"/>
                                                                    <Concat>
                                                                      <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="173"/>
                                                                      <Undef/>
                                                                    </Concat>
                                                                  </Select>
                                                                  <Literal Text=";' /&gt;&lt;/td&gt; &lt;td&gt;" Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="174"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="175"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="176"/>
                                                                  <Undef/>
                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="176"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="177"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="178"/>
                                                                </Concat>
                                                              </Concat>
                                                            </Concat>
                                                          </Repeat>
                                                          <Literal 
                                                          Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.parents.page2.value=23;document.parents.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.parents.page2.value=24;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.parents.deleteparent.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="342" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="189">
</Literal>
                                                          <Repeat>
                                                            <Constraint Text="$page2 == 22"/>
                                                            <Concat>
                                                              <Select>
                                                                <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                                                <Concat>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="&lt;a href='JavaScript: document.parents.deleteparent.value=0;document.parents.page2.value=3;document.parents.onpage.value=" Length="120" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195"/>
                                                                    <Literal 
                                                                    Text=";document.parents.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="92" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195"/>
                                                                    <Literal 
                                                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195"/>
                                                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="195"/>
                                                                  </Concat>
                                                                </Concat>
                                                                <Concat>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="&lt;a href='JavaScript: document.parents.deleteparent.value=0;document.parents.page2.value=3;document.parents.onpage.value=" Length="120" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199"/>
                                                                    <Literal 
                                                                    Text=";document.parents.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="84" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199"/>
                                                                    <Literal 
                                                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199">
</Literal>
                                                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199"/>
                                                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="199"/>
                                                                  </Concat>
                                                                </Concat>
                                                              </Select>
                                                            </Concat>
                                                          </Repeat>
                                                          <Concat>
                                                            <Literal 
                                                            Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteparent'&gt;
  &lt;input type='hidden' name='selectparent'&gt;
  &lt;input type='hidden' name='studentid'&gt;
  &lt;input type='hidden' name='page2' value='" Length="183" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="207">
</Literal>
                                                            <Symbolic Text="$_POST"/>
                                                            <Literal 
                                                            Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="208">
</Literal>
                                                            <Symbolic Text="$_POST"/>
                                                            <Literal 
                                                            Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="210">
</Literal>
                                                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="223"/>
                                                            <Literal 
                                                            Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageParents.php" Line="223">
</Literal>
                                                          </Concat>
                                                        </Concat>
                                                        <Select>
                                                          <Constraint Text="$page2 == 23"/>
                                                          <Concat>
                                                            <Literal 
                                                            Text="&lt;h1&gt;Add New Parent&lt;/h1&gt;

  &lt;form name='addparent' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='500'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Student Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='lname' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='student'&gt;" Length="497" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="18">
</Literal>
                                                            <Concat>
                                                              <Literal 
                                                              Text="AddParent: Unable to retrieve the list of users - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="21">
</Literal>
                                                              <Symbolic Text="mysql_error()"/>
                                                            </Concat>
                                                            <Repeat>
                                                              <Constraint Text="$page2 == 23"/>
                                                              <Concat>
                                                                <Concat>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="25"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="25"/>
                                                                  <Undef/>
                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="25"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="25"/>
                                                                </Concat>
                                                              </Concat>
                                                            </Repeat>
                                                            <Literal 
                                                            Text="  &lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="30">
</Literal>
                                                            <Concat>
                                                              <Literal 
                                                              Text="AddParent: Unable to retrieve the list of users - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="33">
</Literal>
                                                              <Symbolic Text="mysql_error()"/>
                                                            </Concat>
                                                            <Repeat>
                                                              <Constraint Text="$page2 == 23"/>
                                                              <Concat>
                                                                <Concat>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="37"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="37"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="37"/>
                                                                </Concat>
                                                              </Concat>
                                                            </Repeat>
                                                            <Concat>
                                                              <Literal 
                                                              Text="	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='500'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Parent' onClick='document.addparent.addparent.value=1;document.addparent.page2.value=22;document.addparent.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addparent.page2.value=22;document.addparent.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addparent' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="511" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="53">
</Literal>
                                                              <Symbolic Text="$_POST"/>
                                                              <Literal 
                                                              Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="55">
</Literal>
                                                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="66"/>
                                                              <Literal 
                                                              Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddParent.php" Line="66">
</Literal>
                                                            </Concat>
                                                          </Concat>
                                                          <Select>
                                                            <Constraint Text="$page2 == 24"/>
                                                            <Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="EditParent.php: Unable to retrieve the information about the parent to edit - " Length="78" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="6">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="&lt;h1&gt;Edit Parent&lt;/h1&gt;

  &lt;form name='editparent' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='550'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;First Name&lt;/th&gt;
	&lt;th&gt;Last Name&lt;/th&gt;
	&lt;th&gt;Student Name&lt;/th&gt;
	&lt;th&gt;Username&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='fname' maxlength='15' value='" Length="383" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="22">
</Literal>
                                                                <Undef/>
                                                                <Literal 
                                                                Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='test' name='lname' maxlength='15' value='" Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="23">
</Literal>
                                                                <Undef/>
                                                                <Literal 
                                                                Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='student'&gt;" Length="41" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="25">
</Literal>
                                                              </Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="AddParent: Unable to retrieve the list of users - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="28">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Select>
                                                                <Constraint Text="($students[0] == $_POST[&quot;studentid&quot;])"/>
                                                                <Concat>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="35"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="35"/>
                                                                  <Undef/>
                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="35"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="35"/>
                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="30"/>
                                                                </Concat>
                                                                <Concat>
                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="30"/>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="39"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="39"/>
                                                                  <Undef/>
                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="39"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="39"/>
                                                                </Concat>
                                                              </Select>
                                                              <Literal 
                                                              Text="  &lt;/select&gt;
	&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='username'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="48">
</Literal>
                                                              <Concat>
                                                                <Literal 
                                                                Text="Addparent: Unable to get the current parent's userid - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="52">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Concat>
                                                                <Literal 
                                                                Text="Editparent: Unable to retrieve the list of users - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="56">
</Literal>
                                                                <Symbolic Text="mysql_error()"/>
                                                              </Concat>
                                                              <Select>
                                                                <Constraint Text="($parent[0] == $user[0])"/>
                                                                <Concat>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="63"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="63"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="63"/>
                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="58"/>
                                                                </Concat>
                                                                <Concat>
                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="58"/>
                                                                  <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="67"/>
                                                                  <Undef/>
                                                                  <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="67"/>
                                                                  <Undef/>
                                                                  <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="67"/>
                                                                </Concat>
                                                              </Select>
                                                              <Concat>
                                                                <Literal 
                                                                Text="     &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='550'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit parent' onClick='document.editparent.editparent.value=1;document.editparent.page2.value=22;document.editparent.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editparent.page2.value=22;document.editparent.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editparent'&gt;
  &lt;input type='hidden' name='parentid' value='" Length="523" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="90">
</Literal>
                                                                <Undef/>
                                                                <Literal 
                                                                Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="91">
</Literal>
                                                                <Symbolic Text="$_POST"/>
                                                                <Literal 
                                                                Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="93">
</Literal>
                                                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="104"/>
                                                                <Literal 
                                                                Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditParent.php" Line="104">
</Literal>
                                                              </Concat>
                                                            </Concat>
                                                            <Select>
                                                              <Constraint Text="$page2 == 25"/>
                                                              <Concat>
                                                                <Concat>
                                                                  <Literal 
                                                                  Text="
 &lt;h1&gt;School Class Schedule&lt;/h1&gt;
 &lt;table align='center' width='800' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
  &lt;table width='600' border='0' align='center'&gt;
  &lt;tr&gt;
   &lt;td&gt;
   &lt;b&gt;Semester:&lt;/b&gt; " Length="265" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="15">
</Literal>
                                                                  <Undef/>
                                                                  <Literal 
                                                                  Text="
   &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
  &lt;table cellspacing='0' width='600' cellpadding='5' border='1' bordercolor='black' cellspacing='0' cellpadding='5' align='center'&gt;
  &lt;tr class='header'&gt;
   &lt;th width='15'&gt;Period&lt;/th&gt;
   &lt;th&gt;Monday&lt;/th&gt;
   &lt;th&gt;Tuesday&lt;/th&gt;
   &lt;th&gt;Wednesday&lt;/th&gt;
   &lt;th&gt;Thursday&lt;/th&gt;
   &lt;th&gt;Friday&lt;/th&gt;
  &lt;/tr&gt;
   " Length="325" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="28">
</Literal>
                                                                </Concat>
                                                                <Concat>
                                                                  <Literal 
                                                                  Text="VisualizeClasses.php: Unable to get number of periods - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="31">
</Literal>
                                                                  <Symbolic Text="mysql_error()"/>
                                                                </Concat>
                                                                <Repeat>
                                                                  <Constraint Text="$page2 == 25"/>
                                                                  <Concat>
                                                                    <Concat>
                                                                      <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="45"/>
                                                                      <Select>
                                                                        <Constraint Text="(($i % 2) == 0)"/>
                                                                        <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="45"/>
                                                                        <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="45"/>
                                                                      </Select>
                                                                      <Literal Text="' valign='top'&gt; &lt;td&gt;" Length="22" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="46"/>
                                                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="46"/>
                                                                      <Literal Text="&lt;/td&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="46"/>
                                                                    </Concat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="VisualizeClasses.php: Unable to get class information - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="50">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Concat>
                                                                      <Select>
                                                                        <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                        <Select>
                                                                          <Constraint Text="($monday != &quot;&lt;td&gt;&quot;)"/>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                              <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="64"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="65"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="66"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="67"/>
                                                                              <Undef/>
                                                                              <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="67"/>
                                                                              <Undef/>
                                                                            </Concat>
                                                                            <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="70"/>
                                                                          </Concat>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                              <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="64"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="65"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="66"/>
                                                                              <Undef/>
                                                                              <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="67"/>
                                                                              <Undef/>
                                                                              <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="67"/>
                                                                              <Undef/>
                                                                            </Concat>
                                                                            <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="72"/>
                                                                          </Concat>
                                                                        </Select>
                                                                        <Select>
                                                                          <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                          <Select>
                                                                            <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                            <Select>
                                                                              <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="39"/>
                                                                                <Undef/>
                                                                              </Select>
                                                                            </Select>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                      <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="131"/>
                                                                      <Select>
                                                                        <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                        <Select>
                                                                          <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                          <Select>
                                                                            <Constraint Text="($tuesday != &quot;&lt;td&gt;&quot;)"/>
                                                                            <Concat>
                                                                              <Concat>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                                <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="78"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="79"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="80"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="81"/>
                                                                                <Undef/>
                                                                                <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="81"/>
                                                                                <Undef/>
                                                                              </Concat>
                                                                              <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="83"/>
                                                                            </Concat>
                                                                            <Concat>
                                                                              <Concat>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                                <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="78"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="79"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="80"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="81"/>
                                                                                <Undef/>
                                                                                <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="81"/>
                                                                                <Undef/>
                                                                              </Concat>
                                                                              <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="85"/>
                                                                            </Concat>
                                                                          </Select>
                                                                          <Select>
                                                                            <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                            <Select>
                                                                              <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="40"/>
                                                                                <Undef/>
                                                                              </Select>
                                                                            </Select>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                      <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="131"/>
                                                                      <Select>
                                                                        <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                        <Select>
                                                                          <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                          <Select>
                                                                            <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                            <Select>
                                                                              <Constraint Text="($wednesday != &quot;&lt;td&gt;&quot;)"/>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                                  <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="92"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="93"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="94"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="95"/>
                                                                                  <Undef/>
                                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="95"/>
                                                                                  <Undef/>
                                                                                </Concat>
                                                                                <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="97"/>
                                                                              </Concat>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                                  <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="92"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="93"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="94"/>
                                                                                  <Undef/>
                                                                                  <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="95"/>
                                                                                  <Undef/>
                                                                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="95"/>
                                                                                  <Undef/>
                                                                                </Concat>
                                                                                <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="99"/>
                                                                              </Concat>
                                                                            </Select>
                                                                            <Select>
                                                                              <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="41"/>
                                                                                <Undef/>
                                                                              </Select>
                                                                            </Select>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                      <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="131"/>
                                                                      <Select>
                                                                        <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                        <Select>
                                                                          <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                          <Select>
                                                                            <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                            <Select>
                                                                              <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                              <Select>
                                                                                <Constraint Text="($thursday != &quot;&lt;td&gt;&quot;)"/>
                                                                                <Concat>
                                                                                  <Concat>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                                    <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="105"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="106"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="107"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="108"/>
                                                                                    <Undef/>
                                                                                    <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="108"/>
                                                                                    <Undef/>
                                                                                  </Concat>
                                                                                  <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="110"/>
                                                                                </Concat>
                                                                                <Concat>
                                                                                  <Concat>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                                    <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="105"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="106"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="107"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="108"/>
                                                                                    <Undef/>
                                                                                    <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="108"/>
                                                                                    <Undef/>
                                                                                  </Concat>
                                                                                  <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="112"/>
                                                                                </Concat>
                                                                              </Select>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="42"/>
                                                                                <Undef/>
                                                                              </Select>
                                                                            </Select>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                      <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="131"/>
                                                                      <Select>
                                                                        <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                        <Select>
                                                                          <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                          <Select>
                                                                            <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                            <Select>
                                                                              <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                <Select>
                                                                                  <Constraint Text="($friday != &quot;&lt;td&gt;&quot;)"/>
                                                                                  <Concat>
                                                                                    <Concat>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                                      <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="118"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="119"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="120"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="121"/>
                                                                                      <Undef/>
                                                                                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="121"/>
                                                                                      <Undef/>
                                                                                    </Concat>
                                                                                    <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="123"/>
                                                                                  </Concat>
                                                                                  <Concat>
                                                                                    <Concat>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="43"/>
                                                                                      <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="118"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="119"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="120"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="121"/>
                                                                                      <Undef/>
                                                                                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="121"/>
                                                                                      <Undef/>
                                                                                    </Concat>
                                                                                    <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="125"/>
                                                                                  </Concat>
                                                                                </Select>
                                                                                <Undef/>
                                                                              </Select>
                                                                            </Select>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                      <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="131"/>
                                                                    </Concat>
                                                                    <Literal Text="&lt;/tr&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="135"/>
                                                                  </Concat>
                                                                </Repeat>
                                                                <Concat>
                                                                  <Literal 
                                                                  Text="  &lt;/table&gt;
&lt;table width='600' border=0 align='center'&gt;
&lt;tr&gt;
&lt;td&gt;
&lt;p align='left'&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=0;document.classes.submit();'&gt;
  &lt;input type='hidden' name='page2' value='" Length="232" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="144">
</Literal>
                                                                  <Symbolic Text="$_POST"/>
                                                                  <Literal 
                                                                  Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="146">
</Literal>
                                                                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="162"/>
                                                                  <Literal 
                                                                  Text="'&gt;
&lt;/p&gt;
&lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="196" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeClasses.php" Line="162">
</Literal>
                                                                </Concat>
                                                              </Concat>
                                                              <Select>
                                                                <Constraint Text="$page2 == 26"/>
                                                                <Concat>
                                                                  <Concat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="Registration.php: Unable to get the information about the new class to add for validation - " Length="92" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="11">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="Registration.php: Unable to get a list of registrations for validation - " Length="73" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="18">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Select>
                                                                      <Constraint Text="$insert"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal 
                                                                          Text="Registration.php: Unable to add the new registration - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="51">
</Literal>
                                                                          <Symbolic Text="mysql_error()"/>
                                                                        </Concat>
                                                                      </Concat>
                                                                      <Concat>
                                                                        <Literal 
                                                                        Text="&lt;font color='red'&gt;*That class interferes with another registration for this student&lt;/font&gt;" Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="63">
</Literal>
                                                                      </Concat>
                                                                    </Select>
                                                                  </Concat>
                                                                  <Concat>
                                                                    <Repeat>
                                                                      <Constraint Text="($_POST[&quot;deletereg&quot;] == 1)"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Concat>
                                                                            <Literal 
                                                                            Text="DeleteFunctions.php: Unable to delete selected Registration(s) - " Length="65" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="162">
</Literal>
                                                                            <Symbolic Text="mysql_error()"/>
                                                                          </Concat>
                                                                        </Concat>
                                                                      </Concat>
                                                                    </Repeat>
                                                                  </Concat>
                                                                  <Literal 
                                                                  Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the student wants to delete the registration(s) //
  function validate()
  {
   if( document.registration.selectreg.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this registration?&quot;);

	if( confirmed == true )
	{
	 document.registration.submit();
	}
   }
   else
   {
	alert('You must select a registration to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.registration.selectreg.value == 1 )
   {
	document.registration.submit();
   }
   else
   {
	if( document.registration.selectreg.value &gt; 1 )
	{
	 alert('You can only edit one student at a time.');
	}
	else
	{
	 alert('You must select a registration to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 4;
   if(document.registration.elements[row].checked)
   {
	document.registration.selectreg.value = Math.round(document.registration.selectreg.value) + 1;
   }
   else
   {
	document.registration.selectreg.value = Math.round(document.registration.selectreg.value) - 1;
   }
  }
 &lt;/script&gt;
 &lt;h1&gt;Registration&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='400' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='registration' action='./index.php' method='POST'&gt;
 &lt;b&gt;Student:&lt;/b&gt;
 &lt;select name='student' onChange='document.registration.addreg.value=0;document.registration.deletereg.value=0;document.registration.submit();'&gt;" Length="1551" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="146">
</Literal>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="Registration.php: Unable to get a list of students - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="150">
</Literal>
                                                                    <Symbolic Text="mysql_error()"/>
                                                                  </Concat>
                                                                  <Repeat>
                                                                    <Constraint Text="$page2 == 26"/>
                                                                    <Concat>
                                                                      <Concat>
                                                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                        <Undef/>
                                                                        <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                        <Select>
                                                                          <Constraint Text="(($_POST[&quot;student&quot;] == $student[0]) &amp;&amp; ($_POST[&quot;student&quot;] != null))"/>
                                                                          <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                          <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                        </Select>
                                                                        <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                        <Undef/>
                                                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                        <Undef/>
                                                                        <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="160"/>
                                                                      </Concat>
                                                                    </Concat>
                                                                  </Repeat>
                                                                  <Literal 
                                                                  Text="  &lt;/select&gt;
&amp;nbsp;&amp;nbsp;&lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.registration.addreg.value=0;document.registration.deletereg.value=0;document.registration.submit();'&gt;
" Length="187" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="165">
</Literal>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="Registration.php: Unable to get a list of semesters for drop-down - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="168">
</Literal>
                                                                    <Symbolic Text="mysql_error()"/>
                                                                  </Concat>
                                                                  <Repeat>
                                                                    <Constraint Text="$page2 == 26"/>
                                                                    <Concat>
                                                                      <Concat>
                                                                        <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                        <Undef/>
                                                                        <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                        <Select>
                                                                          <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                                                          <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                          <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                        </Select>
                                                                        <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                        <Undef/>
                                                                        <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="182"/>
                                                                      </Concat>
                                                                    </Concat>
                                                                  </Repeat>
                                                                  <Literal 
                                                                  Text="
	 &lt;/select&gt;
   &lt;br /&gt;&lt;br /&gt;
   &lt;input type='button' value='Show in Grid' onClick='document.registration.addreg.value=0;document.registration.deletereg.value=0;document.registration.page2.value=29;document.registration.submit();' /&gt;
   &lt;br /&gt;&lt;br /&gt;
  &lt;table width='400' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header'&gt;
   &lt;th colspan='3'&gt;&lt;span style='font-size: 14pt;'&gt;&lt;b&gt;Add Class&lt;/b&gt;&lt;/span&gt;&lt;/th&gt;
  &lt;/tr&gt;
  &lt;tr class='header'&gt;
   &lt;td align='center' colspan='3'&gt;
	&lt;select name='class'&gt;" Length="511" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="196">
</Literal>
                                                                  <Concat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="&lt;/select&gt;Registration.php: Unable to get a list of courses - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="202">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Repeat>
                                                                      <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="206"/>
                                                                          <Undef/>
                                                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="206"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="206"/>
                                                                        </Concat>
                                                                      </Concat>
                                                                    </Repeat>
                                                                  </Concat>
                                                                  <Literal 
                                                                  Text="	&lt;/select&gt;
   &lt;input type='button' value='Add' onClick='document.registration.addreg.value=1;document.registration.submit();' /&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;tr class='header'&gt;
   &lt;th colspan='2'&gt;Class Name&lt;/th&gt;&lt;th&gt;Period Number&lt;/th&gt;
  &lt;/tr&gt;" Length="231" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="215">
</Literal>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="Registration.php: Unable to get a list of registrations - " Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="219">
</Literal>
                                                                    <Symbolic Text="mysql_error()"/>
                                                                  </Concat>
                                                                  <Repeat>
                                                                    <Constraint Text="$page2 == 26"/>
                                                                    <Concat>
                                                                      <Concat>
                                                                        <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="231"/>
                                                                        <Select>
                                                                          <Constraint Text="(($row % 2) == 0)"/>
                                                                          <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="231"/>
                                                                          <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="231"/>
                                                                        </Select>
                                                                        <Literal 
                                                                        Text="'&gt;
   &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="232">
</Literal>
                                                                        <Undef/>
                                                                        <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="232"/>
                                                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="236"/>
                                                                        <Literal 
                                                                        Text=");' /&gt;&lt;/td&gt;
   &lt;td align='left'&gt;" Length="32" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="233">
</Literal>
                                                                        <Undef/>
                                                                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="234"/>
                                                                        <Undef/>
                                                                        <Literal Text="&lt;/td&gt; &lt;/tr&gt; " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="236"/>
                                                                      </Concat>
                                                                    </Concat>
                                                                  </Repeat>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value='Delete' onClick='document.registration.deletereg.value=1;validate();'&gt;
  &lt;input type='hidden' name='addreg' /&gt;
  &lt;input type='hidden' name='deletereg' /&gt;
  &lt;input type='hidden' name='selectreg' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="290" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="246">
</Literal>
                                                                    <Symbolic Text="$_POST"/>
                                                                    <Literal 
                                                                    Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="248">
</Literal>
                                                                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="260"/>
                                                                    <Literal 
                                                                    Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/Registration.php" Line="260">
</Literal>
                                                                  </Concat>
                                                                </Concat>
                                                                <Select>
                                                                  <Constraint Text="$page2 == 27"/>
                                                                  <Concat>
                                                                    <Literal 
                                                                    Text="
 &lt;h1&gt;Deficiency Report&lt;/h1&gt;
 &lt;table align='center' width='400' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;

 &lt;table border='0' width='400'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;
" Length="305" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="13">
</Literal>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="ViewCourses.php: Unable to get a list of semesters for drop-down - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="16">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Repeat>
                                                                      <Constraint Text="$page2 == 27"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                          <Undef/>
                                                                          <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                          <Select>
                                                                            <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                                                            <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                          </Select>
                                                                          <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="27"/>
                                                                        </Concat>
                                                                      </Concat>
                                                                    </Repeat>
                                                                    <Concat>
                                                                      <Literal Text=" &lt;option value='-1' " Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="31"/>
                                                                      <Select>
                                                                        <Constraint Text="($_POST[&quot;semester&quot;] == -1)"/>
                                                                        <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="31"/>
                                                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="31"/>
                                                                      </Select>
                                                                      <Literal 
                                                                      Text="&gt;All&lt;/option&gt;
	 &lt;/select&gt;
&lt;br&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

  &lt;table cellspacing='0' width='400' class='dynamiclist'&gt;
  &lt;tr class='header' align='left'&gt;
   &lt;th style='padding-left: 10px;'&gt;Student&lt;/th&gt;
   &lt;th&gt;Class&lt;/th&gt;
   &lt;th&gt;Percentage&lt;/th&gt;
  &lt;/tr&gt;" Length="245" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="43">
</Literal>
                                                                    </Concat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="DeficiencyReport: Unable to get a list of deficient students - " Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="47">
</Literal>
                                                                      <Symbolic Text="mysql_error()"/>
                                                                    </Concat>
                                                                    <Repeat>
                                                                      <Constraint Text="$page2 == 27"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="82"/>
                                                                          <Select>
                                                                            <Constraint Text="(($row % 2) == 0)"/>
                                                                            <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="82"/>
                                                                            <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="82"/>
                                                                          </Select>
                                                                          <Literal 
                                                                          Text="'&gt;
   &lt;td align='left' style='padding-left: 10px;'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="83">
</Literal>
                                                                          <Undef/>
                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="83"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/td&gt; &lt;td align='left'&gt;" Length="26" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="84"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/td&gt; &lt;td align='left'&gt;" Length="26" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="85"/>
                                                                          <Symbolic Text="($percentage * 100)"/>
                                                                          <Literal Text="&amp;#37;&lt;/td&gt; &lt;/tr&gt; " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="87"/>
                                                                        </Concat>
                                                                      </Concat>
                                                                    </Repeat>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=2;document.classes.submit();'&gt;
  &lt;input type='hidden' name='page2' value='" Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="93">
</Literal>
                                                                      <Symbolic Text="$_POST"/>
                                                                      <Literal 
                                                                      Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="95">
</Literal>
                                                                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="107"/>
                                                                      <Literal 
                                                                      Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeficiencyReport.php" Line="107">
</Literal>
                                                                    </Concat>
                                                                  </Concat>
                                                                  <Select>
                                                                    <Constraint Text="$page2 == 28"/>
                                                                    <Concat>
                                                                      <Literal 
                                                                      Text="
 &lt;h1&gt;Grade Report&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;" Length="260" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="9">
</Literal>
                                                                      <Concat>
                                                                        <Literal 
                                                                        Text="PointsReport.php: Unable to get a list of semesters for drop-down - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="12">
</Literal>
                                                                        <Symbolic Text="mysql_error()"/>
                                                                      </Concat>
                                                                      <Repeat>
                                                                        <Constraint Text="$page2 == 28"/>
                                                                        <Concat>
                                                                          <Concat>
                                                                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                            <Undef/>
                                                                            <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                            <Select>
                                                                              <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                                                              <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                            </Select>
                                                                            <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                            <Undef/>
                                                                            <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="23"/>
                                                                          </Concat>
                                                                        </Concat>
                                                                      </Repeat>
                                                                      <Literal 
                                                                      Text="
	 &lt;/select&gt;
  &lt;b&gt;Student:&lt;/b&gt;&lt;select name='student' onChange='document.classes.submit();'&gt;" Length="91" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="28">
</Literal>
                                                                      <Concat>
                                                                        <Literal 
                                                                        Text="GradeReport.php: Unable to get a list of students - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="32">
</Literal>
                                                                        <Symbolic Text="mysql_error()"/>
                                                                      </Concat>
                                                                      <Select>
                                                                        <Constraint Text="($student[0] == $_POST[&quot;student&quot;])"/>
                                                                        <Concat>
                                                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="46"/>
                                                                          <Undef/>
                                                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="46"/>
                                                                          <Undef/>
                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="46"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="46"/>
                                                                          <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="34"/>
                                                                        </Concat>
                                                                        <Concat>
                                                                          <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="34"/>
                                                                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="50"/>
                                                                          <Undef/>
                                                                          <Literal Text="'&gt;" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="50"/>
                                                                          <Undef/>
                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="50"/>
                                                                          <Undef/>
                                                                          <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="50"/>
                                                                        </Concat>
                                                                      </Select>
                                                                      <Literal 
                                                                      Text="  &lt;/select&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' class='dynamiclist'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Class&lt;/th&gt;
   &lt;th&gt;1st Quarter Grade&lt;/th&gt;
   &lt;th&gt;2nd Quarter Grade&lt;/th&gt;
   &lt;th&gt;Current Grade&lt;/th&gt;
  &lt;/tr&gt;" Length="214" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="63">
</Literal>
                                                                      <Concat>
                                                                        <Literal 
                                                                        Text="GradeReport.php: Unable to get the list of classes this student is registered for - " Length="84" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="67">
</Literal>
                                                                        <Symbolic Text="mysql_error()"/>
                                                                      </Concat>
                                                                      <Repeat>
                                                                        <Constraint Text="$page2 == 28"/>
                                                                        <Concat>
                                                                          <Concat>
                                                                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="136"/>
                                                                            <Select>
                                                                              <Constraint Text="(($row % 2) == 0)"/>
                                                                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="136"/>
                                                                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="136"/>
                                                                            </Select>
                                                                            <Literal 
                                                                            Text="'&gt;
   &lt;td align='left' style='padding-left: 30px;'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="137">
</Literal>
                                                                            <Undef/>
                                                                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="138"/>
                                                                            <Select>
                                                                              <Constraint Text="($cinfo[1] == 0)"/>
                                                                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="86"/>
                                                                              <Select>
                                                                                <Constraint Text="($q1grade &gt;= ($cinfo[4] / 100))"/>
                                                                                <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="88"/>
                                                                                <Select>
                                                                                  <Constraint Text="($q1grade &gt;= ($cinfo[5] / 100))"/>
                                                                                  <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="90"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($q1grade &gt;= ($cinfo[6] / 100))"/>
                                                                                    <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="92"/>
                                                                                    <Select>
                                                                                      <Constraint Text="($q1grade &gt;= ($cinfo[7] / 100))"/>
                                                                                      <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="94"/>
                                                                                      <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="96"/>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                            </Select>
                                                                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="139"/>
                                                                            <Select>
                                                                              <Constraint Text="($q2grade == 0)"/>
                                                                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="105"/>
                                                                              <Select>
                                                                                <Constraint Text="($q2grade &gt;= ($cinfo[4] / 100))"/>
                                                                                <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="107"/>
                                                                                <Select>
                                                                                  <Constraint Text="($q2grade &gt;= ($cinfo[5] / 100))"/>
                                                                                  <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="109"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($q2grade &gt;= ($cinfo[6] / 100))"/>
                                                                                    <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="111"/>
                                                                                    <Select>
                                                                                      <Constraint Text="($q2grade &gt;= ($cinfo[7] / 100))"/>
                                                                                      <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="113"/>
                                                                                      <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="115"/>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                            </Select>
                                                                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="140"/>
                                                                            <Select>
                                                                              <Constraint Text="($cinfo[3] == 0)"/>
                                                                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="124"/>
                                                                              <Select>
                                                                                <Constraint Text="($currgrade &gt;= ($cinfo[4] / 100))"/>
                                                                                <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="126"/>
                                                                                <Select>
                                                                                  <Constraint Text="($currgrade &gt;= ($cinfo[5] / 100))"/>
                                                                                  <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="128"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($currgrade &gt;= ($cinfo[6] / 100))"/>
                                                                                    <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="130"/>
                                                                                    <Select>
                                                                                      <Constraint Text="($currgrade &gt;= ($cinfo[7] / 100))"/>
                                                                                      <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="132"/>
                                                                                      <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="134"/>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                            </Select>
                                                                            <Literal Text="&lt;/td&gt; " Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="141"/>
                                                                          </Concat>
                                                                        </Concat>
                                                                      </Repeat>
                                                                      <Concat>
                                                                        <Literal 
                                                                        Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=2;document.classes.submit();'&gt;
  &lt;input type='hidden' name='page2' value='" Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="147">
</Literal>
                                                                        <Symbolic Text="$_POST"/>
                                                                        <Literal 
                                                                        Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="149">
</Literal>
                                                                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="161"/>
                                                                        <Literal 
                                                                        Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/GradeReport.php" Line="161">
</Literal>
                                                                      </Concat>
                                                                    </Concat>
                                                                    <Select>
                                                                      <Constraint Text="$page2 == 29"/>
                                                                      <Concat>
                                                                        <Concat>
                                                                          <Literal 
                                                                          Text="VisualizeRegistration: Unable to get the student's name - " Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="4">
</Literal>
                                                                          <Symbolic Text="mysql_error()"/>
                                                                        </Concat>
                                                                        <Concat>
                                                                          <Literal Text=" &lt;h1&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="11"/>
                                                                          <Undef/>
                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="11"/>
                                                                          <Undef/>
                                                                          <Literal 
                                                                          Text="'s Schedule&lt;/h1&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
  &lt;b&gt;Semester:&lt;/b&gt; " Length="185" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="16">
</Literal>
                                                                          <Undef/>
                                                                          <Literal 
                                                                          Text="
  &lt;table cellspacing='0' width='600' cellpadding='5' border='1' align='center' bordercolor='black' cellspacing='0' cellpadding='5'&gt;
  &lt;tr class='header'&gt;
   &lt;th width='10'&gt;Period&lt;/th&gt;
   &lt;th&gt;Monday&lt;/th&gt;
   &lt;th&gt;Tuesday&lt;/th&gt;
   &lt;th&gt;Wednesday&lt;/th&gt;
   &lt;th&gt;Thursday&lt;/th&gt;
   &lt;th&gt;Friday&lt;/th&gt;
  &lt;/tr&gt;
   " Length="297" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="26">
</Literal>
                                                                        </Concat>
                                                                        <Concat>
                                                                          <Literal 
                                                                          Text="VisualizeRegistration.php: Unable to get number of periods - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="29">
</Literal>
                                                                          <Symbolic Text="mysql_error()"/>
                                                                        </Concat>
                                                                        <Repeat>
                                                                          <Constraint Text="$page2 == 29"/>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="43"/>
                                                                              <Select>
                                                                                <Constraint Text="(($i % 2) == 0)"/>
                                                                                <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="43"/>
                                                                                <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="43"/>
                                                                              </Select>
                                                                              <Literal Text="' valign='top'&gt; &lt;td&gt;" Length="22" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="44"/>
                                                                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="44"/>
                                                                              <Literal Text="&lt;/td&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="44"/>
                                                                            </Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="VisualizeRegistration.php: Unable to get a list of CourseIDs for the student's registration - " Length="94" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="48">
</Literal>
                                                                              <Symbolic Text="mysql_error()"/>
                                                                            </Concat>
                                                                            <Repeat>
                                                                              <Constraint Text="$page2 == 29"/>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Literal 
                                                                                  Text="VisualizeRegistration.php: Unable to get class information - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="54">
</Literal>
                                                                                  <Symbolic Text="mysql_error()"/>
                                                                                </Concat>
                                                                              </Concat>
                                                                            </Repeat>
                                                                            <Concat>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                                <Select>
                                                                                  <Constraint Text="($monday != &quot;&lt;td&gt;&quot;)"/>
                                                                                  <Concat>
                                                                                    <Concat>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                      <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="68"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="69"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="70"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="71"/>
                                                                                      <Undef/>
                                                                                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="71"/>
                                                                                      <Undef/>
                                                                                    </Concat>
                                                                                    <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="74"/>
                                                                                  </Concat>
                                                                                  <Concat>
                                                                                    <Concat>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                      <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="68"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="69"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="70"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="71"/>
                                                                                      <Undef/>
                                                                                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="71"/>
                                                                                      <Undef/>
                                                                                    </Concat>
                                                                                    <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="76"/>
                                                                                  </Concat>
                                                                                </Select>
                                                                                <Select>
                                                                                  <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                  <Select>
                                                                                    <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                    <Select>
                                                                                      <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                      <Select>
                                                                                        <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="37"/>
                                                                                        <Undef/>
                                                                                      </Select>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                              <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="135"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                <Select>
                                                                                  <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($tuesday != &quot;&lt;td&gt;&quot;)"/>
                                                                                    <Concat>
                                                                                      <Concat>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                        <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="82"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="83"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="84"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="85"/>
                                                                                        <Undef/>
                                                                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="85"/>
                                                                                        <Undef/>
                                                                                      </Concat>
                                                                                      <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="87"/>
                                                                                    </Concat>
                                                                                    <Concat>
                                                                                      <Concat>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                        <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="82"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="83"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="84"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="85"/>
                                                                                        <Undef/>
                                                                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="85"/>
                                                                                        <Undef/>
                                                                                      </Concat>
                                                                                      <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="89"/>
                                                                                    </Concat>
                                                                                  </Select>
                                                                                  <Select>
                                                                                    <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                    <Select>
                                                                                      <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                      <Select>
                                                                                        <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="38"/>
                                                                                        <Undef/>
                                                                                      </Select>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                              <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="135"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                <Select>
                                                                                  <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                  <Select>
                                                                                    <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                                    <Select>
                                                                                      <Constraint Text="($wednesday != &quot;&lt;td&gt;&quot;)"/>
                                                                                      <Concat>
                                                                                        <Concat>
                                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                          <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="96"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="97"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="98"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="99"/>
                                                                                          <Undef/>
                                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="99"/>
                                                                                          <Undef/>
                                                                                        </Concat>
                                                                                        <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="101"/>
                                                                                      </Concat>
                                                                                      <Concat>
                                                                                        <Concat>
                                                                                          <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                          <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="96"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="97"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="98"/>
                                                                                          <Undef/>
                                                                                          <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="99"/>
                                                                                          <Undef/>
                                                                                          <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="99"/>
                                                                                          <Undef/>
                                                                                        </Concat>
                                                                                        <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="103"/>
                                                                                      </Concat>
                                                                                    </Select>
                                                                                    <Select>
                                                                                      <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                      <Select>
                                                                                        <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="39"/>
                                                                                        <Undef/>
                                                                                      </Select>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                              <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="135"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                <Select>
                                                                                  <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                  <Select>
                                                                                    <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                    <Select>
                                                                                      <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                                      <Select>
                                                                                        <Constraint Text="($thursday != &quot;&lt;td&gt;&quot;)"/>
                                                                                        <Concat>
                                                                                          <Concat>
                                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                            <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="109"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="110"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="111"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="112"/>
                                                                                            <Undef/>
                                                                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="112"/>
                                                                                            <Undef/>
                                                                                          </Concat>
                                                                                          <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="114"/>
                                                                                        </Concat>
                                                                                        <Concat>
                                                                                          <Concat>
                                                                                            <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                            <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="109"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="110"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="111"/>
                                                                                            <Undef/>
                                                                                            <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="112"/>
                                                                                            <Undef/>
                                                                                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="112"/>
                                                                                            <Undef/>
                                                                                          </Concat>
                                                                                          <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="116"/>
                                                                                        </Concat>
                                                                                      </Select>
                                                                                      <Select>
                                                                                        <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                        <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="40"/>
                                                                                        <Undef/>
                                                                                      </Select>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                              <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="135"/>
                                                                              <Select>
                                                                                <Constraint Text="$days[$j] == &quot;M&quot;"/>
                                                                                <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                <Select>
                                                                                  <Constraint Text="$days[$j] == &quot;T&quot;"/>
                                                                                  <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                  <Select>
                                                                                    <Constraint Text="$days[$j] == &quot;W&quot;"/>
                                                                                    <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                    <Select>
                                                                                      <Constraint Text="$days[$j] == &quot;H&quot;"/>
                                                                                      <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                      <Select>
                                                                                        <Constraint Text="$days[$j] == &quot;F&quot;"/>
                                                                                        <Select>
                                                                                          <Constraint Text="($friday != &quot;&lt;td&gt;&quot;)"/>
                                                                                          <Concat>
                                                                                            <Concat>
                                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                              <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="122"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="123"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="124"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="125"/>
                                                                                              <Undef/>
                                                                                              <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="125"/>
                                                                                              <Undef/>
                                                                                            </Concat>
                                                                                            <Literal Text="&lt;br /&gt;&lt;br /&gt;" Length="12" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="127"/>
                                                                                          </Concat>
                                                                                          <Concat>
                                                                                            <Concat>
                                                                                              <Literal Text="&lt;td align='left'&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="41"/>
                                                                                              <Literal Text="&lt;b&gt;" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="122"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;/b&gt;&lt;br /&gt; Section: " Length="25" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="123"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;br /&gt; Room: " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="124"/>
                                                                                              <Undef/>
                                                                                              <Literal Text="&lt;br /&gt; Teacher: " Length="21" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="125"/>
                                                                                              <Undef/>
                                                                                              <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="125"/>
                                                                                              <Undef/>
                                                                                            </Concat>
                                                                                            <Literal Text="&lt;br /&gt;" Length="6" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="129"/>
                                                                                          </Concat>
                                                                                        </Select>
                                                                                        <Undef/>
                                                                                      </Select>
                                                                                    </Select>
                                                                                  </Select>
                                                                                </Select>
                                                                              </Select>
                                                                              <Literal Text="&amp;nbsp;&lt;/td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="135"/>
                                                                            </Concat>
                                                                            <Literal Text="&lt;/tr&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="139"/>
                                                                          </Concat>
                                                                        </Repeat>
                                                                        <Concat>
                                                                          <Literal 
                                                                          Text="  &lt;/table&gt;
&lt;table border='0' width='600' align='center'&gt;
&lt;tr&gt;
&lt;td&gt;
&lt;p align='left'&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=26;document.classes.submit();'&gt;
  &lt;input type='hidden' name='page2' value='" Length="235" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="148">
</Literal>
                                                                          <Symbolic Text="$_POST"/>
                                                                          <Literal 
                                                                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="150">
</Literal>
                                                                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="166"/>
                                                                          <Literal 
                                                                          Text="'&gt;
&lt;/p&gt;
&lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="196" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/VisualizeRegistration.php" Line="166">
</Literal>
                                                                        </Concat>
                                                                      </Concat>
                                                                      <Select>
                                                                        <Constraint Text="$page2 == 30"/>
                                                                        <Concat>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="ManageAttendance.php: Unable to insert new attendance record - " Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="13">
</Literal>
                                                                              <Symbolic Text="mysql_error()"/>
                                                                            </Concat>
                                                                          </Concat>
                                                                          <Concat>
                                                                            <Repeat>
                                                                              <Constraint Text="($_POST[&quot;deletereg&quot;] == 1)"/>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Concat>
                                                                                    <Literal 
                                                                                    Text="DeleteFunctions.php: Unable to delete selected Attendanc(es) - " Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="19">
</Literal>
                                                                                    <Symbolic Text="mysql_error()"/>
                                                                                  </Concat>
                                                                                </Concat>
                                                                              </Concat>
                                                                            </Repeat>
                                                                          </Concat>
                                                                          <Literal 
                                                                          Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the student wants to delete the registration(s) //
  function validate()
  {
   if( document.registration.selectreg.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this attendance record?&quot;);

	if( confirmed == true )
	{
	 document.registration.submit();
	}
   }
   else
   {
	alert('You must select an attendance record to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.registration.selectreg.value == 1 )
   {
	document.registration.submit();
   }
   else
   {
	if( document.registration.selectreg.value &gt; 1 )
	{
	 alert('You can only edit one student at a time.');
	}
	else
	{
	 alert('You must select a registration to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 1;
   if(document.registration.elements[row].checked)
   {
	document.registration.selectreg.value = Math.round(document.registration.selectreg.value) + 1;
   }
   else
   {
	document.registration.selectreg.value = Math.round(document.registration.selectreg.value) - 1;
   }
   //alert(document.registration.selectreg.value);
  }
 &lt;/script&gt;
 &lt;h1&gt;Attendance&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='400' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='registration' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.registration.addattend.value=0;document.registration.deletereg.value=0;document.registration.submit();'&gt;
 " Length="1618" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="92">
</Literal>
                                                                          <Concat>
                                                                            <Literal 
                                                                            Text="Registration.php: Unable to get a list of semesters for drop-down - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="95">
</Literal>
                                                                            <Symbolic Text="mysql_error()"/>
                                                                          </Concat>
                                                                          <Repeat>
                                                                            <Constraint Text="$page2 == 30"/>
                                                                            <Concat>
                                                                              <Concat>
                                                                                <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                                <Undef/>
                                                                                <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                                <Select>
                                                                                  <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                                                                  <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                                </Select>
                                                                                <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="109"/>
                                                                              </Concat>
                                                                            </Concat>
                                                                          </Repeat>
                                                                          <Literal 
                                                                          Text="
	 &lt;/select&gt;
 &lt;b&gt;Student:&lt;/b&gt;
 &lt;select name='student' onChange='document.registration.addattend.value=0;document.registration.deletereg.value=0;document.registration.submit();'&gt;" Length="177" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="116">
</Literal>
                                                                          <Concat>
                                                                            <Literal 
                                                                            Text="ManageAttendance.php: Unable to get a list of students - " Length="57" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="120">
</Literal>
                                                                            <Symbolic Text="mysql_error()"/>
                                                                          </Concat>
                                                                          <Repeat>
                                                                            <Constraint Text="$page2 == 30"/>
                                                                            <Concat>
                                                                              <Concat>
                                                                                <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                <Undef/>
                                                                                <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                <Select>
                                                                                  <Constraint Text="(($_POST[&quot;student&quot;] == $student[0]) &amp;&amp; ($_POST[&quot;student&quot;] != null))"/>
                                                                                  <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                </Select>
                                                                                <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                <Undef/>
                                                                                <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                                <Undef/>
                                                                                <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="131"/>
                                                                              </Concat>
                                                                            </Concat>
                                                                          </Repeat>
                                                                          <Literal 
                                                                          Text="  &lt;/select&gt;
   &lt;br /&gt;&lt;br /&gt;
  &lt;table width='400' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header' align='center'&gt;
   &lt;th&gt;&amp;nbsp;&lt;/th&gt;&lt;th&gt;Tardy&lt;/th&gt;&lt;th&gt;Absent&lt;/th&gt;
  &lt;/tr&gt;" Length="194" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="140">
</Literal>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="ManageAttendance.php: Unable to get a list of registrations - " Length="62" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="150">
</Literal>
                                                                              <Symbolic Text="mysql_error()"/>
                                                                            </Concat>
                                                                            <Repeat>
                                                                              <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Literal 
                                                                                  Text="&lt;tr style='color: red; font-weight: bold;' align='center' class='" Length="65" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="161">
</Literal>
                                                                                  <Select>
                                                                                    <Constraint Text="(($row % 2) == 0)"/>
                                                                                    <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="161"/>
                                                                                    <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="161"/>
                                                                                  </Select>
                                                                                  <Literal 
                                                                                  Text="'&gt;
   &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="162">
</Literal>
                                                                                  <Undef/>
                                                                                  <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="162"/>
                                                                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="166"/>
                                                                                  <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="163"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($class[0] != &quot;&quot;)"/>
                                                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DBFunctions.php" Line="44"/>
                                                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="163"/>
                                                                                  </Select>
                                                                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="164"/>
                                                                                  <Select>
                                                                                    <Constraint Text="($class[1] != &quot;&quot;)"/>
                                                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DBFunctions.php" Line="44"/>
                                                                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="164"/>
                                                                                  </Select>
                                                                                  <Literal Text="&lt;/td&gt; &lt;/tr&gt; " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="166"/>
                                                                                </Concat>
                                                                              </Concat>
                                                                            </Repeat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="&lt;tr class='even'&gt;&lt;td&gt;&amp;nbsp;&lt;/td&gt;&lt;td&gt;N/A&lt;/td&gt;&lt;td&gt;N/A&lt;/td&gt;&lt;/tr&gt;" Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="172">
</Literal>
                                                                            </Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="ManageAttendance.php: Unable to get the total number of tardies - " Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="174">
</Literal>
                                                                              <Symbolic Text="mysql_error()"/>
                                                                            </Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="ManageAttendance.php: Unable to get the total number of absences - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="177">
</Literal>
                                                                              <Symbolic Text="mysql_error()"/>
                                                                            </Concat>
                                                                          </Concat>
                                                                          <Concat>
                                                                            <Literal 
                                                                            Text="
   &lt;tr class='header' align='center'&gt;
	&lt;td&gt;&lt;b&gt;Totals:&lt;/td&gt;&lt;td&gt;" Length="63" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="183">
</Literal>
                                                                            <Undef/>
                                                                            <Literal Text="&lt;/td&gt;&lt;td&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="183"/>
                                                                            <Undef/>
                                                                            <Literal 
                                                                            Text="&lt;/td&gt;
   &lt;/tr&gt;
  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value='Add' onClick='document.registration.addattend.value=1;document.registration.page2.value=31;document.registration.submit();'&gt; &lt;input type='button' value='Delete' onClick='document.registration.deletereg.value=1;validate();'&gt;

  &lt;input type='hidden' name='addattend' /&gt;
  &lt;input type='hidden' name='deletereg' /&gt;
  &lt;input type='hidden' name='selectreg' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="462" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="192">
</Literal>
                                                                            <Symbolic Text="$_POST"/>
                                                                            <Literal 
                                                                            Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="194">
</Literal>
                                                                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="206"/>
                                                                            <Literal 
                                                                            Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAttendance.php" Line="206">
</Literal>
                                                                          </Concat>
                                                                        </Concat>
                                                                        <Select>
                                                                          <Constraint Text="$page2 == 31"/>
                                                                          <Concat>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="&lt;h1&gt;Add New Attendance Record&lt;/h1&gt;

  &lt;form name='addattendance' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='300'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Date&lt;/th&gt;
	&lt;th&gt;Type&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;&lt;input type='text' name='attdate' maxlength='10' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;
	 &lt;select name='type'&gt;
	  &lt;option value='tardy'&gt;Tardy&lt;/option&gt;
	  &lt;option value='absent'&gt;Absent&lt;/option&gt;
	 &lt;/select&gt;
	&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='300'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Attendance' onClick='document.addattendance.addattendance.value=1;document.addattendance.page2.value=30;document.addattendance.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addattendance.page2.value=30;document.addattendance.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addattendance' value='' /&gt;
  &lt;input type='hidden' name='semester' value='" Length="1032" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="32">
</Literal>
                                                                              <Symbolic Text="$_POST"/>
                                                                              <Literal 
                                                                              Text="' /&gt;
  &lt;input type='hidden' name='student' value='" Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="33">
</Literal>
                                                                              <Symbolic Text="$_POST"/>
                                                                              <Literal 
                                                                              Text="' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="34">
</Literal>
                                                                              <Symbolic Text="$_POST"/>
                                                                              <Literal 
                                                                              Text="'&gt;
  &lt;input type='hidden' name='date'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="119" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="37">
</Literal>
                                                                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="48"/>
                                                                              <Literal 
                                                                              Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAttendance.php" Line="48">
</Literal>
                                                                            </Concat>
                                                                          </Concat>
                                                                          <Select>
                                                                            <Constraint Text="$page2 == 32"/>
                                                                            <Concat>
                                                                              <Literal 
                                                                              Text="
 &lt;h1&gt;Points Report&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;" Length="261" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="10">
</Literal>
                                                                              <Concat>
                                                                                <Literal 
                                                                                Text="PointsReport.php: Unable to get a list of semesters for drop-down - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="13">
</Literal>
                                                                                <Symbolic Text="mysql_error()"/>
                                                                              </Concat>
                                                                              <Repeat>
                                                                                <Constraint Text="$page2 == 32"/>
                                                                                <Concat>
                                                                                  <Concat>
                                                                                    <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                    <Select>
                                                                                      <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                                                                      <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                      <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                    </Select>
                                                                                    <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                    <Undef/>
                                                                                    <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="24"/>
                                                                                  </Concat>
                                                                                </Concat>
                                                                              </Repeat>
                                                                              <Literal 
                                                                              Text="
	 &lt;/select&gt;
 &amp;nbsp;&amp;nbsp;&lt;b&gt;Classes:&lt;/b&gt;
 &lt;select name='selectclass' onChange='document.classes.submit();'&gt;" Length="108" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="30">
</Literal>
                                                                              <Concat>
                                                                                <Concat>
                                                                                  <Literal 
                                                                                  Text="&lt;/select&gt;PointsReport.php: Unable to get a list of courses - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="52">
</Literal>
                                                                                  <Symbolic Text="mysql_error()"/>
                                                                                </Concat>
                                                                                <Repeat>
                                                                                  <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                                                                                  <Concat>
                                                                                    <Concat>
                                                                                      <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                      <Select>
                                                                                        <Constraint Text="($_POST[&quot;selectclass&quot;] == $classes[0])"/>
                                                                                        <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                      </Select>
                                                                                      <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                      <Undef/>
                                                                                      <Literal Text="&lt;/option&gt; " Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="56"/>
                                                                                    </Concat>
                                                                                  </Concat>
                                                                                </Repeat>
                                                                              </Concat>
                                                                              <Literal 
                                                                              Text="  &lt;/select&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' class='dynamiclist'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Student&lt;/th&gt;
   &lt;th&gt;Current Points&lt;/th&gt;
   &lt;th&gt;Total Points&lt;/th&gt;
   &lt;th&gt;Percent&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
  &lt;/tr&gt;" Length="220" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="69">
</Literal>
                                                                              <Select>
                                                                                <Constraint Text="($_POST[&quot;selectclass&quot;] != null)"/>
                                                                                <Concat>
                                                                                  <Repeat>
                                                                                    <Constraint Text="($_POST[&quot;selectclass&quot;] != null)"/>
                                                                                    <Concat>
                                                                                      <Concat>
                                                                                        <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="108"/>
                                                                                        <Select>
                                                                                          <Constraint Text="(($row % 2) == 0)"/>
                                                                                          <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="108"/>
                                                                                          <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="108"/>
                                                                                        </Select>
                                                                                        <Literal 
                                                                                        Text="'&gt;
	&lt;td align='left' style='padding-left: 30px;'&gt;" Length="49" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="109">
</Literal>
                                                                                        <Undef/>
                                                                                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="109"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="110"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="111"/>
                                                                                        <Undef/>
                                                                                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="112"/>
                                                                                        <Select>
                                                                                          <Constraint Text="($total != 0)"/>
                                                                                          <Symbolic Text="number_format()"/>
                                                                                          <Literal Text="0.00" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="95"/>
                                                                                        </Select>
                                                                                        <Literal Text="&amp;#37;&lt;/td&gt; &lt;td&gt;" Length="16" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="113"/>
                                                                                        <Select>
                                                                                          <Constraint Text="($perc &gt;= $classinfo[1])"/>
                                                                                          <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="98"/>
                                                                                          <Select>
                                                                                            <Constraint Text="($perc &gt;= $classinfo[2])"/>
                                                                                            <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="100"/>
                                                                                            <Select>
                                                                                              <Constraint Text="($perc &gt;= $classinfo[3])"/>
                                                                                              <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="102"/>
                                                                                              <Select>
                                                                                                <Constraint Text="($perc &gt;= $classinfo[4])"/>
                                                                                                <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="104"/>
                                                                                                <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="106"/>
                                                                                              </Select>
                                                                                            </Select>
                                                                                          </Select>
                                                                                        </Select>
                                                                                        <Literal Text="&lt;/td&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="113"/>
                                                                                      </Concat>
                                                                                    </Concat>
                                                                                  </Repeat>
                                                                                </Concat>
                                                                                <Concat>
                                                                                  <Literal 
                                                                                  Text="&lt;tr class='even'&gt;&lt;td colspan='5'&gt;There are no students registered for this class.&lt;/td&gt;&lt;/tr&gt;" Length="91" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="118">
</Literal>
                                                                                </Concat>
                                                                              </Select>
                                                                              <Concat>
                                                                                <Literal 
                                                                                Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=2;document.classes.submit();'&gt;
  &lt;input type='hidden' name='page2' value='" Length="170" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="124">
</Literal>
                                                                                <Symbolic Text="$_POST"/>
                                                                                <Literal 
                                                                                Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="126">
</Literal>
                                                                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="138"/>
                                                                                <Literal 
                                                                                Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/PointsReport.php" Line="138">
</Literal>
                                                                              </Concat>
                                                                            </Concat>
                                                                            <Concat>
                                                                              <Literal Text="AdminMain.php: Invalid Page" Length="27" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="264"/>
                                                                            </Concat>
                                                                          </Select>
                                                                        </Select>
                                                                      </Select>
                                                                    </Select>
                                                                  </Select>
                                                                </Select>
                                                              </Select>
                                                            </Select>
                                                          </Select>
                                                        </Select>
                                                      </Select>
                                                    </Select>
                                                  </Select>
                                                </Select>
                                              </Select>
                                            </Select>
                                          </Select>
                                        </Select>
                                      </Select>
                                    </Select>
                                  </Select>
                                </Select>
                              </Select>
                            </Select>
                          </Select>
                        </Select>
                      </Select>
                    </Select>
                  </Select>
                </Select>
              </Select>
            </Select>
          </Select>
          <Literal 
          Text=" 	 &lt;/td&gt;
	&lt;/tr&gt;
   &lt;/table&gt;

  &lt;/td&gt;" Length="36" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AdminMain.php" Line="272">
</Literal>
        </Concat>
        <Select>
          <Constraint Text="$page == 2"/>
          <Concat>
            <Concat>
              <Literal Text="Invalid User!" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="5"/>
            </Concat>
            <Literal 
            Text="&lt;script language='JavaScript'&gt;

  function classes()
  {
	document.teacher.page2.value=0;
	document.teacher.submit();
  }

  function settings()
  {
	document.teacher.page2.value=1;
	document.teacher.submit();
  }

  function assignments()
  {
	document.teacher.page2.value=2;
	document.teacher.submit();
  }

  function grades()
  {
   document.teacher.page2.value=3;
   document.teacher.submit();
  }

  function announcements()
  {
	document.teacher.page2.value=9;
	document.teacher.submit();
  }

  function students()
  {
	document.teacher.page2.value=8;
	document.teacher.submit();
  }

  function logoutteacher()
  {
	document.teacher.logout.value=1;
	document.teacher.submit();
  }
 &lt;/script&gt;

 &lt;body&gt;" Length="709" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="55">
</Literal>
            <Concat>
              <Literal 
              Text="&lt;table cellpadding=0 cellspacing=0 border=0 width='100%' height='80'&gt;
 &lt;tr&gt;
 &lt;td class='b' width='300'&gt;
  &lt;img src='./images/title.gif' height='75' width='300' /&gt;
 &lt;/td&gt;
 &lt;td class='b'&gt;
  &lt;table cellpadding=0 cellspacing=0 border=0 width='80%'&gt;
  &lt;tr&gt;
  &lt;td class='b'&gt;
   &lt;div class='yellowtext' align='center'&gt;" Length="311" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="11">
</Literal>
              <Symbolic Text="htmlspecialchars()"/>
              <Literal 
              Text="&lt;/div&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;

 &lt;table width='100%' height='88%' border=0 cellspacing=0 cellpadding=0 align='center'&gt;
 &lt;tr&gt;
  &lt;td class='b' width='130' height=10&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height=10 background='./images/top.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;

" Length="423" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="27">
</Literal>
            </Concat>
            <Literal 
            Text="
 &lt;tr&gt;
  &lt;td class='b' width=130 valign='top'&gt;
   &lt;br&gt;
   &lt;form name='teacher' action='./index.php' method='POST'&gt;

   &lt;a class='menu' href='javascript: classes();' onMouseover=&quot;window.status='View Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Classes&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;" Length="289" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="66">
</Literal>
            <Concat>
              <Literal 
              Text="
	 &lt;a class='menu' href='javascript: settings();' onMouseover=&quot;window.status='Manage Class Settings'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Settings&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: assignments();' onMouseover=&quot;window.status='Manage Assignments'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Assignments&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: grades();' onMouseover=&quot;window.status='Manage Grades'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Grades&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: students();' onMouseover=&quot;window.status='View Student Information'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Students&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: announcements();' onMouseover=&quot;window.status='View Announcements'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Announcements&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;" Length="916" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="80">
</Literal>
            </Concat>
            <Concat>
              <Literal 
              Text="   &lt;a class='menu' href='javascript: logoutteacher();' onMouseover=&quot;window.status='Log Out';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;Log Out&lt;/a&gt;

   &lt;input type='hidden' name='page2' value='" Length="206" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="85">
</Literal>
              <Symbolic Text="$_POST"/>
              <Literal 
              Text="'&gt;
   &lt;input type='hidden' name='logout'&gt;
   &lt;input type='hidden' name='page' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="87">
</Literal>
              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="95"/>
              <Literal 
              Text="'&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="88">
</Literal>
              <Symbolic Text="$_POST"/>
              <Literal 
              Text="' /&gt;
 &lt;/form&gt;
  &lt;/td&gt;
  &lt;td class='b' width='10' background='./images/left.gif'&gt;&lt;div style='letter-spacing: 1pt;'&gt;&amp;nbsp;&lt;/div&gt;&lt;/td&gt;
  &lt;td class='w' valign='top'&gt;
   &lt;table border=0 cellspacing=0 cellpadding=10 width='100%' height='100%'&gt;
	&lt;tr&gt;
	 &lt;td valign='top'&gt;" Length="263" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="95">
</Literal>
            </Concat>
            <Select>
              <Constraint Text="$page2 == 0"/>
              <Concat>
                <Concat>
                  <Literal Text="ViewCourses.php: Unable to get the teacherid - " Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="2">
                  </Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Concat>
                  <Literal Text=" &lt;h1&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="5"/>
                  <Undef/>
                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="5"/>
                  <Undef/>
                  <Literal 
                  Text="'s Classes&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='300' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;
" Length="257" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="12">
</Literal>
                </Concat>
                <Concat>
                  <Literal 
                  Text="ViewCourses.php: Unable to get a list of semesters for drop-down - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="15">
</Literal>
                  <Symbolic Text="mysql_error()"/>
                </Concat>
                <Repeat>
                  <Constraint Text="$page2 == 0"/>
                  <Concat>
                    <Concat>
                      <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                      <Undef/>
                      <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                      <Select>
                        <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                        <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                      </Select>
                      <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                      <Undef/>
                      <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="28"/>
                    </Concat>
                  </Concat>
                </Repeat>
                <Literal 
                Text="
	 &lt;/select&gt;
	 &lt;br&gt;&lt;br&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='300' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
   &lt;/tr&gt;
   " Length="196" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="43">
</Literal>
                <Concat>
                  <Concat>
                    <Literal 
                    Text="ViewCourses.php: Unable to get a list of classes - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="47">
</Literal>
                    <Symbolic Text="mysql_error()"/>
                  </Concat>
                  <Repeat>
                    <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                    <Concat>
                      <Concat>
                        <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="53"/>
                        <Select>
                          <Constraint Text="(($row % 2) == 0)"/>
                          <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="53"/>
                          <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="53"/>
                        </Select>
                        <Literal 
                        Text="'&gt;
   &lt;td&gt;&lt;a class='items' href=&quot;JavaScript:document.classes.selectclass.value=" Length="79" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="54">
</Literal>
                        <Undef/>
                        <Literal 
                        Text=";document.classes.page2.value=1;document.classes.submit();&quot; onMouseover=&quot;window.status='View Information For " Length="109" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="54">
</Literal>
                        <Undef/>
                        <Literal 
                        Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="54">
</Literal>
                        <Undef/>
                        <Literal Text="&lt;/a&gt;&lt;/td&gt; &lt;/tr&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="55"/>
                      </Concat>
                    </Concat>
                  </Repeat>
                </Concat>
                <Concat>
                  <Literal 
                  Text="&lt;/table&gt;
   &lt;input type='hidden' name='page2' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="59">
</Literal>
                  <Symbolic Text="$_POST"/>
                  <Literal 
                  Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="61">
</Literal>
                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="75"/>
                  <Literal 
                  Text="' /&gt;
   &lt;input type='hidden' name='selectclass' /&gt;
   &lt;input type='hidden' name='teacherid' value='" Length="99" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="63">
</Literal>
                  <Undef/>
                  <Literal 
                  Text="' /&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;/form&gt;

  &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;" Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewCourses.php" Line="75">
</Literal>
                </Concat>
              </Concat>
              <Select>
                <Constraint Text="$page2 == 1"/>
                <Concat>
                  <Concat>
                    <Concat>
                      <Literal 
                      Text="ClassSettings.php: Unable to update the grading scale - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="6">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                  </Concat>
                  <Concat>
                    <Literal 
                    Text="ClassInfo.php: Unable to get the class information - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="9">
</Literal>
                    <Symbolic Text="mysql_error()"/>
                  </Concat>
                  <Concat>
                    <Literal 
                    Text=" &lt;h1&gt;Class Settings&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;table align='center' width='500' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='500' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='5' align='center'&gt;&lt;h2&gt;" Length="332" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="20">
</Literal>
                    <Undef/>
                    <Literal 
                    Text="&lt;/h2&gt;&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header' align='center'&gt;
	&lt;th&gt;A Percent&lt;/th&gt;
	&lt;th&gt;B Percent&lt;/th&gt;
	&lt;th&gt;C Percent&lt;/th&gt;
	&lt;th&gt;D Percent&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;&lt;input type='text' name='aperc' value='" Length="212" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="29">
</Literal>
                    <Undef/>
                    <Literal 
                    Text="' size=5 /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='bperc' value='" Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="30">
</Literal>
                    <Undef/>
                    <Literal 
                    Text="' size=5 /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='cperc' value='" Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="31">
</Literal>
                    <Undef/>
                    <Literal 
                    Text="' size=5 /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='dperc' value='" Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="32">
</Literal>
                    <Undef/>
                    <Literal Text="' size=5 /&gt;&lt;/td&gt; &lt;/tr&gt; " Length="29" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="34"/>
                  </Concat>
                  <Concat>
                    <Literal 
                    Text="&lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Update' onClick='document.classes.update.value=1;document.classes.submit();'&gt;
   &lt;input type='hidden' name='page2' value='" Length="168" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="39">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="41">
</Literal>
                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="48"/>
                    <Literal 
                    Text="' /&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="42">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="' /&gt;
   &lt;input type='hidden' name='update' /&gt;
   &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ClassSettings.php" Line="48">
</Literal>
                  </Concat>
                </Concat>
                <Select>
                  <Constraint Text="$page2 == 2"/>
                  <Concat>
                    <Concat>
                      <Literal 
                      Text="ManageAssignments.php: Unable to get the course name - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="6">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAssignments.php: Unable to insert new assignment - " Length="57" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="28">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                      </Concat>
                    </Concat>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="ManageAssignments.php: Unable to update the assignment information - " Length="69" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="52">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                    </Concat>
                    <Concat>
                      <Repeat>
                        <Constraint Text="($_POST[&quot;deleteassignment&quot;] == 1)"/>
                        <Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="DeleteFunctions.php: Unable to delete selected CourseBulletin(s) - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="183">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                        </Concat>
                      </Repeat>
                    </Concat>
                    <Concat>
                      <Literal 
                      Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the assignment wants to delete the assignment(s) //
  function validate()
  {
   if( document.assignments.selectassignment.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this assignment?&quot;);

	if( confirmed == true )
	{
	 document.assignments.submit();
	}
   }
   else
   {
	alert('You must select a assignment to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.assignments.selectassignment.value == 1 )
   {
	document.assignments.submit();
   }
   else
   {
	if( document.assignments.selectassignment.value &gt; 1 )
	{
	 alert('You can only edit one assignment at a time.');
	}
	else
	{
	 alert('You must select a assignment to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row + 2;
   if(document.assignments.elements[row].checked)
   {
	document.assignments.selectassignment.value = Math.round(document.assignments.selectassignment.value) + 1;
   }
   else
   {
	document.assignments.selectassignment.value = Math.round(document.assignments.selectassignment.value) - 1;
   }
  }
 &lt;/script&gt;

 &lt;h1&gt;Manage Assignments&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='assignments' action='./index.php' method='POST'&gt;
  &lt;input type='button' value='Add' onClick='document.assignments.page2.value=4;document.assignments.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.assignments.page2.value=5;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.assignments.deleteassignment.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='6'&gt;&lt;h2&gt;" Length="1881" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="184">
</Literal>
                      <Symbolic Text="mysql_result()"/>
                      <Literal 
                      Text="&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header'&gt;
	&lt;td&gt;&amp;nbsp;&lt;/td&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Assigned Task&lt;/th&gt;
	&lt;th&gt;Possible Points&lt;/th&gt;
	&lt;th&gt;Date Assigned&lt;/th&gt;
	&lt;th&gt;Date Due&lt;/th&gt;
   &lt;/tr&gt;" Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="193">
</Literal>
                    </Concat>
                    <Concat>
                      <Literal 
                      Text="ManageAssignments.php: Unable to retrieve total number of assignments - " Length="72" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="197">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Concat>
                      <Literal 
                      Text="ManageAssignments.php: Unable to get a list of assignments - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="210">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Repeat>
                      <Constraint Text="$page2 == 2"/>
                      <Concat>
                        <Concat>
                          <Concat>
                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="225"/>
                            <Select>
                              <Constraint Text="(($row % 2) == 0)"/>
                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="225"/>
                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="225"/>
                            </Select>
                            <Literal 
                            Text="'&gt;
	  &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="226">
</Literal>
                            <Undef/>
                            <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="226"/>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="232"/>
                            <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="227"/>
                            <Undef/>
                            <Literal 
                            Text="&lt;/td&gt;
	  &lt;td style='text-align: left;'&gt;" Length="39" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="228">
</Literal>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="229"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="230"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="231"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="232"/>
                          </Concat>
                        </Concat>
                      </Concat>
                    </Repeat>
                    <Literal 
                    Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;input type='button' value='Add' onClick='document.assignments.page2.value=4;document.assignments.submit();'&gt;
  &lt;input type='button' value='Edit' onClick='document.assignments.page2.value=5;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.assignments.deleteassignment.value=1;validate();'&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="360" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="243">
</Literal>
                    <Repeat>
                      <Constraint Text="$page2 == 2"/>
                      <Concat>
                        <Select>
                          <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249"/>
                              <Literal 
                              Text=";document.assignments.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="96" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249"/>
                              <Literal 
                              Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249"/>
                              <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="249"/>
                            </Concat>
                          </Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253"/>
                              <Literal 
                              Text=";document.assignments.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="88" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253"/>
                              <Literal 
                              Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253">
</Literal>
                              <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253"/>
                              <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="253"/>
                            </Concat>
                          </Concat>
                        </Select>
                      </Concat>
                    </Repeat>
                    <Concat>
                      <Literal 
                      Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteassignment'&gt;
  &lt;input type='hidden' name='selectassignment'&gt;
  &lt;input type='hidden' name='page2' value='" Length="150" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="260">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="261">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="263">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="' /&gt;
  &lt;input type='hidden' name='page' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="264">
</Literal>
                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="277"/>
                      <Literal 
                      Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageAssignments.php" Line="277">
</Literal>
                    </Concat>
                  </Concat>
                  <Select>
                    <Constraint Text="$page2 == 3"/>
                    <Concat>
                      <Concat>
                        <Select>
                          <Constraint Text="($_POST[&quot;gradeid&quot;] != &quot;&quot;)"/>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="ManageGrades.php: Unable to update the grade - " Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="12">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                          <Concat>
                            <Concat>
                              <Literal 
                              Text="ManageGrades.php: Unable to insert the new grade - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="21">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                          </Concat>
                        </Select>
                      </Concat>
                      <Concat>
                        <Repeat>
                          <Constraint Text="($_POST[&quot;deletegrade&quot;] == 1)"/>
                          <Concat>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="DeleteFunctions.php: Unable to delete selected grade(s) - " Length="58" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DeleteFunctions.php" Line="9">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                            </Concat>
                          </Concat>
                        </Repeat>
                      </Concat>
                      <Literal 
                      Text="&lt;script language='JavaScript'&gt;

  // Function to make sure the student wants to delete the grade(s) //
  function validate()
  {
   if( document.grades.selectgrade.value &gt; 0 )
   {
	var confirmed = confirm(&quot;Are you sure you want to delete this grade?&quot;);

	if( confirmed == true )
	{
	 document.grades.submit();
	}
   }
   else
   {
	alert('You must select a grade to delete.');
   }
  }


  // Function to make sure only one checkbox has been selected //
  function checkboxes()
  {
   if( document.grades.selectgrade.value == 1 )
   {
	document.grades.submit();
   }
   else
   {
	if( document.grades.selectgrade.value &gt; 1 )
	{
	 alert('You can only edit one grade at a time.');
	}
	else
	{
	 alert('You must select a grade to edit.');
	}
   }
  }


  // Function to keep track of how many checkboxes are checked //
  function updateboxes(row)
  {
   row = row;
   if(document.grades.elements[row].checked)
   {
	document.grades.selectgrade.value = Math.round(document.grades.selectgrade.value) + 1;
   }
   else
   {
	document.grades.selectgrade.value = Math.round(document.grades.selectgrade.value) - 1;
   }
  }
 &lt;/script&gt;
 &lt;h1&gt;Grades&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='595' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='grades' action='./index.php' method='POST'&gt;
 &lt;b&gt;Assignment:&lt;/b&gt;
 &lt;select name='assignment' onChange='document.grades.addgrade.value=0;document.grades.deletegrade.value=0;document.grades.submit();'&gt;" Length="1451" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="144">
</Literal>
                      <Concat>
                        <Literal 
                        Text="&lt;/select&gt;
	 &lt;br /&gt;&lt;br /&gt;
  &lt;table width='595' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;&amp;nbsp;&lt;/th&gt;
   &lt;th&gt;Student Name&lt;/th&gt;
   &lt;th&gt;Date Submitted&lt;/th&gt;
   &lt;th&gt;Earned Points&lt;/th&gt;
   &lt;th&gt;Possible Points&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
   &lt;th&gt;Late&lt;/th&gt;
  &lt;/tr&gt;
  &lt;tr class='even'&gt;
   &lt;td colspan='10' align='center'&gt;No assignments have been defined for this class.&lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;" Length="416" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="166">
</Literal>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="ManageGrades.php: Unable to get a list of assignments - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="171">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint Text="$page2 == 3"/>
                        <Concat>
                          <Concat>
                            <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                            <Undef/>
                            <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                            <Select>
                              <Constraint Text="(($_POST[&quot;assignment&quot;] == $assignment[0]) &amp;&amp; ($_POST[&quot;assignment&quot;] != null))"/>
                              <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                            </Select>
                            <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                            <Undef/>
                            <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="183"/>
                          </Concat>
                        </Concat>
                      </Repeat>
                      <Literal 
                      Text="  &lt;/select&gt;
  &lt;br /&gt;&lt;br /&gt;
  &lt;table width='595' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;&amp;nbsp;&lt;/th&gt;
   &lt;th&gt;Student Name&lt;/th&gt;
   &lt;th&gt;Date Submitted&lt;/th&gt;
   &lt;th&gt;Earned Points&lt;/th&gt;
   &lt;th&gt;Possible Points&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
   &lt;th&gt;Late&lt;/th&gt;
  &lt;/tr&gt;" Length="290" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="197">
</Literal>
                      <Concat>
                        <Literal 
                        Text="ManageGrades.php: Unable to get the list of students for this class - " Length="70" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="201">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint Text="$page2 == 3"/>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="ManageGrades.php: Unable to get a list of gradess - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="209">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Concat>
                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="217"/>
                            <Select>
                              <Constraint Text="(($row % 2) == 0)"/>
                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="217"/>
                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="217"/>
                            </Select>
                            <Literal 
                            Text="'&gt;
   &lt;td&gt;&lt;input type='checkbox' name='delete[]' value='" Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="218">
</Literal>
                            <Undef/>
                            <Literal Text="' onClick='updateboxes(" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="218"/>
                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="223"/>
                            <Literal Text=");' /&gt;&lt;/td&gt; &lt;td&gt;" Length="19" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="219"/>
                            <Undef/>
                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="219"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="220"/>
                            <Select>
                              <Constraint Text="(convertfromdb() != &quot;//&quot;)"/>
                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DBFunctions.php" Line="44"/>
                              <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="220"/>
                            </Select>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="221"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="222"/>
                            <Undef/>
                            <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="223"/>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="ManageGrades.php: Unable to get the grade percentages - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="226">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Concat>
                            <Select>
                              <Constraint Text="($letter == -1)"/>
                              <Concat>
                                <Literal Text="Total Not Found" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="239"/>
                              </Concat>
                              <Concat>
                                <Select>
                                  <Constraint Text="($letter &gt;= $percs[0])"/>
                                  <Concat>
                                    <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="241"/>
                                  </Concat>
                                  <Concat>
                                    <Select>
                                      <Constraint Text="($letter &gt;= $percs[1])"/>
                                      <Concat>
                                        <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="243"/>
                                      </Concat>
                                      <Concat>
                                        <Select>
                                          <Constraint Text="($letter &gt;= $percs[2])"/>
                                          <Concat>
                                            <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="245"/>
                                          </Concat>
                                          <Concat>
                                            <Select>
                                              <Constraint Text="($letter &gt;= $percs[3])"/>
                                              <Concat>
                                                <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="247"/>
                                              </Concat>
                                              <Concat>
                                                <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="249"/>
                                              </Concat>
                                            </Select>
                                          </Concat>
                                        </Select>
                                      </Concat>
                                    </Select>
                                  </Concat>
                                </Select>
                              </Concat>
                            </Select>
                          </Concat>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="253"/>
                          <Select>
                            <Constraint Text="($grade[4] == 1)"/>
                            <Concat>
                              <Literal Text="Yes" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="256"/>
                            </Concat>
                            <Concat>
                              <Concat>
                                <Literal Text="No" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="258"/>
                              </Concat>
                            </Concat>
                          </Select>
                          <Literal Text=" &lt;/td&gt; &lt;/tr&gt; " Length="20" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="263"/>
                        </Concat>
                      </Repeat>
                      <Concat>
                        <Literal 
                        Text="&lt;tr class='even'&gt;&lt;td colspan='9'&gt;&lt;center&gt;There are currently no students registered for this class.&lt;/center&gt;&lt;/td&gt;&lt;/tr&gt;" Length="118" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="267">
</Literal>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value='Edit' onClick='document.grades.page2.value=7;checkboxes();'&gt;
  &lt;input type='button' value='Delete' onClick='document.grades.deletegrade.value=1;validate();'&gt;

  &lt;input type='hidden' name='addgrade' /&gt;
  &lt;input type='hidden' name='deletegrade' /&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="345" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="277">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="' /&gt;
  &lt;input type='hidden' name='selectgrade' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="279">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="281">
</Literal>
                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="293"/>
                        <Literal 
                        Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ManageGrades.php" Line="293">
</Literal>
                      </Concat>
                    </Concat>
                    <Select>
                      <Constraint Text="$page2 == 4"/>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="&lt;h1&gt;Add New Assignment&lt;/h1&gt;

  &lt;form name='addassignment' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='700'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Assigned Task&lt;/th&gt;
	&lt;th&gt;Total Points&lt;/th&gt;
	&lt;th&gt;Date Assigned&lt;/th&gt;
	&lt;th&gt;Date Due&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;textarea name='task'&gt;&lt;/textarea&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='total' maxlength='6' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='assigneddate' maxlength='10' size='15' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='duedate' maxlength='10' size='15' /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='700'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Add Assignment' onClick='document.addassignment.addassignment.value=1;document.addassignment.page2.value=2;document.addassignment.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.addassignment.page2.value=2;document.addassignment.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='addassignment' value=''&gt;
  &lt;input type='hidden' name='page2' value='" Length="1224" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAssignment.php" Line="33">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAssignment.php" Line="35">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="' /&gt;
  &lt;input type='hidden' name='page' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAssignment.php" Line="36">
</Literal>
                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAssignment.php" Line="47"/>
                          <Literal 
                          Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/AddAssignment.php" Line="47">
</Literal>
                        </Concat>
                      </Concat>
                      <Select>
                        <Constraint Text="$page2 == 5"/>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="EditAssignment.php: Unable to retrieve the information about the assignment to edit - " Length="86" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="6">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="&lt;h1&gt;Edit Assignment&lt;/h1&gt;

  &lt;form name='editassignment' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='700'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Assigned Task&lt;/th&gt;
	&lt;th&gt;Total Points&lt;/th&gt;
	&lt;th&gt;Date Assigned&lt;/th&gt;
	&lt;th&gt;Date Due&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;&lt;input type='text' name='title' maxlength='15' size='15' value='" Length="437" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="23">
</Literal>
                            <Undef/>
                            <Literal 
                            Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;textarea name='task'&gt;" Length="37" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="24">
</Literal>
                            <Undef/>
                            <Literal 
                            Text="&lt;/textarea&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='total' maxlength='6' size='15' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="25">
</Literal>
                            <Symbolic Text="number_format()"/>
                            <Literal 
                            Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='assigneddate' maxlength='10' size='15' value='" Length="86" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="26">
</Literal>
                            <Symbolic Text="convertfromdb()"/>
                            <Literal 
                            Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='duedate' maxlength='10' size='15' value='" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="27">
</Literal>
                            <Symbolic Text="convertfromdb()"/>
                            <Literal 
                            Text="' /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='700'&gt;
   &lt;tr&gt;
   &lt;td&gt;
	&lt;input type='button' value='Edit Assignment' onClick='document.editassignment.editassignment.value=1;document.editassignment.page2.value=2;document.editassignment.submit();'&gt;
	&lt;input type='button' value='Cancel' onClick='document.editassignment.page2.value=2;document.editassignment.submit();'&gt;
   &lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editassignment'&gt;
  &lt;input type='hidden' name='assignmentid' value='" Length="545" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="43">
</Literal>
                            <Undef/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='page2' value='" Length="46" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="44">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="45">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="' /&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='wastotal' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="47">
</Literal>
                            <Undef/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='wasdate' value='" Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="48">
</Literal>
                            <Undef/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='page' value='" Length="45" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="49">
</Literal>
                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="60"/>
                            <Literal 
                            Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditAssignment.php" Line="60">
</Literal>
                          </Concat>
                        </Concat>
                        <Select>
                          <Constraint Text="$page2 == 6"/>
                          <Concat> </Concat>
                          <Select>
                            <Constraint Text="$page2 == 7"/>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="EditGrade.php: Unable to retrieve the information about the grade - " Length="68" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="5">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Literal 
                              Text="&lt;h1&gt;Edit Grade&lt;/h1&gt;

  &lt;form name='editgrade' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' cellpadding='5' class='dynamiclist' align='center' width='500'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Student Name&lt;/th&gt;
	&lt;th&gt;Date Submitted&lt;/th&gt;
	&lt;th&gt;Earned Points&lt;/th&gt;
	&lt;th&gt;Comment&lt;/th&gt;
	&lt;th&gt;Late&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even' valign='top'&gt;
	&lt;td&gt;" Length="362" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="22">
</Literal>
                              <Concat>
                                <Literal 
                                Text="EditGrade.php: Unable to get the student's name - " Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="25">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Concat>
                                <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="29"/>
                                <Undef/>
                                <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="29"/>
                                <Undef/>
                              </Concat>
                              <Concat>
                                <Literal 
                                Text="	&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='gradedate' maxlength='10' size='10' value='" Length="80" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="32">
</Literal>
                                <Select>
                                  <Constraint Text="(convertfromdb() != &quot;//&quot;)"/>
                                  <Symbolic Text="convertfromdb()"/>
                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="32"/>
                                </Select>
                                <Literal 
                                Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='text' name='points' maxlength='5' size='5' value='" Length="78" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="33">
</Literal>
                                <Symbolic Text="number_format()"/>
                                <Literal 
                                Text="' /&gt;&lt;/td&gt;
	&lt;td&gt;&lt;textarea cols='20' rows='3' name='comment'&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="34">
</Literal>
                                <Undef/>
                                <Literal 
                                Text="&lt;/textarea&gt;&lt;/td&gt;
	&lt;td&gt;&lt;input type='checkbox' name='late' value='1'" Length="66" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="35">
</Literal>
                                <Select>
                                  <Constraint Text="($grade[3] == 1)"/>
                                  <Literal Text="CHECKED" Length="7" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="35"/>
                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="35"/>
                                </Select>
                                <Literal 
                                Text=" /&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

   &lt;br&gt;

   &lt;table cellpadding='0' border='0' align='center' width='500'&gt;
   &lt;tr&gt;
   &lt;td&gt;&lt;input type='button' value='Edit Grade' onClick='document.editgrade.editgrade.value=1;document.editgrade.page2.value=3;document.editgrade.submit();'&gt; &lt;input type='button' value='Cancel' onClick='document.editgrade.page2.value=3;document.editgrade.submit();'&gt;&lt;/td&gt;
   &lt;/tr&gt;
   &lt;/table&gt;

  &lt;input type='hidden' name='editgrade' value=''&gt;
  &lt;input type='hidden' name='gradeid' value='" Length="501" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="48">
</Literal>
                                <Undef/>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='wasgrade' value='" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="49">
</Literal>
                                <Symbolic Text="number_format()"/>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='wasdate' value='" Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="50">
</Literal>
                                <Select>
                                  <Constraint Text="(convertfromdb() != &quot;//&quot;)"/>
                                  <Symbolic Text="convertfromdb()"/>
                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="50"/>
                                </Select>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='student' value='" Length="50" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="51">
</Literal>
                                <Undef/>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='assignment' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="52">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="53">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="54">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="'&gt;
  &lt;input type='hidden' name='date'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="119" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="57">
</Literal>
                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="68"/>
                                <Literal 
                                Text="'&gt;

 &lt;/form&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
	&amp;nbsp;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="145" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/EditGrade.php" Line="68">
</Literal>
                              </Concat>
                            </Concat>
                            <Select>
                              <Constraint Text="$page2 == 8"/>
                              <Concat>
                                <Literal 
                                Text="
 &lt;h1&gt;Students&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;" Length="174" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="8">
</Literal>
                                <Concat>
                                  <Literal 
                                  Text="ViewStudents.php: Unable to get a list of students - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="12">
</Literal>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Literal 
                                Text="
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' class='dynamiclist'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Student&lt;/th&gt;
   &lt;th&gt;Current Points&lt;/th&gt;
   &lt;th&gt;Possible Points&lt;/th&gt;
   &lt;th&gt;Percent&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
  &lt;/tr&gt;" Length="212" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="33">
</Literal>
                                <Concat>
                                  <Literal 
                                  Text="ViewStudents.php: Unable to get the list of classes this student is registered for - " Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="37">
</Literal>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Repeat>
                                  <Constraint Text="$page2 == 8"/>
                                  <Concat>
                                    <Concat>
                                      <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="74"/>
                                      <Select>
                                        <Constraint Text="(($row % 2) == 0)"/>
                                        <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="74"/>
                                        <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="74"/>
                                      </Select>
                                      <Literal Text="'&gt; &lt;td align='left'&gt;" Length="23" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="75"/>
                                      <Undef/>
                                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="75"/>
                                      <Undef/>
                                      <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="76"/>
                                      <Undef/>
                                      <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="77"/>
                                      <Undef/>
                                      <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="78"/>
                                      <Symbolic Text="number_format()"/>
                                      <Literal Text="&amp;#37;&lt;/td&gt; &lt;td&gt;" Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="79"/>
                                      <Select>
                                        <Constraint Text="($currperc &gt;= ($classes[1] / 100))"/>
                                        <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="56"/>
                                        <Select>
                                          <Constraint Text="($currperc &gt;= ($classes[2] / 100))"/>
                                          <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="60"/>
                                          <Select>
                                            <Constraint Text="($currperc &gt;= ($classes[3] / 100))"/>
                                            <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="64"/>
                                            <Select>
                                              <Constraint Text="($currperc &gt;= ($classes[4] / 100))"/>
                                              <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="68"/>
                                              <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="72"/>
                                            </Select>
                                          </Select>
                                        </Select>
                                      </Select>
                                      <Literal Text="&lt;/td&gt; " Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="80"/>
                                    </Concat>
                                  </Concat>
                                </Repeat>
                                <Concat>
                                  <Literal 
                                  Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='button' value=' Back ' onClick='document.classes.page2.value=2;document.classes.submit();'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="176" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="86">
</Literal>
                                  <Symbolic Text="$_POST"/>
                                  <Literal 
                                  Text="' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="48" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="87">
</Literal>
                                  <Symbolic Text="$_POST"/>
                                  <Literal 
                                  Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="89">
</Literal>
                                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="101"/>
                                  <Literal 
                                  Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewStudents.php" Line="101">
</Literal>
                                </Concat>
                              </Concat>
                              <Select>
                                <Constraint Text="$page2 == 9"/>
                                <Concat>
                                  <Literal 
                                  Text="&lt;h1&gt;View Announcements&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='announcements' action='./index.php' method='POST'&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
	&lt;th&gt;Date&lt;/th&gt;
   &lt;/tr&gt;" Length="347" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="15">
</Literal>
                                  <Concat>
                                    <Literal 
                                    Text="ViewAnnouncements.php: Unable to retrieve total number of announcements - " Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="19">
</Literal>
                                    <Symbolic Text="mysql_error()"/>
                                  </Concat>
                                  <Concat>
                                    <Literal 
                                    Text="ViewAnnouncements.php: Unable to retrieve the announcements - " Length="62" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="32">
</Literal>
                                    <Symbolic Text="mysql_error()"/>
                                  </Concat>
                                  <Repeat>
                                    <Constraint Text="$page2 == 9"/>
                                    <Concat>
                                      <Concat>
                                        <Concat>
                                          <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                          <Select>
                                            <Constraint Text="(($row % 2) == 0)"/>
                                            <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                            <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                          </Select>
                                          <Literal Text="'&gt; &lt;td&gt;&lt;b&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="43"/>
                                          <Undef/>
                                          <Literal 
                                          Text="&lt;/b&gt;&lt;/td&gt;
	  &lt;td class='announcement'&gt;" Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="44">
</Literal>
                                          <Undef/>
                                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="45"/>
                                          <Symbolic Text="convertfromdb()"/>
                                          <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="46"/>
                                        </Concat>
                                      </Concat>
                                    </Concat>
                                  </Repeat>
                                  <Literal 
                                  Text=" &lt;/table&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="53">
</Literal>
                                  <Repeat>
                                    <Constraint Text="$page2 == 9"/>
                                    <Concat>
                                      <Select>
                                        <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                            <Literal 
                                            Text=";document.announcements.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="98" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                            <Literal 
                                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                          </Concat>
                                        </Concat>
                                        <Concat>
                                          <Concat>
                                            <Literal 
                                            Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                            <Literal 
                                            Text=";document.announcements.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                            <Literal 
                                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                            <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                            <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                          </Concat>
                                        </Concat>
                                      </Select>
                                    </Concat>
                                  </Repeat>
                                  <Concat>
                                    <Literal 
                                    Text="
&lt;/center&gt;
  &lt;input type='hidden' name='page2' value='" Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="68">
</Literal>
                                    <Symbolic Text="$_POST"/>
                                    <Literal 
                                    Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="69">
</Literal>
                                    <Symbolic Text="$_POST"/>
                                    <Literal 
                                    Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="71">
</Literal>
                                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84"/>
                                    <Literal 
                                    Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84">
</Literal>
                                  </Concat>
                                </Concat>
                                <Concat>
                                  <Literal Text="teacherMain.php: Invalid Page" Length="29" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="140"/>
                                </Concat>
                              </Select>
                            </Select>
                          </Select>
                        </Select>
                      </Select>
                    </Select>
                  </Select>
                </Select>
              </Select>
            </Select>
            <Literal 
            Text=" 	 &lt;/td&gt;
	&lt;/tr&gt;
   &lt;/table&gt;

  &lt;/td&gt;" Length="36" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/TeacherMain.php" Line="148">
</Literal>
          </Concat>
          <Select>
            <Constraint Text="$page == 3"/>
            <Concat> </Concat>
            <Select>
              <Constraint Text="$page == 4"/>
              <Concat>
                <Concat>
                  <Literal Text="Invalid User!" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="5"/>
                </Concat>
                <Literal 
                Text="&lt;script language='JavaScript'&gt;

  function classes()
  {
	document.student.page2.value=0;
	document.student.submit();
  }

  function settings()
  {
	document.student.page2.value=1;
	document.student.submit();
  }

  function assignments()
  {
	document.student.page2.value=2;
	document.student.submit();
  }

  function grades()
  {
   document.student.page2.value=3;
   document.student.submit();
  }

  function announcements()
  {
	document.student.page2.value=4;
	document.student.submit();
  }

  function logoutstudent()
  {
	document.student.logout.value=1;
	document.student.submit();
  }
 &lt;/script&gt;

 &lt;body&gt;" Length="617" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="49">
</Literal>
                <Concat>
                  <Literal 
                  Text="&lt;table cellpadding=0 cellspacing=0 border=0 width='100%' height='80'&gt;
 &lt;tr&gt;
 &lt;td class='b' width='300'&gt;
  &lt;img src='./images/title.gif' height='75' width='300' /&gt;
 &lt;/td&gt;
 &lt;td class='b'&gt;
  &lt;table cellpadding=0 cellspacing=0 border=0 width='80%'&gt;
  &lt;tr&gt;
  &lt;td class='b'&gt;
   &lt;div class='yellowtext' align='center'&gt;" Length="311" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="11">
</Literal>
                  <Symbolic Text="htmlspecialchars()"/>
                  <Literal 
                  Text="&lt;/div&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;

 &lt;table width='100%' height='88%' border=0 cellspacing=0 cellpadding=0 align='center'&gt;
 &lt;tr&gt;
  &lt;td class='b' width='130' height=10&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height=10 background='./images/top.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;

" Length="423" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="27">
</Literal>
                </Concat>
                <Literal 
                Text="
 &lt;tr&gt;
  &lt;td class='b' width=130 valign='top'&gt;
   &lt;br&gt;
   &lt;form name='student' action='./index.php' method='POST'&gt;

   &lt;a class='menu' href='javascript: classes();' onMouseover=&quot;window.status='View Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Classes&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;" Length="289" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="60">
</Literal>
                <Concat>
                  <Literal 
                  Text="
	 &lt;a class='menu' href='javascript: settings();' onMouseover=&quot;window.status='View Class Settings'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Settings&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: assignments();' onMouseover=&quot;window.status='View Assignments'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Assignments&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: grades();' onMouseover=&quot;window.status='View Grades'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Grades&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: announcements();' onMouseover=&quot;window.status='View Announcements'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Announcements&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;" Length="724" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="72">
</Literal>
                </Concat>
                <Concat>
                  <Literal 
                  Text="   &lt;a class='menu' href='javascript: logoutstudent();' onMouseover=&quot;window.status='Log Out';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;Log Out&lt;/a&gt;

   &lt;input type='hidden' name='page2' value='" Length="206" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="77">
</Literal>
                  <Symbolic Text="$_POST"/>
                  <Literal 
                  Text="'&gt;
   &lt;input type='hidden' name='logout'&gt;
   &lt;input type='hidden' name='page' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="79">
</Literal>
                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="87"/>
                  <Literal 
                  Text="'&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="80">
</Literal>
                  <Symbolic Text="$_POST"/>
                  <Literal 
                  Text="' /&gt;
 &lt;/form&gt;
  &lt;/td&gt;
  &lt;td class='b' width='10' background='./images/left.gif'&gt;&lt;div style='letter-spacing: 1pt;'&gt;&amp;nbsp;&lt;/div&gt;&lt;/td&gt;
  &lt;td class='w' valign='top'&gt;
   &lt;table border=0 cellspacing=0 cellpadding=10 width='100%' height='100%'&gt;
	&lt;tr&gt;
	 &lt;td valign='top'&gt;" Length="263" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="87">
</Literal>
                </Concat>
                <Select>
                  <Constraint Text="$page2 == 0"/>
                  <Concat>
                    <Concat>
                      <Literal 
                      Text="ViewCourses.php: Unable to get the studentid - " Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="2">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Concat>
                      <Literal Text=" &lt;h1&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="5"/>
                      <Undef/>
                      <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="5"/>
                      <Undef/>
                      <Literal 
                      Text="'s Classes&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='300' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;
" Length="257" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="12">
</Literal>
                    </Concat>
                    <Concat>
                      <Literal 
                      Text="ViewCourses.php: Unable to get a list of semesters for drop-down - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="15">
</Literal>
                      <Symbolic Text="mysql_error()"/>
                    </Concat>
                    <Repeat>
                      <Constraint Text="$page2 == 0"/>
                      <Concat>
                        <Concat>
                          <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                          <Undef/>
                          <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                          <Select>
                            <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                            <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                            <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                          </Select>
                          <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                          <Undef/>
                          <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="28"/>
                        </Concat>
                      </Concat>
                    </Repeat>
                    <Literal 
                    Text="
	 &lt;/select&gt;
	 &lt;br&gt;&lt;br&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='300' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
   &lt;/tr&gt;
   " Length="196" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="43">
</Literal>
                    <Concat>
                      <Concat>
                        <Literal 
                        Text="ViewCourses.php: Unable to get a list of classes - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="48">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                        <Concat>
                          <Repeat>
                            <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                            <Concat>
                              <Concat>
                                <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="57"/>
                                <Select>
                                  <Constraint Text="(($row % 2) == 0)"/>
                                  <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="57"/>
                                  <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="57"/>
                                </Select>
                                <Literal 
                                Text="'&gt;
   &lt;td&gt;&lt;a class='items' href=&quot;JavaScript:document.classes.selectclass.value=" Length="79" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="58">
</Literal>
                                <Undef/>
                                <Literal 
                                Text=";document.classes.page2.value=1;document.classes.submit();&quot; onMouseover=&quot;window.status='View Information For " Length="109" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="58">
</Literal>
                                <Undef/>
                                <Literal 
                                Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="58">
</Literal>
                                <Undef/>
                                <Literal Text="&lt;/a&gt;&lt;/td&gt; &lt;/tr&gt;" Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="59"/>
                              </Concat>
                            </Concat>
                          </Repeat>
                        </Concat>
                      </Repeat>
                    </Concat>
                    <Concat>
                      <Literal 
                      Text="&lt;/table&gt;
   &lt;input type='hidden' name='page2' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="64">
</Literal>
                      <Symbolic Text="$_POST"/>
                      <Literal 
                      Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="66">
</Literal>
                      <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="80"/>
                      <Literal 
                      Text="' /&gt;
   &lt;input type='hidden' name='selectclass' /&gt;
   &lt;input type='hidden' name='studentid' value='" Length="99" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="68">
</Literal>
                      <Undef/>
                      <Literal 
                      Text="' /&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;/form&gt;

  &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;" Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentViewCourses.php" Line="80">
</Literal>
                    </Concat>
                  </Concat>
                  <Select>
                    <Constraint Text="$page2 == 1"/>
                    <Concat>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ClassSettings.php: Unable to update the grading scale - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="6">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="ClassInfo.php: Unable to get the class information - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="9">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text=" &lt;h1&gt;Class Settings&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;table align='center' width='500' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='500' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='5' align='center'&gt;&lt;h2&gt;" Length="332" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="20">
</Literal>
                        <Undef/>
                        <Literal 
                        Text="&lt;/h2&gt;&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header' align='center'&gt;
	&lt;th&gt;A Percent&lt;/th&gt;
	&lt;th&gt;B Percent&lt;/th&gt;
	&lt;th&gt;C Percent&lt;/th&gt;
	&lt;th&gt;D Percent&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;" Length="173" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="29">
</Literal>
                        <Undef/>
                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="30"/>
                        <Undef/>
                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="31"/>
                        <Undef/>
                        <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="32"/>
                        <Undef/>
                        <Literal Text="&lt;/td&gt; &lt;/tr&gt; " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="34"/>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="&lt;/table&gt;
   &lt;input type='hidden' name='page2' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="37">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="39">
</Literal>
                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="46"/>
                        <Literal 
                        Text="' /&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="40">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="' /&gt;
   &lt;input type='hidden' name='update' /&gt;
   &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="46">
</Literal>
                      </Concat>
                    </Concat>
                    <Select>
                      <Constraint Text="$page2 == 2"/>
                      <Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAssignments.php: Unable to get the course name - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="6">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="
 &lt;h1&gt;View Assignments&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='assignments' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='6'&gt;&lt;h2&gt;" Length="320" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="19">
</Literal>
                          <Symbolic Text="mysql_result()"/>
                          <Literal 
                          Text="&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header'&gt;
	&lt;th width='120' align='left' style='padding-left: 20px;'&gt;Title&lt;/th&gt;
	&lt;th&gt;Assigned Task&lt;/th&gt;
	&lt;th&gt;Possible Points&lt;/th&gt;
	&lt;th&gt;Date Assigned&lt;/th&gt;
	&lt;th&gt;Date Due&lt;/th&gt;
   &lt;/tr&gt;" Length="208" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="27">
</Literal>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAssignments.php: Unable to retrieve total number of assignments - " Length="72" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="31">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="ManageAssignments.php: Unable to get a list of assignments - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="44">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Repeat>
                          <Constraint Text="$page2 == 2"/>
                          <Concat>
                            <Concat>
                              <Concat>
                                <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                <Select>
                                  <Constraint Text="(($row % 2) == 0)"/>
                                  <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                  <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                </Select>
                                <Literal 
                                Text="'&gt;
	  &lt;td align='left' style='padding-left: 20px;'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="60">
</Literal>
                                <Undef/>
                                <Literal 
                                Text="&lt;/td&gt;
	  &lt;td style='text-align: left;'&gt;" Length="39" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="61">
</Literal>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="62"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="63"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="64"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="65"/>
                              </Concat>
                            </Concat>
                          </Concat>
                        </Repeat>
                        <Literal 
                        Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;center&gt;Page: " Length="33" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="71">
</Literal>
                        <Repeat>
                          <Constraint Text="$page2 == 2"/>
                          <Concat>
                            <Select>
                              <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                  <Literal 
                                  Text=";document.assignments.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="96" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                  <Literal 
                                  Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                  <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                </Concat>
                              </Concat>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                  <Literal 
                                  Text=";document.assignments.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="88" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                  <Literal 
                                  Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                  <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                  <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                </Concat>
                              </Concat>
                            </Select>
                          </Concat>
                        </Repeat>
                        <Concat>
                          <Literal 
                          Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteassignment'&gt;
  &lt;input type='hidden' name='selectassignment'&gt;
  &lt;input type='hidden' name='page2' value='" Length="150" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="88">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="89">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="91">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="' /&gt;
  &lt;input type='hidden' name='page' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="92">
</Literal>
                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="105"/>
                          <Literal 
                          Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="105">
</Literal>
                        </Concat>
                      </Concat>
                      <Select>
                        <Constraint Text="$page2 == 3"/>
                        <Concat>
                          <Literal 
                          Text="
 &lt;h1&gt;View Grades&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='grades' action='./index.php' method='POST'&gt;
  &lt;br /&gt;
  &lt;table width='595' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Assignment Name&lt;/th&gt;
   &lt;th&gt;Date Submitted&lt;/th&gt;
   &lt;th&gt;Earned Points&lt;/th&gt;
   &lt;th&gt;Possible Points&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
   &lt;th&gt;Comment&lt;/th&gt;
   &lt;th&gt;Late&lt;/th&gt;
  &lt;/tr&gt;" Length="453" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="29">
</Literal>
                          <Concat>
                            <Literal 
                            Text="ViewGrades.php: Unable to get the list of assignments for this class - " Length="71" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="33">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Repeat>
                            <Constraint Text="$page2 == 3"/>
                            <Concat>
                              <Concat>
                                <Literal 
                                Text="ManageGrades.php: Unable to get a list of gradess - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="41">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Concat>
                                <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                <Select>
                                  <Constraint Text="(($row % 2) == 0)"/>
                                  <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                  <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                </Select>
                                <Literal Text="'&gt; &lt;td&gt;" Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="50"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="51"/>
                                <Select>
                                  <Constraint Text="(convertfromdb() != &quot;//&quot;)"/>
                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DBFunctions.php" Line="44"/>
                                  <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="51"/>
                                </Select>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="52"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="53"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="54"/>
                              </Concat>
                              <Concat>
                                <Literal 
                                Text="ManageGrades.php: Unable to get the grade percentages - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="57">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Concat>
                                <Select>
                                  <Constraint Text="($letter == -1)"/>
                                  <Concat>
                                    <Literal Text="Total Not Found" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="70"/>
                                  </Concat>
                                  <Concat>
                                    <Select>
                                      <Constraint Text="($letter &gt;= $percs[0])"/>
                                      <Concat>
                                        <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="72"/>
                                      </Concat>
                                      <Concat>
                                        <Select>
                                          <Constraint Text="($letter &gt;= $percs[1])"/>
                                          <Concat>
                                            <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="74"/>
                                          </Concat>
                                          <Concat>
                                            <Select>
                                              <Constraint Text="($letter &gt;= $percs[2])"/>
                                              <Concat>
                                                <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="76"/>
                                              </Concat>
                                              <Concat>
                                                <Select>
                                                  <Constraint Text="($letter &gt;= $percs[3])"/>
                                                  <Concat>
                                                    <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="78"/>
                                                  </Concat>
                                                  <Concat>
                                                    <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="80"/>
                                                  </Concat>
                                                </Select>
                                              </Concat>
                                            </Select>
                                          </Concat>
                                        </Select>
                                      </Concat>
                                    </Select>
                                  </Concat>
                                </Select>
                              </Concat>
                              <Concat>
                                <Literal Text="&lt;/td&gt; &lt;td align='left'&gt;" Length="24" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="84"/>
                                <Undef/>
                                <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="85"/>
                              </Concat>
                              <Select>
                                <Constraint Text="($grade[3] == 1)"/>
                                <Concat>
                                  <Literal Text="Yes" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="88"/>
                                </Concat>
                                <Concat>
                                  <Concat>
                                    <Literal Text="No" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="90"/>
                                  </Concat>
                                </Concat>
                              </Select>
                              <Literal Text=" &lt;/td&gt; &lt;/tr&gt; " Length="20" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="95"/>
                            </Concat>
                          </Repeat>
                          <Concat>
                            <Literal 
                            Text="&lt;tr class='even'&gt;&lt;td colspan='9'&gt;&lt;center&gt;There are currently no students registered for this class.&lt;/center&gt;&lt;/td&gt;&lt;/tr&gt;" Length="118" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="99">
</Literal>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="  &lt;/table&gt;
  &lt;br /&gt;

  &lt;input type='hidden' name='addgrade' /&gt;
  &lt;input type='hidden' name='deletegrade' /&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="157" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="107">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="' /&gt;
  &lt;input type='hidden' name='selectgrade' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="109">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="111">
</Literal>
                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="123"/>
                            <Literal 
                            Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="123">
</Literal>
                          </Concat>
                        </Concat>
                        <Select>
                          <Constraint Text="$page2 == 4"/>
                          <Concat>
                            <Literal 
                            Text="&lt;h1&gt;View Announcements&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='announcements' action='./index.php' method='POST'&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
	&lt;th&gt;Date&lt;/th&gt;
   &lt;/tr&gt;" Length="347" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="15">
</Literal>
                            <Concat>
                              <Literal 
                              Text="ViewAnnouncements.php: Unable to retrieve total number of announcements - " Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="19">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                            <Concat>
                              <Literal 
                              Text="ViewAnnouncements.php: Unable to retrieve the announcements - " Length="62" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="32">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                            <Repeat>
                              <Constraint Text="$page2 == 4"/>
                              <Concat>
                                <Concat>
                                  <Concat>
                                    <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                    <Select>
                                      <Constraint Text="(($row % 2) == 0)"/>
                                      <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                      <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                    </Select>
                                    <Literal Text="'&gt; &lt;td&gt;&lt;b&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="43"/>
                                    <Undef/>
                                    <Literal 
                                    Text="&lt;/b&gt;&lt;/td&gt;
	  &lt;td class='announcement'&gt;" Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="44">
</Literal>
                                    <Undef/>
                                    <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="45"/>
                                    <Symbolic Text="convertfromdb()"/>
                                    <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="46"/>
                                  </Concat>
                                </Concat>
                              </Concat>
                            </Repeat>
                            <Literal 
                            Text=" &lt;/table&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="53">
</Literal>
                            <Repeat>
                              <Constraint Text="$page2 == 4"/>
                              <Concat>
                                <Select>
                                  <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                  <Concat>
                                    <Concat>
                                      <Literal 
                                      Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                      <Literal 
                                      Text=";document.announcements.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="98" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                      <Literal 
                                      Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                      <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                    </Concat>
                                  </Concat>
                                  <Concat>
                                    <Concat>
                                      <Literal 
                                      Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                      <Literal 
                                      Text=";document.announcements.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                      <Literal 
                                      Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                      <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                      <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                    </Concat>
                                  </Concat>
                                </Select>
                              </Concat>
                            </Repeat>
                            <Concat>
                              <Literal 
                              Text="
&lt;/center&gt;
  &lt;input type='hidden' name='page2' value='" Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="68">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="69">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="71">
</Literal>
                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84"/>
                              <Literal 
                              Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84">
</Literal>
                            </Concat>
                          </Concat>
                          <Concat>
                            <Literal Text="StudentMain.php: Invalid Page" Length="29" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="112"/>
                          </Concat>
                        </Select>
                      </Select>
                    </Select>
                  </Select>
                </Select>
                <Literal 
                Text="      &lt;/td&gt;
	&lt;/tr&gt;
   &lt;/table&gt;

  &lt;/td&gt;" Length="39" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/StudentMain.php" Line="120">
</Literal>
              </Concat>
              <Select>
                <Constraint Text="$page == 5"/>
                <Concat>
                  <Concat>
                    <Literal Text="Invalid User!" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="5"/>
                  </Concat>
                  <Literal 
                  Text="&lt;script language='JavaScript'&gt;

  function students()
  {
	document.student.page2.value=0;
	document.student.submit();
  }

  function settings()
  {
	document.student.page2.value=1;
	document.student.submit();
  }

  function assignments()
  {
	document.student.page2.value=2;
	document.student.submit();
  }

  function grades()
  {
   document.student.page2.value=3;
   document.student.submit();
  }

  function announcements()
  {
	document.student.page2.value=4;
	document.student.submit();
  }

  function classes()
  {
	document.student.page2.value=5;
	document.student.submit();
  }

  function logoutstudent()
  {
	document.student.logout.value=1;
	document.student.submit();
  }
 &lt;/script&gt;

 &lt;body&gt;" Length="709" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="55">
</Literal>
                  <Concat>
                    <Literal 
                    Text="&lt;table cellpadding=0 cellspacing=0 border=0 width='100%' height='80'&gt;
 &lt;tr&gt;
 &lt;td class='b' width='300'&gt;
  &lt;img src='./images/title.gif' height='75' width='300' /&gt;
 &lt;/td&gt;
 &lt;td class='b'&gt;
  &lt;table cellpadding=0 cellspacing=0 border=0 width='80%'&gt;
  &lt;tr&gt;
  &lt;td class='b'&gt;
   &lt;div class='yellowtext' align='center'&gt;" Length="311" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="11">
</Literal>
                    <Symbolic Text="htmlspecialchars()"/>
                    <Literal 
                    Text="&lt;/div&gt;
  &lt;/td&gt;
  &lt;/tr&gt;
  &lt;/table&gt;
 &lt;/td&gt;
&lt;/tr&gt;
&lt;/table&gt;

 &lt;table width='100%' height='88%' border=0 cellspacing=0 cellpadding=0 align='center'&gt;
 &lt;tr&gt;
  &lt;td class='b' width='130' height=10&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height=10 background='./images/top.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10 background='./images/topright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;

" Length="423" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/maketop.php" Line="27">
</Literal>
                  </Concat>
                  <Literal 
                  Text="
 &lt;tr&gt;
  &lt;td class='b' width=130 valign='top'&gt;
   &lt;br&gt;
   &lt;form name='student' action='./index.php' method='POST'&gt;

   &lt;a class='menu' href='javascript: students();' onMouseover=&quot;window.status='View Students'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Students&lt;/a&gt;
   &lt;br&gt;&lt;br&gt;" Length="292" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="66">
</Literal>
                  <Concat>
                    <Select>
                      <Constraint Text="(($_POST[&quot;selectclass&quot;] != &quot;&quot;) &amp;&amp; ($page2 != 5))"/>
                      <Concat>
                        <Literal 
                        Text="
	 &lt;a class='menu' href='javascript: classes();' onMouseover=&quot;window.status='View Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Classes&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: settings();' onMouseover=&quot;window.status='View Class Settings'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Settings&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: assignments();' onMouseover=&quot;window.status='View Assignments'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Assignments&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: grades();' onMouseover=&quot;window.status='View Grades'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Grades&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 &lt;a class='menu' href='javascript: announcements();' onMouseover=&quot;window.status='View Announcements'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Announcements&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;" Length="896" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="82">
</Literal>
                      </Concat>
                      <Concat>
                        <Literal 
                        Text="
	 &lt;a class='menu' href='javascript: classes();' onMouseover=&quot;window.status='View Classes'; return true;&quot; onMouseout=&quot;window.status=''; return true;&quot;&gt;Classes&lt;/a&gt;
	 &lt;br&gt;&lt;br&gt;
	 " Length="175" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="89">
</Literal>
                      </Concat>
                    </Select>
                  </Concat>
                  <Concat>
                    <Literal 
                    Text="   &lt;a class='menu' href='javascript: logoutstudent();' onMouseover=&quot;window.status='Log Out';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;Log Out&lt;/a&gt;

   &lt;input type='hidden' name='page2' value='" Length="206" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="95">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="'&gt;
   &lt;input type='hidden' name='logout'&gt;
   &lt;input type='hidden' name='page' value='" Length="85" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="97">
</Literal>
                    <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="106"/>
                    <Literal 
                    Text="'&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="98">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="' /&gt;
   &lt;input type='hidden' name='student' value='" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="99">
</Literal>
                    <Symbolic Text="$_POST"/>
                    <Literal 
                    Text="' /&gt;
 &lt;/form&gt;
  &lt;/td&gt;
  &lt;td class='b' width='10' background='./images/left.gif'&gt;&lt;div style='letter-spacing: 1pt;'&gt;&amp;nbsp;&lt;/div&gt;&lt;/td&gt;
  &lt;td class='w' valign='top'&gt;
   &lt;table border=0 cellspacing=0 cellpadding=10 width='100%' height='100%'&gt;
	&lt;tr&gt;
	 &lt;td valign='top'&gt;" Length="263" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="106">
</Literal>
                  </Concat>
                  <Select>
                    <Constraint Text="$page2 == 0"/>
                    <Concat>
                      <Concat>
                        <Literal Text=" &lt;h1&gt;Students of " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="7"/>
                        <Undef/>
                        <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="7"/>
                        <Undef/>
                        <Literal 
                        Text="&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='300' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;" Length="160" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="12">
</Literal>
                      </Concat>
                      <Literal 
                      Text="
  &lt;br&gt;
  &lt;table cellspacing='0' width='300' align='center' class='dynamiclist'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Student Name&lt;/th&gt;
  &lt;/tr&gt;" Length="135" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="19">
</Literal>
                      <Concat>
                        <Literal 
                        Text="ViewStudents.php: Unable to get a list of students - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="22">
</Literal>
                        <Symbolic Text="mysql_error()"/>
                      </Concat>
                      <Repeat>
                        <Constraint Text="$page2 == 0"/>
                        <Concat>
                          <Concat>
                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="33"/>
                            <Select>
                              <Constraint Text="(($row % 2) == 0)"/>
                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="33"/>
                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="33"/>
                            </Select>
                            <Literal 
                            Text="'&gt;
  &lt;td&gt;&lt;a class='items' href=&quot;javascript:document.classes.student.value=" Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="34">
</Literal>
                            <Undef/>
                            <Literal 
                            Text=";document.classes.page2.value=5;document.classes.submit();&quot;&gt;" Length="60" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="34">
</Literal>
                            <Undef/>
                            <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="34"/>
                            <Undef/>
                            <Literal Text="&lt;/a&gt;&lt;/td&gt; &lt;/tr&gt;" Length="17" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="35"/>
                          </Concat>
                        </Concat>
                      </Repeat>
                      <Concat>
                        <Literal 
                        Text="  &lt;/table&gt;
  &lt;br /&gt;
  &lt;input type='hidden' name='student' value='' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="113" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="40">
</Literal>
                        <Symbolic Text="$_POST"/>
                        <Literal 
                        Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="42">
</Literal>
                        <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="54"/>
                        <Literal 
                        Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewStudents.php" Line="54">
</Literal>
                      </Concat>
                    </Concat>
                    <Select>
                      <Constraint Text="$page2 == 1"/>
                      <Concat>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="ClassSettings.php: Unable to update the grading scale - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="6">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="ClassInfo.php: Unable to get the class information - " Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="9">
</Literal>
                          <Symbolic Text="mysql_error()"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text=" &lt;h1&gt;Class Settings&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;table align='center' width='500' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='500' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='5' align='center'&gt;&lt;h2&gt;" Length="332" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="20">
</Literal>
                          <Undef/>
                          <Literal 
                          Text="&lt;/h2&gt;&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header' align='center'&gt;
	&lt;th&gt;A Percent&lt;/th&gt;
	&lt;th&gt;B Percent&lt;/th&gt;
	&lt;th&gt;C Percent&lt;/th&gt;
	&lt;th&gt;D Percent&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='even'&gt;
	&lt;td&gt;" Length="173" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="29">
</Literal>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="30"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="31"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="32"/>
                          <Undef/>
                          <Literal Text="&lt;/td&gt; &lt;/tr&gt; " Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="34"/>
                        </Concat>
                        <Concat>
                          <Literal 
                          Text="&lt;/table&gt;
   &lt;input type='hidden' name='page2' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="37">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="39">
</Literal>
                          <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="46"/>
                          <Literal 
                          Text="' /&gt;
   &lt;input type='hidden' name='selectclass' value='" Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="40">
</Literal>
                          <Symbolic Text="$_POST"/>
                          <Literal 
                          Text="' /&gt;
   &lt;input type='hidden' name='update' /&gt;
   &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
" Length="81" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewClassSettings.php" Line="46">
</Literal>
                        </Concat>
                      </Concat>
                      <Select>
                        <Constraint Text="$page2 == 2"/>
                        <Concat>
                          <Concat>
                            <Literal 
                            Text="ManageAssignments.php: Unable to get the course name - " Length="55" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="6">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="
 &lt;h1&gt;View Assignments&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='assignments' action='./index.php' method='POST'&gt;
  &lt;br&gt;&lt;br&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th colspan='6'&gt;&lt;h2&gt;" Length="320" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="19">
</Literal>
                            <Symbolic Text="mysql_result()"/>
                            <Literal 
                            Text="&lt;/th&gt;
   &lt;/tr&gt;
   &lt;tr class='header'&gt;
	&lt;th width='120' align='left' style='padding-left: 20px;'&gt;Title&lt;/th&gt;
	&lt;th&gt;Assigned Task&lt;/th&gt;
	&lt;th&gt;Possible Points&lt;/th&gt;
	&lt;th&gt;Date Assigned&lt;/th&gt;
	&lt;th&gt;Date Due&lt;/th&gt;
   &lt;/tr&gt;" Length="208" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="27">
</Literal>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="ManageAssignments.php: Unable to retrieve total number of assignments - " Length="72" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="31">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Concat>
                            <Literal 
                            Text="ManageAssignments.php: Unable to get a list of assignments - " Length="61" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="44">
</Literal>
                            <Symbolic Text="mysql_error()"/>
                          </Concat>
                          <Repeat>
                            <Constraint Text="$page2 == 2"/>
                            <Concat>
                              <Concat>
                                <Concat>
                                  <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                  <Select>
                                    <Constraint Text="(($row % 2) == 0)"/>
                                    <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                    <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="59"/>
                                  </Select>
                                  <Literal 
                                  Text="'&gt;
	  &lt;td align='left' style='padding-left: 20px;'&gt;" Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="60">
</Literal>
                                  <Undef/>
                                  <Literal 
                                  Text="&lt;/td&gt;
	  &lt;td style='text-align: left;'&gt;" Length="39" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="61">
</Literal>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="62"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="63"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="64"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="65"/>
                                </Concat>
                              </Concat>
                            </Concat>
                          </Repeat>
                          <Literal 
                          Text=" &lt;/table&gt;
  &lt;br&gt;
  &lt;center&gt;Page: " Length="33" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="71">
</Literal>
                          <Repeat>
                            <Constraint Text="$page2 == 2"/>
                            <Concat>
                              <Select>
                                <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                <Concat>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                    <Literal 
                                    Text=";document.assignments.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="96" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                    <Literal 
                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="77"/>
                                  </Concat>
                                </Concat>
                                <Concat>
                                  <Concat>
                                    <Literal 
                                    Text="&lt;a href='JavaScript: document.assignments.deleteassignment.value=0;document.assignments.page2.value=2;document.assignments.onpage.value=" Length="136" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                    <Literal 
                                    Text=";document.assignments.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="88" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                    <Literal 
                                    Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81">
</Literal>
                                    <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                    <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="81"/>
                                  </Concat>
                                </Concat>
                              </Select>
                            </Concat>
                          </Repeat>
                          <Concat>
                            <Literal 
                            Text="
&lt;/center&gt;
  &lt;input type='hidden' name='deleteassignment'&gt;
  &lt;input type='hidden' name='selectassignment'&gt;
  &lt;input type='hidden' name='page2' value='" Length="150" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="88">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="89">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="91">
</Literal>
                            <Symbolic Text="$_POST"/>
                            <Literal 
                            Text="' /&gt;
  &lt;input type='hidden' name='page' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="92">
</Literal>
                            <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="105"/>
                            <Literal 
                            Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAssignments.php" Line="105">
</Literal>
                          </Concat>
                        </Concat>
                        <Select>
                          <Constraint Text="$page2 == 3"/>
                          <Concat>
                            <Literal 
                            Text="
 &lt;h1&gt;View Grades&lt;/h1&gt;
 &lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='grades' action='./index.php' method='POST'&gt;
  &lt;br /&gt;
  &lt;table width='595' class='dynamiclist' cellpadding='5' cellspacing='0'&gt;
  &lt;tr class='header'&gt;
   &lt;th&gt;Assignment Name&lt;/th&gt;
   &lt;th&gt;Date Submitted&lt;/th&gt;
   &lt;th&gt;Earned Points&lt;/th&gt;
   &lt;th&gt;Possible Points&lt;/th&gt;
   &lt;th&gt;Grade&lt;/th&gt;
   &lt;th&gt;Comment&lt;/th&gt;
   &lt;th&gt;Late&lt;/th&gt;
  &lt;/tr&gt;" Length="453" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="29">
</Literal>
                            <Concat>
                              <Literal 
                              Text="ViewGrades.php: Unable to get the list of assignments for this class - " Length="71" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="33">
</Literal>
                              <Symbolic Text="mysql_error()"/>
                            </Concat>
                            <Repeat>
                              <Constraint Text="$page2 == 3"/>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="ManageGrades.php: Unable to get a list of gradess - " Length="52" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="41">
</Literal>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Concat>
                                  <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                  <Select>
                                    <Constraint Text="(($row % 2) == 0)"/>
                                    <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                    <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="49"/>
                                  </Select>
                                  <Literal Text="'&gt; &lt;td&gt;" Length="10" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="50"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="51"/>
                                  <Select>
                                    <Constraint Text="(convertfromdb() != &quot;//&quot;)"/>
                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/DBFunctions.php" Line="44"/>
                                    <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="51"/>
                                  </Select>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="52"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="53"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="54"/>
                                </Concat>
                                <Concat>
                                  <Literal 
                                  Text="ManageGrades.php: Unable to get the grade percentages - " Length="56" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="57">
</Literal>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Concat>
                                  <Select>
                                    <Constraint Text="($letter == -1)"/>
                                    <Concat>
                                      <Literal Text="Total Not Found" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="70"/>
                                    </Concat>
                                    <Concat>
                                      <Select>
                                        <Constraint Text="($letter &gt;= $percs[0])"/>
                                        <Concat>
                                          <Literal Text="A" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="72"/>
                                        </Concat>
                                        <Concat>
                                          <Select>
                                            <Constraint Text="($letter &gt;= $percs[1])"/>
                                            <Concat>
                                              <Literal Text="B" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="74"/>
                                            </Concat>
                                            <Concat>
                                              <Select>
                                                <Constraint Text="($letter &gt;= $percs[2])"/>
                                                <Concat>
                                                  <Literal Text="C" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="76"/>
                                                </Concat>
                                                <Concat>
                                                  <Select>
                                                    <Constraint Text="($letter &gt;= $percs[3])"/>
                                                    <Concat>
                                                      <Literal Text="D" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="78"/>
                                                    </Concat>
                                                    <Concat>
                                                      <Literal Text="F" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="80"/>
                                                    </Concat>
                                                  </Select>
                                                </Concat>
                                              </Select>
                                            </Concat>
                                          </Select>
                                        </Concat>
                                      </Select>
                                    </Concat>
                                  </Select>
                                </Concat>
                                <Concat>
                                  <Literal Text="&lt;/td&gt; &lt;td align='left'&gt;" Length="24" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="84"/>
                                  <Undef/>
                                  <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="85"/>
                                </Concat>
                                <Select>
                                  <Constraint Text="($grade[3] == 1)"/>
                                  <Concat>
                                    <Literal Text="Yes" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="88"/>
                                  </Concat>
                                  <Concat>
                                    <Concat>
                                      <Literal Text="No" Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="90"/>
                                    </Concat>
                                  </Concat>
                                </Select>
                                <Literal Text=" &lt;/td&gt; &lt;/tr&gt; " Length="20" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="95"/>
                              </Concat>
                            </Repeat>
                            <Concat>
                              <Literal 
                              Text="&lt;tr class='even'&gt;&lt;td colspan='9'&gt;&lt;center&gt;There are currently no students registered for this class.&lt;/center&gt;&lt;/td&gt;&lt;/tr&gt;" Length="118" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="99">
</Literal>
                            </Concat>
                            <Concat>
                              <Literal 
                              Text="  &lt;/table&gt;
  &lt;br /&gt;

  &lt;input type='hidden' name='addgrade' /&gt;
  &lt;input type='hidden' name='deletegrade' /&gt;
  &lt;input type='hidden' name='selectclass' value='" Length="157" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="107">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="' /&gt;
  &lt;input type='hidden' name='selectgrade' /&gt;
  &lt;input type='hidden' name='page2' value='" Length="93" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="109">
</Literal>
                              <Symbolic Text="$_POST"/>
                              <Literal 
                              Text="' /&gt;
  &lt;input type='hidden' name='logout' /&gt;
  &lt;input type='hidden' name='page' value='" Length="87" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="111">
</Literal>
                              <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="123"/>
                              <Literal 
                              Text="' /&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewGrades.php" Line="123">
</Literal>
                            </Concat>
                          </Concat>
                          <Select>
                            <Constraint Text="$page2 == 4"/>
                            <Concat>
                              <Literal 
                              Text="&lt;h1&gt;View Announcements&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='600' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='announcements' action='./index.php' method='POST'&gt;
  &lt;table cellspacing='0' width='600' cellpadding='8' class='dynamiclist'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Title&lt;/th&gt;
	&lt;th&gt;Message&lt;/th&gt;
	&lt;th&gt;Date&lt;/th&gt;
   &lt;/tr&gt;" Length="347" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="15">
</Literal>
                              <Concat>
                                <Literal 
                                Text="ViewAnnouncements.php: Unable to retrieve total number of announcements - " Length="74" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="19">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Concat>
                                <Literal 
                                Text="ViewAnnouncements.php: Unable to retrieve the announcements - " Length="62" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="32">
</Literal>
                                <Symbolic Text="mysql_error()"/>
                              </Concat>
                              <Repeat>
                                <Constraint Text="$page2 == 4"/>
                                <Concat>
                                  <Concat>
                                    <Concat>
                                      <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                      <Select>
                                        <Constraint Text="(($row % 2) == 0)"/>
                                        <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                        <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="42"/>
                                      </Select>
                                      <Literal Text="'&gt; &lt;td&gt;&lt;b&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="43"/>
                                      <Undef/>
                                      <Literal 
                                      Text="&lt;/b&gt;&lt;/td&gt;
	  &lt;td class='announcement'&gt;" Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="44">
</Literal>
                                      <Undef/>
                                      <Literal Text="&lt;/td&gt; &lt;td&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="45"/>
                                      <Symbolic Text="convertfromdb()"/>
                                      <Literal Text="&lt;/td&gt; &lt;/tr&gt;" Length="13" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="46"/>
                                    </Concat>
                                  </Concat>
                                </Concat>
                              </Repeat>
                              <Literal 
                              Text=" &lt;/table&gt;
  &lt;br&gt;&lt;br&gt;

  &lt;center&gt;Page: " Length="38" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="53">
</Literal>
                              <Repeat>
                                <Constraint Text="$page2 == 4"/>
                                <Concat>
                                  <Select>
                                    <Constraint Text="($i == $_POST[&quot;onpage&quot;])"/>
                                    <Concat>
                                      <Concat>
                                        <Literal 
                                        Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                        <Literal 
                                        Text=";document.announcements.submit();' class='selectedpagenum' onMouseover=&quot;window.status='Go to page " Length="98" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                        <Literal 
                                        Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                        <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="59"/>
                                      </Concat>
                                    </Concat>
                                    <Concat>
                                      <Concat>
                                        <Literal 
                                        Text="&lt;a href='JavaScript: document.announcements.deleteannouncement.value=0;document.announcements.page2.value=4;document.announcements.onpage.value=" Length="144" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                        <Literal 
                                        Text=";document.announcements.submit();' class='pagenum' onMouseover=&quot;window.status='Go to page " Length="90" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                        <Literal 
                                        Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63">
</Literal>
                                        <Literal Text="1" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                        <Literal Text="&lt;/a&gt;&amp;nbsp; " Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="63"/>
                                      </Concat>
                                    </Concat>
                                  </Select>
                                </Concat>
                              </Repeat>
                              <Concat>
                                <Literal 
                                Text="
&lt;/center&gt;
  &lt;input type='hidden' name='page2' value='" Length="54" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="68">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="'&gt;
  &lt;input type='hidden' name='onpage' value='" Length="47" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="69">
</Literal>
                                <Symbolic Text="$_POST"/>
                                <Literal 
                                Text="'&gt;
  &lt;input type='hidden' name='logout'&gt;
  &lt;input type='hidden' name='page' value='" Length="83" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="71">
</Literal>
                                <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84"/>
                                <Literal 
                                Text="'&gt;
 &lt;/form&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;

 &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;
 " Length="171" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ViewAnnouncements.php" Line="84">
</Literal>
                              </Concat>
                            </Concat>
                            <Select>
                              <Constraint Text="$page2 == 5"/>
                              <Concat>
                                <Concat>
                                  <Literal 
                                  Text="ParentViewCourses.php: Unable to get the studentid " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="3">
</Literal>
                                  <Symbolic Text="$_POST"/>
                                  <Literal Text=" - " Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="3"/>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Concat>
                                  <Literal Text=" &lt;h1&gt;" Length="5" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="6"/>
                                  <Undef/>
                                  <Literal Text=" " Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="6"/>
                                  <Undef/>
                                  <Literal 
                                  Text="'s Classes&lt;/h1&gt;
 &lt;br&gt;&lt;br&gt;
 &lt;table align='center' width='300' cellspacing='0' cellpadding='0' border='0'&gt;
 &lt;tr&gt;
 &lt;td&gt;
 &lt;form name='classes' action='./index.php' method='POST'&gt;
 &lt;b&gt;Semester: &lt;/b&gt; &lt;select name='semester' onChange='document.classes.submit();'&gt;
" Length="257" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="13">
</Literal>
                                </Concat>
                                <Concat>
                                  <Literal 
                                  Text="ViewCourses.php: Unable to get a list of semesters for drop-down - " Length="67" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="16">
</Literal>
                                  <Symbolic Text="mysql_error()"/>
                                </Concat>
                                <Repeat>
                                  <Constraint Text="$page2 == 5"/>
                                  <Concat>
                                    <Concat>
                                      <Literal Text="&lt;option value='" Length="15" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                      <Undef/>
                                      <Literal Text="' " Length="2" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                      <Select>
                                        <Constraint Text="(($_POST[&quot;semester&quot;] == $semester[0]) &amp;&amp; ($_POST[&quot;semester&quot;] != null))"/>
                                        <Literal Text="SELECTED" Length="8" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                        <Literal Text="" Length="0" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                      </Select>
                                      <Literal Text="&gt;" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                      <Undef/>
                                      <Literal Text="&lt;/option&gt;" Length="9" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="29"/>
                                    </Concat>
                                  </Concat>
                                </Repeat>
                                <Literal 
                                Text="
	 &lt;/select&gt;
	 &lt;br&gt;&lt;br&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr&gt;
 &lt;td&gt;
  &lt;table cellspacing='0' width='300' cellpadding='5' class='dynamiclist' align='center'&gt;
   &lt;tr class='header'&gt;
	&lt;th&gt;Class Name&lt;/th&gt;
   &lt;/tr&gt;
   " Length="196" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="44">
</Literal>
                                <Concat>
                                  <Concat>
                                    <Literal 
                                    Text="ViewCourses.php: Unable to get a list of classes - " Length="51" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="49">
</Literal>
                                    <Symbolic Text="mysql_error()"/>
                                  </Concat>
                                  <Repeat>
                                    <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                                    <Concat>
                                      <Repeat>
                                        <Constraint Text="($_POST[&quot;semester&quot;] != null)"/>
                                        <Concat>
                                          <Concat>
                                            <Literal Text="&lt;tr class='" Length="11" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="58"/>
                                            <Select>
                                              <Constraint Text="(($row % 2) == 0)"/>
                                              <Literal Text="even" Length="4" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="58"/>
                                              <Literal Text="odd" Length="3" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="58"/>
                                            </Select>
                                            <Literal 
                                            Text="'&gt;
   &lt;td&gt;&lt;a class='items' href=&quot;JavaScript:document.classes.selectclass.value=" Length="79" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="59">
</Literal>
                                            <Undef/>
                                            <Literal 
                                            Text=";document.classes.page2.value=1;document.classes.submit();&quot; onMouseover=&quot;window.status='View Information For " Length="109" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="59">
</Literal>
                                            <Undef/>
                                            <Literal 
                                            Text="';return true;&quot; onMouseout=&quot;window.status='';return true;&quot;&gt;" Length="59" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="59">
</Literal>
                                            <Undef/>
                                            <Literal Text="&lt;/a&gt;&lt;/td&gt; &lt;/tr&gt;" Length="18" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="60"/>
                                          </Concat>
                                        </Concat>
                                      </Repeat>
                                    </Concat>
                                  </Repeat>
                                </Concat>
                                <Concat>
                                  <Literal 
                                  Text="&lt;/table&gt;
   &lt;input type='hidden' name='page2' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="65">
</Literal>
                                  <Symbolic Text="$_POST"/>
                                  <Literal 
                                  Text="' /&gt;
   &lt;input type='hidden' name='logout' /&gt;
   &lt;input type='hidden' name='page' value='" Length="89" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="67">
</Literal>
                                  <Literal Text="0" Length="1" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="82"/>
                                  <Literal 
                                  Text="' /&gt;
   &lt;input type='hidden' name='selectclass' /&gt;
   &lt;input type='hidden' name='student' value='" Length="97" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="69">
</Literal>
                                  <Symbolic Text="$_POST"/>
                                  <Literal 
                                  Text="' /&gt;
   &lt;input type='hidden' name='studentid' value='" Length="53" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="70">
</Literal>
                                  <Undef/>
                                  <Literal 
                                  Text="' /&gt;
 &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;/form&gt;

  &lt;table width='520' border=0 cellspacing=0 cellpadding=0 height=1&gt;
  &lt;tr&gt;
   &lt;td valign='top'&gt;
   &lt;empty&gt;
   &lt;/td&gt;
  &lt;/tr&gt;
 &lt;/table&gt;" Length="172" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentViewCourses.php" Line="82">
</Literal>
                                </Concat>
                              </Concat>
                              <Concat>
                                <Literal Text="ParentMain.php: Invalid Page" Length="28" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="135"/>
                              </Concat>
                            </Select>
                          </Select>
                        </Select>
                      </Select>
                    </Select>
                  </Select>
                  <Literal 
                  Text="      &lt;/td&gt;
	&lt;/tr&gt;
   &lt;/table&gt;

  &lt;/td&gt;" Length="39" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/ParentMain.php" Line="143">
</Literal>
                </Concat>
                <Concat> </Concat>
              </Select>
            </Select>
          </Select>
        </Select>
      </Select>
    </Select>
    <Literal 
    Text="
   &lt;td class='bv' width=10 background='./images/right.gif'&gt;&amp;nbsp;&amp;nbsp;&lt;/font&gt;&lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr&gt;
  &lt;td class='b' width=130 height='10'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10  height='10' background='./images/bottomleft.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' height='10' background='./images/bottom.gif'&gt;&lt;empty&gt;&lt;/td&gt;
  &lt;td class='b' width=10  height='10' background='./images/bottomright.gif'&gt;&lt;empty&gt;&lt;/td&gt;
 &lt;/tr&gt;
 &lt;tr&gt;
  &lt;td colspan=4 bgColor='#336699' height='20'&gt;
   &lt;center&gt;
    &lt;span class='footer'&gt;Powered By - &lt;/span&gt;&lt;a target='_BLANK' href='http://www.primateapplications.com' class='footer'&gt;SchoolMate&lt;/a&gt;
   &lt;center&gt;
  &lt;/td&gt;
 &lt;/tr&gt;
 &lt;/table&gt;
 &lt;/body&gt;
 &lt;/html&gt;" Length="662" File="/home/stefan/git/oak/edu.cmu.cs.oak/bin/schoolmate/footer.php" Line="20">
</Literal>
  </Concat>
</DataModel>