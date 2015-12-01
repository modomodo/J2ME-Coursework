<?php
$link = @mysql_connect("localhost","root","");
@mysql_select_db("project",$link) or die(mysql_error());




?>